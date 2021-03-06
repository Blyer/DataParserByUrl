package com.ysy.dataparserbyurl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.os.Handler;

public class TextLoader
{
	private ExecutorService pool;
	private TextMemoryCache textCache;
	private SdcardCache sdcardCache;
	private Handler handler;
	private final int MAX_THREAD_COUNT = 2;

	private static TextLoader instance = null;

	private TextLoader()
	{
		pool = Executors.newFixedThreadPool(MAX_THREAD_COUNT);
		textCache = TextMemoryCache.getInstance();
		sdcardCache = new SdcardCache();
		handler = new Handler();
	}

	public static TextLoader getInstance(Context context)
	{
		if (DataParserUtil.getContext() == null)
		{
			DataParserUtil.setContext(context);
		}
		if (instance == null)
		{
			return new TextLoader();
		}
		return instance;
	}

	public void loadText(final String urlAddr, final OnLoadingFinishedListener listener)
	{
		String text = textCache.get(urlAddr);
		if (text != null)
		{
			listener.loadingFinished(urlAddr, text);
			return;
		}
		else
		{
			pool.execute(new TextLoadingThread(urlAddr, listener));
		}
	}

	private class TextLoadingThread implements Runnable
	{
		private String urlAddr;
		private OnLoadingFinishedListener listener;

		public TextLoadingThread(String urlAddr, OnLoadingFinishedListener listener)
		{
			super();
			this.urlAddr = urlAddr;
			this.listener = listener;
		}

		@Override
		public void run()
		{
			String text = sdcardCache.getText(urlAddr);
			if (text == null)
			{
				text = ParserTextFromNet.getText(urlAddr);
			}
			final String temp = text;
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

	public interface OnLoadingFinishedListener
	{
		public void loadingFinished(String urlAddr, String text);
	}
}
