package com.creatorblue.cbitedu.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.core.utils.StringPrintWriter;
public class HihSoftHandlerException implements HandlerExceptionResolver {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(HihSoftHandlerException.class);
	// 业务逻辑层异常的处理
	/** The service_exception. */
	private String service_exception = null;
	// WEB控制层异常统一处理
	/** The controller_exception. */
	private String controller_exception = null;
	// 自定义异常处理
	/** The hihsoft_exception. */
	private String hihsoft_exception = null;
	private String error = null;

	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Sets the service_exception.
	 *
	 * @param service_exception the new service_exception
	 */
	public void setService_exception(String service_exception) {
		this.service_exception = service_exception;
	}

	/**
	 * Sets the controller_exception.
	 *
	 * @param controller_exception the new controller_exception
	 */
	public void setController_exception(String controller_exception) {
		this.controller_exception = controller_exception;
	}

	/**
	 * Sets the hihsoft_exception.
	 *
	 * @param hihsoft_exception the new hihsoft_exception
	 */
	public void sethihsoft_exception(String hihsoft_exception) {
		this.hihsoft_exception = hihsoft_exception;
	}

	/**
	 * 处理异常情况
	 */
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse resonpnse, Object object, Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringPrintWriter strintPrintWriter = new StringPrintWriter();
		if (ex instanceof ServiceException) {
			log.info("服务端异常");
			ex.printStackTrace(strintPrintWriter);
			map.put("errorMsg", strintPrintWriter.getString());// 将错误信息传递给view
			return new ModelAndView("common/error", map);

		} else if (ex instanceof ControllerException) {
			log.info("控制层异常");
			map.put("errorMsg", strintPrintWriter.getString());// 将错误信息传递给view
			return new ModelAndView("common/error", map);
		} else 
		{log.info("平台自定义异常");
			ex.printStackTrace(strintPrintWriter);
			map.put("errorMsg", strintPrintWriter.getString());// 将错误信息传递给view
			return new ModelAndView("common/error", map);
	}
}

}
