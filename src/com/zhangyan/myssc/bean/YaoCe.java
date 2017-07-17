package com.zhangyan.myssc.bean;
import java.io.Serializable;

import com.zhangyan.myssc.frame.NkProperty;
import com.zhangyan.myssc.frame.NkPropertyForUpdate;
import com.zhangyan.myssc.frame.NkTable;


/**
 * 遥测表
 */
@NkTable(name = "t_yaoce")
public class YaoCe implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	/** 外键 **/ 
	private String foreignId;
	/** 采集设备名称 **/ 
	private String CollName;
	/** 采集设备描述**/
	private String CollDesc;
	/** 采集设备地址 **/ 
	private String CollAddr;
	/** 遥测信号值 **/ 
	private String value;
	/** 更新时间**/ 
	private String updateTime;
	
	public YaoCe(){
		super();
	}
	
	/** 有参构造函数 **/
	public YaoCe(String id,String foreignId,String CollName,String CollDesc,String CollAddr,String value,String updateTime){
		super();
		this.id=id;
		this.foreignId = foreignId;
		this.CollName = CollName;
		this.CollDesc = CollDesc;
		this.CollAddr = CollAddr;
		this.value = value;
		this.updateTime = updateTime;
	}
	
	@NkPropertyForUpdate(name = "c_id")
	@NkProperty(name="c_id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return 外键
	 */
	@NkProperty(name="c_foreign_id")
	public String getForeignId() {
		return foreignId;
	}
	/**
	 * @param foreignId 外键
	 */
	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}
		
	/**
	 * @return 采集设备名称
	 */
	@NkProperty(name="c_coll_name")
	public String getCollName() {
		return CollName;
	}
	/**
	 * @param collName采集设备名称
	 */
	public void setCollName(String CollName) {
		this.CollName = CollName;
	}
		
	/**
	 * @return 采集设备描述
	 */
	@NkProperty(name="c_coll_desc")
	public String getCollDesc() {
		return CollDesc;
	}
	/**
	 * @param code 采集设备描述
	 */
	public void setCollDesc(String CollDesc) {
		this.CollDesc = CollDesc;
	}
		
	/**
	 * @return 采集设备地址
	 */
	@NkProperty(name="c_coll_addr")
	public String getCollAddr() {
		return CollAddr;
	}
	/**
	 * @param longitude采集设备地址
	 */
	public void setCollAddr(String CollAddr) {
		this.CollAddr = CollAddr;
	}
		
	/**
	 * @return 遥测信号值
	 */
	@NkProperty(name="c_value")
	public String getValue() {
		return value;
	}
	/**
	 * @param latitude 遥测信号值
	 */
	public void setValue(String value) {
		this.value = value;
	}
		
	/**
	 * @return 更新时间
	 */
	@NkProperty(name="c_update_time")
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param address 更新时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
