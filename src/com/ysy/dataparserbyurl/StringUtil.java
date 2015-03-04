package com.ysy.dataparserbyurl;

public class StringUtil
{
	public static String getPicName(String name)
	{
		return "img_" + name.hashCode();
	}

	public static String getTextName(String name)
	{
		return "text_" + name.hashCode();
	}

	public static void clearCache()
	{
		SdcardCache.getInstance().clear();
		BitmapMemoryCache.getInstance().clear();
		TextMemoryCache.getInstance().clear();
	}
}
