package com.cinema.cinemabooking.controller.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping
    public String home() {
        return "user/userHome";
    }
}
