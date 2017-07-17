package com.zhangyan.myssc.bean;
import java.io.Serializable;

import com.zhangyan.myssc.frame.NkProperty;
import com.zhangyan.myssc.frame.NkPropertyForUpdate;
import com.zhangyan.myssc.frame.NkTable;


/**
 * 遥信表
 */
@NkTable(name = "t_yaoxin")
public class YaoXin implements Serializable {
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
	/** 状态:on/off/未知 **/ 
	private String status;
	/** 更新时间**/ 
	private String updateTime;
	
	public YaoXin(){
		super();
	}
	
	/** 有参构造函数 **/
	public YaoXin(String id,String foreignId,String CollName,String CollDesc,String CollAddr,String status,String updateTime){
		super();
		this.id=id;
		this.foreignId = foreignId;
		this.CollName = CollName;
		this.CollDesc = CollDesc;
		this.CollAddr = CollAddr;
		this.status = status;
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
	 * @return 状态:on/off/未知
	 */
	@NkProperty(name="c_status")
	public String getstatus() {
		return status;
	}
	/**
	 * @param status 状态:on/off/未知
	 */
	public void setstatus(String status) {
		this.status = status;
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
