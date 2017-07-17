package com.zhangyan.myssc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhangyan.myssc.bean.BaseData;
import com.zhangyan.myssc.bean.BanBiaoTemp;
import com.zhangyan.myssc.bean.YaoCe;
import com.zhangyan.myssc.bean.YaoKong;
import com.zhangyan.myssc.bean.YaoXin;
import com.zhangyan.myssc.dao.IBasedataDao;
import com.zhangyan.myssc.dao.IYaoCeDao;
import com.zhangyan.myssc.dao.IYaoKongDao;
import com.zhangyan.myssc.dao.IYaoXinDao;
import com.zhangyan.myssc.dao.impl.BasedataDaoImpl;
import com.zhangyan.myssc.dao.impl.YaoCeDaoImpl;
import com.zhangyan.myssc.dao.impl.YaoKongDaoImpl;
import com.zhangyan.myssc.dao.impl.YaoXinDaoImpl;
import com.zhangyan.myssc.service.IBasedataService;
import com.zhangyan.myssc.util.JsonUtils;
public class BasedataServiceImpl implements IBasedataService{
	
	private static final String TAG = "BanBiaoServiceImpl";
	
	private IBasedataDao basedataDao = new BasedataDaoImpl();
	private IYaoCeDao yaoCeDao = new YaoCeDaoImpl();
	private IYaoXinDao yaoXinDao = new YaoXinDaoImpl();
	private IYaoKongDao yaoKongDao = new YaoKongDaoImpl();
	

	@Override
	public List<BaseData> findAll(Context context) throws Exception {
		List<BaseData> bbList = new ArrayList<BaseData>();
		try {
			bbList = this.basedataDao.findAll();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return bbList;
	}
	
	@Override
	public void saveBBList(Context context, List<BaseData> bbList) throws Exception {
		try {
			this.basedataDao.saveBBList(bbList);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	@Override
	public boolean delAll(Context context) throws Exception {
		return this.basedataDao.delAll();
	}

	@Override
	public boolean saveBanBiaoTempOne(Context context,BanBiaoTemp bbTemp) throws Exception {
		try {
			//保存版表主表
			BaseData bb = new BaseData();
			String id = UUID.randomUUID().toString();
			bb.setId(id);
			//bb.setUnitDesc(bbTemp.getUnitDesc());
			//bb.setUnitName(bbTemp.getUnitName());
			this.basedataDao.addOne(bb);
			
			JSONObject jsonObject = bbTemp.getColls();
			JSONArray yxJsonArray = jsonObject.getJSONArray(JsonUtils.YX);
			JSONArray ycJsonArray = jsonObject.getJSONArray(JsonUtils.YC);
			JSONArray ykJsonArray = jsonObject.getJSONArray(JsonUtils.YK);
			
			//保存遥信表
			String yxJson = yxJsonArray.toString();
			List<YaoXin> yxList = new Gson().fromJson(yxJson, new TypeToken<List<YaoXin>>() {}.getType());
			for (int i = 0; i < yxList.size(); i++) {
				yxList.get(i).setForeignId(id);
				yxList.get(i).setId(UUID.randomUUID().toString());
			}
			this.yaoXinDao.saveYXList(yxList);
			
			//保存遥测表
			String ycJson = ycJsonArray.toString();
			List<YaoCe> ycList = new Gson().fromJson(ycJson, new TypeToken<List<YaoCe>>() {}.getType());
			for (int i = 0; i < ycList.size(); i++) {
				ycList.get(i).setForeignId(id);
				ycList.get(i).setId(UUID.randomUUID().toString());
			}
			this.yaoCeDao.saveYCList(ycList);
			
			//保存遥控表
			String ykJson = ykJsonArray.toString();
			List<YaoKong> ykList = new Gson().fromJson(ykJson, new TypeToken<List<YaoKong>>() {}.getType());
			for (int i = 0; i < ykList.size(); i++) {
				ykList.get(i).setForeignId(id);
				ykList.get(i).setId(UUID.randomUUID().toString());
			}
			this.yaoKongDao.saveYKList(ykList);
			
			return true;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return false;
	}

	@Override
	public void updateListByMap(Context mContext, Map<String, String> dataMap) throws Exception {
		for (String unitName : dataMap.keySet()) {
			// map.keySet()返回的是所有key的值
			String runStatus = dataMap.get(unitName);
			BaseData bb = this.findOneByName(mContext, unitName);
			//bb.setRunStatus(runStatus);
			this.updateBB(mContext, bb);
		}
		
	}

	@Override
	public boolean updateBB(Context context, BaseData bb) throws Exception {
		return this.basedataDao.updateBB(bb);
	}

	@Override
	public BaseData findOneByName(Context context, String unitName)
			throws Exception {
		return this.basedataDao.findOneByName(unitName);
	}

	

}
