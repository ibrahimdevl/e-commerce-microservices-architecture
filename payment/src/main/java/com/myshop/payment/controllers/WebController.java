package com.myshop.payment.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class WebController {

    @GetMapping("/")
    public  String index() {
        return "index";
    }
}
