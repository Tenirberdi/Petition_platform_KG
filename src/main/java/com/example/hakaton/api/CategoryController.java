package com.example.hakaton.api;

import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.response.CategoryResponse;
import com.example.hakaton.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController
//@RequestMapping("/api/category")
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class CategoryController {
//    CategoryService categoryService;
//
//    @PostMapping("/")
//    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
//        CategoryResponse response = categoryService.create(request);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryRequest request, @PathVariable Long id) {
//        CategoryResponse response = categoryService.update(request, id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
//        CategoryResponse response = categoryService.findById(id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping("/list")
//    @ResponseStatus(HttpStatus.OK)
//    public List<CategoryResponse> findList() {
//        return categoryService.findList();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        categoryService.delete(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
