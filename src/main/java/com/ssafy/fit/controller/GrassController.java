package com.ssafy.fit.controller;

import com.ssafy.fit.model.dto.Grass;
import com.ssafy.fit.model.service.GrassService;
import com.ssafy.fit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-grass")
public class GrassController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private GrassService grassService;

    @PostMapping("/grass")
    public ResponseEntity<?> grassCut(@RequestHeader HttpHeaders header) throws Exception{
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        grassService.insertGrass(requestUserNo);
        return new ResponseEntity<String>("nice", HttpStatus.CREATED);
    }

    @GetMapping("/grass")
    public ResponseEntity<?> myGrass(@RequestHeader HttpHeaders header) throws Exception{
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        List<Grass> myGrass = grassService.selectMyGrass(requestUserNo);
        return new ResponseEntity<List<Grass>>(myGrass, HttpStatus.OK);
    }
}
