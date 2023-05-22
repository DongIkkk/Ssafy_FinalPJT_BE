package com.ssafy.fit.model.dto;

public class Routine {

    private int routineNo;
    private int userNo;
    private String createdDate;
    private int routineType;
    private String dayOne;
    private String dayTwo;
    private String dayThree;
    private String dayFour;



    public int getRoutineNo() {
        return routineNo;
    }

    public void setRoutineNo(int routineNo) {
        this.routineNo = routineNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getRoutineType() {
        return routineType;
    }

    public void setRoutineType(int routineType) {
        this.routineType = routineType;
    }

    public String getDayOne() {
        return dayOne;
    }

    public void setDayOne(String dayOne) {
        this.dayOne = dayOne;
    }

    public String getDayTwo() {
        return dayTwo;
    }

    public void setDayTwo(String dayTwo) {
        this.dayTwo = dayTwo;
    }

    public String getDayThree() {
        return dayThree;
    }

    public void setDayThree(String dayThree) {
        this.dayThree = dayThree;
    }

    public String getDayFour() {
        return dayFour;
    }

    public void setDayFour(String dayFour) {
        this.dayFour = dayFour;
    }
}
