package com.example.controller.controllers;

import com.example.service.dto.UserDto;
import com.example.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.database.data.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;


    @GetMapping(value = USERS_URL)
    public ResponseEntity<List<UserDto>> findAll(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE, Sort.by(EMAIL));
        Page<UserDto> userPage = service.findAll(pageable);
        return new ResponseEntity<>(userPage.getContent(), HttpStatus.OK);
    }

    @PostMapping(value = USERS_URL, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> add(@RequestBody UserDto userDto) {
        Optional<UserDto> optionalUserDto = service.create(userDto);
        return optionalUserDto
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
