package com.rio.Blogging.website.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class mainController {
    @GetMapping("/Home")
    public String hello(){
        return "Hello from Rio";
    }
}
