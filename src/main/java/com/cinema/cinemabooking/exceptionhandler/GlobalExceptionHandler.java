package com.cinema.cinemabooking.exceptionhandler;

import com.cinema.cinemabooking.exception.ResourceNotFoundException;
import com.cinema.cinemabooking.exception.booking.BookingException;
import com.cinema.cinemabooking.exception.booking.SeatAlreadyBookedException;
import com.cinema.cinemabooking.exception.hall.HallException;
import com.cinema.cinemabooking.exception.movie.MovieException;
import com.cinema.cinemabooking.exception.session.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Обработчик исключений
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/notFound";
    }

    @ExceptionHandler(HallException.class)
    public String handleHallException(HallException e, RedirectAttributes redirect) {
        redirect.addFlashAttribute("error", e.getMessage());
        return "redirect:/admin/halls";
    }

    @ExceptionHandler(MovieException.class)
    public String handleMovieException(MovieException e, RedirectAttributes redirect) {
        redirect.addFlashAttribute("error", e.getMessage());
        return "redirect:/admin/movies";
    }

    @ExceptionHandler(SessionException.class)
    public String handleSessionException(SessionException e, RedirectAttributes redirect) {
        redirect.addFlashAttribute("error", e.getMessage());
        return "redirect:/admin/sessions";
    }

    @ExceptionHandler(BookingException.class)
    public String handleBookingException(BookingException e, RedirectAttributes redirect) {
        redirect.addFlashAttribute("error", e.getMessage());
        return "redirect:/bookings/my";
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public String handleSeatAlreadyBookedException(SeatAlreadyBookedException e, RedirectAttributes redirect) {
        redirect.addFlashAttribute("error", e.getMessage());
        return "redirect:/sessions/" + e.getSessionId();
    }
}