package com.multi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.biz.Detail_SchedulesBiz;
import com.multi.biz.GenreBiz;
import com.multi.biz.MovieBiz;
import com.multi.biz.SchedulesBiz;
import com.multi.vo.Detail_SchedulesVO;
import com.multi.vo.GenreVO;
import com.multi.vo.MovieVO;
import com.multi.vo.SchedulesVO;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	SchedulesBiz sbiz;
	@Autowired
	MovieBiz mbiz;
	@Autowired
	Detail_SchedulesBiz dsbiz;
	
	@RequestMapping("/add")
	public String add(Model m) {
		List<SchedulesVO> slist = null;
		List<MovieVO> mlist = null;
		try {
			slist = sbiz.get();
			mlist = mbiz.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "schedule/add");
		m.addAttribute("slist", slist);
		m.addAttribute("mlist", mlist);
		return "index";
	}
	@RequestMapping("/adddetail")
	public String adddetail(Model m, SchedulesVO schedule) {
		Integer sid = null;
		try {
			sbiz.register(schedule);
			sid = schedule.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("center", "schedule/adddetail");
		m.addAttribute("sid", sid);
		return "index";
	}
	@RequestMapping("addimpl")
	public String addimpl(Model m, @RequestParam List<String> check, Integer id) {
		Detail_SchedulesVO ds1 = new Detail_SchedulesVO(id, 1,"13:00:00" , "15:00:00");
		Detail_SchedulesVO ds2 = new Detail_SchedulesVO(id, 2,"16:00:00" , "18:00:00");
		Detail_SchedulesVO ds3 = new Detail_SchedulesVO(id, 3,"19:00:00" , "21:00:00");
		for (String string : check) {
			if (string.equals("1")) {
				try {
					dsbiz.register(ds1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (string.equals("2")) {
				try {
					dsbiz.register(ds2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (string.equals("3")) {
				try {
					dsbiz.register(ds3);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/schedule/list";
	}
	@RequestMapping("/list")
	public String list(Model m, Integer mnum) {
		int page = 0;
		List<SchedulesVO> list = null;
		try {
			if (mnum != null) {
				page= mnum;
				list = sbiz.selectallpage(page);
			} else if(mnum == null) {
				list = sbiz.selectallpage(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		m.addAttribute("slist", list);
		m.addAttribute("mnum", page);
		m.addAttribute("center", "schedule/list");
		return "index";
	}
	
}