package com.potato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.potato.domain.MemberVO;
import com.potato.domain.PayVO;
import com.potato.mapper.Pay_mapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Pay_service_impl implements Pay_service {
	
	@Autowired
    private Pay_mapper pay_mapper;
	
	// 머니 충전
    @Override
    public boolean chargePay(PayVO payVO) {
        try {
            // 충전 정보 저장
        	pay_mapper.chargePay(payVO);
            // 송금하는 회원의 가상머니 증가
        	pay_mapper.incrementPay(payVO.getFrom_member_number(), payVO.getPay_amount());
            return true; // 성공
        } catch (Exception e) {
            // 예외 발생 시 로깅 등 처리
            return false; // 실패
        }
    }
    
    // 포인트를 머니로 전환
    @Override
    public boolean convertPointsToPay(String member_number, long point) {
        // 필요한 로직 추가
        int rowsAffected = pay_mapper.convertPointsToPay(member_number, point);
        return rowsAffected > 0; // 성공적으로 업데이트되었는지 여부 반환
    }
    
    //채팅방의 board_number를 이용해 해당 상품 가격 가져오기
    @Override
    public int getProductPrice(String board_number) {
        return pay_mapper.findPriceByBoardNumber(board_number);
    }
    
    // 결제
    @Override
    public boolean processPayment(String buyer_number, String seller_number, int price) {
        // 구매자와 판매자 정보를 조회
        MemberVO buyer = pay_mapper.getMemberById(buyer_number);
        MemberVO seller = pay_mapper.getMemberById(seller_number);

        // 구매자의 잔액이 충분한지 확인
        if (buyer.getPay() >= price) {
            // 구매자의 잔액 차감
            buyer.setPay(buyer.getPay() - price);
            // 판매자의 잔액 추가
            seller.setPay(seller.getPay() + price);
            
            // 변경사항을 데이터베이스에 저장
            pay_mapper.updateMember(buyer);
            pay_mapper.updateMember(seller);
            return true; // 결제 성공
        }
        return false; // 잔액 부족으로 결제 실패
    }
    
    // 거래완료 후 매너칭찬 설문시 포인트 적립
    @Override
    public void addPoints(String member_number, long point) {
        int rowsAffected = pay_mapper.addPoints(member_number, point);
        if (rowsAffected <= 0) {
            // 로깅 또는 예외 처리
            log.error("포인트 적립 실패: 회원 번호 = " + member_number);
        }
    }
    
    // 환불 기능
    @Override
    public boolean processRefund(PayVO payVO) {
        try {
            // 환불 정보 삽입
            pay_mapper.insertRefund(payVO);
            // 가상머니 차감
            pay_mapper.decrementPay(payVO.getFrom_member_number(), payVO.getPay_amount()); // pay_amount가 아닌, 개별 파라미터로 전달
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 예외 발생 시 false 반환
        }
    }
    
    // 잔액조회
    @Override
    public long getBalance(String member_number) {
        MemberVO memberVO = pay_mapper.getMemberById(member_number);
        return memberVO != null ? memberVO.getPay() : 0; // 잔액 반환
    }
}
