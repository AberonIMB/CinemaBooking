package com.cinema.cinemabooking.controller.api;

import com.cinema.cinemabooking.dto.hall.AdminHallDTO;
import com.cinema.cinemabooking.dto.hall.ShortHallDTO;
import com.cinema.cinemabooking.mapper.interfaces.HallMapper;
import com.cinema.cinemabooking.service.interfaces.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Api контроллер для работы с залами
 */
@RestController
@RequestMapping("/api/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @Autowired
    private HallMapper hallMapper;

    /**
     * Получить список всех залов
     */
    @GetMapping("")
    public List<ShortHallDTO> getHalls() {
        return hallService.getHalls()
                .stream()
                .map(hallMapper::mapToShortHallDTO)
                .toList();
    }

    /**
     * Получить список активных залов
     */
    @GetMapping("/active")
    public List<AdminHallDTO> getActiveHalls() {
        return hallService.getHallsByActive(true)
                .stream()
                .map(hallMapper::mapToAdminHallDTO)
                .toList();
    }

    /**
     * Получить список неактивных залов
     */
    @GetMapping("/inactive")
    public List<AdminHallDTO> getInactiveHalls() {
        return hallService.getHallsByActive(false)
                .stream()
                .map(hallMapper::mapToAdminHallDTO)
                .toList();
    }
}
