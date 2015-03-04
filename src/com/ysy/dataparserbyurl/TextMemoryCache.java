package com.ysy.dataparserbyurl;

import android.util.LruCache;

public class TextMemoryCache
{
	private LruCache<String, String> lruCache;
	private static final int DEFAULT_SIZE = 5;

	private static TextMemoryCache instance = new TextMemoryCache();

	public static TextMemoryCache getInstance()
	{
		return instance;
	}

	private TextMemoryCache()
	{
		lruCache = new LruCache<String, String>(DEFAULT_SIZE)
		{
			@Override
			protected int sizeOf(String key, String value)
			{
				return value.getBytes().length;
			}
		};
	}

	public String get(String key)
	{
		return lruCache.get(StringUtil.getTextName(key));
	}

	public void put(String key, String text)
	{
		lruCache.put(StringUtil.getTextName(key), text);
	}

	public void clear()
	{
		lruCache.evictAll();
	}
}
