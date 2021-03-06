package com.jumper.angel.utils;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
public final class HttpPost {

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

//	private static String prepareParam(Map<String,Object> paramMap){
//		StringBuffer sb = new StringBuffer();
//		if(paramMap.isEmpty()){
//			return "" ;
//		}else{
//			for(String key: paramMap.keySet()){
//				String value = (String)paramMap.get(key);
//				if(sb.length()<1){
//					sb.append(key).append("=").append(value);
//				}else{
//					sb.append("&").append(key).append("=").append(value);
//				}
//			}
//			return sb.toString();
//		}
//	}

	public static void main(String[] args) {
		String url = "https://console.tim.qq.com/v4/openim/sendmsg?usersig=eJxNjV1PgzAUhv8LtzWmBeqIyS5wGcrmssEmBG9IhQ6Pk7aWYlgW-7tAWLJzd96P571Yh9f9fVOecqYUlNajRVyMMSE2pdbdaPJOgeY5OxquB59SaveRyR1rOTO5o4f2VTZQ8xE2wx6h2HUmHUouDBxhRLGyBnHlFIVshcnNWfEbTgNV-22W0SIMdt1KzKT96UmUVM*pYOui87LtIXyPnfADnfdvRZp0qy368cE-bYJImTROgt1XW0fhk4uE26qI*Wvk6GX8IpnCTfbwnVXz*TT2y3UDUvSDNiaU2A4ezvr7B6QMV28_&identifier=admin&sdkappid=1400011255&random=0123456789&contenttype=json";
		String param = "{\"MsgBody\":[{\"MsgContent\":{\"Text\":\"hi,beauty\"},\"MsgType\":\"TIMTextElem\"}],\"MsgTimeStamp\":5454457,\"MsgRandom\":1287657,\"From_Account\":\"test\",\"To_Account\":\"wanghl\"}";
		String rsp = doPost(url,param);
		System.out.println(rsp);
	}

	/**
	 * 发送post请求
	 * @param urlStr
	 * @param param
	 * @return
	 */
	public static String  doPost(String urlStr,String param ){
		try{
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			String paramStr = param;
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();    
			os.write(paramStr.toString().getBytes("utf-8"));    
			os.close();        
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line ;
			String result ="";
			while( (line =br.readLine()) != null ){
				result += ""+line;
			}
			br.close();
			return result;
		}catch (Exception e) {
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