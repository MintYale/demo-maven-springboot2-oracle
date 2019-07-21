package com.alipay.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pay")
public class AlipayController {

	@RequestMapping("/index")
	public String payIndex() {
		return "index";
	}

}
