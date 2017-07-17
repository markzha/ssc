package com.zhangyan.myssc.dao.impl;

import java.util.List;

import android.annotation.SuppressLint;

import com.zhangyan.myssc.bean.YaoKong;
import com.zhangyan.myssc.dao.IYaoKongDao;
import com.zhangyan.myssc.frame.NkContext;

@SuppressLint("UseValueOf")
public class YaoKongDaoImpl implements IYaoKongDao{

	@Override
	public void addOne(YaoKong yk) throws Exception {
		NkContext.getDbutilThread().get().addObj(yk);
	}

	@Override
	public void saveYKList(List<YaoKong> ykList) throws Exception {
		if(null!=ykList && ykList.size()>0){
			for(YaoKong yk : ykList){
				this.addOne(yk);
			}
		}
	}

	@Override
	public boolean delAll() throws Exception {
		String sql = "delete from t_yaokong;";
		NkContext.getDbutilThread().get().delAllObj(sql);
		return true;
	}

	@Override
	public List<YaoKong> findByBBId(String bbId) throws Exception {
		String sql = "select * from t_yaokong t where t.c_foreign_id = ?";
		return  NkContext.getDbutilThread().get().getList(new YaoKong(), sql, new String[]{bbId});
	}

	@Override
	public YaoKong findOneByName(String collName) throws Exception {
		String sql = "select * from t_yaokong t where t.c_coll_name = ?";
		return  NkContext.getDbutilThread().get().getObject(new YaoKong(), sql, new String[]{collName});
	}

	@Override
	public boolean updateYK(YaoKong yk) throws Exception {
		try {
			NkContext.getDbutilThread().get().updateObj(yk);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
