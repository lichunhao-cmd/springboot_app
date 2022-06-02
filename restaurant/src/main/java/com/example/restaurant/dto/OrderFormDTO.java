package com.example.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderFormDTO {

    private List<OrderDTO> orderDTOS;

    private String allGoodsPrice;

    private String tableNumber;

    private String buyerRemarks;
}
