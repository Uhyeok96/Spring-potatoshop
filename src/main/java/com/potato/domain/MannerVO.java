package com.potato.domain;

import lombok.Data;

@Data
public class MannerVO {
	
	private String id;	// 매너칭찬 번호
    private String member_number;	// 해당 회원번호
    private int rating;		// 선택 번호
}
