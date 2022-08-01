package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.biz.GenreBiz;
import com.multi.biz.MovieBiz;
import com.multi.frame.Util;
import com.multi.vo.CustVO;
import com.multi.vo.GenreVO;
import com.multi.vo.MovieVO;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@Value("${admindir}")
	String admindir;
	
	@Value("${userdir}")
	String userdir;
	
	@Autowired
	MovieBiz mbiz;
	@Autowired
	GenreBiz gbiz;
	
	@RequestMapping("/add")
	public String add(Model m) {
		List<GenreVO> glist = null;
		try {
			glist = gbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "movie/add");
		m.addAttribute("glist", glist);
		return "index";
	}
	@RequestMapping("/addimpl")
	public String addimpl(Model m, MovieVO obj) {
		String imgname1 = obj.getMf1().getOriginalFilename();
		String imaname2 = obj.getMf2().getOriginalFilename();
		
		obj.setPosterimg1(imgname1);
		obj.setPosterimg2(imaname2);
		try {
			mbiz.register(obj);
			Util.saveFile(obj.getMf1(),admindir,userdir);
			Util.saveFile(obj.getMf2(),admindir,userdir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "movie/addimpl");
		m.addAttribute("movie", obj);
		return "index";
	}
	@RequestMapping("/list")
	public String list(Model m) {
		List<MovieVO> mlist = null;
		try {
			mlist = mbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "movie/list");
		m.addAttribute("mlist", mlist);
		return "index";
	}
	
	
}