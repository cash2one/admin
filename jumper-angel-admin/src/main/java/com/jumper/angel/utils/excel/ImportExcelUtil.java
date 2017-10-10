package com.jumper.angel.utils.excel;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 导入excel文件工具类
 * 
 * @Description TODO
 * @author qinxiaowei
 * @date 2017年3月14日
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc.
 *             All rights reserved.
 */
public class ImportExcelUtil {

	private final static Logger logger = Logger
			.getLogger(ImportExcelUtil.class);

	// 2003- 版本的excel
	private final static String excel2003L = ".xls";

	// 2007+ 版本的excel
	private final static String excel2007U = ".xlsx";

	/**
	 * 获取IO流中的数据，组装成List<List<Object>>对象
	 * 
	 * @version 1.0
	 * @createTime 2017年3月14日,下午6:04:12
	 * @updateTime 2017年3月14日,下午6:04:12
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param in
	 *            输入流
	 * @param fileName
	 *            文件名
	 * @return
	 * @throws Exception
	 */
	public List<List<Object>> getBankListByExcel(InputStream in, String fileName)
			throws Exception {
		List<List<Object>> list = new ArrayList<List<Object>>();
		// 创建Excel工作薄
		Workbook work = this.getWorkbook(in, fileName);
		if (null == work) {
			logger.info("创建Excel工作薄为空！");
		}
		// 获取Sheet的数目
		int numberOfSheets = work.getNumberOfSheets();
		// 遍历Excel中所有的sheet
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			//获取第一行数
//			int firstRowNum = sheet.getFirstRowNum();
			//获取最后行数，不知道为什么会少一行所以就加了一行
			int lastRowNum = sheet.getLastRowNum()+1;
			// 遍历当前sheet中的所有行 从第一行开始
			for (int j = 1; j < lastRowNum; j++) {
				Row row = sheet.getRow(j);
				if (row == null) {
					continue;
				}
				// 遍历所有的列
				List<Object> li = new ArrayList<Object>();
				for (int y = 0; y < row.getLastCellNum(); y++) {
					Cell cell = row.getCell(y);
					//li.add(this.getCellValue(cell));
					//不对数字进行格式化
					li.add(cell!=null?cell.getRichStringCellValue().getString():"");
				}
				list.add(li);
			}
		}
		// work.close();
		return list;
	}

	/**
	 * 根据文件后缀，自适应上传文件的版本
	 * 
	 * @version 1.0
	 * @createTime 2017年3月14日,下午6:06:03
	 * @updateTime 2017年3月14日,下午6:06:03
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param inStr
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(InputStream inStr, String fileName)
			throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		// 2003-
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr);
		} else if (excel2007U.equals(fileType)) { // 2007+
			wb = new XSSFWorkbook(inStr);
		} else {
			logger.info("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 对表格中数值进行格式化
	 * 
	 * @version 1.0
	 * @createTime 2017年3月14日,下午6:06:57
	 * @updateTime 2017年3月14日,下午6:06:57
	 * @createAuthor qinxiaowei
	 * @updateAuthor
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param cell
	 * @return
	 */
	public Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字

		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if ("General".equals(cell.getCellStyle().getDataFormatString())) {
					value = df.format(cell.getNumericCellValue());
				} else if ("m/d/yy".equals(cell.getCellStyle()
						.getDataFormatString())) {
					value = sdf.format(cell.getDateCellValue());
				} else {
					value = df2.format(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			default:
				break;
		}
		return value;
	}
}
