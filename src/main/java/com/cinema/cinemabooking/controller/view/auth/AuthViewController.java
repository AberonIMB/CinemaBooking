package com.cinema.cinemabooking.controller.view.auth;

import com.cinema.cinemabooking.dto.user.RegisterDTO;
import com.cinema.cinemabooking.exception.user.UserAlreadyExistsException;
import com.cinema.cinemabooking.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер для отображения страницы регистрации и авторизации
 */
@Controller
@RequestMapping("/auth")
public class AuthViewController {

    @Autowired
    private UserService userService;

    /**
     * Отображает страницу для авторизации
     */
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    /**
     * Отображает страницу регистрации
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new RegisterDTO());
        return "auth/registration";
    }

    /**
     * Регистрирует пользователя, если данные валидны, иначе добавляет ошибку в модель
     * @param user Данные для создания пользователя
     * @param bindingResult Результат валидации
     * @param model Модель
     */
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") RegisterDTO user,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "auth/registration";
        }

        try {
            userService.saveUser(user);
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("user", user);
            model.addAttribute("error", e.getMessage());
            return "auth/registration";
        }

        return "redirect:/auth/login?registered";
    }
}
