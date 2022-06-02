package com.example.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {

    private Integer tableId;

    private Integer tableStatus;

    private String tableType;

    private Integer tableNumber;
}
