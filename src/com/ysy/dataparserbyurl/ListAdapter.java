package com.ysy.dataparserbyurl;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysy.dataparserbyurl.ImageLoader.OnLoadingFinishedListener;

@SuppressLint("InflateParams")
public class ListAdapter extends BaseAdapter
{

	private ArrayList<String> mData = new ArrayList<String>();

	private LayoutInflater mInflater;

	private ImageLoader mImageLoader;

	public ListAdapter(Context ctx)
	{
		mInflater = LayoutInflater.from(ctx);

		mImageLoader = ImageLoader.getInstance();
	}

	public void setData(List<String> list)
	{
		mData.addAll(list);
	}

	@Override
	public int getCount()
	{
		return mData.size();
	}

	@Override
	public String getItem(int position)
	{
		return mData.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		View view = convertView;

		if (view == null)
		{
			view = mInflater.inflate(R.layout.item_list, null);
		}

		TextView tv = (TextView)view.findViewById(R.id.title);
		final ImageView img = (ImageView)view.findViewById(R.id.img);
		img.setImageBitmap(null);

		tv.setText(" ------  " + position);

		final String url = getItem(position);
		img.setTag(url);
		mImageLoader.loadImage(url, new OnLoadingFinishedListener()
		{
			@Override
			public void loadingFinished(String urlAddr, Bitmap bmp)
			{
				if (img.getTag().equals(urlAddr))
				{
					img.setImageBitmap(bmp);
				}
			}
		});

		return view;
	}
}
