package com.ysy.dataparserbyurl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.os.Handler;

/**
 * 调用此类对象的loadImage方法可以从URL地址加载图片（得到是Bitmap对象)。</p>
 * 此类采用了二级缓存（LruCache缓存和SDcard缓存），可以提高应用性能。
 * 
 * @author 殷胜义
 */
public class ImageLoader
{
	/**
	 * 线程池对象，将开启固定的6个现成用于图片加载
	 */
	private ExecutorService pool;
	/**
	 * 二级缓存机制中的LruCache缓存使用的对象
	 */
	private BitmapMemoryCache bitmapMemoryCache;
	/**
	 * 二级缓存机制中的SDCard缓存使用的对象
	 */
	private SdcardCache sdcardCache;
	/**
	 * 定义一个handler用于回调UI线程的View方法
	 */
	private Handler handler;
	/**
	 * 自定义加载图片的线程数量
	 */
	private final int MAX_THREAD_COUNT = 5;

	/**
	 * ImageLoader类的唯一实例
	 */
	private static ImageLoader instance = new ImageLoader();

	/**
	 * 此类设计为单例模式，通过此静态方法获取ImageLoader类对象
	 * 
	 * @return ImageLoader对象
	 */
	public static ImageLoader getInstance()
	{
		return instance;
	}

	private ImageLoader()
	{
		pool = Executors.newFixedThreadPool(MAX_THREAD_COUNT);
		bitmapMemoryCache = BitmapMemoryCache.getInstance();
		sdcardCache = new SdcardCache();
		handler = new Handler();
	}

	/**
	 * 通过URL地址解析得到Bitmap后，通过OnLoadingFinishedListener回调接口同步到UI线程View上去
	 * 
	 * @param urlAddr
	 *            图片对应的URL地址
	 * @param listener
	 *            UI线程调用此方法是传入的回调接口
	 */
	public void loadImage(final String urlAddr, final OnLoadingFinishedListener listener)
	{
		Bitmap bm = bitmapMemoryCache.get(urlAddr);
		if (bm != null)
		{
			listener.loadingFinished(urlAddr, bm);
			return;
		}
		else
		{
			pool.execute(new ImageLoadingThread(urlAddr, listener));
		}
	}

	private class ImageLoadingThread implements Runnable
	{
		private String urlAddr;
		private OnLoadingFinishedListener listener;

		public ImageLoadingThread(String urlAddr, OnLoadingFinishedListener listener)
		{
			super();
			this.urlAddr = urlAddr;
			this.listener = listener;
		}

		@Override
		public void run()
		{
			Bitmap bmp = sdcardCache.getBitmap(urlAddr);
			if (bmp == null)
			{
				bmp = ParserBitmapFromNet.getBitmap(urlAddr);
			}
			final Bitmap temp = bmp;
			handler.post(new Runnable()
			{

				@Override
				public void run()
				{
					listener.loadingFinished(urlAddr, temp);
				}

			});
		}

	}

	/**
	 * 提供给UI线程在图片解析完成后的回调接口
	 * 
	 * @author 殷胜义
	 * 
	 */
	public interface OnLoadingFinishedListener
	{
		/**
		 * 当图片解析完成后，提供此方法给UI线程做数据处理。
		 * 
		 * @param urlAddr
		 *            图片的URL地址
		 * @param bmp
		 *            URL地址解析完成后得到的Bitmap对象
		 */
		public void loadingFinished(String urlAddr, Bitmap bmp);
	}
}
