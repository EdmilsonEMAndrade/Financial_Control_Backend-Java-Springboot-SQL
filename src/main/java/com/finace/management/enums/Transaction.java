package com.finace.management.enums;

public enum Transaction {
    CREDIT(0, "credit"),
    DEBIT(1, "debit");

    private Integer code;
    private String type;

    private Transaction(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public static Transaction toEnumByCode(Integer code) {
        if(code == null) {
            throw new NullPointerException("Trasaction code is required");
        }
        for(Transaction x : Transaction.values()){
            if(code.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid trasaction code: " + code );
    }

    public static Transaction toEnumByType(String type) {
        if(type == null) {
            throw new NullPointerException("Trasaction type is required");
        }
        for(Transaction x : Transaction.values()){
            if(type.equals(x.getType())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid trasaction type: " + type );
    }

}
