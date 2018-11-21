package com.usermanager.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BadRequestController implements org.springframework.boot.web.servlet.error.ErrorController{

	private static final String PATH="/error";
	
	@RequestMapping(value=PATH,method=RequestMethod.GET)
	public String defaultErrorMessage(){
		return "Resource is not found, contact admin!!!";
	}
	@Override
	public String getErrorPath() {
		return PATH;
	}
}
