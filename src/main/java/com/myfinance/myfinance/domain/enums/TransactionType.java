package com.myfinance.myfinance.domain.enums;

import lombok.Getter;

public enum TransactionType {
    PIX("PIX", 1), SCHEDULED("AGENDADA", 2);

    @Getter
    private String description;
    @Getter
    private Integer code;

    TransactionType(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public static TransactionType getEnum(Integer code) {
        for (TransactionType x : values()) {
            if (x.getCode().equals(code)) {
                return x;
            }
        }
        return TransactionType.PIX;
    }
}
