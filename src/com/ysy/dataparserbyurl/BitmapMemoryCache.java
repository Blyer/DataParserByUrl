package com.ysy.dataparserbyurl;

import android.graphics.Bitmap;
import android.util.LruCache;

public class BitmapMemoryCache
{
	private LruCache<String, Bitmap> lruCache;
	private static final int DEFAULT_SIZE = (int)(Runtime.getRuntime().maxMemory() / 8);

	private static BitmapMemoryCache instance = new BitmapMemoryCache();

	public static BitmapMemoryCache getInstance()
	{
		return instance;
	}

	private BitmapMemoryCache()
	{
		lruCache = new LruCache<String, Bitmap>(DEFAULT_SIZE)
		{
			@Override
			protected int sizeOf(String key, Bitmap value)
			{
				return value.getRowBytes() * value.getHeight();
			}
		};
	}

	public Bitmap get(String key)
	{
		return lruCache.get(StringUtil.getPicName(key));
	}

	public void put(String key, Bitmap bmp)
	{
		lruCache.put(StringUtil.getPicName(key), bmp);
	}

	public void clear()
	{
		lruCache.evictAll();
	}
}
