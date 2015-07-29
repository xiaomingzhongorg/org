package com.example.test;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private WindowManager manager;
	private WindowManager.LayoutParams p;
	private LocationClient mLocationClient;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mLocationClient = new LocationClient(this.getApplicationContext());

		Vibrator mVibrator = (Vibrator) getApplicationContext()
				.getSystemService(Service.VIBRATOR_SERVICE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button).setOnClickListener(this);
		text = (TextView) findViewById(R.id.textview);
		manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		p = new WindowManager.LayoutParams();

		p.height = LayoutParams.WRAP_CONTENT;

		p.width = LayoutParams.MATCH_PARENT;
		mLocationClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation location) {
				StringBuffer sb = new StringBuffer(256);
				sb.append("您在：" + location.getLocationDescribe()).append(
						"更新时间：" + location.getTime());

				text.setText(sb);
			}
		});
	}

	private void initLocation() {
		// 4 客户端定位参数
		LocationClientOption option = new LocationClientOption();
		// option.setLocationMode(tempMode);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIgnoreKillProcess(true);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
		option.setEnableSimulateGps(true);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
		option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

		mLocationClient.setLocOption(option);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:

			initLocation();
			mLocationClient.start();
			break;

		default:
			break;
		}

	}
}
