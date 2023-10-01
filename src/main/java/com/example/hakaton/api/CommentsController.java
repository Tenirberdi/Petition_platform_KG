package com.example.hakaton.api;

import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.response.CategoryResponse;
import com.example.hakaton.model.response.CommentsResponse;
import com.example.hakaton.service.CommentsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {
//    CommentsService commentsService;
//
//    @PostMapping("/")
//    public ResponseEntity<CommentsResponse> create(@Valid @RequestBody CommentsRequest request) {
//        CommentsResponse response = commentsService.create(request);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<CommentsResponse> update(@Valid @RequestBody CommentsRequest request, @PathVariable Long id) {
//        CommentsResponse response = commentsService.update(request, id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<CommentsResponse> findById(@PathVariable Long id) {
//        CommentsResponse response = commentsService.findById(id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping("/list")
//    @ResponseStatus(HttpStatus.OK)
//    public List<CommentsResponse> findList() {
//        return commentsService.findList();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        commentsService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}
