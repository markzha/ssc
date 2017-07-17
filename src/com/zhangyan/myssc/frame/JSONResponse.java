package com.zhangyan.myssc.frame;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhangyan.myssc.util.ObjectUtil;

public class JSONResponse implements Serializable {

	private static final long serialVersionUID = 2866352044513698346L;
	
	private boolean success = false;
	
	private String msg;
	
	private Object data;
	
	private String lastDownloadTime;
	
	private boolean finish = false;
	
	public JSONResponse(){
	}
	
	public JSONResponse(String msg, boolean success){
		this.msg = msg;
		this.success = success;
	}

	public String getLastDownloadTime() {
		return lastDownloadTime;
	}

	public void setLastDownloadTime(String lastDownloadTime) {
		this.lastDownloadTime = lastDownloadTime;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
	public JSONResponse jsonAnaysis(String jsonStr){
		JSONResponse jsonResponse = new JSONResponse();
		try {
			JSONObject ObjectInfo = new JSONObject(jsonStr);
			String success = ObjectInfo.optString("success");  
	        if (ObjectUtil.isNotBlank(success)) {
	        	jsonResponse.setSuccess(success.equals("true") ? true : false);
			}
	        String msg = ObjectInfo.optString("msg");  
	        if (ObjectUtil.isNotBlank(msg)) {
	        	jsonResponse.setMsg(msg + "");
	        }
	        String data = ObjectInfo.optString("data");  
	        if (ObjectUtil.isNotBlank(data)) {
	        	jsonResponse.setData((Object)data);
	        }
	        String lastDownloadTime = ObjectInfo.optString("lastDownloadTime");  
	        if (ObjectUtil.isNotBlank(lastDownloadTime)) {
	        	jsonResponse.setLastDownloadTime(lastDownloadTime);
	        }
	        String finish = ObjectInfo.optString("finish"); 	
	        if (ObjectUtil.isNotBlank(finish)) {
	        	jsonResponse.setFinish(finish.equals("false") ? false : true);
	        }		
		} catch (JSONException e) {
			jsonResponse.setSuccess(false);
			e.printStackTrace();
		}          
		return jsonResponse;
	}
	
}
