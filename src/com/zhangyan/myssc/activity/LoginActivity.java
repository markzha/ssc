package com.zhangyan.myssc.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhangyan.myssc.R;
import com.zhangyan.myssc.frame.AOPFactory;
import com.zhangyan.myssc.service.IBasedataService;
import com.zhangyan.myssc.service.impl.BasedataServiceImpl;
import com.zhangyan.myssc.util.ObjectUtil;
import com.zhangyan.myssc.util.Util;

/**
 * 登陆
 * @author 张颜
 * @time 2017年4月28日 16:03:57
 */
public class LoginActivity extends ZYActivity {
	
	protected static final String TAG = "ClientConnActivity";
	private Context mContext;
	
	private DrawerLayout mUserLayout;
	private LinearLayout setkey,setting;
	private Button btnConnect;
	private EditText edt_loginName,edt_password;
	
	private IBasedataService basedataService = (IBasedataService) AOPFactory.getAOPProxyedObject(BasedataServiceImpl.class.getName());

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_socket_conn);
		
		initData();
		initView();
		setListener();
		
	}
	
	/**
	 * 初始化数据 
	 */
	private void initData() {
		mContext = this;
		
	}

	public void handleException(Exception e, String prefix) {
		e.printStackTrace();
		Toast.makeText(this, prefix + e.toString(), Toast.LENGTH_LONG).show();
	}
	
	/**
	 * 查找控件
	 */
	public void initView() {
		btnConnect = (Button) findViewById(R.id.btnConnect);
		edt_loginName = (EditText) findViewById(R.id.edt_loginName);
		edt_password = (EditText) findViewById(R.id.edt_password);
		
		mUserLayout = (DrawerLayout) findViewById(R.id.user_layout);
		setkey = (LinearLayout) findViewById(R.id.setkey);
		setting = (LinearLayout) findViewById(R.id.setting);
		
	}
	
	/**
	 * 给控件设置监听事件
	 */
	@SuppressLint("RtlHardcoded") 
	private void setListener() {
		setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(!mUserLayout.isDrawerOpen(Gravity.LEFT))
					mUserLayout.openDrawer(Gravity.LEFT);
			}
		});
		//设置口令
		setkey.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@SuppressLint("InflateParams") 
			@Override
			public void onClick(View v) {
				final AlertDialog dialog = new AlertDialog.Builder(mContext).create();  
		        dialog.setView(LayoutInflater.from(mContext).inflate(R.layout.alert_dialog, null));  
		        dialog.show();  
		        Window dialogWindow = dialog.getWindow();
		        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		        int width = wm.getDefaultDisplay().getWidth();
		        lp.width = (width * 3) / 4; // 宽度
		        dialogWindow.setAttributes(lp);
		        
		        dialog.getWindow().setContentView(R.layout.alert_dialog);  
		        Button btnPositive = (Button) dialog.findViewById(R.id.btn);  
		        final EditText etContent = (EditText) dialog.findViewById(R.id.edt);  
		        btnPositive.setOnClickListener(new OnClickListener() {  
		  
		            @Override  
		            public void onClick(View arg0) {  
		                String str = etContent.getText().toString().trim();  
		                if (ObjectUtil.isBlank(str)) {  
		                    Util.setEditError(mContext,etContent,"输入内容不能为空");
		                } else {  
		                	SharedPreferences sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
							Editor editor=sp.edit();
							editor.putString("key", str);
							editor.commit();
							
							Util.key = str;
		                    dialog.dismiss();
		                    mUserLayout.closeDrawers();
		                }  
		            }  
		        });  
				
			}
		});
		
		btnConnect.setOnClickListener(new Button.OnClickListener() {
			@Override 
			public void onClick(View v) {
				if (Util.isFastDoubleClick()) { 
					Util.myToast(mContext, "请勿点击过快");
					return;
				}
				
				if (edt_loginName.getText().toString().trim().equals("张颜")) {
					Util.setEditError(mContext,edt_loginName,Html.fromHtml("<font color=#ff0000>账号错误!</font>"));
					return;
				}
				if (edt_password.getText().toString().trim().equals("1")) {
				}else{
					Util.setEditError(mContext,edt_password,Html.fromHtml("<font color=#ff0000>密码错误!</font>"));
					return;
				}
				showProgressDialog("正在登陆,请稍后...");
			}
		});

	}
	
}