package com.wung.springdemo.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Description: 对象转换成json的工具类
 */
public class JSONService {
	
	public JSONService(){
		
	}
	//List
	public static String listToJson(List<?> list){
		return JSONArray.fromObject(list).toString();
	}
	//Map,普通类
	public static String beanToJSON(Object bean){
		return JSONObject.fromObject(bean).toString();
	}
	
	//将转换好的json数据放入HttpServletResponse内
	private static void writeJsonIntoResponse(HttpServletResponse response, String json) {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json; charset=UTF-8");
		PrintWriter out = null;
        try {
            out = response.getWriter();
    		out.print(json);
    		out.flush();
        } catch (Exception e) {
            out.write("error");
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
	}
	//将未转换的bean(map,普通类)放入HttpServletResponse内
	public static void writeBeanIntoResponse(HttpServletResponse response,Object bean) {
	    writeJsonIntoResponse(response,beanToJSON(bean));
	}
	//将未转换的list放入HttpServletResponse内
	public static void  writeListIntoResponse(HttpServletResponse response,List<?> list) {
	    writeJsonIntoResponse(response,listToJson(list));
	}
	
    public static void writeStringIntoResponse(HttpServletResponse response, String string) {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(string);
            out.flush();
        } catch (Exception e) {
            out.write("error");
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

	
	//将转换好的json数据放入HttpServletResponse内  按照指定编码
	private static void writeJsonIntoResponse(HttpServletResponse response, String json, String charset) {
	    response.setHeader("Cache-Control", "no-cache");
	    response.setContentType("text/json; charset=" + charset);
	    PrintWriter out = null;
	    try {
	        out = response.getWriter();
	        out.print(json);
	        out.flush();
	    } catch (Exception e) {
	        out.write("error");
	        e.printStackTrace();
	    } finally {
	        out.flush();
	        out.close();
	    }
	}
	//将未转换的bean(map,普通类)放入HttpServletResponse内 按照指定编码
	public static void writeBeanIntoResponse(HttpServletResponse response,Object bean, String charset) {
		writeJsonIntoResponse(response,beanToJSON(bean), charset);
	}
	//将未转换的list放入HttpServletResponse内 按照指定编码
	public static void  writeListIntoResponse(HttpServletResponse response,List<?> list, String charset) {
		writeJsonIntoResponse(response,listToJson(list), charset);
	}
    public static void writeStringIntoResponse(HttpServletResponse response, String string, String charset) {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html; charset=" + charset);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(string);
            out.flush();
        } catch (Exception e) {
            out.write("error");
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

}
