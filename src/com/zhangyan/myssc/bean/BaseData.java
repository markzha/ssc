package com.zhangyan.myssc.bean;
import java.io.Serializable;

import com.zhangyan.myssc.frame.NkProperty;
import com.zhangyan.myssc.frame.NkPropertyForUpdate;
import com.zhangyan.myssc.frame.NkTable;


/**
 * 主表-原始数据表
 * {"expect":"20170716095","opencode":"4,3,2,1,8","opentime":"2017-07-16 21:50:40","opentimestamp":1500213040}
 */
@NkTable(name = "t_basedata")
public class BaseData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	/** 期号 **/ 
	private String expect;
	/** 开奖号 **/ 
	private String openCode;
	/** 开奖时间 **/ 
	private String openTime;
	/** 开奖时间戳 **/ 
	private String openTimeStamp;	
	
	public BaseData(){
		super();
	}
	
	/** 有参构造函数 **/
	public BaseData(String expect, String openCode, String openTime,
			String openTimeStamp) {
		super();
		this.expect = expect;
		this.openCode = openCode;
		this.openTime = openTime;
		this.openTimeStamp = openTimeStamp;
	}
	
	
	@NkPropertyForUpdate(name = "c_id")
	@NkProperty(name="c_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/** 期号 **/ 
	@NkProperty(name="c_expect")
	public String getExpect() {
		return expect;
	}

	/** 期号 **/ 
	public void setExpect(String expect) {
		this.expect = expect;
	}

	/** 开奖号 **/ 
	@NkProperty(name="c_opencode")
	public String getOpenCode() {
		return openCode;
	}

	/** 开奖号 **/ 
	public void setOpenCode(String openCode) {
		this.openCode = openCode;
	}

	/** 开奖时间 **/ 
	@NkProperty(name="c_opentime")
	public String getOpenTime() {
		return openTime;
	}

	/** 开奖时间 **/ 
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	/** 开奖时间戳 **/ 
	@NkProperty(name="c_opentime_stamp")
	public String getOpenTimeStamp() {
		return openTimeStamp;
	}

	/** 开奖时间戳 **/ 
	public void setOpenTimeStamp(String openTimeStamp) {
		this.openTimeStamp = openTimeStamp;
	}
	
}
