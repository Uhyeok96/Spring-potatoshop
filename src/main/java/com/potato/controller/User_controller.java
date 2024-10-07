package com.potato.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato.domain.UserVO;
import com.potato.service.ReplyService;
import com.potato.service.User_service;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class User_controller {
	
	@Autowired
    private User_service user_service;
	
	
	// 설문 후 판매자 온도 변경
//    @PostMapping("/updateTemperature")
//    public ResponseEntity<Void> updateTemperature(@RequestBody UserVO userVO) {
//        // UserVO는 sellerNumber와 temperatureChange를 포함해야 합니다.
//        Long temperatureChange = userVO.getTemper(); // 변경할 온도 값
//        String user_number = userVO.getUser_number(); // 판매자 번호
//        
//        user_service.updateUserTemperature(user_number, temperatureChange);
//        return ResponseEntity.ok().build();
//    }
    
	// 설문 후 판매자 온도 변경
	@PostMapping("/updateTemperature")
	public ResponseEntity<Void> updateTemperature(@RequestBody Map<String, Object> payload) {
	    String user_number = (String) payload.get("user_number"); // 판매자 번호
	    Long temperatureChange = ((Number) payload.get("temperatureChange")).longValue(); // 변경할 온도 값

	    user_service.updateUserTemperature(user_number, temperatureChange);
	    return ResponseEntity.ok().build();
	}
}
