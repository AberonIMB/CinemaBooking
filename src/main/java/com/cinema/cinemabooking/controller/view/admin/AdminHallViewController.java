package com.cinema.cinemabooking.controller.view.admin;

import com.cinema.cinemabooking.dto.hall.AdminHallDTO;
import com.cinema.cinemabooking.dto.hall.CreateHallDTO;
import com.cinema.cinemabooking.dto.hall.EditHallDTO;
import com.cinema.cinemabooking.exception.hall.HallAlreadyExistsException;
import com.cinema.cinemabooking.mapper.interfaces.HallMapper;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.service.interfaces.HallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с залами
 */
@Controller
@RequestMapping("/admin/halls")
public class AdminHallViewController {

    @Autowired
    private HallService hallService;

    @Autowired
    private HallMapper hallMapper;

    /**
     * Отображает главную страницу
     */
    @GetMapping("")
    public String home() {
        return "admin/hall/adminHalls";
    }

    /**
     * Отображает страницу создания зала
     */
    @GetMapping("/create")
    public String createHall(Model model) {
        model.addAttribute("hall", new CreateHallDTO());
        return "admin/hall/adminCreateHall";
    }

    /**
     * Создает зал из переданного DTO, если ошибок нет, то перенаправляет на главную страницу,
     * иначе добавляет ошибку на страницу
     * @param createHallDTO дто для создания зала
     * @param bindingResult результат валидации
     * @param model модель
     */
    @PostMapping("/create")
    public String createHall(@Valid @ModelAttribute("hall") CreateHallDTO createHallDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/hall/adminCreateHall";
        }

        try {
            hallService.createHallFromDTO(createHallDTO);
        } catch (HallAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/hall/adminCreateHall";
        }

        return "redirect:/admin/halls";
    }

    /**
     * Отображает страницу редактирования зала
     * @param id идентификатор редактируемого зала
     * @param model модель
     */
    @GetMapping("/edit/{id}")
    public String editHall(@PathVariable Long id, Model model) {
        Hall hall = hallService.getHallById(id);

        EditHallDTO hallDTO = hallMapper.mapToEditHallDTO(hall);

        model.addAttribute("hall", hallDTO);
        return "admin/hall/adminEditHall";
    }

    /**
     * Редактирует зал из переданного DTO, если ошибок нет, то перенаправляет на главную страницу,
     * иначе добавляет ошибку на страницу
     * @param id идентификатор редактируемого зала
     * @param hallDTO дто для редактирования зала
     * @param bindingResult результат валидации
     * @param model модель
     */
    @PostMapping("/edit/{id}")
    public String editHall(@PathVariable Long id,
                           @Valid @ModelAttribute("hall") EditHallDTO hallDTO,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("hall", hallDTO);
            return "admin/hall/adminEditHall";
        }

        try {
            hallService.updateHall(id, hallDTO);
        } catch (RuntimeException e) {
            model.addAttribute("hall", hallDTO);
            model.addAttribute("error", e.getMessage());
            return "admin/hall/adminEditHall";
        }

        return "redirect:/admin/halls";
    }

    /**
     * Переводиит зал в неактивное состояние, если ошибок нет, то перенаправляет на главную страницу,
     * иначе добавляет ошибку на страницу
     * @param id идентификатор зала
     * @param model модель
     */
    @PostMapping("/deactivate/{id}")
    public String deactivateHall(@PathVariable Long id, Model model) {
        try {
            hallService.deactivateHall(id);
        } catch (RuntimeException e) {
            Hall hall = hallService.getHallById(id);
            AdminHallDTO hallDTO = hallMapper.mapToAdminHallDTO(hall);

            model.addAttribute("hall", hallDTO);
            model.addAttribute("error", e.getMessage());
            return "admin/hall/adminEditHall";
        }

        return "redirect:/admin/halls";
    }

    /**
     * Переводиит зал в активное состояние, если ошибок нет, то перенаправляет на главную страницу,
     * иначе добавляет ошибку на страницу
     * @param id идентификатор зала
     * @param model модель
     */
    @PostMapping("/activate/{id}")
    public String activateHall(@PathVariable Long id, Model model) {
        try {
            hallService.activateHall(id);
        } catch (RuntimeException e) {
            Hall hall = hallService.getHallById(id);
            AdminHallDTO hallDTO = hallMapper.mapToAdminHallDTO(hall);

            model.addAttribute("hall", hallDTO);
            model.addAttribute("error", e.getMessage());
            return "admin/hall/adminEditHall";
        }

        return "redirect:/admin/halls";
    }
}