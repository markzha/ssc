package com.zhangyan.myssc.frame;

import com.zhangyan.myssc.util.MyDbUtil;


public class NkContext {
	private static ThreadLocal<MyDbUtil> dbutilThread = new ThreadLocal<MyDbUtil>();

	public static ThreadLocal<MyDbUtil> getDbutilThread() {
		return dbutilThread;
	}

	public static void setDbutilThread(ThreadLocal<MyDbUtil> dbutilThread) {
		NkContext.dbutilThread = dbutilThread;
	}
}
