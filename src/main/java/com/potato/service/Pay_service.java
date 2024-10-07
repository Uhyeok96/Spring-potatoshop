package com.potato.service;

import com.potato.domain.MemberVO;
import com.potato.domain.PayVO;

public interface Pay_service {
	
	// 충전 기능
    boolean chargePay(PayVO payVO);
    
    // 포인트를 머니로 전환
    boolean convertPointsToPay(String member_number, long point);
    
    //채팅방의 board_number를 이용해 해당 상품 가격 가져오기
    int getProductPrice(String board_number);
    
    // 결제 처리를 위한 메서드
    boolean processPayment(String buyer_number, String seller_number, int price);
    
    // 거래완료 후 매너칭찬 설문시 포인트 적립
    void addPoints(String member_number, long point);
    
    // 환불 기능
    boolean processRefund(PayVO payVO); // 환불 처리
    
    // 잔액 조회 메서드
    long getBalance(String member_number); 
}
