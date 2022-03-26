package com.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffee.entity.TbrdBrdBas;
import com.coffee.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	public List<TbrdBrdBas> selectBoardList(String brdType) {
		
		return boardMapper.selectBoardList(brdType);
	}

}
