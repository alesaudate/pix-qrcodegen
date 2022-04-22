package com.github.alesaudate.pix.qrcode;

public enum TransactionCurrency {

    BRL("986"),
    ;


    private final String value;

    TransactionCurrency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
