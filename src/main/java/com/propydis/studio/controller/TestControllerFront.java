package com.propydis.studio.controller;


import com.propydis.studio.config.ApiConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH )
public class TestControllerFront {

    @GetMapping("/hello")
    public String hello(){
        return "hello, i´m connect from backend";
    }
}
