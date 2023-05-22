package com.ssafy.fit.model.service;

import com.ssafy.fit.model.dao.IRoutineDao;
import com.ssafy.fit.model.dto.Routine;

public class RoutineServiceImpl implements RoutineService{

    private IRoutineDao routineDao;

    public RoutineServiceImpl(IRoutineDao routineDao){
        this.routineDao = routineDao;
    }

    @Override
    public Routine selectRoutineByUserNo(int userNo) {
        return routineDao.selectRoutineByUserNo(userNo);
    }

    @Override
    public void insertRoutine(Routine routine) {
        routineDao.insertRoutine(routine);
    }

    @Override
    public void deleteRoutine(int userNo) {
        routineDao.deleteRoutine(userNo);
    }

}
