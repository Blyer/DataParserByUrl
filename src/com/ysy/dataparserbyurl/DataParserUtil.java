package com.ysy.dataparserbyurl;

import android.widget.Toast;

public class DataParserUtil
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
		(new SdcardCache()).clear();
		BitmapMemoryCache.getInstance().clear();
		TextMemoryCache.getInstance().clear();
		Toast.makeText(MyApplication.getContext(), "缓存清理成功！", Toast.LENGTH_SHORT).show();
	}
}
