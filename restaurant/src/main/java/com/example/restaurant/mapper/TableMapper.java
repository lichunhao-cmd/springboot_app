package com.example.restaurant.mapper;

import com.example.restaurant.dto.TableDTO;
import com.example.restaurant.entity.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TableMapper {

    // list
    List<TableDTO> listTables();

    // 选定桌号
    int updateTable(@Param("tableNumber") Integer tableNumber);

    // 空位
    List<Table> listNullTables();

    // 释放位子
    int updateTableForNull(@Param("tableNumber") Integer tableNumber);
}
