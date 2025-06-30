package com.cinema.cinemabooking.controller.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ScheduleViewController {

    @GetMapping("/schedule")
    public String getSchedule() {
        return "user/schedule";
    }
}