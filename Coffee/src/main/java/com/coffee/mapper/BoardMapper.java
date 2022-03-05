package com.coffee.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.coffee.entity.TbrdBrdBas;

@Mapper
public interface BoardMapper {
	
	public Long insertBoard(TbrdBrdBas brdBas);

}
