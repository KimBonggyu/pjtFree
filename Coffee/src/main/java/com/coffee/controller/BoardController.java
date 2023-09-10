package com.coffee.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String getCustomer() {
		
		return "/page/board/customer";
	}
	
	/*
	 * 고객센터 > 선택 카테고리 목록
	 */
	@GetMapping("/{brdType}")
	public ModelAndView getSelectBoardList(@PathVariable("brdType") String brdType, ModelAndView mv) {
		
		List<TbrdBrdBas> brdBasList = boardService.selectBoardList(brdType);
		
		mv.addObject("pg", brdBasList);
		mv.setViewName("page/board/" + brdType + "/" + brdType);
		
		return mv;
	}
	
	/*
	 * 고객센터 > 선택 카테고리 목록 > 상세 
	 */
	@GetMapping("/{brdType}/{brdNo}")
	public ModelAndView getSelectBoard(@PathVariable("brdType") String brdType, @PathVariable("brdNo") Long brdNo, ModelAndView mv) {
		
		boardService.updateBrdViewCnt(brdNo);						// 조회수 증가
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
	public ModelAndView getModifyBoard(@PathVariable("brdType") String brdType, @PathVariable(name="brdNo", required=false) Long brdNo, ModelAndView mv) {
		
		TbrdBrdBas brdBas = new TbrdBrdBas();
		if(null != brdNo) {
			brdBas = boardService.selectBoardDetail(brdNo);
		}
		
		System.out.println("getModifyBoard : " + brdBas);
		
		mv.addObject("brdDto", brdBas);
		mv.addObject("brdType", brdType);
		mv.setViewName("page/board/" + brdType + "/" + brdType + "modify");
		
		return mv;
	}
	
	/*
	 * 고객센터 > 선택 카테고리 작성/수정 화면
	 */
	/*
	@ResponseBody
	@PostMapping(value = {"/{brdType}/modify", "/{brdType}/modify/{brdNo}"})
	public HashMap<String, String> postModifyBoard(@PathVariable("brdType") String brdType, TbrdBrdBas brdBas, ModelAndView mv) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("rsltCd", "success");
		long brdNo = 0;
		try {
			if(null != brdBas.getBrdNo()) {
				brdNo = brdBas.getBrdNo();
				System.out.println("postModifyBoard : " + brdBas);
				boardService.modifyBrdBas(brdBas);
				result.put("brdNo", String.valueOf(brdNo));
			}else {
			}
		} catch (Exception e) {
			throw new Exception();
		}
		System.out.println(result);
		return result;
	}
	*/
	
	/*
	 * 고객센터 > 선택 카테고리 작성/수정 화면
	 */
//	@ResponseBody
	@PostMapping(value = {"/{brdType}/modify", "/{brdType}/modify/{brdNo}"})
	public String postModifyBoard(@PathVariable("brdType") String brdType, TbrdBrdBas brdBas, ModelAndView mv) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			if(null != brdBas.getBrdNo()) {
				System.out.println("postModifyBoard : " + brdBas);
				boardService.modifyBrdBas(brdBas);
			}else {
				System.out.println("postModifyBoard : " + brdBas);
				boardService.insertBrdBas(brdBas);
			}
		} catch (Exception e) {
			throw new Exception();
		}
		return "redirect:/board/" + brdType + "/" + brdBas.getBrdNo();
	}
}
