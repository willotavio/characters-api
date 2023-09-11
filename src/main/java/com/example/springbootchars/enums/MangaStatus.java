package com.example.springbootchars.enums;

public enum MangaStatus {

    ON_GOING(1),
    FINISHED(2),
    CANCELED(3);

    private final int code;

    MangaStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static MangaStatus valueOf(int code){
        for(MangaStatus value : MangaStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid MangaStatus code");
    }

}
