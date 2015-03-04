package com.ysy.dataparserbyurl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.os.Environment;

public class SdcardCache
{
	private File savePath;

	private static SdcardCache instance = new SdcardCache();

	public static SdcardCache getInstance()
	{
		return instance;
	}

	private SdcardCache()
	{
		if (isSdcardReady())
		{
			savePath = new File(Environment.getExternalStorageDirectory() + "/" + MyApplication.getContext().getPackageName() + "/cache");
			savePath.mkdirs();
		}
	}

	public void saveBitmap(String key, Bitmap bmp)
	{
		File file = new File(savePath, StringUtil.getPicName(key));
		try
		{
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void saveBitmap(String key, InputStream in)
	{
		File file = new File(savePath, StringUtil.getPicName(key));
		try
		{
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			byte[] buffer = new byte[8 * 1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1)
			{
				out.write(buffer, 0, len);
			}
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public Bitmap getBitmap(String key)
	{
		File file = new File(savePath, StringUtil.getPicName(key));
		if (file.exists())
		{
			Bitmap bmp = BitmapUtil.getBitmap(file.getAbsolutePath(), 100, 100);
			if (bmp != null)
			{
				BitmapMemoryCache.getInstance().put(key, bmp);
			}
			return bmp;
		}
		else
		{
			return null;
		}
	}

	public void saveText(String key, InputStream in)
	{
		File file = new File(savePath, StringUtil.getTextName(key));
		try
		{
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file);
			byte[] buffer = new byte[8 * 1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1)
			{
				out.write(buffer, 0, len);
			}
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public String getText(String key)
	{
		File file = new File(savePath, StringUtil.getTextName(key));
		if (file.exists())
		{
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = null;
			try
			{
				reader = new BufferedReader(new FileReader(file));
				String temp = null;
				while ((temp = reader.readLine()) != null)
				{
					builder.append(temp);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (reader != null)
					{
						reader.close();
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			TextMemoryCache.getInstance().put(key, builder.toString());
			return builder.toString();
		}
		else
		{
			return null;
		}
	}

	public void clear()
	{

	}

	private boolean isSdcardReady()
	{
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
}
