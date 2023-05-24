package com.ssafy.fit.controller;

import com.ssafy.fit.model.dto.Comment;
import com.ssafy.fit.model.dto.Routine;
import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.service.RoutineService;
import com.ssafy.fit.model.service.UserService;
import com.ssafy.fit.util.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api-routine")
@Api(tags = "루틴 컨트롤러")
public class RoutineController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoutineService routineService;

    @Autowired
    private UserService userService;

    //루틴 조회
    @GetMapping("/routine")
    public ResponseEntity<?> selectRoutine(@RequestHeader HttpHeaders header) throws Exception {
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        Routine myRoutine = routineService.selectRoutineByUserNo(requestUserNo);
        return new ResponseEntity<Routine>(myRoutine, HttpStatus.OK);
    }

    @GetMapping("/today-routine")
    public ResponseEntity<?> selectTodayRoutine(@RequestHeader HttpHeaders header) throws Exception {
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        Routine myRoutine = routineService.selectRoutineByUserNo(requestUserNo);
        String result = "";
        String[] datesplit = myRoutine.getCreatedDate().split("-");
        LocalDate mydate = LocalDate.of(Integer.parseInt(datesplit[0]), Integer.parseInt(datesplit[1]), Integer.parseInt(datesplit[2]));
        LocalDate currentDate = LocalDate.now();

        long daysBetween = ChronoUnit.DAYS.between(mydate, currentDate);

        if(myRoutine.getRoutineType() == 2){
            long today = daysBetween % 3;
            if(today == 0){
                result += myRoutine.getDayOne();
            }else if(today == 1){
                result += myRoutine.getDayTwo();
            }else if(today == 2){
                result += myRoutine.getDayThree();
            }
        }

        if(myRoutine.getRoutineType() == 3){
            long today = daysBetween % 4;
            if(today == 0){
                result += myRoutine.getDayOne();
            }else if(today == 1){
                result += myRoutine.getDayTwo();
            }else if(today == 2){
                result += myRoutine.getDayThree();
            }else if(today == 3){
                result += myRoutine.getDayFour();
            }
        }

        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    //루틴 작성
    @PostMapping("/routine")
    public ResponseEntity<String> insertRoutine(Routine routine, @RequestParam("myId") String requestId) throws Exception {

        User requestUser = userService.selectUserByUserId(requestId);

        routine.setUserNo(requestUser.getUserNo());

        routineService.insertRoutine(routine);
        return new ResponseEntity<String>("Insert Complete!", HttpStatus.CREATED);
    }

    //루틴 삭제
    @DeleteMapping("/routine")
    public ResponseEntity<String> deleteRoutine(@RequestHeader HttpHeaders header) throws Exception {
        String token = header.get("access-token").toString();
        int requestUserNo = jwtUtil.getUserNoAtToken(token);

        routineService.deleteRoutine(requestUserNo);
        return new ResponseEntity<String>("Delete Complete!", HttpStatus.OK);


    }

}
