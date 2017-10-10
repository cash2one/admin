package com.jumper.angel.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

import com.jumper.angel.home.file.controller.FileController;
import com.jumper.angel.utils.file.FastdfsUpload;


public class createHtml {
	/**
	 * 
	 * @param 模板的路径
	 * @param 生成html的路径
	 * @param html的内容
	 * @return
	 */
	public static String CreateHtmlFile(String filePath, String HtmlFile, String context) {
		long beginDate = (new Date()).getTime();
		String str = getMBContent(filePath);
		str = str.replaceAll("###content###", context);
		String path_url = uploadMB(HtmlFile,str);
		System.out.println("共用时：" + ((new Date()).getTime() - beginDate)	+ "ms");
		return path_url;
	}
	
	/**
	 * 生成新闻静态文件
	 * @param 新闻模板的路径
	 * @param 生成html的路径
	 * @param html的内容
	 * @return
	 */
	public static String CreateHtmlFile(String filePath, String HtmlFile, String context,String newsId,String author,String authorImage,String title) {
		long beginDate = (new Date()).getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = getMBContent(filePath);
		str = str.replaceAll("###content###", context).replaceAll("###newsId###", newsId).
		          replaceAll("###from_logo_url###", authorImage).replaceAll("###source_from###", author).
		          replaceAll("###add_time###", sdf.format(new Timestamp(System.currentTimeMillis()))).
		          replaceAll("###title###", title);
		
		String path_url = uploadMB(HtmlFile,str);
		System.out.println("共用时：" + ((new Date()).getTime() - beginDate)	+ "ms");
		return path_url;
	}
	
	//读取模板文件
	private static String getMBContent(String filePath){
		String str = "";
		FileInputStream is=null;
		try {
			String tempStr = "";
			System.out.println("filePath=====:"+filePath);
			is = new FileInputStream(filePath);// 读取模块文件
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((tempStr = br.readLine()) != null){
				str = str + tempStr;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			str=null;
		}catch(Throwable able){
			able.printStackTrace();
			str=null;
		}finally{
			try{
			if(is!=null){
				is.close();
			}
			}catch(Exception exp){
				exp.printStackTrace();
			}
		}
		return str;
	}
	
	//生成静态文件并返回文件地址
	private static String uploadMB(String HtmlFile,String str){
		String path_url = null;
		try {
			String dir=HtmlFile+File.separator+GetFilesUploadDir();
			new File(dir).mkdirs();
			String filename=createNewFilesName()+".html";
			path_url="/"+ConfigProUtils.get("HTML_FOLDER_NAME")+"/"+GetFilesUploadDir()+"/"+filename;
			HtmlFile=dir+"/"+filename;
			System.out.println(HtmlFile);
			File f = new File(HtmlFile);
			OutputStreamWriter o = new OutputStreamWriter(new FileOutputStream(HtmlFile, true),"UTF-8");
			o.write(str);
			o.close();
			if(f.exists()){
//				InputStream inputStream = new FileInputStream(f);
				//上传到服务器
				path_url = new FileController().uploadToFileServer(f);
//				path_url = FastdfsUpload.upoladFile(filename, inputStream);
//				f.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path_url;
	}
	
	private static String GetFilesUploadDir()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}
    private static String createNewFilesName()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		return formatter.format(new Date());
	}

}
