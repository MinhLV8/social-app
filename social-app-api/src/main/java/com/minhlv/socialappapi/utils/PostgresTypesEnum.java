package com.minhlv.socialappapi.utils;

public enum PostgresTypesEnum {
    
    VARCHAR("varchar"), 
    INT2("int2"), 
    INT4("int4"), 
    INT8("int8"), 
    TIMESTAMP("timestamp"), 
    TEXT("text"),
    NUMERIC("numeric"),
    BOOL("bool"),
    TSVECTOR("tsvector");

    public final String label;

    private PostgresTypesEnum(String label) {
        this.label = label;
    }
}
