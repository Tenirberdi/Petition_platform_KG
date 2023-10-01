package com.example.hakaton.controller;

import com.example.hakaton.model.Comment;
import com.example.hakaton.model.Petition;
import com.example.hakaton.service.CategoryService;
import com.example.hakaton.service.CommentsService;
import com.example.hakaton.service.PetitionsService;
import com.example.hakaton.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/petitions")
@RequiredArgsConstructor
public class PetitionsController {
    private final PetitionsService petitionsService;
    private final CategoryService categoryService;
    private final UsersService usersService;
    private final CommentsService commentsService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String create(@ModelAttribute Petition petition, RedirectAttributes redirectAttributes, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            petitionsService.createPetition(petition, file);
            return "redirect:/petitions/";
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/addPetition")
    public String create(Model model) {
        model.addAttribute("categories" , categoryService.findAll());
        model.addAttribute("petition" , new Petition());
        return "create-pettion";
    }

    @GetMapping("/vote/{id}")
    public String create(Model model, @PathVariable Long id) {
        petitionsService.vote(id);
        return "redirect:/petitions/" + id;
    }

    @GetMapping
    public String getMainPage(Model model, Integer page, Integer size) {
        model.addAttribute("petitions", petitionsService.findPetitions(PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 4
        )));
        model.addAttribute("categories" , categoryService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String getDetailedInfo(@PathVariable Long id, Model model) {
        Petition petition = petitionsService.findPetitionById(id);
        model.addAttribute("petition", petition);
        model.addAttribute("votes", petitionsService.getPetitionVotes(id));
        model.addAttribute("currentUser", usersService.getMyProfile());
        model.addAttribute("comments", commentsService.findAllByPetitionId(id));
        model.addAttribute("newComment", new Comment());
        return "info";
    }

    @GetMapping("/editPetition/{id}")
    public String editMyPetition(@PathVariable Long id, Model model) {
        model.addAttribute("petition", petitionsService.findPetitionById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentUserId", usersService.getAuthorizedUserId());
        return "edit-petition";
    }

    @PostMapping(value =  "/editPetition/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editMyPetition(@PathVariable Long id, @ModelAttribute Petition petition, @RequestParam(value = "file", required = false) MultipartFile file) {
        petition.setId(id);
        petitionsService.updateOwnPetition(petition, file);
        return "redirect:/petitions/" + id;
    }
}
