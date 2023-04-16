package com.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView selectBoard(@PathVariable("brdType") String brdType, @PathVariable("brdNo") Long brdNo, ModelAndView mv) {
		
		boardService.updateBrdViewCnt(brdNo);		// 조회수 증가
		TbrdBrdBas brdBas = boardService.selectBoardDetail(brdNo);	// 게시글 정보
		
		mv.addObject("brdDto", brdBas);
		mv.addObject("brdType", brdType);
		mv.setViewName("page/board/" + brdType + "/" + brdType + "detail");
		
		return mv;
	}
	
	/*
	 * 고객센터 > 선택 카테고리 작성/수정 화면
	 */
	@GetMapping(value = {"/{brdType}/modify", "/{brdType}/modify/{brdNo}"})
	public ModelAndView modifyBoard(@PathVariable("brdType") String brdType, @PathVariable(name="brdNo", required=false) Long brdNo, ModelAndView mv) {
		
		TbrdBrdBas brdBas = new TbrdBrdBas();
		if(null != brdNo) {
			brdBas = boardService.selectBoardDetail(brdNo);
		}
		
		mv.addObject("brdDto", brdBas);
		mv.setViewName("page/board/" + brdType + "/" + brdType + "detail");
		
		return mv;
	}
}
