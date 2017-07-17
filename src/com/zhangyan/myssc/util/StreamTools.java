package com.zhangyan.myssc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;


public class StreamTools {
	public static String streamToString(InputStream in) throws IOException{

		//1.将字节流转化为字符流
		InputStreamReader reader = new InputStreamReader(in);
		//2.读取字符流
		BufferedReader br = new BufferedReader(reader);
		//3.创建写入流
		StringWriter sw = new StringWriter();
		//4.在缓冲区进行流的读写操作
		String str = null;
		while((str = br.readLine())!=null){
			sw.write(str);
		}
		sw.close();
		br.close();
		return sw.toString();
	}
}
