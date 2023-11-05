package com.example.hakaton.controller;

import com.example.hakaton.model.User;
import com.example.hakaton.service.TendersService;
import com.example.hakaton.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final TendersService tendersService;

    @GetMapping("/login")
    public String login(Model model) {
        return "auth";
    }
    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("user" , new User());
        return "registration";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        User user = usersService.getMyProfile();
        model.addAttribute("tenders", tendersService.findMyTenders());
        model.addAttribute("user", user);
        return "myprofile";
    }

    @GetMapping("/editProfile")
    public String editProfile( Model model) {
        User user = usersService.findById(usersService.getAuthorizedUserId());
        model.addAttribute("user",user);
//        model.addAttribute("categories", categoryService.findAll());
        return "edit-profile";
    }

    @PostMapping(value =  "/editProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editProfile(@ModelAttribute User user, @RequestParam(value = "file", required = false) MultipartFile file) {
        user.setId(usersService.getAuthorizedUserId());
        usersService.updateProfile(user, file);
        return "redirect:/users/profile";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute User user, @RequestParam(value = "file", required = false) MultipartFile file) {
        usersService.createUser(user, file);
        return "redirect:/users/login";
    }

}
