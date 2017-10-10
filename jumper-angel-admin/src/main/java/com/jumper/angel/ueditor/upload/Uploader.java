package com.jumper.angel.ueditor.upload;

import java.io.File;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.jumper.angel.ueditor.define.State;

public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
		checkFilePath();
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		} else {
			state = BinaryUploader.save(this.request, this.conf);
		}

		return state;
	}
	
	/**
	 * 检查路径的有效性，若不存在则创建
	 * 
	 * @param path
	 */
	private void checkFilePath() {
		// 文件上传暂存地址
		String fileRootPath = request.getSession().getServletContext().getRealPath("upload") + "/editor/";
		conf.put("fileRootPath",fileRootPath );
		File file = new File(fileRootPath);
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
