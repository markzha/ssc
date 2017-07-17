package com.zhangyan.myssc.service;

import java.util.List;

import com.zhangyan.myssc.bean.YaoCe;

import android.content.Context;



public interface IYaoCeService {
	
	/**
	 * 清空表
	 * @param mContext
	 */
	boolean delAll(Context mContext) throws Exception;
	/**
	 * 通过版表id来查询遥测数据
	 * @param mContext
	 * @param bbId
	 * @return
	 */
	List<YaoCe> findByBBId(Context mContext, String bbId) throws Exception;
	/**
	 * 通过collName来查询单个遥测对象
	 * @param mContext
	 * @param collName
	 * @return
	 * @throws Exception
	 */
	YaoCe findOneByName(Context mContext,String collName) throws Exception;
	/**
	 * 更新遥测运行信息
	 * @param mContext
	 * @param yc
	 * @return
	 * @throws Exception
	 */
	boolean updateYC(Context mContext, YaoCe yc) throws Exception;
}
