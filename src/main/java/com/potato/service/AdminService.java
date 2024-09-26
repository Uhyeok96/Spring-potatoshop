package com.potato.service;

import java.util.List;

import com.potato.domain.BoardVO;
import com.potato.domain.MemberVO;
import com.potato.domain.ReplyVO;
import com.potato.domain.ReportVO;
import com.potato.domain.NotificationVO;

public interface AdminService {

	
	   // 관리자 정보 확인
 public MemberVO getList(String id);

 // 회원 정보 검색
 public MemberVO getMemberList(String id);

 // 회원등급 등록
 public void updateBlack(MemberVO memberVO);

 // 공지사항 추가
 public void insertBoard(BoardVO board);

 // 게시글 삭제
 public void deleteReport(BoardVO boardVO);

 // 댓글 삭제
 public void replyDelete(ReplyVO replyVO);

 // 신고 내역 확인
 public List<ReportVO> readReport();

 // 신고 처리
 public void updateReport(ReportVO report);
 
 // 블랙리스트 보기
 public List<MemberVO> viewBlack();
 
 public ReportVO get_report(String report_number);
 
 // 블랙리스트 해제
 public void clearBlack(String member_number);
 
 // 관리자 공지 확인
 public List<NotificationVO> notification();
	
}
