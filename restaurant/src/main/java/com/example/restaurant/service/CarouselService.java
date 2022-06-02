package com.example.restaurant.service;

import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.mapper.CarouselMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarouselService {

    @Autowired
    CarouselMapper carouselMapper;

    public ResponseDTO listCarousels(){
        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,carouselMapper.listCarousels());
    }
}
