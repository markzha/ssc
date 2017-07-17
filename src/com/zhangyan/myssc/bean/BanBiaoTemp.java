package com.zhangyan.myssc.bean;
import java.io.Serializable;

import org.json.JSONObject;


/**
 * 版表临时表
 */
public class BanBiaoTemp implements Serializable {
	
	private static final long serialVersionUID = 7313823661616495389L;
	/** 版表名 **/ 
	private String unitName;
	/** 版表描述 **/ 
	private String unitDesc;
	/** 版表Colls **/ 
	private JSONObject Colls;
	
	public BanBiaoTemp(){
		super();
	}
	
	/** 有参构造函数 **/
	

	public BanBiaoTemp(String id, String unitName, String unitDesc,
			JSONObject colls) {
		super();
		this.unitName = unitName;
		this.unitDesc = unitDesc;
		this.Colls = colls;
	}

	/**
	 * @return 版表名
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName 版表名
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
		
	/**
	 * @return 版表描述
	 */
	public String getUnitDesc() {
		return unitDesc;
	}
	/**
	 * @param unitDesc版表描述
	 */
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
	}
	
	public JSONObject getColls() {
		return Colls;
	}

	public void setColls(JSONObject colls) {
		Colls = colls;
	}
	
}
