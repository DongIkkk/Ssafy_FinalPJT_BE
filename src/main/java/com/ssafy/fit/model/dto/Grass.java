package com.ssafy.fit.model.dto;

public class Grass {

    private int GrassNo;
    private int userNo;
    private String GDate;

    public int getGrassNo() {
        return GrassNo;
    }

    public void setGrassNo(int grassNo) {
        GrassNo = grassNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getGDate() {
        return GDate;
    }

    public void setGDate(String GDate) {
        this.GDate = GDate;
    }

    @Override
    public String toString() {
        return "Grass{" +
                "GrassNo=" + GrassNo +
                ", userNo=" + userNo +
                ", GDate='" + GDate + '\'' +
                '}';
    }
}
