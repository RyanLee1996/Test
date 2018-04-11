package com.ryan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExecutionChain;

@Controller

public class HelloController{

    @RequestMapping("/hello.do")
    public String printHello(ModelMap model) {

        System.out.println("Controller");
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

}
