package com.cinema.cinemabooking.controller.view.user;

import com.cinema.cinemabooking.dto.booking.UserInfoBookingDTO;
import com.cinema.cinemabooking.mapper.interfaces.BookingMapper;
import com.cinema.cinemabooking.model.User;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import com.cinema.cinemabooking.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/**
 * Контроллер для работы с бронями
 */
@Controller
@RequestMapping("/bookings")
public class BookingViewController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private UserService userService;

    /**
     * Отображает страницу броней пользователя
     */
    @GetMapping("/my")
    public String getMyBookings(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());

        List<UserInfoBookingDTO> activeBookings = bookingMapper.mapToUserInfoBookingDTOList(
                bookingService.getActiveUsersBookings(user));

        List<UserInfoBookingDTO> finishedBookings = bookingMapper.mapToUserInfoBookingDTOList(
                bookingService.getFinishedUsersBookings(user));

        model.addAttribute("activeBookings", activeBookings);
        model.addAttribute("finishedBookings", finishedBookings);

        return "user/myBookings";
    }

    /**
     *  Отменяет бронирования
     * @param bookingIds список идентификаторов броней
     */
    @PostMapping("/cancel")
    public String cancelBookings(@RequestParam("bookingIds") List<Long> bookingIds,
                                 Principal principal, RedirectAttributes redirect) {
        User user = userService.getUserByEmail(principal.getName());
        bookingService.cancelBookings(bookingIds, user);
        redirect.addFlashAttribute("success", "Бронь успешно отменена");

        return "redirect:/bookings/my";
    }

    /**
     * Имитирует оплату брони
     * @param id идентификатор брони
     */
    @PostMapping("/pay/{id}")
    public String payBooking(@PathVariable Long id, Principal principal, RedirectAttributes redirect) {
        User user = userService.getUserByEmail(principal.getName());
        bookingService.payBooking(id, user);
        redirect.addFlashAttribute("success", "Бронь успешно оплачен");

        return "redirect:/bookings/my";
    }
}
