package com.coffee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coffee.entity.TbrdBrdBas;
import com.coffee.mapper.BoardMapper;

@SpringBootTest
public class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsertBoard() {
		TbrdBrdBas brdBas = new TbrdBrdBas();
		
		brdBas.setTitle("title111");
		brdBas.setContent("content111");
		brdBas.setWriter("writer111");
		
		Long result = boardMapper.insertBoard(brdBas);
		
		System.out.println("result ::: "+brdBas.getBrdNo());
		
	}
	
}
