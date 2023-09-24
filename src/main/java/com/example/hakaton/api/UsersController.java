package com.example.hakaton.api;

import com.example.hakaton.model.Petition;
import com.example.hakaton.model.User;
import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.CommentsResponse;
import com.example.hakaton.model.response.UsersResponse;
import com.example.hakaton.service.PetitionsService;
import com.example.hakaton.service.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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
        User user = usersService.findById(usersService.getAuthorizedUserId());
        model.addAttribute("petitions", petitionsService.findMyPetitions());
        model.addAttribute("user", user);
        return "myprofile";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute User user, RedirectAttributes redirectAttributes, @RequestParam(value = "file", required = false) MultipartFile file) {
        usersService.create(user, file);
        return "redirect:/users/login";
    }

}
