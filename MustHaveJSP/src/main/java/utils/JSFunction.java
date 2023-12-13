package utils;

import jakarta.servlet.jsp.JspWriter;

public class JSFunction {
	//after message alert, send to certain url
	public static void alertLocation(String msg,String url, JspWriter out) {
		try {
			String script=""
					+ "<script>"
					+ "		alert('"+msg+"');"
					+ "		location.href='"+url+"';"
					+ "</script>";
			out.println(script);
		}catch (Exception e) {
			e.getMessage();
		}
	}
	
	public static void alertBack(String msg,JspWriter out) {
		try {
			String script=""
					+ "<script>"
					+ "		alert('"+msg+"');"
					+ "		history.back();"
					+ "</script>";
			out.println(script);
		}catch (Exception e) {
			e.getMessage();
		}
	}
}
