package com.example.hakaton.api;

import com.example.hakaton.model.User;
import com.example.hakaton.service.PetitionsService;
import com.example.hakaton.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final PetitionsService petitionsService;

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
        model.addAttribute("petitions", petitionsService.findMyPetitions());
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
    public String editProfile(@ModelAttribute User user, RedirectAttributes redirectAttributes, @RequestParam(value = "file", required = false) MultipartFile file) {
        user.setId(usersService.getAuthorizedUserId());
        usersService.update(user, file);
        return "redirect:/users/profile";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute User user, @RequestParam(value = "file", required = false) MultipartFile file) {
        usersService.createUser(user, file);
        return "redirect:/users/login";
    }

}
