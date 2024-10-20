package com.omkar.security.controller;

import com.omkar.security.model.MyUserDetails;
import com.omkar.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {


    @Autowired
    private MyUserDetailsService service;

    @PostMapping("/addUser")
    public void addUser(@RequestBody MyUserDetails userDetails){
        this.service.addUser(userDetails);
    }

    @GetMapping("/student")
    public String student(){
        return "Hello student";
    }

    @GetMapping("/teacher")
    public String teacher(){
        return "Hello teacher";
    }

    @GetMapping("/principal")
    public String principal(){
        return "Hello principal";
    }

    @GetMapping("/all")
    public String all(){
        return "Hello all";
    }


}
