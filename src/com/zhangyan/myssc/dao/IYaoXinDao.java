package com.zhangyan.myssc.dao;

import java.util.List;

import com.zhangyan.myssc.bean.YaoXin;

/**
 * 遥信表
 * @author 张颜
 *
 */
public interface IYaoXinDao {
	/**
	 * 添加单个
	 * @param yx
	 * @throws Exception
	 */
	void addOne(YaoXin yx) throws Exception;
	/**
	 * 保存集合
	 * @param yxList
	 */
	void saveYXList(List<YaoXin> yxList) throws Exception;
	/**
	 * 清空表
	 * @return
	 * @throws Exception
	 */
	boolean delAll() throws Exception;
	/**
	 * 通过版表id来查询遥信数据
	 * @param bbId
	 * @return
	 * @throws Exception
	 */
	List<YaoXin> findByBBId(String bbId) throws Exception;
	/**
	 * 通过collName来查询单个遥信对象
	 * @param collName
	 * @return
	 * @throws Exception
	 */
	YaoXin findOneByName(String collName) throws Exception;
	/**
	 * 更新遥信的运行信息
	 * @param yx
	 * @return
	 * @throws Exception
	 */
	boolean updateYX(YaoXin yx) throws Exception;
}
