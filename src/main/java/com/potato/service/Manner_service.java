package com.potato.service;

import java.util.List;

import com.potato.domain.MannerVO;

public interface Manner_service {
	
	// 칭찬하기 데이터 전송
	int submitManner(MannerVO manner);
	
	// 회원별 매너칭찬 리스트 출력
	List<MannerVO> getAllMannerItems(String member_number);
}
