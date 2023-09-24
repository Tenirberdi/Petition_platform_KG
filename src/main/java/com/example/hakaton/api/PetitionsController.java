package com.example.hakaton.api;

import com.example.hakaton.model.Category;
import com.example.hakaton.model.Petition;
import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.response.CommentsResponse;
import com.example.hakaton.service.CategoryService;
import com.example.hakaton.service.PetitionsService;
import com.example.hakaton.service.impl.PetitionServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/petitions")
@RequiredArgsConstructor
public class PetitionsController {
    private final PetitionsService petitionsService;
    private final CategoryService categoryService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String create(@ModelAttribute Petition petition, RedirectAttributes redirectAttributes, @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            petitionsService.createPetition(petition, file);
            return "redirect:/petitions/";
        } catch (Exception e) {
            throw e;
//            return "error";
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
    public String getMainPage(Model model) {
        model.addAttribute("petitions", petitionsService.findPetitions());
        model.addAttribute("categories" , categoryService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String getDetailedInfo(@PathVariable Long id, Model model) {
        Petition petition = petitionsService.findPetitionById(id);
        model.addAttribute("petition",petition);
        model.addAttribute("votes", petitionsService.getPetitionVotes(id));
        return "info";
    }

    @GetMapping("/editPetition/{id}")
    public String editPetition(@PathVariable Long id, Model model) {
        Petition petition = petitionsService.findPetitionById(id);
        model.addAttribute("petition",petition);
        model.addAttribute("categories", categoryService.findAll());
        return "edit-petition";
    }

    @PostMapping(value =  "/editPetition/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editPetition(@PathVariable Long id, @ModelAttribute Petition petition, RedirectAttributes redirectAttributes, @RequestParam(value = "file", required = false) MultipartFile file) {
        petition.setId(id);
        petitionsService.updatePetition(petition, file);
        return "redirect:/petitions/" + id;
    }
}
