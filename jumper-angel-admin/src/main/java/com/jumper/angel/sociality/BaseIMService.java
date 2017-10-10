package com.jumper.angel.sociality;

import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.jumper.angel.sociality.util.Constans;
import com.jumper.angel.sociality.util.HttpPost;
import com.jumper.angel.sociality.util.WebUtils;


/**
 * 公共抽象类，只用于继承
 * 
 * @author psy
 * @since 2016年4月14日
 * @version 1.0.0
 */
public abstract class BaseIMService<T> {
	private static Logger logger = Logger.getLogger(BaseIMService.class);

	private final static String QUESTION_MARK = "?";
	
	public String sendPost(String url ,String params) throws Exception{
		String result = HttpPost.doPost(url,params);
		return result;
	}

	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	protected String doPost(String url, String params) throws IOException {
		String finalUrl = Constans.IM_SERVER_URL + url + QUESTION_MARK + params;
		logger.info("\n\n  URL:" + finalUrl + "  \n\n");
		String result = WebUtils.doPost(finalUrl, null, Constans.UTF8_CODE, 20000, 20000);
		return result;
	}

	/**
	 * 获得model的url，将model转成url，例如a=1&b=2
	 * 
	 * @param sb
	 * @param obj
	 * @return String
	 */
	private String getParamStrByModel(StringBuffer sb, Object obj) {
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (int i = 0; i < fields.length; i++) {
					Field f = fields[i];
					f.setAccessible(true);
					Object object = f.get(obj);
					if (sb.length() != 0) {
						sb.append("&");
					}
					sb.append(f.getName());
					sb.append("=");
					if (object != null) {
						sb.append(object);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 获得model的url，将model转成url，例如a=1&b=2
	 * 
	 * @param obj
	 * @param hasSuper
	 * @return String
	 */
	protected String getParamStrByModel(Object obj) {
		StringBuffer sb = new StringBuffer();
		
		getParamStrByModel(sb, obj);
		
		String result = sb.toString();
		
		result = result.replaceAll("serialVersionUID=1000&", "");
		
		return result;
	}
}
