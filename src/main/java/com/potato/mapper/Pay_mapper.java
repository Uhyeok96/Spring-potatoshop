package com.potato.mapper;

import org.apache.ibatis.annotations.Param;

import com.potato.domain.MemberVO;
import com.potato.domain.PayVO;

public interface Pay_mapper {
	
	// 충전 정보 삽입
    void chargePay(PayVO payVO);

    // 송금하는 회원의 가상머니 증가
    void incrementPay(@Param("from_member_number") String from_member_number, @Param("pay_amount") long pay_amount);
    
    // 포인트를 머니로 전환
    int convertPointsToPay(@Param("member_number") String member_number, @Param("point") long point);
    
    // 채팅방의 board_number를 이용해 해당 상품 가격 가져오기
    int findPriceByBoardNumber(@Param("board_number") String board_number);
    
    // 회원 ID로 회원 정보를 조회
    MemberVO getMemberById(String member_number);
    
    // 회원 정보를 업데이트
    void updateMember(MemberVO member);
    
    // 거래완료 후 매너칭찬 설문시 포인트 적립
    int addPoints(@Param("member_number") String member_number, @Param("point") long point);
    
    // 환불 정보 삽입
    void insertRefund(PayVO payVO);

    // 환불하는 회원의 가상머니 차감
    void decrementPay(@Param("member_number") String memberNumber, @Param("pay_amount") long payAmount); // 가상머니 차감
}
