package com.ssafy.fit.model.dao;

import com.ssafy.fit.model.dto.Grass;

import java.util.List;

public interface IGrassDao {

    public List<Grass> selectMyGrass(int userNo);

    public void insertGrass(int userNo);

}
