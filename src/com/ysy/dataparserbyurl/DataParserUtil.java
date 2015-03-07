package com.ysy.dataparserbyurl;

import android.content.Context;
import android.widget.Toast;

public class DataParserUtil
{
	private static Context context = null;
	
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
		Toast.makeText(DataParserUtil.getContext(), "缓存清理成功！", Toast.LENGTH_SHORT).show();
	}
	
	public static void setContext(Context ctx)
	{
		context = ctx;
	}
	
	public static Context getContext()
	{
		return context;
	}
}
