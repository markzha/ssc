package com.zhangyan.myssc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhangyan.myssc.bean.BanBiaoTemp;

/**
 * 解析数据,发送数据用到的常量
 * @author张颜
 * @date2017年4月15日 13:52:05
 */
public class JsonUtils {
	
	/*第一次连接通讯管理机接受的json串的key*/
	public static final String RTUServer = "RTUServer";
	/*连接成功*/
	public static final String CONN_SUCCESS = "连接成功";
	
	/*遥信key*/
	public static final String YX = "YX";
	/*遥测*/
	public static final String YC = "YC";
	/*遥控*/
	public static final String YK = "YK";
	
	/*请求版表运行状态的语句*/
	public static final String update_banbiaolist = "CU,R,AllCollectUnitRunStatus=?;";
	/*请求版表运行状态的语句*/
	public static final String update_banbiaolist_error = "CU,R,AllCollectUnitRunStatus=NOK;";
	/*请求遥控运行信息的语句*/
	public static final String update_yaokong = "YK,R,";
	/*请求遥测运行信息的语句*/
	public static final String update_yaoce = "YC,R,";
	/*请求遥信运行信息的语句*/
	public static final String update_yaoxin = "YX,R,";
	/*请求遥信,遥控,遥测运行信息的语句的结尾拼串*/
	public static final String update_str_end = "=?;";
	
	/*请求录波文件列表*/
	public static final String request_lubo_filelist = "TF,R,FileList=?;";
	/*返回的录波文件列表头*/
	public static final String return_lubo_filelist_tip = "TF,R,";
	/*返回的录波文件列表头*/
	public static final String return_lubo_filename = "TF,R,FileName=";
	
	
	/*运行情况描述*/
	public static final Integer RUN_STATUS_RUN = 1;
    public static final Integer RUN_STATUS_STOP = 2;
    public static final Integer RUN_STATUS_ERROR = 3;
    public static final Map<Integer, String> RUN_STATUS_MAP = new HashMap<Integer, String>();
    static {
    	JsonUtils.RUN_STATUS_MAP.put(JsonUtils.RUN_STATUS_RUN, "运行");
    	JsonUtils.RUN_STATUS_MAP.put(JsonUtils.RUN_STATUS_STOP, "停止");
    	JsonUtils.RUN_STATUS_MAP.put(JsonUtils.RUN_STATUS_ERROR, "故障");
    }
    
    /**
     * 将json字符串初步解析成临时版本对象集合
     * @param jsonArray
     * @return
     */
    public static List<BanBiaoTemp> parseJsonData(JSONArray jsonArray){
    	List<BanBiaoTemp> bbtList = new ArrayList<BanBiaoTemp>();
    	if (ObjectUtil.isBlank(jsonArray)) {
    		return bbtList;
		}
    	for (int i = 0; i < jsonArray.length(); i++) {
    		try {
				JSONObject mObject = (JSONObject) jsonArray.get(i);
				BanBiaoTemp bbTemp = new BanBiaoTemp();
				bbTemp.setUnitName(mObject.getString("UnitName"));
				bbTemp.setUnitDesc(mObject.getString("UnitDesc"));
				bbTemp.setColls(mObject.getJSONObject("Colls"));
				bbtList.add(bbTemp);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
    	return bbtList;
    }
    
    /**
     * 在版表列表页,将请求到的版表运行数据串解析成map集合
     * @param 成功时dataStr 格式如下
     * CU,R,T-01-01=停止,T-01-02=停止,T-01-03=停止,T-01-04=停止,
	 * T-01-05=停止,T-01-06=停止,T-01-07=停止,T-01-08=停止,T-01-09=停止,T-01-10=停止,T-02-01=停止,
	 * T-02-02=停止,T-02-03=停止,T-02-04=停止,T-02-05=停止,T-02-06=停止,T-02-07=停止,T-02-08=停止,
 	 * T-02-09=停止,T-02-10=停止,T-03-01=停止,T-03-02=停止,T-03-03=停止,T-03-04=停止,T-03-05=停止,
	 * T-03-06=停止,T-03-07=停止,T-03-08=停止,T-03-09=运行,T-03-10=停止;
	 * 
	 * @param 失败时dataStr 格式如下
	 * CU,R,AllCollectUnitRunStatus=NOK
     * @return
     */
	public static Map<String, String> parseDataMap(String dataStr) {
		Map<String, String> dataMap = new HashMap<String, String>();
		if (ObjectUtil.isBlank(dataStr)) {
    		return dataMap;
		}
		
		dataStr = dataStr.replace("CU,R,", "");
		dataStr = dataStr.replace(";", "");
		
		String[] strArr = dataStr.split(",");
		for (int i = 0; i < strArr.length; i++) {
			String s = strArr[i];
			String key = (s.split("="))[0];
		    String value = (s.split("="))[1];
		    dataMap.put(key, value);
		}
		
		return dataMap;
	}
    
    
}
