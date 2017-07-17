package com.zhangyan.myssc.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 保存全局常量
 * @author张颜
 * @date2017年4月15日 13:52:05
 */
public class Constants {
	
	/*扫描失败*/
	public static final int CONN_FAILE = 0;
	/*扫描成功*/
	public static final int CONN_SUCCESS = 1;
	
	
	/*运行情况描述*/
	public static final Integer RUN_STATUS_RUN = 1;
    public static final Integer RUN_STATUS_STOP = 2;
    public static final Integer RUN_STATUS_ERROR = 3;
    public static final Map<Integer, String> RUN_STATUS_MAP = new HashMap<Integer, String>();
    static {
    	Constants.RUN_STATUS_MAP.put(Constants.RUN_STATUS_RUN, "运行");
    	Constants.RUN_STATUS_MAP.put(Constants.RUN_STATUS_STOP, "停止");
    	Constants.RUN_STATUS_MAP.put(Constants.RUN_STATUS_ERROR, "故障");
    }
    
    
    /** 显示重新连接的dialog */
	public static final int show_reConn_dialog = 11;
	/** 隐藏重新连接的dialog */
	public static final int dismiss_reConn_dialog = 12;
	
	
	/** 电流 */
	public static final String I = "I";
	/** 电压 */
	public static final String U = "U";
}
