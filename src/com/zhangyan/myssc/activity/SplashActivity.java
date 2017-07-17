package com.zhangyan.myssc.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

import com.zhangyan.myssc.R;
import com.zhangyan.myssc.thread.SearchApiDetialService;
import com.zhangyan.myssc.util.Util;

/**
 * APP启动页，可以做一些准备数据，初始化项目的操作；
 * @author 张颜
 */
@SuppressLint("WorldReadableFiles") 
public class SplashActivity extends Activity {
	protected static final String TAG = SplashActivity.class.getSimpleName();
	private Context mContext;
	//private SharedPreferences sp;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		mContext = this;
		findView();
		
		Context otherAppContext = null;
		try {
			otherAppContext = createPackageContext("com.zhangyan.myssc", Context.CONTEXT_IGNORE_SECURITY);
			SharedPreferences sp = otherAppContext.getSharedPreferences("config", Context.MODE_WORLD_READABLE);
			//读取数据
			String key = sp.getString("key", "");
			Util.key = key;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		startService();
	}
	
	private void findView() {
	}
	
	/**
	 * 开启服务
	 */
	private void startService() {
		Intent mIntent = new Intent(mContext,SearchApiDetialService.class);
		startService(mIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	
	
	
}
