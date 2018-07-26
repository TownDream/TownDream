package com.yidu.d257.util;

import java.awt.Font;
import java.awt.Frame;
import java.util.UUID;

public class MyUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").substring(16).toUpperCase();
	}
	
	public void S(){
		System.out.println(ss);
	};
	
	public static void main(String[] args) throws Exception {
		Integer a = new Integer(3);
		Integer b = new Integer(3);
		System.out.println(a==b);
	}
	
}
