package com.potato.service;

import com.potato.domain.UserVO;

public interface User_service {
	
	// 설문 후 회원 온도 변경
	void updateUserTemperature(String user_number, long temperatureChange);
}
