package com.haui.thaind.common;

/**
 * @author duyenthai
 */
public enum Ranking {

    BELOW_AVERAGE("Trung bình"),AVERAGE("Khá"), GOOD("Giỏi"), EXCELLENT("Xuất sắc");

    private final String value;

    Ranking(String value){
        this.value = value;
    }

    public String get(){
        return this.value;
    }

}
