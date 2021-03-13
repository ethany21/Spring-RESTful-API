package com.github.ethany21.RESTfulAPI.controller;

import com.github.ethany21.RESTfulAPI.exception.CustomException;
import com.github.ethany21.RESTfulAPI.exception.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    private List<String> strings = new ArrayList<>();

    @PostConstruct
    public void loadData(){
        strings.add("test");
        strings.add("for");
        strings.add("RESTful API");
    }

    @GetMapping("/test/{studentId}")
    public String strings(@PathVariable int studentId){

        if((studentId) >= strings.size() || (studentId < 0)){
            throw new CustomException("Student id not found - " + studentId);
        }
        return strings.get(studentId);
    }

    @GetMapping("/test")
    public List<String> strings(){

        return strings;

    }

    @ExceptionHandler
    public ResponseEntity<StudentException> handlerException(CustomException exception){

        StudentException error = new StudentException();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(error.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentException> handlerException(Exception exception){

        StudentException error = new StudentException();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(error.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
