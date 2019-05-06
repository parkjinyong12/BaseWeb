package com.ruokit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/test.do")
	public String getTestPage(Model m) {
		m.addAttribute("context", "mission complete");
		return "test";
	}
}
