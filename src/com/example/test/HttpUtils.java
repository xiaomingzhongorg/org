package com.example.test;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HttpUtils {

	public static String getStringFromNet(Context context,String url, Listener<String> success,
			ErrorListener defu) {
		StringRequest request = new StringRequest(Method.POST, url, success,
				defu);
		Volley.newRequestQueue(context).add(request);
		return null;

	}

	public static Bitmap getBitmapFromNet(String url) {
		return null;
	}

}
