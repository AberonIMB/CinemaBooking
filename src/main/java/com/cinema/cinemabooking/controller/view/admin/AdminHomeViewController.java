package com.cinema.cinemabooking.controller.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для работы с главной страницей
 */
@Controller
@RequestMapping("/admin")
public class AdminHomeViewController {

    /**
     * Отображает главную страницу
     */
    @GetMapping("")
    public String home() {
        return "admin/adminHome";
    }
}
