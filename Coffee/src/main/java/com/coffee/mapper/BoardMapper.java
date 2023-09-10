package com.coffee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.coffee.entity.board.TbrdBrdBas;

@Mapper
public interface BoardMapper {
	
	public List<TbrdBrdBas> selectBoardList(String brdType);

	public TbrdBrdBas selectBoardDetail(Long brdNo);

	public void updateBrdViewCnt(Long brdNo);

	public void modifyBrdBas(TbrdBrdBas brdBas);

	public long insertBrdBas(TbrdBrdBas brdBas);

}
