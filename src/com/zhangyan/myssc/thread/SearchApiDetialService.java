package com.zhangyan.myssc.thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.zhangyan.myssc.util.StreamTools;
import com.zhangyan.myssc.util.UrlContasts;

/**
 * 查询api数据的服务
 * @author 张颜
 *
 */
public class SearchApiDetialService extends IntentService{

	private static final String TAG = "SearchApiDetialService";
	private String res;
	private int flag;
	private boolean threadRun = true;

	public SearchApiDetialService() {
		super("");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.e(TAG, "开启服务"+TAG);
		//开启线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (threadRun) {
					try {
						
						//每隔5分钟执行一次
						Thread.sleep(5*60*1000);
						
						String path = UrlContasts.cqssc_5;
						URL url = new URL(path);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setReadTimeout(5000);					
						conn.setRequestMethod("GET");	
						int code = conn.getResponseCode();
						if(code == 200){
							flag = 1;
							InputStream in = conn.getInputStream();
							res = StreamTools.streamToString(in);

						}	
						if (code == 408) {
							Log.e(TAG, "请求超时......");
							flag = 0;
						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						Log.e(TAG, "IO异常......");
						flag = 0;

					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally{		

					}
				}
			}
		}).start();

		

		//以下为发广播
		/*try {
			Intent mIntent = new Intent();
			mIntent.setAction("com.duan.aqidetail."+now_areaid);
			if(flag == 0){
			}else{	
				JSONObject js = new JSONObject(res);
				Bundle bundle = new Bundle();
				JSONObject js_showapi_res_body = js.getJSONObject("showapi_res_body");
				LogCatPrint.LogPrint(TAG, js_showapi_res_body.getString("now"));
				AqiDetailBean aqiDetailBean = JsonUtils.parseAqiDetail(js_showapi_res_body);
				LogCatPrint.LogPrint(TAG, aqiDetailBean.now_aqi_primary_pollutant);
				bundle.putSerializable("aqiDetailBean", aqiDetailBean);		
				mIntent.putExtras(bundle);
			}

			sendBroadcast(mIntent);


		} catch (JSONException e) {
			e.printStackTrace();
		}
*/

	}
	
	
	@Override
	public void onDestroy() {
		threadRun = false;
		super.onDestroy();
	}
	
	
	

}
