package com.jumper.angel.utils.excel;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

import com.jumper.angel.user.statistics.vo.VOAllHospitalStatisticsInfo;

/**
 * 将数据导出到表格
 * @author gyx
 * @time 2017年3月16日
 */
public class ExportExcel<T> {
	private final static Logger logger = Logger.getLogger(ExportExcel.class);

	/**
	 * 导出医院统计数据
	 * @param statisticsList 数据列表
	 * @param response
	 */
	public static void ExportPromotionData(
			List<VOAllHospitalStatisticsInfo> statisticsList,
			HttpServletResponse response) {
		ExportExcel<VOAllHospitalStatisticsInfo> excel = new ExportExcel<VOAllHospitalStatisticsInfo>();
		String[] headers = {"医院名称","总用户数","日新增用户数","医院问诊服务次数","设备租赁服务次数","胎心监护服务次数","营养门诊服务人数","近7天新增用户数","本月新增用户数"};
		try {
			response.reset();
			String fileName = "PromotionInfo"+createNewFilesName()+".xls";
			response.addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes()));
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			excel.exportExcel(150, 150, headers, statisticsList, toClient);
			logger.info("export finish!");
			toClient.flush();
			logger.info("export flush!");
			toClient.close();
			logger.info("export close!");
		} catch (IOException e) {
			logger.info("ExportPromotionData error!");
			e.printStackTrace();
		}
		
	}
	
	public static String createNewFilesName()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmssSSS");
		return formatter.format(new Date());
	}
	
	public void exportExcel(int width,int height,String[] headers, Collection<T> dataset, OutputStream out) {
		exportExcel(width,height,"POI导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
	}
	
	
	
	/**
	 * 自定义图片尺寸导出
	 * @param title
	 * @param headers
	 * @param dataset
	 * @param out
	 * @param pattern
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void exportExcel(int width,int height,String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
		/** 声明一个工作簿 **/
		HSSFWorkbook workbook = new HSSFWorkbook();
		/** 声明一个表格 **/
		HSSFSheet sheet = workbook.createSheet(title);
		/** 设置表格默认列宽度为15个字节 **/
		sheet.setDefaultColumnWidth(50);
		/** 生成样式，用于表格标题行 **/
		HSSFCellStyle style = workbook.createCellStyle();
		/** 设置样式 **/
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		/** 生成字体 **/
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		/** 将字体应用到样式中 **/
		style.setFont(font);
		
		/** 样式二，用于表格内容行 **/
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(CellStyle.BORDER_THIN);
		style2.setBorderLeft(CellStyle.BORDER_THIN);
		style2.setBorderRight(CellStyle.BORDER_THIN);
		style2.setBorderTop(CellStyle.BORDER_THIN);
		style2.setAlignment(CellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		/** 字体二 **/
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		style2.setFont(font2);
		/** 声明画图顶级管理器 **/
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		/** 定义注释的大小和位置 **/
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 100, 50, (short) 10, 10, (short) 15, 15));
		comment.setString(new HSSFRichTextString("POI导出Excel添加的注释！"));
		/** 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容 **/
		
		/** 设置表格标题行 **/
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		/** 以下是数据内容 **/
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while(it.hasNext() && it != null){
			index++;
			row = sheet.createRow(index);
			T t = it.next();
			/** 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值 **/
			Field[] fields = t.getClass().getDeclaredFields();
			int cellIndex = 0;
			for(int i = 0;i < fields.length;i++){
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					/** 判断值的类型后进行强制类型转换 **/
					String textValue = null;
					if(value == null){
						continue;
					}
					HSSFCell cell = row.createCell(cellIndex);
					cellIndex ++ ;
					cell.setCellStyle(style2);
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "是";
						if (!bValue) {
							textValue ="否";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
						
					} else if (value instanceof byte[]) {
						sheet.setAutobreaks(true);
						/** 有图片时，自定义设置行高 **/
						row.setHeightInPoints(height);
						/** 自定义设置图片所在列宽度,注意这里单位的一个换算 **/
						sheet.setColumnWidth(cellIndex, width);
						sheet.autoSizeColumn(cellIndex);
						byte[] bsValue = (byte[]) value;
					//	HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 150,150, (short)0,index, (short)1, index);
	                //	HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 500,255, (short)1,index*2, (short)1, index*2+1);
	                	HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 330,150, (short)6,index, (short)6, index);
						anchor.setAnchorType(3);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, Workbook.PICTURE_TYPE_PNG));
					} else if(value instanceof Calendar){
						Calendar cale = Calendar.getInstance();  
						Date tasktime = cale.getTime();  
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						textValue = df.format(tasktime);
					} else{
						/** 其它数据类型都当作字符串简单处理 **/
						textValue = value.toString();
					}
					/** 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成 **/
					if(textValue!=null){
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
						Matcher matcher = p.matcher(textValue);
						if(matcher.matches()){
							/** 是数字当作double处理 **/
							cell.setCellValue(Double.parseDouble(textValue));
						}else{
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					//清理资源
				}
			}
		}
		
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
