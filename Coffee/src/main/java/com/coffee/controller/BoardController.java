package com.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coffee.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/test")
	public ModelAndView test() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("name", "BongKyu");
		mv.addObject("phone", "010-1111-2222");
		mv.setViewName("/page/board/test");
		
		return mv;
	}
	
}
