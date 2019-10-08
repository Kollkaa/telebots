package org.telegramBot.zakaz1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.telegramBot.zakaz1.domain.Role;
import org.telegramBot.zakaz1.domain.User;
import org.telegramBot.zakaz1.repos.TeluUserRepo;
import org.telegramBot.zakaz1.repos.UserRepo;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private TeluUserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "users";
    }





}