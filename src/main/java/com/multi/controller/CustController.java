package com.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@Controller
@RequestMapping("/cust")
public class CustController {

	@Autowired
	CustBiz cbiz;
	
	@RequestMapping("/add")
	public String add(Model m) {
		m.addAttribute("center", "cust/add");
		return "index";
	}
	@RequestMapping("/addimpl")
	public String addimpl(Model m, CustVO cust) {
		
		try {
			cbiz.register(cust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "cust/addimpl");
		m.addAttribute("cust", cust);
		return "index";
	}
	
	
}