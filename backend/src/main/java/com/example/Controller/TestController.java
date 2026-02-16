package com.example.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/get")
public class TestController
{
    @GetMapping("/getuser")
    public String TEST (){

        return "TEST";
    }
}
