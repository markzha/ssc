package com.zhangyan.myssc.service.impl;

import java.util.List;

import android.content.Context;

import com.zhangyan.myssc.bean.YaoKong;
import com.zhangyan.myssc.dao.IYaoKongDao;
import com.zhangyan.myssc.dao.impl.YaoKongDaoImpl;
import com.zhangyan.myssc.service.IYaoKongService;

public class YaoKongServiceImpl implements IYaoKongService {
	
	private IYaoKongDao yaoKongDao = new YaoKongDaoImpl();
	
	@Override
	public boolean delAll(Context mContext) throws Exception{
		return this.yaoKongDao.delAll();
	}

	@Override
	public List<YaoKong> findByBBId(Context mContext, String bbId) throws Exception {
		return this.yaoKongDao.findByBBId(bbId);
	}

	@Override
	public YaoKong findOneByName(Context mContext, String collName)	throws Exception {
		return this.yaoKongDao.findOneByName(collName);
	}

	@Override
	public boolean updateYK(Context mContext, YaoKong yk) throws Exception {
		return this.yaoKongDao.updateYK(yk);
	}
	
	
}
