package com.zhangyan.myssc.dao.impl;

import java.util.List;

import com.zhangyan.myssc.bean.YaoCe;
import com.zhangyan.myssc.dao.IYaoCeDao;
import com.zhangyan.myssc.frame.NkContext;

public class YaoCeDaoImpl implements IYaoCeDao{

	@Override
	public void addOne(YaoCe yc) throws Exception {
		NkContext.getDbutilThread().get().addObj(yc);
	}

	@Override
	public void saveYCList(List<YaoCe> ycList) throws Exception {
		if(null!=ycList && ycList.size()>0){
			for(YaoCe yc : ycList){
				this.addOne(yc);
			}
		}
	}

	@Override
	public boolean delAll() throws Exception {
		String sql = "delete from t_yaoce;";
		NkContext.getDbutilThread().get().delAllObj(sql);
		return true;
	}

	@Override
	public List<YaoCe> findByBBId(String bbId) throws Exception {
		String sql = "select * from t_yaoce t where t.c_foreign_id = ?";
		return  NkContext.getDbutilThread().get().getList(new YaoCe(), sql, new String[]{bbId});
	}

	@Override
	public YaoCe findOneByName(String collName) throws Exception {
		String sql = "select * from t_yaoce t where t.c_coll_name = ?";
		return  NkContext.getDbutilThread().get().getObject(new YaoCe(), sql, new String[]{collName});
	}

	@Override
	public boolean updateYC(YaoCe yc) throws Exception {
		try {
			NkContext.getDbutilThread().get().updateObj(yc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
