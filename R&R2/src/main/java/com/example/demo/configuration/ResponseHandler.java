package com.example.demo.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ResponseHandler {
    public static void generate(String message, HttpStatus status, Object responseObj,Error error){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("error",error);



        ResponseEntity<Object> response= new ResponseEntity<Object>(map,status);
        System.out.println("data : {" +
                "message :"+map.get("message")
        +",\nstatus :"+map.get("status")
        +",\nObject :"+map.get("data")
        +"\n},\nerror : " +map.get("error"));
    }
}

