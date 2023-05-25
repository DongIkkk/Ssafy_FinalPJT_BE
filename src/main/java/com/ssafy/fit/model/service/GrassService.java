package com.ssafy.fit.model.service;

import com.ssafy.fit.model.dto.Grass;

import java.util.List;

public interface GrassService {

    public List<Grass> selectMyGrass(int userNo);

    public void insertGrass(int userNo);
}
