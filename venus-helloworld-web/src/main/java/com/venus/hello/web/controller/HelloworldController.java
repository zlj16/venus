package com.venus.hello.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.meidusa.fastjson.JSON;
import com.meidusa.venus.hello.api.HelloService;


@Controller
public class HelloworldController {

	@Resource
	private HelloService helloService;

	@RequestMapping(value = "hello.htm")
	public @ResponseBody
	String getHello(HttpServletRequest req) {
		return JSON.toJSONString(helloService.getHello(req.getParameter("name")));
	}

}
