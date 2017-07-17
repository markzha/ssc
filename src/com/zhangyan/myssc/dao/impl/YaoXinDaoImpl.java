package com.zhangyan.myssc.dao.impl;

import java.util.List;

import com.zhangyan.myssc.bean.YaoXin;
import com.zhangyan.myssc.dao.IYaoXinDao;
import com.zhangyan.myssc.frame.NkContext;

public class YaoXinDaoImpl implements IYaoXinDao{

	@Override
	public void addOne(YaoXin yx) throws Exception {
		NkContext.getDbutilThread().get().addObj(yx);
	}

	@Override
	public void saveYXList(List<YaoXin> yxList) throws Exception {
		if(null!=yxList && yxList.size()>0){
			for(YaoXin yx : yxList){
				this.addOne(yx);
			}
		}
	}

	@Override
	public boolean delAll() throws Exception {
		String sql = "delete from t_yaoxin;";
		NkContext.getDbutilThread().get().delAllObj(sql);
		return true;
	}

	@Override
	public List<YaoXin> findByBBId(String bbId) throws Exception {
		String sql = "select * from t_yaoxin t where t.c_foreign_id = ?";
		return  NkContext.getDbutilThread().get().getList(new YaoXin(), sql, new String[]{bbId});
	}

	@Override
	public YaoXin findOneByName(String collName) throws Exception {
		String sql = "select * from t_yaoxin t where t.c_coll_name = ?";
		return  NkContext.getDbutilThread().get().getObject(new YaoXin(), sql, new String[]{collName});
	}

	@Override
	public boolean updateYX(YaoXin yx) throws Exception {
		try {
			NkContext.getDbutilThread().get().updateObj(yx);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
