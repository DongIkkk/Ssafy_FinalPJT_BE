package com.ssafy.fit.model.service;

import com.ssafy.fit.model.dao.IRoutineDao;
import com.ssafy.fit.model.dto.Routine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutineServiceImpl implements RoutineService{

    private IRoutineDao routineDao;

    @Autowired
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
