package com.myfinance.myfinance.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageError {

    private Long timestamp;
    private Integer status;
    private String message;

}
