package com.potato.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.potato.domain.BoardVO;
import com.potato.domain.CartVO;
import com.potato.domain.Criteria;

@Mapper
public interface BoardMapper {
	
	//완 완
	public List<BoardVO> getList();
	
	// 페이징 처리 리스트 출력
	public List<BoardVO> getMoreList(Criteria cri);
	
	// 게시물의 전체 개수 구함
	public int getTotalCount(Criteria cri);

	//완 완
	public void insert(BoardVO board);
	
	//완 완
	public BoardVO read(String board_number);

	//완 완
	public int update(BoardVO vo);

	//완 완
	public int delete(String board_number);
	
	// 완
	public List<BoardVO> search(String title);

	
	//완
	public int updateLikes(String board_number);

	//완
	public int updateInterest(String board_number);

	//완
	public int cancelLikes(String board_number);

	//완
	public int cancelInterest(String board_number);

	//완
	public int updateViews(String board_number);
	
	public int cancelViews(String board_number);
	
	// 좋아요, 관심 버튼 장바구니
	

	//완
	public int insertLike(CartVO cart);

	//완
	public int insertInterest(CartVO cart);
	
	//좋아요 , 관심 둘 다 누른경우
	public int insert_both(CartVO cart);

	//2개 다 누른경우 취소
	public int cancelLike(CartVO cart);

	//2개 다 누른경우 취소
	public int cancelInterest2(CartVO cart);
	
	// 1개 취소
	public int cancel_both(CartVO cart);
	
	//get.jsp용 리스트 가져오기
	public CartVO get_cart(CartVO cart);
	
	//20.판매상태만 변경하기
		public int set_status(BoardVO board);
}
