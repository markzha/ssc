package com.zhangyan.myssc.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 录波文件详情
 */
public class LuBoFileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 文件名 **/
	private String fileName;
	/** 版表名 **/
	private String banbiaoName;
	/** 位置 **/
	private String address;
	/** 类型：a电流，电压u **/
	private String type;
	/** 中间点时间 **/
	private Date middleTime;
	/** 点数量 **/
	private Integer pointNum;
	/** y值集合 **/
	private List<Float> yValueList;
	/** x值集合 **/
	private List<String> xValueList;
	/** y值的最大值 **/
	private Float yMaxValue;

	public LuBoFileDetail() {
		super();
	}
	
	public LuBoFileDetail(String fileName, String banbiaoName, String address,String type, Date middleTime, Integer pointNum,
			List<Float> yValueList, List<String> xValueList, Float yMaxValue) {
		super();
		this.fileName = fileName;
		this.banbiaoName = banbiaoName;
		this.address = address;
		this.type = type;
		this.middleTime = middleTime;
		this.pointNum = pointNum;
		this.yValueList = yValueList;
		this.xValueList = xValueList;
		this.yMaxValue = yMaxValue;
	}


	/** 文件名 **/
	public String getFileName() {
		return fileName;
	}

	/** 文件名 **/
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/** 版表名 **/
	public String getBanbiaoName() {
		return banbiaoName;
	}

	/** 版表名 **/
	public void setBanbiaoName(String banbiaoName) {
		this.banbiaoName = banbiaoName;
	}

	/** 位置 **/
	public String getAddress() {
		return address;
	}

	/** 位置 **/
	public void setAddress(String address) {
		this.address = address;
	}
	
	/** 类型：a电流，电压u **/
	public String getType() {
		return type;
	}
	
	/** 类型：a电流，电压u **/
	public void setType(String type) {
		this.type = type;
	}
	
	/** 中间点时间 **/
	public Date getMiddleTime() {
		return middleTime;
	}

	/** 中间点时间 **/
	public void setMiddleTime(Date middleTime) {
		this.middleTime = middleTime;
	}

	/** 点数量 **/
	public Integer getPointNum() {
		return pointNum;
	}

	/** 点数量 **/
	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

	/** y值集合 **/
	public List<Float> getYValueList() {
		return yValueList;
	}

	/** y值集合 **/
	public void setYValueList(List<Float> yValueList) {
		this.yValueList = yValueList;
	}
	
	/** x值集合 **/
	public List<String> getXValueList() {
		return xValueList;
	}
	
	/** x值集合 **/
	public void setXValueList(List<String> xValueList) {
		this.xValueList = xValueList;
	}

	/** y值的最大值 **/
	public Float getyMaxValue() {
		return yMaxValue;
	}

	/** y值的最大值 **/
	public void setyMaxValue(Float yMaxValue) {
		this.yMaxValue = yMaxValue;
	}
	
	

}
