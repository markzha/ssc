package com.zhangyan.myssc.frame;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.content.Context;

import com.zhangyan.myssc.util.MyDbUtil;

public class MyHandler implements InvocationHandler {
	private Object proxyObj;

	public Object bind(Object obj) {
		this.proxyObj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		Context context = null;
		MyDbUtil myDbUtil = null;
		//把参数中的context找出来
		for(int i=0;args !=null && i<args.length;i++){
			if(args[i] instanceof Context){
				context = (Context) args[i];
				break;
			}
		}
		if(context != null){
			myDbUtil = new MyDbUtil(context);
			NkContext.getDbutilThread().set(myDbUtil);
		}
		try {
			// 如果配置了事务处理的注解,开始事务处理
			if (method.isAnnotationPresent(NkTranscation.class)) {				
				myDbUtil.getDb().beginTransaction();
				try {
					result = method.invoke(proxyObj, args);
					myDbUtil.getDb().setTransactionSuccessful();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					myDbUtil.getDb().endTransaction();
					NkContext.getDbutilThread().remove();
				}
			} 
			//如果没有进行事务处理的注解,不进行事务处理
			else {
				result = method.invoke(proxyObj, args);
				NkContext.getDbutilThread().remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(context != null){
				myDbUtil.getDb().close();
			}			
		}
		return result;
	}
}