package com.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coffee.entity.TbrdBrdBas;
import com.coffee.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/customer")
	public String customer() {
		
		return "/page/board/customer";
	}
	
	@GetMapping("/{brdType}")
	public ModelAndView selectBoardList(@PathVariable("brdType") String brdType, ModelAndView mv) {
		
		List<TbrdBrdBas> brdBasList = boardService.selectBoardList(brdType);
		
		mv.addObject("pg", brdBasList);
		mv.setViewName("/page/board/notice");
		
		return mv;
	}
	
}
