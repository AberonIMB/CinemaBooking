package com.cinema.cinemabooking.controller.view.admin;

import com.cinema.cinemabooking.dto.hall.ShortHallDTO;
import com.cinema.cinemabooking.dto.session.EditSessionDTO;
import com.cinema.cinemabooking.dto.session.CreateSessionDTO;
import com.cinema.cinemabooking.dto.session.SessionCreateData;
import com.cinema.cinemabooking.dto.session.SessionUpdateData;
import com.cinema.cinemabooking.mapper.interfaces.HallMapper;
import com.cinema.cinemabooking.mapper.interfaces.SessionMapper;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.service.interfaces.HallService;
import com.cinema.cinemabooking.service.interfaces.SessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Контроллер для работы с сеансами
 */
@Controller
@RequestMapping("admin/sessions")
public class AdminSessionViewController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private HallService hallService;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private HallMapper hallMapper;

    /**
     * Отображает главную страницу c сеансами
     */
    @GetMapping("")
    public String home() {
        return "admin/session/adminSessions";
    }

    /**
     * Отображает страницу создания сеанса
     */
    @GetMapping("/create")
    public String createSession(Model model) {
        model.addAttribute("session", new CreateSessionDTO());
        model.addAttribute("halls", getActiveHalls());

        return "admin/session/adminCreateSession";
    }

    /**
     * Создает сеанс из переданного DTO
     * @param createSessionDTO дто для создания сеанса
     * @param bindingResult результат валидации
     * @param model модель
     */
    @PostMapping("/create")
    public String createSession(@Valid @ModelAttribute("session") CreateSessionDTO createSessionDTO,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("session", createSessionDTO);
            model.addAttribute("halls", getActiveHalls());
            return "admin/session/adminCreateSession";
        }

        SessionCreateData sessionData = sessionMapper.mapToCreateData(createSessionDTO);
        sessionService.createSession(sessionData);

        return "redirect:/admin/sessions";
    }

    /**
     * Отображает страницу редактирования сеанса
     * @param id идентификатор редактируемого сеанса
     * @param model модель
     */
    @GetMapping("edit/{id}")
    public String editSession(@PathVariable Long id, Model model) {

        Session session = sessionService.getSessionById(id);
        EditSessionDTO sessionDTO = sessionMapper.mapToEditSessionDTO(session);
        model.addAttribute("session", sessionDTO);

        model.addAttribute("halls", getActiveHalls());
        return "admin/session/adminEditSession";
    }

    /**
     * Редактирует сеанс из переданного DTO
     * @param id идентификатор редактируемого сеанса
     * @param sessionDTO дто для редактирования сеанса
     * @param bindingResult результат валидации
     * @param model модель
     */
    @PostMapping("edit/{id}")
    public String editSession(@PathVariable Long id,
                              @Valid @ModelAttribute("session") EditSessionDTO sessionDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirect,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("session", sessionDTO);
            model.addAttribute("halls", getActiveHalls());
            return "admin/session/adminEditSession";
        }

        SessionUpdateData sessionUpdateData = sessionMapper.mapToUpdateData(sessionDTO);
        sessionService.updateSession(sessionUpdateData);

        redirect.addFlashAttribute("success", "Сеанс успешно изменен");
        return "redirect:/admin/sessions";
    }

    /**
     * Отменяет сеанс
     * @param id идентификатор сеанса
     */
    @PostMapping("cancel/{id}")
    public String cancelSession(@PathVariable Long id, RedirectAttributes redirect) {
        Session session = sessionService.getSessionById(id);
        sessionService.cancelSession(session);

        redirect.addFlashAttribute("success", "Сеанс успешно отменен");
        return "redirect:/admin/sessions";
    }

    /**
     * Возвращает список активных залов
     */
    private List<ShortHallDTO> getActiveHalls() {
        return hallService.getHallsByActive(true).stream().map(hallMapper::mapToShortHallDTO).toList();
    }
}