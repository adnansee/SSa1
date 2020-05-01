package com.example.security.demo.model;

import org.springframework.data.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

@GetMapping("/")
    public String home(){
    return ("<h2>WELCOME VISITOR</h2>");
}
    @RequestMapping("/user")
    public String user(){
        return ("<h2>WELCOME USER</h2>");
    }
    @RequestMapping("/admin")
    public String admin(){
        return ("<h2>WELCOME ADMIN</h2>");
    }

}
