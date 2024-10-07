package com.potato.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import com.potato.domain.MemberVO;
import com.potato.domain.PayVO;
import com.potato.service.Pay_service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/pay")
@Log4j2
@AllArgsConstructor
public class Rest_pay_controller {
	
	@Autowired
    private Pay_service pay_service;

    // 충전 요청 처리
    @PostMapping(value = "/charge", produces = "application/json")
    public ResponseEntity<Map<String, String>> charge(@RequestBody PayVO payVO) {
    	Ulid ulid = UlidCreator.getUlid();
		payVO.setId(ulid.toString());
    	
        boolean isSuccess = pay_service.chargePay(payVO); // 충전 서비스 호출

        Map<String, String> response = new HashMap<>();
        if (isSuccess) {
            response.put("message", "충전이 완료되었습니다."); // 성공 메시지
        } else {
            response.put("message", "충전 실패."); // 실패 메시지
        }
        
        return ResponseEntity.ok(response); // JSON 형태로 응답
    }
    
    // 포인트를 머니로 전환
    @PostMapping(value = "/convertPoints", produces = "application/json; charset=UTF-8")
    @ResponseBody // 추가: 이 어노테이션을 사용하여 JSON 응답을 보장합니다.
    public ResponseEntity<String> convertPoints(@RequestBody Map<String, Object> request) {
        String member_number = (String) request.get("member_number");
        long point = Long.parseLong(request.get("point").toString());

        boolean isSuccess = pay_service.convertPointsToPay(member_number, point);
        if (isSuccess) {
            return ResponseEntity.ok("{\"message\": \"포인트가 페이로 전환되었습니다.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"포인트 전환 실패.\"}");
        }
    }
    
    //채팅방의 board_number를 이용해 해당 상품 가격 가져오기
    @GetMapping(value = "/getPrice", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getPrice(@RequestParam String board_number) {
        int price = pay_service.getProductPrice(board_number);

        Map<String, Object> response = new HashMap<>();
        response.put("price", price);

        return ResponseEntity.ok(response);
    }
    
    // 결제 요청을 처리하는 메서드
    @PostMapping("/processPayment")
    public ResponseEntity<Map<String, String>> processPayment(@RequestBody Map<String, String> params) {
        String buyer_number = params.get("buyer_number");
        String seller_number = params.get("seller_number");
        int price = Integer.parseInt(params.get("price"));

        boolean success = pay_service.processPayment(buyer_number, seller_number, price);

        Map<String, String> response = new HashMap<>();
        response.put("status", success ? "success" : "failure");

        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_JSON) // JSON 응답 보장
                             .body(response);
    }
    
    // 거래완료 후 매너칭찬 설문시 포인트 적립
    @PostMapping("/addPoints")
    public ResponseEntity<String> addPoints(@RequestBody Map<String, Object> request) {
        String member_number = (String) request.get("member_number");
        long point = ((Number) request.get("point")).longValue();

        pay_service.addPoints(member_number, point);

        // JSON 응답을 명시적으로 UTF-8로 설정
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON) // 기본적으로 UTF-8로 설정됨
                .body("{\"message\": \"포인트가 적립되었습니다.\"}");
    }
    
    // 환불 요청 처리
    @PostMapping(value = "/refund", produces = "application/json")
    public ResponseEntity<Map<String, String>> refund(@RequestBody PayVO refundVO) {
        // ULID 생성 및 ID 설정
        Ulid ulid = UlidCreator.getUlid();
        refundVO.setId(ulid.toString());

        // 환불 처리
        boolean isSuccess = pay_service.processRefund(refundVO);
        
        if (isSuccess) {
            return ResponseEntity.ok(Map.of("message", "환불이 완료되었습니다.")); // 성공 메시지
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "환불 처리 실패."));
        }
    }
    
    // 잔액 조회
    @GetMapping(value = "/getBalance", produces = "application/json")
    public ResponseEntity<Long> getBalance(@RequestParam String member_number) {
        long balance = pay_service.getBalance(member_number); // 서비스에서 잔액 조회
        return ResponseEntity.ok(balance);
    }
}
