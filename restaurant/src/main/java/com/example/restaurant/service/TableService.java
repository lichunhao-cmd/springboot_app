package com.example.restaurant.service;

import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.entity.Table;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.mapper.TableMapper;
import com.example.restaurant.mapper.UserMapper;
import com.example.restaurant.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class TableService {

    @Autowired
    TableMapper tableMapper;

    @Autowired
    UserMapper userMapper;

    public ResponseDTO list(){
        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,tableMapper.listTables());
    }

    // 获取当前登录openid
    @Transactional
    public ResponseDTO create(String tableType, HttpServletRequest httpServletRequest){

        String token = httpServletRequest.getHeader("token");
        String openId = JWTUtils.getInfo(token);
        List<Table> maps = tableMapper.listNullTables();
        if (!maps.isEmpty()){
            for (Table map : maps) {
                if (map.getTableType().equals(tableType)){
                    int i = tableMapper.updateTable(map.getTableNumber());
                    int i1 = userMapper.updateUserForTable(map.getTableNumber(),openId);
                    if (i > 0){
                        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,map.getTableNumber());
                    }
                }
            }
        }

        return ResponseDTO.onError("桌号已满");
    }


    @Transactional
    public ResponseDTO delete(Integer tableNumber,HttpServletRequest httpServletRequest){

        String token = httpServletRequest.getHeader("token");
        String openId = JWTUtils.getInfo(token);

        tableMapper.updateTableForNull(tableNumber);
        int i = userMapper.updateUserForNull(openId);
        if (i>0){
            return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,i);
        }
        return ResponseDTO.onError(ErrorConfig.IS_ERROR);
    }
}
