package com.zhangyan.myssc.service;

import java.util.List;

import com.zhangyan.myssc.bean.YaoXin;

import android.content.Context;


public interface IYaoXinService {
	
	/**
	 * 清空表
	 * @param mContext
	 */
	boolean delAll(Context mContext) throws Exception ;

	/**
	 * 通过版表id来查询遥信数据
	 * @param mContext
	 * @param bbId
	 * @return
	 * @throws Exception
	 */
	List<YaoXin> findByBBId(Context mContext, String bbId) throws Exception;
	/**
	 * 通过collName来查询单个遥信对象
	 * @param mContext
	 * @param collName
	 * @return
	 * @throws Exception
	 */
	YaoXin findOneByName(Context mContext, String collName) throws Exception;
	/**
	 * 更新遥信信息
	 * @param mContext
	 * @param yx
	 * @return
	 * @throws Exception
	 */
	boolean updateYX(Context mContext, YaoXin yx) throws Exception;

}
