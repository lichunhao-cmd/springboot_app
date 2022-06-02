package com.example.restaurant.controller;


import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carousels")
public class CarouselController {

    @Autowired
    CarouselService carouselService;

    @GetMapping("list")
    public ResponseDTO list(){
        return carouselService.listCarousels();
    }
}
