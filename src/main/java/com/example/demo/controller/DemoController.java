package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@RequestMapping("/index")
	public String index() {
		return "<h1>This is a demo/中文是否乱码测试_01...</h1>";
	}
}
