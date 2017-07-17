package com.zhangyan.myssc.bean;
import java.io.Serializable;


/**
 * 录波文件
 */
public class LuBoFile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 文件名 **/ 
	private String fileName;
	/** 录波原因 **/ 
	private String reason;
	/** 时间 **/ 
	private String time;
	
	public LuBoFile(){
		super();
	}
	
	public LuBoFile(String fileName, String reason, String time) {
		super();
		this.fileName = fileName;
		this.reason = reason;
		this.time = time;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	
}
