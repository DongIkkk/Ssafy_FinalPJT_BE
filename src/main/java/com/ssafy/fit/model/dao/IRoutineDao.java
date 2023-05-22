package com.ssafy.fit.model.dao;

import com.ssafy.fit.model.dto.Routine;

public interface IRoutineDao {

    public Routine selectRoutineByUserNo(int userNo);

    public void insertRoutine(Routine routine);

    public void deleteRoutine(int userNo);

}
