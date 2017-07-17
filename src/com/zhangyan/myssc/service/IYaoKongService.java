package com.zhangyan.myssc.service;

import java.util.List;

import com.zhangyan.myssc.bean.YaoKong;

import android.content.Context;


public interface IYaoKongService {
	
	/**
	 * 清空表
	 * @param mContext
	 */
	boolean delAll(Context mContext) throws Exception;
	/**
	 * 通过版表id来查询遥控数据
	 * @param mContext
	 * @param bbId
	 * @return
	 * @throws Exception
	 */
	List<YaoKong> findByBBId(Context mContext, String bbId) throws Exception;
	/**
	 * 通过collName来查询单个遥控对象
	 * @param mContext
	 * @param collName
	 * @return
	 * @throws Exception
	 */
	YaoKong findOneByName(Context mContext, String collName) throws Exception;
	/**
	 * 更新遥控运行信息
	 * @param mContext
	 * @param yk
	 * @return
	 * @throws Exception
	 */
	boolean updateYK(Context mContext, YaoKong yk) throws Exception;
}