package com.example.test;

import android.app.Application;

public class TestApp extends Application {

	private static TestApp app;

	@Override
	public void onCreate() {

		super.onCreate();
		app = this;
	}

	public static TestApp getapp() {
		if (app == null) {
			app = new TestApp();
		}
		return app;
	}
}
