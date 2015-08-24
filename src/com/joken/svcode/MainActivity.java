package com.joken.svcode;

import org.apache.cordova.DroidGap;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends DroidGap {

	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);  //无title
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
	                  WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
		//super.setIntegerProperty("splashscreen", R.drawable.splash);
//	    if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//	    }
		ConnectivityManager cwjManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo info = cwjManager.getActiveNetworkInfo(); 
		if (info != null && info.isAvailable()){ 
		super.loadUrl("http://www.svcode.net",10000);
		} 
		else
		{ 
		super.loadUrl("file:///android_asset/www/error.html", 4500); 
		}
	}
}