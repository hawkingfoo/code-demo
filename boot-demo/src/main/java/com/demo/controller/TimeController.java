package com.demo.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
@RequestMapping("now")
public class TimeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    String user() {
        return new Date().toString();
    }
}

