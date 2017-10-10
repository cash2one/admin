package com.jumper.angel.utils;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
/**
 * 
 * @author 汪腾
 * time:2015.7.22
 * 模拟post请求 非流模式
 *
 */
public final class HttpPostUtils {

	private final static String TAG = "HttpTookit";
	private final static int OverTime = 5000;

	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @return 返回请求响应的HTML
	 */
	public static String doGet(String url) {
		return doGet(new GetMethod(url));
	}
	

	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML</br>这个方法可以在外部对HttpMethod进行操作，中止请求
	 * 
	 * @param method
	 * @return
	 */
	public static String doGet(HttpMethod method) {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams()
				.setConnectionTimeout(OverTime);
		client.getParams().setSoTimeout(OverTime);
		String result = "";
		try {
			//printErrorMsg(TAG, "==================== UrlAddress ====================");
			//PrintMsg(TAG, "" + method.getURI());
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(method.getResponseBodyAsStream(),
								"UTF-8"));
				StringBuffer response = new StringBuffer();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line).append("\n");
				}
				result = response.toString();
			//	printErrorMsg(TAG, "==================== Response ====================");
			//	PrintMsg(TAG, "" + result);
				reader.close();
			}
		} catch (Exception e) {
			printErrorMsg(TAG, "Get request failed.", e);
			result = "";
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, NameValuePair[] parametersBody) {
		PostMethod method = new PostMethod(url);
		// 设置Http Post数据
		if (parametersBody != null) {
//			HttpMethodParams p = new HttpMethodParams();
//			for (Map.Entry<String, String> entry : params.entrySet()) {
//				p.setParameter(entry.getKey(), entry.getValue());
//			}
//			method.setParams(p);
			method.setRequestBody(parametersBody);
		}
		return doPost(method);
	}
	
	 private static String prepareParam(Map<String,Object> paramMap){
         StringBuffer sb = new StringBuffer();
         if(paramMap.isEmpty()){
             return "" ;
         }else{
             for(String key: paramMap.keySet()){
                 String value = (String)paramMap.get(key);
                 if(sb.length()<1){
                     sb.append(key).append("=").append(value);
                 }else{
                     sb.append("&").append(key).append("=").append(value);
                 }
             }
             return sb.toString();
         }
     }

	   public static String  doPost(String urlStr,Map<String,Object> paramMap ){
	       try{
		   	 URL url = new URL(urlStr);
	         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	         conn.setRequestMethod("POST");
	         String paramStr = prepareParam(paramMap);
	         conn.setDoInput(true);
	         conn.setDoOutput(true);
	         OutputStream os = conn.getOutputStream();    
	         os.write(paramStr.toString().getBytes("utf-8"));    
	         os.close();        
	        
	         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String line ;
	         String result ="";
	         while( (line =br.readLine()) != null ){
	             result += ""+line;
	         }
	         br.close();
	         return result;
	       }catch (Exception e) {
			// TODO: handle exception
	    	   e.printStackTrace();
	    	   return "获取异常";
		}
	  }
	
	
	
	
	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML</br>这个方法可以在外部对HttpMethod进行操作，中止请求
	 * 
	 * @param method
	 * @return
	 */
	public static String doPost(HttpMethod method) {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(OverTime);
		client.getParams().setSoTimeout(0);
		String result = "";
		try {
		//	printErrorMsg(TAG, "==================== UrlAddress ====================");
	///		PrintMsg(TAG, "" + method.getURI());
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(method.getResponseBodyAsStream(),
								"utf-8"));
				StringBuffer response = new StringBuffer();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				result = response.toString();
			//	printErrorMsg(TAG,"===================== Response =====================");
			//	PrintMsg(TAG, "OK");
				reader.close();
			}
		} catch (Exception e) {
			printErrorMsg(TAG, "Post request failed.", e);
			result = "";
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	
	
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Charsert", "UTF-8");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			if (param != null && param.toString().trim().length() > 0) {

				out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲
				out.flush();
			}

			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			// System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static void PrintMsg(String Tag,String msg){
		System.out.println(Tag+"\n"+msg);
	}
	
	public static void printErrorMsg(String Tag,String msg){
		System.err.println(Tag +"\n"+msg+"\n");
	}
	
	public static void printErrorMsg(String Tag,String msg,Exception e){
		System.err.println(Tag +"\n"+msg+"\n"+ e.toString());
	}
	
}