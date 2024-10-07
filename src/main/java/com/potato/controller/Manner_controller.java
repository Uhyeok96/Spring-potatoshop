package com.potato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import com.potato.domain.MannerVO;
import com.potato.service.Manner_service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/manner") // API URL: http://localhost:80/manner/???
@Log4j2
@AllArgsConstructor // 생성자 주입
public class Manner_controller {
	
	@Autowired
    private Manner_service manner_service;
    
    // 칭찬하기 데이터 전송
    @PostMapping(value = "/submit", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE}) // 입력값은 JSON으로
    public ResponseEntity<String> create(@RequestBody MannerVO manner) {
        log.info("MannerVO 객체 입력 값: " + manner); // 파라미터로 넘어온 값 출력 테스트
        
        // 칭찬 번호 생성
        Ulid ulid = UlidCreator.getUlid();
		manner.setId(ulid.toString());

        int insertCount = manner_service.submitManner(manner); // 서비스 호출하여 SQL 처리
        
        log.info("서비스+매퍼 처리 결과: " + insertCount);
        
        return insertCount == 1 
            ? new ResponseEntity<>("success", HttpStatus.OK) // 200 정상
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 서버 오류
    }
    
    // 회원별 매너칭찬 리스트 출력
    @GetMapping("/items/{member_number}")
    public ResponseEntity<List<MannerVO>> getMannerItems(@PathVariable String member_number) {
        List<MannerVO> mannerList = manner_service.getAllMannerItems(member_number);
        return new ResponseEntity<>(mannerList, HttpStatus.OK);
    }
}
