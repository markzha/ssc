package com.zhangyan.myssc.service;

import java.util.List;
import java.util.Map;

import android.content.Context;

import com.zhangyan.myssc.bean.BaseData;
import com.zhangyan.myssc.bean.BanBiaoTemp;

public interface IBasedataService {

	/**
	 * 查询所有版表数据
	 * @param context
	 * @return List<BanBiao>
	 * @throws Exception 
	 */
	List<BaseData> findAll(Context context)throws Exception;
	
	/**
	 * 保存版表集合
	 * @param context
	 * @param bbList
	 * @throws Exception
	 */
	void saveBBList(Context context,List<BaseData> bbList)throws Exception;
	
	/**
	 * 删除所有版表数据
	 * @param context
	 * @return
	 * @throws Exception
	 */
	boolean delAll(Context context)throws Exception;
	
	/**
	 * 保存单个对象,同时保存遥控,遥信,遥测三张表
	 * @param bb
	 * @return
	 * @throws Exception
	 */
	boolean saveBanBiaoTempOne(Context context,BanBiaoTemp bbTemp) throws Exception;
	
	/**
	 * 通过map集合来更新版表运行数据
	 * @param mContext
	 * @param dataMap
	 * @throws Exception
	 */
	void updateListByMap(Context mContext, Map<String, String> dataMap) throws Exception;
	
	/**
	 * 更新版表的运行状态
	 * @param context
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean updateBB(Context context,BaseData bb) throws Exception;
	
	/**
	 * 通过unitName来查询单个对象
	 * @param context
	 * @return List<BanBiao>
	 * @throws Exception 
	 */
	BaseData findOneByName(Context context,String unitName) throws Exception;
}
