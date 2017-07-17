package com.zhangyan.myssc.dao;

import java.util.List;

import com.zhangyan.myssc.bean.YaoKong;


public interface IYaoKongDao  {
	/**
	 * 添加单个对象
	 * @param yk
	 * @throws Exception
	 */
	void addOne(YaoKong yk) throws Exception;
	/**
	 * 保存集合
	 * @param ykList
	 */
	void saveYKList(List<YaoKong> ykList) throws Exception;
	/**
	 * 清空表
	 * @return
	 * @throws Exception
	 */
	boolean delAll() throws Exception;
	/**
	 * 通过版表id来查询遥控数据
	 * @param bbId
	 * @return
	 * @throws Exception
	 */
	List<YaoKong> findByBBId(String bbId) throws Exception;
	/**
	 * 通过collNmae来查询单个对象
	 * @param collName
	 * @return
	 * @throws Exception
	 */
	YaoKong findOneByName(String collName) throws Exception;
	/**
	 * 更新遥控运行信息
	 * @param yk
	 * @return
	 * @throws Exception
	 */
	boolean updateYK(YaoKong yk) throws Exception;
}
