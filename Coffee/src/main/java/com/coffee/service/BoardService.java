package com.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.entity.board.TbrdBrdBas;
import com.coffee.mapper.BoardMapper;
import com.coffee.util.EntityUtil;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	/*
	 * 게시판 목록 조회 
	 */
	public List<TbrdBrdBas> selectBoardList(String brdType) {
		
		return boardMapper.selectBoardList(brdType);
	}
	
	/*
	 * 게시글 클릭시 해당 게시글 조회수 +1
	 */
	public void updateBrdViewCnt(Long brdNo) {
		boardMapper.updateBrdViewCnt(brdNo);
	}

	/*
	 * 게시판 상세 
	 */
	public TbrdBrdBas selectBoardDetail(Long brdNo) {
		// 조회수 증가
		updateBrdViewCnt(brdNo);
		return boardMapper.selectBoardDetail(brdNo);
	}

}
