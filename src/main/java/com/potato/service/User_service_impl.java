package com.potato.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.potato.domain.UserVO;
import com.potato.mapper.User_mapper;

@Service
public class User_service_impl implements User_service {

    @Autowired
    private User_mapper userMapper;
    
    
    @Override
    public void updateUserTemperature(String user_number, long temperatureChange) {
    	
	    // 판매자의 현재 온도를 가져옴
	    Long currentTemperature = userMapper.getTemperatureBySellerNumber(user_number);
	    
	    // 새로운 온도를 계산
	    Long newTemperature = currentTemperature + temperatureChange;
	    
	    // 판매자의 온도 업데이트
	    userMapper.updateTemperature(user_number, newTemperature);
    }
}
