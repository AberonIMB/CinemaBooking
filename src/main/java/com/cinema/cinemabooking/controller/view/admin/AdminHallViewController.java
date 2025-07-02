package com.cinema.cinemabooking.controller.view.admin;

import com.cinema.cinemabooking.dto.hall.CreateHallDTO;
import com.cinema.cinemabooking.dto.hall.EditHallDTO;
import com.cinema.cinemabooking.mapper.interfaces.HallMapper;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.service.interfaces.HallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createHallForm(Model model) {
        model.addAttribute("hall", new CreateHallDTO());
        return "admin/hall/adminCreateHall";
    }

    /**
     * Создает зал из переданного DTO
     * @param createHallDTO дто для создания зала
     * @param bindingResult результат валидации
     */
    @PostMapping("/create")
    public String createHall(@Valid @ModelAttribute("hall") CreateHallDTO createHallDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "admin/hall/adminCreateHall";
        }

        hallService.createHallFromDTO(createHallDTO);

        redirect.addFlashAttribute("success", "Зал успешно создан");
        return "redirect:/admin/halls";
    }

    /**
     * Отображает страницу редактирования зала
     * @param id идентификатор редактируемого зала
     * @param model модель
     */
    @GetMapping("/edit/{id}")
    public String editHallForm(@PathVariable Long id, Model model) {
        Hall hall = hallService.getHallById(id);
        EditHallDTO hallDTO = hallMapper.mapToEditHallDTO(hall);
        model.addAttribute("hall", hallDTO);

        return "admin/hall/adminEditHall";
    }

    /**
     * Редактирует зал из переданного DTO
     * @param id идентификатор редактируемого зала
     * @param hallDTO дто для редактирования зала
     * @param bindingResult результат валидации
     * @param model модель
     */
    @PostMapping("/edit/{id}")
    public String editHall(@PathVariable Long id,
                           @Valid @ModelAttribute("hall") EditHallDTO hallDTO,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("hall", hallDTO);
            return "admin/hall/adminEditHall";
        }

        hallService.updateHall(id, hallDTO);

        redirect.addFlashAttribute("success", "Зал успешно изменен");
        return "redirect:/admin/halls";
    }

    /**
     * Переводиит зал в неактивное состояние
     * @param id идентификатор зала
     */
    @PostMapping("/deactivate/{id}")
    public String deactivateHall(@PathVariable Long id, RedirectAttributes redirect) {
        hallService.deactivateHall(id);

        redirect.addFlashAttribute("success", "Зал успешно деактивирован");
        return "redirect:/admin/halls";
    }

    /**
     * Переводиит зал в активное состояние
     * @param id идентификатор зала
     */
    @PostMapping("/activate/{id}")
    public String activateHall(@PathVariable Long id, RedirectAttributes redirect) {
        hallService.activateHall(id);

        redirect.addFlashAttribute("success", "Зал успешно активирован");
        return "redirect:/admin/halls";
    }
}