
package com.zhangyan.myssc.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.tencent.bugly.crashreport.CrashReport;

import android.app.Application;
import android.content.Context;
import android.util.Log;
//import com.tencent.bugly.crashreport.CrashReport;

public class ZYApplication extends Application {
	
	private static final String TAG = "NKApplication";
	public static float sScale;
	public static int sWidthDp;
	public static int sWidthPix;
	public static int sHeightPix;
	
	@Override
	public void onCreate() {
		super.onCreate();

		//CrashHandler.getInstance().init(this);
		sScale = getResources().getDisplayMetrics().density;
        sWidthPix = getResources().getDisplayMetrics().widthPixels;
        sHeightPix = getResources().getDisplayMetrics().heightPixels;
        sWidthDp = (int) (sWidthPix / sScale);
	        
		
		// 初始化数据库
		String dbFilePath = getExternalFilesDir(Context.STORAGE_SERVICE).getAbsolutePath() + File.separator + "db" + File.separator;
  		copyDataBase(dbFilePath, "rtu.db");
  		
  		/* Bugly SDK初始化
         * 参数1：上下文对象
         * 参数2：APPID，平台注册时得到
         * 参数3：是否开启调试模式，true-调试模式下会输出'CrashReport'tag的日志
         */
        CrashReport.initCrashReport(getApplicationContext(), "aceb316cca", false);
	}
	
	/**
	 * 复制assets下的大数据库文件时用这个
	 * @param dbPath
	 * @param dbName
	 */
 	private void copyDataBase(String dbPath, String dbName) {
 		File dir = new File(dbPath);
 		if (!dir.exists()){
 			dir.mkdirs();
 			Log.i(TAG, "mkdirs success :" + dbPath);
 		}
 			
 		
 		File file = new File(dir, dbName);
 		Log.i(TAG, "db path" + file.getAbsolutePath());
 		if (file.exists()) {
 			Log.d(TAG, "db file exists");
 			return;
 		}
 		try {
 			InputStream myInput;
 			String outFileName = file.getAbsolutePath();
 			OutputStream myOutput = new FileOutputStream(outFileName);
 			myInput = getAssets().open(dbName);
 			byte[] buffer = new byte[1024];
 			int length;
 			while ((length = myInput.read(buffer)) > 0) {
 				myOutput.write(buffer, 0, length);
 			}
 			myOutput.flush();
 			myInput.close();
 			myOutput.close();
 		} catch (Exception e) {
             Log.e(TAG, e.getMessage(), e);
 		}
 	}

}
