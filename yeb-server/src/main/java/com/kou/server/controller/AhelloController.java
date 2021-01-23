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

}
