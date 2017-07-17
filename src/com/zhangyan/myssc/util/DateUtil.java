package com.zhangyan.myssc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;


/**
 * 时间工具类
 * 
 * @author 张颜
 * @date 2017年4月14日 16:55:33
 */
public class DateUtil {

	/** 保存最后发送数据的时间 */
	public static long lastSendDataDate = 0;
	/** 最后发送数据后，是否有数据接受 */
	public static boolean isReceiveData = false;
	/** 理论最迟接受数据的时间 */
	public static long receiveDataDate = 0;

	public DateUtil() {
		super();
	}
	
	/**
	 * 设定最后发送数据的时间，获取理论接受数据的时间
	 * @param sendTime
	 */
	public static void setLastSendDataTime() {
		DateUtil.isReceiveData = false;
		DateUtil.lastSendDataDate = System.currentTimeMillis();
		DateUtil.receiveDataDate = DateUtil.lastSendDataDate + 8000;
	}
	
	/**
	 * 20170525162116518 -> date
	 * @param time
	 */
	@SuppressLint("SimpleDateFormat") 
	public static Date parseStrToDate(String time) throws ParseException{
		SimpleDateFormat fmt =new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = fmt.parse(time);
		return date;
	}
	
	/**
	 * 返回x值集合
	 * @param time
	 */
	@SuppressLint("SimpleDateFormat") 
	public static List<String> getXlist(int num, Date midTime) throws ParseException{
		
		Date start = new Date(midTime.getTime() - 100);
		List<String> xList = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			Date d = new Date(start.getTime() + ((i*200)/num));
			xList.add(new SimpleDateFormat("HH:mm:ss:SSS").format(d));
		}
		return xList;
	}
    
}
