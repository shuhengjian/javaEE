package com.creatorblue.cbitedu.core.plugins.poi.excel.view;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.AbstractView;

import com.creatorblue.cbitedu.core.plugins.poi.excel.ExcelExportUtil;
import com.creatorblue.cbitedu.core.plugins.poi.excel.entity.TemplateExportParams;
import com.creatorblue.cbitedu.core.plugins.poi.excel.entity.vo.NormalExcelConstants;
import com.creatorblue.cbitedu.core.plugins.poi.excel.entity.vo.TemplateExcelConstants;

/**
 * Excel模板视图
 * 
 * @author JueYue
 * @date 2014年6月30日 下午9:15:49
 */
@SuppressWarnings("unchecked")
public class JeecgTemplateExcelView extends AbstractView {

	private static final String CONTENT_TYPE = "application/vnd.ms-excel";

	public JeecgTemplateExcelView() {
		setContentType(CONTENT_TYPE);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String codedFileName = "临时文件.xls";
		if (model.containsKey(NormalExcelConstants.FILE_NAME)) {
			codedFileName = (String) model.get(NormalExcelConstants.FILE_NAME)
					+ ".xls";
		}
		response.setHeader("content-disposition", "attachment;filename="
				+ new String(codedFileName.getBytes(), "iso8859-1"));
		Workbook workbook = ExcelExportUtil.exportExcel(
				(TemplateExportParams)model.get(TemplateExcelConstants.PARAMS),
				(Class<?>) model.get(TemplateExcelConstants.CLASS),
				(List<?>)model.get(TemplateExcelConstants.LIST_DATA),
				(Map<String, Object>)model.get(TemplateExcelConstants.MAP_DATA));
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
	}
}
