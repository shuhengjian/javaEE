/**   
 * 功能描述：
 * @Package: com.creatorblue.cbitedu.ty.back.controller 
 * @author: shj 
 * @date: 2019年2月22日 上午10:37:35 
 */
package com.creatorblue.cbitedu.ty.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.ty.domain.TtyAdvertising;
import com.creatorblue.cbitedu.ty.front.service.TtyAdvertisingService;

/**
 * @ClassName: TtyAdvertisingController.java
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: shj
 * @date: 2019年2月22日 上午10:37:35
 */
@Controller
@RequestMapping("/ttyAdvertisingController.do")
public class TtyAdvertisingController extends HihBaseController{
}
