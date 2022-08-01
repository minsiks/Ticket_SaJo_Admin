package com.multi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.biz.CustBiz;
import com.multi.vo.CustVO;

@RestController
public class AJAXController {
	
	@Autowired
	CustBiz cbiz;
	
	@RequestMapping("/cust/checkid")
	public String checkid(String id) {
		String result = "";
		CustVO c = null;
		
		try {
			c = cbiz.get(id);
			if(c == null) {
				result="0";
			}else {
				result="1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
