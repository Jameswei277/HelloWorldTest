package com.jameswei.flauncher.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jameswei.flauncher.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DateTimeView extends RelativeLayout{

	private TextView mTimeView;
	private TextView mDateView;
	private TextView mWeekView;
	
	public DateTimeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater.from(context).inflate(R.layout.datetime_view, this);
		mTimeView = (TextView)findViewById(R.id.dt_time);
		mDateView = (TextView)findViewById(R.id.dt_date);
		mWeekView = (TextView)findViewById(R.id.dt_week);
		
		update();
	}

	private void update() {
		Date date = new Date(System.currentTimeMillis());
		mTimeView.setText(new SimpleDateFormat("HH:mm").format(date));
		mDateView.setText(new SimpleDateFormat("yyyy/MM/dd").format(date));
        mWeekView.setText(new SimpleDateFormat("EEEE").format(date));
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		IntentFilter mDateFilter = new IntentFilter();
		mDateFilter.addAction(Intent.ACTION_TIME_TICK);
		mDateFilter.addAction(Intent.ACTION_TIME_CHANGED);
		mDateFilter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
		mDateFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
		getContext().registerReceiver(mDBroadcastReceiver, mDateFilter);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
	}
	
	
	private BroadcastReceiver mDBroadcastReceiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			update();
		}
	};

}
