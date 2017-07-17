package com.zhangyan.myssc.dao;

import java.util.List;

import com.zhangyan.myssc.bean.BaseData;

/**
 * 版表
 * @author 张颜
 *
 */
public interface IBasedataDao {
	
	/**
	 * 查询所有版表数据
	 * @param context
	 * @return List<BanBiao>
	 * @throws Exception 
	 */
	List<BaseData> findAll() throws Exception;
	/**
	 * 删除所有版表数据
	 * @param context
	 * @return
	 * @throws Exception
	 */
	boolean delAll() throws Exception;
	
	/**
	 * 添加
	 * @param bb
	 * @throws Exception
	 */
	void addOne(BaseData bb) throws Exception;
	
	/**
	 * 保存集合
	 * @param bbList
	 */
	void saveBBList(List<BaseData> bbList) throws Exception;
	/**
	 * 通过unitName来更新runStatus
	 * @param unitName
	 * @param runStatus
	 * @return
	 */
	boolean updateBB(BaseData bb) throws Exception;
	/**
	 * 通过unitName来查询单个对象
	 * @param unitName
	 * @return
	 * @throws Exception
	 */
	BaseData findOneByName(String unitName) throws Exception;

}
