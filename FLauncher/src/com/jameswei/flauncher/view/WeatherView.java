package com.jameswei.flauncher.view;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import com.jameswei.flauncher.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WeatherView extends RelativeLayout{

	private TextView mCityView;
    private TextView mTempView;
    private ImageView mIconView;
	
    private BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			ConnectivityManager manager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = manager.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				new WeatherTask().execute();
			}
		}
	};
    
	public WeatherView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater.from(context).inflate(R.layout.weather_view, this);
		mCityView = (TextView) findViewById(R.id.weather_city);
        mTempView = (TextView) findViewById(R.id.weather_temp);
        mIconView = (ImageView) findViewById(R.id.weather_icon);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		IntentFilter myFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		getContext().registerReceiver(myBroadcastReceiver, myFilter);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		getContext().unregisterReceiver(myBroadcastReceiver);
	}
	
	private class WeatherTask extends AsyncTask<Void, Void, WeatherInfo>{

		protected WeatherInfo doInBackground(Void... params) {
			WeatherInfo info = new WeatherInfo();
			String city = getCityByNet();
			return null;
		}
		
	}
	
	private String getCityByNet() {
		XmlPullParser xpp = Xml.newPullParser();
        InputStream is = null;
        //is = getContext().getAssets()
        
        return null;
	}
	
	
	private class WeatherInfo {
        private String city;
        private String temp;
        private String icon;
    }
}
