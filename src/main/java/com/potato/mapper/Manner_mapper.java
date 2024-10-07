package com.potato.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.potato.domain.MannerVO;

@Mapper
public interface Manner_mapper {

	// 칭찬하기 데이터 전송
	int insertManner(MannerVO manner);
	
	// 회원별 매너칭찬 리스트 출력
	List<MannerVO> selectMannerItems(String member_number);
}
