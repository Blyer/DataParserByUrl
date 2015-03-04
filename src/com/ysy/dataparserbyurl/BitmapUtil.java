package com.ysy.dataparserbyurl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * 读取本地的一张图片并进行压缩
 * 
 * @author 殷胜义
 * 
 */
public class BitmapUtil
{
	/**
	 * 通过本地读取本地的一张图片并根据指定的宽高进行压缩返回一个Bitmap对象
	 * 
	 * @param path
	 *            本地图片对应的路径
	 * @param widthPixel
	 *            指定压缩的像素宽
	 * @param heightPixel
	 *            指定压缩的像素高
	 * @return 压缩后返回Bitmap对象
	 */
	public static Bitmap getBitmap(String path, int widthPixel, int heightPixel)
	{
		BitmapFactory.Options option = new Options();
		option.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, option);
		option.inSampleSize = calculateSampleSize(option, widthPixel, heightPixel);

		option.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, option);
	}

	/**
	 * 根据压缩的宽高像素计算出压缩倍数
	 * 
	 * @param option
	 *            读取图片后的Bitmap.Option对象
	 * @param widthPixel
	 *            指定压缩的像素宽
	 * @param heightPixel
	 *            指定压缩的像素高
	 * @return 压缩的倍数
	 */
	private static int calculateSampleSize(Options option, int widthPixel, int heightPixel)
	{
		int widthRate = Math.round(option.outWidth / widthPixel);
		int heightRate = Math.round(option.outHeight / heightPixel);

		int sampleSize = (widthRate + heightRate) / 2;
		if (sampleSize <= 0)
		{
			sampleSize = 1;
		}

		return sampleSize;
	}
}
