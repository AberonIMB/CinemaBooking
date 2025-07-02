package com.cinema.cinemabooking.controller.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для отображения расписания
 */
@Controller
public class ScheduleViewController {

    /**
     * Отображает страницу расписания
     */
    @GetMapping("/schedule")
    public String getSchedule() {
        return "user/schedule";
    }
}