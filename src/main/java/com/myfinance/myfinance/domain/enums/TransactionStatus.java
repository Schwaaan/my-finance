package com.myfinance.myfinance.domain.enums;

import lombok.Getter;

public enum TransactionStatus {

    SUCESS(1, "Transação Realizada"), REVERT(2, "Transação Revertida");

    @Getter
    public Integer code;
    @Getter
    public String description;

    TransactionStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static TransactionStatus getEnum(Integer code) {
        for (TransactionStatus x : values()) {
            if (x.getCode().equals(code)) {
                return x;
            }
        }
        return TransactionStatus.SUCESS;
    }
}
