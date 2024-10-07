package com.potato.mapper;

import org.apache.ibatis.annotations.Param;

public interface User_mapper {
	
	// 판매자의 현재 온도 가져오기
    Long getTemperatureBySellerNumber(@Param("user_number") String user_number);
    
    // 판매자의 온도 업데이트
    void updateTemperature(@Param("user_number") String user_number, @Param("newTemperature") Long newTemperature);
}
