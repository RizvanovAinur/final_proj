package com.example.final_proj.controllers;

import com.example.final_proj.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthenticationController {
    @GetMapping("/authentication")
    public String login(){
        return "authentication";
    }

}
