package com.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coffee.entity.board.TbrdBrdBas;
import com.coffee.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/*
	 * 고객센터 목록
	 */
	@GetMapping("/customer")
	public String customer() {
		
		return "/page/board/customer";
	}
	
	/*
	 * 고객센터 > 선택 카테고리 목록
	 */
	@GetMapping("/{brdType}")
	public ModelAndView selectBoardList(@PathVariable("brdType") String brdType, ModelAndView mv) {
		
		List<TbrdBrdBas> brdBasList = boardService.selectBoardList(brdType);
		
		mv.addObject("pg", brdBasList);
		mv.setViewName("page/board/" + brdType + "/" + brdType);
		
		return mv;
	}
	
	/*
	 * 고객센터 > 선택 카테고리 목록 > 상세 
	 */
	@GetMapping("/{brdType}/{brdNo}")
	public ModelAndView selectBoard(@PathVariable("brdType") String brdType, @PathVariable("brdNo") String brdNo, ModelAndView mv) {
		
		TbrdBrdBas brdBas = boardService.selectBoardDetail(Long.parseLong(brdNo));
		
		mv.addObject("brdDto", brdBas);
		mv.setViewName("page/board/" + brdType + "/" + brdType + "detail");
		
		return mv;
	}
	
	
}
