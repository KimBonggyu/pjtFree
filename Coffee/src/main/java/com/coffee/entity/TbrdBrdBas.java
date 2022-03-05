package com.coffee.entity;

import lombok.Data;

@Data
public class TbrdBrdBas {

	private Long brdNo;			// 게시글 번호
	private String title;		// 제목
	private String content;		// 내용
	private String writer;		// 작성
	private Long viewCnt;		// 조회
	private String noticeYn;	// 공지글 여부
	private String secretYn;	// 비밀글 여부
	private String useYn;		// 사용 여부
	private String delYn;		// 삭제 여부
	private String regNo;		// 작성자 번호
	private String regUrl;		// 작성 URL
	private String regDts;		// 작성일
	private String modNo;		// 수정자 번호
	private String modUrl;		// 수정 URL
	private String modDts;		// 수정일
	private String delDts;		// 삭제일
	
}
