package com.example.hakaton.api;

import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.CommentsResponse;
import com.example.hakaton.model.response.UsersResponse;
import com.example.hakaton.service.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UsersController {
    UsersService usersService;

    @PostMapping("/")
    public ResponseEntity<UsersResponse> create(@Valid @RequestBody UsersRequest request) {
        UsersResponse response = usersService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsersResponse> update(@Valid @RequestBody UsersRequest request, @PathVariable Long id) {
        UsersResponse response = usersService.update(request, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> findById(@PathVariable Long id) {
        UsersResponse response = usersService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UsersResponse> findList() {
        return usersService.findList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usersService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
