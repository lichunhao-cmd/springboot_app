package com.example.restaurant.controller;


import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("tables")
public class TableController {

    @Autowired
    TableService tableService;

    @GetMapping("list")
    public ResponseDTO list(){
        return tableService.list();
    }

    // 获取桌号
    @GetMapping("create")
    public ResponseDTO create(String tableType, HttpServletRequest httpServletRequest){

        System.out.println(tableType);
        return tableService.create(tableType,httpServletRequest);
    }

    // 取消桌号
    @GetMapping("delete")
    public ResponseDTO delete(Integer tableNumber,HttpServletRequest httpServletRequest){


        return tableService.delete(tableNumber,httpServletRequest);

    }
}
