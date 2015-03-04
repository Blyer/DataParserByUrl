package com.ysy.dataparserbyurl;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private ListView listView;
	public String[] urls = new String[] {
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Fpic3.nipic.com%2F20090515%2F1514804_091435007_2.jpg",
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Ff12.topit.me%2Fo129%2F10129120625790e866.jpg",
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Fpica.nipic.com%2F2007-11-11%2F20071111143725266_2.jpg",
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Fpic7.nipic.com%2F20100506%2F4876019_150816079897_2.jpg",
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Fpic11.nipic.com%2F20101123%2F3320946_195231983035_2.jpg",
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2Fmonth_1011%2F1011250123c614b43b52ccec6b.jpg",
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Fpic9.nipic.com%2F20100828%2F2531170_134726497520_2.jpg",
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Fpic14.nipic.com%2F20110609%2F4526587_092030409110_2.jpg",
			"http://image.baidu.com/i?tn=download&ipn=dwnl&word=%E9%AB%98%E6%B8%85&ie=utf-8&fr=result&url=http%3A%2F%2Fpic4.nipic.com%2F20091112%2F3137436_124935065257_2.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949578_8744.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949577_5210.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949577_1998.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949482_8813.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949481_6577.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949480_4490.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949455_6792.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949455_6345.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949442_4553.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949441_8987.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949441_5454.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949454_6367.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949442_4562.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg", "http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg", "http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg", };

	String[] textUrls = new String[] { "http://www.baidu.com", "http://www.tencent.com", "http://www.sina.com", "http://www.baidu.com",
			"http://www.tencent.com", "http://www.sina.com", "http://www.baidu.com", "http://www.tencent.com", "http://www.sina.com", "http://www.baidu.com",
			"http://www.tencent.com", "http://www.sina.com", "http://www.baidu.com", "http://www.tencent.com", "http://www.sina.com", "http://www.baidu.com",
			"http://www.tencent.com", "http://www.sina.com" };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView)findViewById(R.id.listView);
		// ListAdapter adapter = new ListAdapter(this);
		// adapter.setData(Arrays.asList(urls));
		listView.setAdapter(new BaseAdapter()
		{
			private List<String> data = Arrays.asList(textUrls);
			private TextLoader loader = TextLoader.getInstance();

			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
				View view = convertView;

				if (view == null)
				{
					view = View.inflate(MainActivity.this, R.layout.item_list, null);
				}

				final TextView tv = (TextView)view.findViewById(R.id.title);

				final String url = (String)getItem(position);
				tv.setTag(url);
				loader.loadText(url, new TextLoader.OnLoadingFinishedListener()
				{

					@Override
					public void loadingFinished(String urlAddr, String text)
					{
						if (tv.getTag().equals(urlAddr))
						{
							tv.setText(text.substring(0, 20));
						}
					}
				});

				return view;
			}

			@Override
			public long getItemId(int position)
			{
				return position;
			}

			@Override
			public Object getItem(int position)
			{
				return data.get(position);
			}

			@Override
			public int getCount()
			{
				return data.size();
			}
		});
	}

}
