package com.kou.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JIAJUN KOU
 */
@RestController

public class AhelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello Joaquin";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "basic";
    }
    @GetMapping("/employee/advanced/hello")
    public String hello3(){
        return "advanced";
    }
}
