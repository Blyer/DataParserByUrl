package com.ysy.dataparserbyurl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ParserTextFromNet
{
	public static String getText(String urlAddr)
	{
		SdcardCache sdcardCache = new SdcardCache();
		HttpURLConnection conn = null;
		InputStream in = null;
		try
		{
			URL url = new URL(urlAddr);
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				in = conn.getInputStream();
				sdcardCache.saveText(urlAddr, in);
				return sdcardCache.getText(urlAddr);
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
				if (conn != null)
				{
					conn.disconnect();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return null;
	}
}
