package com.zhangyan.myssc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 跑马灯TextView ,使textview在不获取焦点的时候也能滚动, 页面上有多个跑马灯时使用
 * 对textview进行设置属性, 在XML中可以省去一些设置
 * android:focusable="true"
 * android:focusableInTouchMode="true"
 * android:singleLine="true"
 * android:marqueeRepeatLimit="marquee_forever"
 * @author 张颜
 *
 */
public class MarqueeTextView extends TextView {

	public MarqueeTextView(Context context) {
		super(context);
		init();
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	@Override
	public boolean isFocused() {
		// 使控件时刻获取焦点
		return true;
	}	
	
	/**
	 * 对textview进行设置属性, 在XML中可以省去一些设置
	 * android:focusable="true"
	 * android:focusableInTouchMode="true"
	 * android:singleLine="true"
	 * android:marqueeRepeatLimit="marquee_forever"
	 */
	private void init() {
		setFocusable(true);
		setFocusableInTouchMode(true);
		setSingleLine();
		setMarqueeRepeatLimit(-1);		
	}

}
