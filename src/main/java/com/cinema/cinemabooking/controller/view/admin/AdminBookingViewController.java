package com.cinema.cinemabooking.controller.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для работы с бронированиями
 */
@Controller
@RequestMapping("/admin/bookings")
public class AdminBookingViewController {

    /**
     * Получить страницу с бронированиями
     */
    @GetMapping
    public String getBookings() {
        return "admin/adminBookings";
    }
}
