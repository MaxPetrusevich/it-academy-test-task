package com.example.service.service;

import com.example.database.data.Constants;
import com.example.database.entity.Role;
import com.example.database.entity.User;
import com.example.database.repository.UserRepository;
import com.example.service.dto.UserDto;
import com.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Sql({
        "/data.sql"
})
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreate() {
        UserDto userDto = UserDto.builder()
                .name("Name")
                .surname("Surname")
                .patronymic("Patronymic")
                .email("test@email.test")
                .role(Role.Sale_User)
                .build();

        User user = new User();


        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        Optional<UserDto> result = userService.create(userDto);

        assertEquals(Optional.of(userDto), result);

        verify(modelMapper).map(userDto, User.class);
        verify(userRepository).save(user);
        verify(modelMapper).map(user, UserDto.class);
    }

    @Test
    public void testFindAll() {
        int page = 0;
        User user1 = User.builder()
                .id(1L)
                .name("Test")
                .surname("Surname")
                .patronymic("Patronymic")
                .email("test@email.test")
                .role(Role.Sale_User)
                .build();

        User user2 = User.builder()
                .id(2L)
                .name("Name")
                .surname("Test")
                .patronymic("Patronymic")
                .email("test1@email.test")
                .role(Role.Administrator)
                .build();

        List<User> users = Arrays.asList(user1, user2);

        UserDto userDto1 = UserDto.builder()
                .name("Test")
                .surname("Surname")
                .patronymic("Patronymic")
                .email("test@email.test")
                .role(Role.Sale_User)
                .build();

        UserDto userDto2 = UserDto.builder()
                .name("Name")
                .surname("Test")
                .patronymic("Patronymic")
                .email("test1@email.test")
                .role(Role.Administrator)
                .build();

        List<UserDto> expectedUserDtos = Arrays.asList(userDto1, userDto2);

        Page<User> pageMock = new PageImpl<>(users);
        when(userRepository.findAll(any(PageRequest.class))).thenReturn(pageMock);

        when(modelMapper.map(user1, UserDto.class)).thenReturn(userDto1);
        when(modelMapper.map(user2, UserDto.class)).thenReturn(userDto2);

        Page<UserDto> result = userService.findAll(PageRequest.of(page, Constants.PAGE_SIZE, Sort.by(Constants.EMAIL)));

        assertEquals(expectedUserDtos, result.getContent());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getTotalPages());

        verify(userRepository).findAll(PageRequest.of(page, Constants.PAGE_SIZE, Sort.by(Constants.EMAIL)));
    }

}
