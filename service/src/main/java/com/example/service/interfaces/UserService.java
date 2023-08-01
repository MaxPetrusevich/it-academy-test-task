package com.example.service.interfaces;

import com.example.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> create(UserDto userDto);
    Page<UserDto> findAll(Pageable pageable);
}
