package com.potato.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.potato.domain.MannerVO;
import com.potato.mapper.Manner_mapper;

@Service
public class Manner_service_Impl implements Manner_service{
	
	@Autowired
    private Manner_mapper manner_mapper;
	
	
	@Override // 칭찬하기 데이터 전송
    public int submitManner(MannerVO manner) {
		return manner_mapper.insertManner(manner);
    }
	
	@Override // 회원별 매너칭찬 리스트 출력
    public List<MannerVO> getAllMannerItems(String member_number) {
        return manner_mapper.selectMannerItems(member_number);
    }

}
