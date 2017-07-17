package com.zhangyan.myssc.util;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangyan.myssc.R;
import com.zhangyan.myssc.bean.BaseData;

/**
 * 全局变量 用来保存用户信息，起到session的作用
 * 
 * @author 张颜
 * @date 2017年4月14日 16:55:33
 */
public class Util {

	public static BaseData baseData = new BaseData();
	public static String key = "";

	/** 公共socket的IP地址 */
	public static String conn_ip = "";

	public static Toast toast;

	public static Long lastClickTime = 0L; // 按钮最后点击事件
	public static int DoubleClickMillis = 2000;

	public Util() {
		super();
	}

	/**
	 * 自定义Toast样式 
	 * @param context
	 * @param message
	 */
	@SuppressLint("InflateParams") 
	public static void myToast(Context context, String message) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View toastView = inflater.inflate(R.layout.toast, null);
		TextView mm = (TextView) toastView.findViewById(R.id.message);

		if (toast == null) {
			mm.setText(message);
			toast = new Toast(context);
		} else {
			mm.setText(message);
		}
		toast.setGravity(Gravity.CENTER, Gravity.CENTER, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(toastView);
		toast.show();

		SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
		// 载入音频流，返回在池中的id
		final int sourceid = soundPool.load(context, R.raw.notify, 0);
		// 播放音频，第二个参数为左声道音量;第三个参数为右声道音量;第四个参数为优先级；第五个参数为循环次数，0不循环，-1循环;第六个参数为速率，速率最低0.5最高为2，1代表正常速度
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				soundPool.play(sourceid, 1, 1, 0, 0, 1);
			}
		});
	}
	
	/**
     * 从顶部弹出的toast
     * @param context
     * @param message
     */
    @SuppressLint("InflateParams") 
    public static void myToastTop(Context context, String message) {
    	LayoutInflater inflater = (LayoutInflater) context
    			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	View toastView = inflater.inflate(R.layout.toast, null);
    	TextView mm = (TextView) toastView.findViewById(R.id.message);
    	mm.setTextSize(18);
    	
    	if(toast == null){
    		mm.setText(message);
    		toast = new Toast(context);
    	}else{
    		mm.setText(message);
    	}
    	toast.setGravity(Gravity.TOP, 0, 0);
    	toast.setDuration(10000);
    	toast.setView(toastView);
    	toast.show();
    }

	// 按钮快速多次点击会重复执行按钮操作，在此限制小于600ms的属于暴利点击不响应事件
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < DoubleClickMillis) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 是否关闭弹窗
	 * 
	 * @param dialog
	 * @param flag
	 *            true-关闭;false-保持弹窗不关闭 void
	 */
	public static void isCloseDialog(DialogInterface dialog, boolean flag) {
		try {
			// 保持对话框不关闭
			Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(dialog, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检查当前网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivityManager == null) {
			return false;
		} else {
			// 获取NetworkInfo对象
			NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0) {
				for (int i = 0; i < networkInfo.length; i++) {
					Log.e("Util", "当前网络状态==" + networkInfo[i].getState()
							+ "  当前网络类型==" + networkInfo[i].getTypeName());
					// 判断当前网络状态是否为连接状态
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void setEditError(Context mContext, EditText etContent,String string) {
		etContent.setError(string); 
		
		SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
		// 载入音频流，返回在池中的id
		final int sourceid = soundPool.load(mContext, R.raw.notify, 0);
		// 播放音频，第二个参数为左声道音量;第三个参数为右声道音量;第四个参数为优先级；第五个参数为循环次数，0不循环，-1循环;第六个参数为速率，速率最低0.5最高为2，1代表正常速度
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				soundPool.play(sourceid, 1, 1, 0, 0, 1);
			}
		});
	}
	
	public static void setEditError(Context mContext, EditText etContent,Spanned spanned) {
		etContent.setError(spanned); 
		
		SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
		// 载入音频流，返回在池中的id
		final int sourceid = soundPool.load(mContext, R.raw.notify, 0);
		// 播放音频，第二个参数为左声道音量;第三个参数为右声道音量;第四个参数为优先级；第五个参数为循环次数，0不循环，-1循环;第六个参数为速率，速率最低0.5最高为2，1代表正常速度
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				soundPool.play(sourceid, 1, 1, 0, 0, 1);
			}
		});
	}
    
}
