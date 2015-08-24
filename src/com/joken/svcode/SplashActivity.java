package com.joken.svcode;
 
//import android.annotation.SuppressLint;
import com.joken.svcode.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
 
/**
 * 功能：使用ViewPager实现初次进入应用时的引导页
 * 
 * (1)判断是否是首次加载应用--采取读取SharedPreferences的方法
 * (2)是，则进入引导activity；否，则进入MainActivity
 * (3)5s后执行(2)操作
 * 
 * @author sz082093
 *
 */
@SuppressLint("HandlerLeak")
public class SplashActivity extends Activity {
	//@SuppressWarnings("unused")
	//private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);    
        
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        Editor editor = sharedPreferences.edit();
            
        if (isFirstRun)
        {
            Log.d("debug", "第一次运行");
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        } else
        {
            Log.d("debug", "不是第一次运行");
        }
        //boolean mFirst = isFirstEnter(SplashActivity.this,SplashActivity.this.getClass().getName());
        if(isFirstRun)
            mHandler.sendEmptyMessageDelayed(SWITCH_GUIDACTIVITY,2000);
        else
            mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY,2000);
    }
     
    //****************************************************************
    // 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段
    //****************************************************************
    /*private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
    @SuppressLint("WorldReadableFiles")
    private boolean isFirstEnter(Context context,String className){
        if(context==null || className==null||"".equalsIgnoreCase(className))return false;
        String mResultStr = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE)
                 .getString(KEY_GUIDE_ACTIVITY, "");//取得所有类名 如 com.my.MainActivity
        if(mResultStr.equalsIgnoreCase("false"))
            return false;
        else
            return true;
    }*/
     
     
    //*************************************************
    // Handler:跳转至不同页面
    //*************************************************
    private final static int SWITCH_MAINACTIVITY = 1000;
    private final static int SWITCH_GUIDACTIVITY = 1001;
    //@SuppressLint("HandlerLeak");
    public Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
            case SWITCH_MAINACTIVITY:
                Intent mIntent = new Intent();
                mIntent.setClass(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mIntent);
                SplashActivity.this.finish();
                //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                break;
            case SWITCH_GUIDACTIVITY:
                mIntent = new Intent();
                //mIntent.setClass(SplashActivity.this, ViewPagerActivity.class);
                mIntent.setClass(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mIntent);
                SplashActivity.this.finish();
                break;
            }
            super.handleMessage(msg);
        }
    };
}