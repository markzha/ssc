package com.zhangyan.myssc.util;

import com.zhangyan.myssc.R;
import com.zhangyan.myssc.view.CircularZoomLoadingAnim;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 布局控件工具类
 * 
 * @author 张颜
 * @date 2017年4月14日 16:55:33
 */
public class ViewUtil {

	/**
	 * 创建重新连接的dialog
	 * 
	 * @param context
	 * @param msg
	 * @return
	 */
	@SuppressLint("InflateParams")
	public static Dialog createLoadingDialog(Context context,int reConnIndex) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_reconn, null);
		RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.reconn_view);
		TextView tv_progress_showtitle = (TextView) v.findViewById(R.id.tv_progress_showtitle);
		CircularZoomLoadingAnim circularZoomLoadingAnim = (CircularZoomLoadingAnim) v.findViewById(R.id.circularzoom);
		
		tv_progress_showtitle.setText("尝试第" + reConnIndex + "次重连");
		//开启点点动画
		circularZoomLoadingAnim.startAnim();
		// 创建自定义样式dialog
		Dialog reConnDialog = new Dialog(context, R.style.loading_dialog);
		// 不可以用“返回键”取消
		reConnDialog.setCancelable(false);
		// 设置布局及宽高（dp）
		reConnDialog.setContentView(layout, new RelativeLayout.LayoutParams(600, 500));
		
		Window window = reConnDialog.getWindow(); 
	    WindowManager.LayoutParams lp = window.getAttributes();
	    lp.alpha = 0.9f;
	    window.setAttributes(lp);
		
		return reConnDialog;
	}
}
