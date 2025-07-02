package com.cinema.cinemabooking.controller.view.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для главной страницы
 */
@Controller
public class HomeViewController {

    /**
     * Возвращает главную страницу
     */
    @GetMapping
    public String home() {
        return "user/userHome";
    }
}
