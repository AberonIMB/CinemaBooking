package com.cinema.cinemabooking.controller.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/bookings")
public class AdminBookingViewController {

    @GetMapping
    public String getBookings() {
        return "admin/adminBookings";
    }
}
