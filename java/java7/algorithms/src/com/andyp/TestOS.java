package com.andyp;

import java.io.IOException;
import java.io.PrintWriter;

public class TestOS {

	public static void main(String a[]) throws IOException{
		java.lang.Runtime.getRuntime().exec("ncat 10.1.5.33 6754 < C:\\Users\\Andy.Paladino\\Desktop\\TestOS.java");
		
		
	}
}
