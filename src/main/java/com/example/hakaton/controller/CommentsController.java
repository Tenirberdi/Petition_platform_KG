package com.example.hakaton.controller;

import com.example.hakaton.model.Comment;
import com.example.hakaton.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;

    @PostMapping()
    public String editMyPetition(@ModelAttribute Comment comment) {
        commentsService.create(comment);
        return "redirect:/petitions/" + comment.getPetition().getId();
    }
}
