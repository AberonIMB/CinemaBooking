package com.cinema.cinemabooking.controller.view.user;

import com.cinema.cinemabooking.dto.booking.BookingRequestDTO;
import com.cinema.cinemabooking.dto.booking.BookingSessionDTO;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.model.User;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import com.cinema.cinemabooking.service.interfaces.SessionDetailsService;
import com.cinema.cinemabooking.service.interfaces.SessionService;
import com.cinema.cinemabooking.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

/**
 * Контроллер для работы с сеансами
 */
@Controller
@RequestMapping("/sessions")
public class SessionViewController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionDetailsService sessionDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;


    /**
     * Отображает страницу сеанса
     * @param id идентификатор сеанса
     */
    @GetMapping("/{id}")
    public String getSession(@PathVariable Long id, Model model) {
        Session session = sessionService.getSessionById(id);

        BookingSessionDTO sessionDetails = sessionDetailsService.getSessionDetails(session);

        model.addAttribute("bookingSession", sessionDetails);

        return "user/session";
    }

    /**
     * бронирует сеанса
     * @param id идентификатор сеанса
     * @param bookingRequestDTO данные бронирования
     */
    @PostMapping("/{id}")
    public String bookSession(@PathVariable Long id,
                              @ModelAttribute BookingRequestDTO bookingRequestDTO,
                              Principal principal,
                              Model model,
                              RedirectAttributes redirect) {

        User user = userService.getUserByEmail(principal.getName());

        Session session = sessionService.getSessionById(id);
        bookingService.createBookings(session, bookingRequestDTO.getSelectedSeats(), user);
        redirect.addFlashAttribute("bookingSession", sessionDetailsService.getSessionDetails(session));
        redirect.addFlashAttribute("success", "Все места забронированы успешно");
        return "redirect:/sessions/{id}";
    }
}
