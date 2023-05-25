package com.ssafy.fit.model.service;

import com.ssafy.fit.model.dao.IGrassDao;
import com.ssafy.fit.model.dto.Grass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrassServiceImpl implements GrassService {

    private IGrassDao grassDao;

    @Autowired
    public GrassServiceImpl(IGrassDao grassDao){
        this.grassDao = grassDao;
    }

    @Override
    public List<Grass> selectMyGrass(int userNo) {
        return grassDao.selectMyGrass(userNo);
    }

    @Override
    public void insertGrass(int userNo) {
        grassDao.insertGrass(userNo);
    }
}
