package com.zhangyan.myssc.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetTool {
	public static String TAG = "NetTool";

	// 获取本地ip地址
	public static String getLocAddress() {

		String ipaddress = "";

		try {
			Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces();
			// 遍历所用的网络接口
			while (en.hasMoreElements()) {
				NetworkInterface networks = en.nextElement();
				// 得到每一个网络接口绑定的所有ip
				Enumeration<InetAddress> address = networks.getInetAddresses();
				// 遍历每一个接口绑定的所有ip
				while (address.hasMoreElements()) {
					InetAddress ip = address.nextElement();
					if (!ip.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(ip
									.getHostAddress())) {
						ipaddress = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			Log.e("", "获取本地ip地址失败");
			e.printStackTrace();
		}

		System.out.println("本机IP:" + ipaddress);

		return ipaddress;

	}

	// 获取IP前缀
	public static String getLocAddrIndex() {

		String str = getLocAddress();

		if (!str.equals("")) {
			return str.substring(0, str.lastIndexOf(".") + 1);
		}

		return null;
	}

	// 获取本机设备名称
	public String getLocDeviceName() {

		return android.os.Build.MODEL;

	}

	/**
	 * 利用正则表达式验证IP地址是否合法
	 * 
	 * @param ipAddress
	 * @return
	 */
	public static boolean isIpv4(String ipAddress) {

		String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
				+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
				+ "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();

	}
	
	/**
	 * 判断当前网络是否为WIFI网络，WIFI网络返回true，非WIFI网络返回false；
	 * @param context
	 * @return
	 */
	public static boolean isWiFiActive(Context context) {
		WifiManager mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
		int ipAddress = wifiInfo == null ? 0 : wifiInfo.getIpAddress();
		if (mWifiManager.isWifiEnabled() && ipAddress != 0) {
			System.out.println("NetTool: WIFI is on");
			return true;
		} else {
			System.out.println("NetTool: WIFI is off");
			return false;
		}
	}

}