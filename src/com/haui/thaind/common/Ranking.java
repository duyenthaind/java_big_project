package com.haui.thaind.common;

/**
 * @author duyenthai
 */
public enum Ranking {

    BELOW_AVERAGE("Trung binh"),AVERAGE("Kha"), GOOD("Gioi"), EXCELLENT("Xuat xac");

    private final String value;

    Ranking(String value){
        this.value = value;
    }

    public String get(){
        return this.value;
    }

}
