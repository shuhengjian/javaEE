package com.musicbar.second.comm.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	@ResponseBody
	public String handlerBindException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("author", "Magical Sam");
	}
}
