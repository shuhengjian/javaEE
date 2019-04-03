package com.creatorblue.cbitedu.core.plugins.poi.excel;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.creatorblue.cbitedu.core.plugins.poi.excel.entity.ExportParams;
import com.creatorblue.cbitedu.core.plugins.poi.excel.entity.TemplateExportParams;
import com.creatorblue.cbitedu.core.plugins.poi.excel.export.ExcelExportServer;
import com.creatorblue.cbitedu.core.plugins.poi.excel.export.template.ExcelExportOfTemplateUtil;

/**
 * excel 导出工具类
 * 
 * @author jueyue
 * @version 1.0
 * @date 2013-10-17
 */
public final class ExcelExportUtil {

	/**
	 * 一个excel 创建多个sheet
	 * 
	 * @param list
	 *            多个Map key title 对应表格Title key entity 对应表格对应实体 key data
	 *            Collection 数据
	 * @return
	 */
	public static HSSFWorkbook exportExcel(List<Map<String, Object>> list) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		ExcelExportServer server = new ExcelExportServer();
		for (Map<String, Object> map : list) {
			server.createSheet(workbook,
					(ExportParams) map.get("title"),
					(Class<?>) map.get("entity"),
					(Collection<?>) map.get("data"));
		}
		return workbook;
	}

	/**
	 * @param entity
	 *            表格标题属性
	 * @param pojoClass
	 *            Excel对象Class
	 * @param dataSet
	 *            Excel对象数据List
	 */
	public static HSSFWorkbook exportExcel(ExportParams entity,
			Class<?> pojoClass, Collection<?> dataSet) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		new ExcelExportServer().createSheet(workbook, entity, pojoClass, dataSet);
		return workbook;
	}
	/**
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param entity
	 *            表格标题属性
	 * @param pojoClass
	 *            Excel对象Class
	 * @param dataSet
	 *            Excel对象数据List
	 * @param column
	 *            Excel对象自定义列
	 */
	public static void exportExcel(HttpServletRequest request,
			HttpServletResponse response,ExportParams entity,
			Class<?> pojoClass, Collection<?> dataSet) {
		//前台显示列
		String column=request.getParameter("column");
		//excel准备导出列
		ArrayList<String> column_list = new ArrayList<String>();
		if(column!=null && !column.equals("")){
			String[] columns=column.split(",");
			for(int i=0;i<columns.length;i++)
				column_list.add(columns[i]);
		}
		//创建Excel
		HSSFWorkbook workbook = new HSSFWorkbook();		
		if(column_list!=null)
			new ExcelExportServer().createSheet(workbook, entity, pojoClass, dataSet,column_list);//自定义导出
		else
			new ExcelExportServer().createSheet(workbook, entity, pojoClass, dataSet);//普通导出
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					.concat(String.valueOf(URLEncoder.encode(entity.getName()+".xls", "UTF-8"))));
			response.setHeader("Connection", "close");
			response.setHeader("Content-Type", "application/vnd.ms-excel");
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出文件通过模板解析
	 * 
	 * @param params
	 *            导出参数类
	 * @param pojoClass
	 *            对应实体
	 * @param dataSet
	 *            实体集合
	 * @param map
	 *            模板集合
	 * @return
	 */
	public static Workbook exportExcel(TemplateExportParams params,
			Class<?> pojoClass, Collection<?> dataSet, Map<String, Object> map) {
		return new ExcelExportOfTemplateUtil().createExcleByTemplate(params,
				pojoClass, dataSet, map);
	}

	/**
	 * 导出文件通过模板解析只有模板,没有集合
	 * 
	 * @param params
	 *            导出参数类
	 * @param map
	 *            模板集合
	 * @return
	 */
	public static Workbook exportExcel(TemplateExportParams params,
			Map<String, Object> map) {
		return new ExcelExportOfTemplateUtil().createExcleByTemplate(params,
				null, null, map);
	}

	
}
