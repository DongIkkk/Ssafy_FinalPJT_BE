package com.ssafy.fit.model.service;

import com.ssafy.fit.model.dto.Routine;

public interface RoutineService {

    public Routine selectRoutineByUserNo(int userNo);

    public void insertRoutine(Routine routine);

    public void deleteRoutine(int userNo);


}
