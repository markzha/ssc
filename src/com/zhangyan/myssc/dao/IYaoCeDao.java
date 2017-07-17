package com.zhangyan.myssc.dao;

import java.util.List;

import com.zhangyan.myssc.bean.YaoCe;

/**
 * 遥测表
 * @author 张颜
 *
 */
public interface IYaoCeDao {
	/**
	 * 添加
	 * @param bb
	 * @throws Exception
	 */
	void addOne(YaoCe yc) throws Exception;/**
	 * 保存集合
	 * @param ycList
	 */
	void saveYCList(List<YaoCe> ycList) throws Exception;
	/**
	 * 清空表
	 * @return
	 * @throws Exception
	 */
	boolean delAll() throws Exception;
	/**
	 * 通过版表id来查询遥测数据
	 * @param bbId
	 * @return
	 * @throws Exception
	 */
	List<YaoCe> findByBBId(String bbId) throws Exception;
	/**
	 * 通过collName来查询单个遥测对象
	 * @param collName
	 * @return
	 * @throws Exception
	 */
	YaoCe findOneByName(String collName) throws Exception;
	/**
	 * 更新遥测运行信息
	 * @param yc
	 * @return
	 * @throws Exception
	 */
	boolean updateYC(YaoCe yc) throws Exception;
	
	
}
