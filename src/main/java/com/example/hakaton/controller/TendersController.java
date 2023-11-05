package com.example.hakaton.controller;

import com.example.hakaton.model.Comment;
import com.example.hakaton.model.Tender;
import com.example.hakaton.service.CategoryService;
import com.example.hakaton.service.CommentsService;
import com.example.hakaton.service.TendersService;
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
@RequestMapping("/tenders")
@RequiredArgsConstructor
public class TendersController {
    private final TendersService tendersService;
    private final CategoryService categoryService;
    private final UsersService usersService;
    private final CommentsService commentsService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String create(@ModelAttribute Tender tender, RedirectAttributes redirectAttributes, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            tendersService.createTender(tender, file);
            return "redirect:/tenders/";
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/addTender")
    public String create(Model model) {
        model.addAttribute("categories" , categoryService.findAll());
        model.addAttribute("tender" , new Tender());
        return "create-tender";
    }

    @GetMapping("/vote/{id}")
    public String create(Model model, @PathVariable Long id) {
        tendersService.vote(id);
        return "redirect:/tenders/" + id;
    }

    @GetMapping
    public String getMainPage(Model model, Integer page, Integer size) {
        model.addAttribute("tenders", tendersService.findTenders(PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 4
        )));
        model.addAttribute("categories" , categoryService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String getDetailedInfo(@PathVariable Long id, Model model) {
        Tender tender = tendersService.findTenderById(id);
        model.addAttribute("tender", tender);
        model.addAttribute("votes", tendersService.getTenderVotes(id));
        model.addAttribute("currentUser", usersService.getMyProfile());
        model.addAttribute("comments", commentsService.findAllByTenderId(id));
        model.addAttribute("newComment", new Comment());
        return "info";
    }

    @GetMapping("/editTender/{id}")
    public String editMyTender(@PathVariable Long id, Model model) {
        model.addAttribute("tender", tendersService.findTenderById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentUserId", usersService.getAuthorizedUserId());
        return "edit-tender";
    }

    @PostMapping(value =  "/editTender/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editMyTender(@PathVariable Long id, @ModelAttribute Tender tender, @RequestParam(value = "file", required = false) MultipartFile file) {
        tender.setId(id);
        tendersService.updateOwnTender(tender, file);
        return "redirect:/tenders/" + id;
    }
}
