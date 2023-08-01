package com.example.controller.controllers;

import com.example.controller.ControllerApplicationTests;
import com.example.controller.application.Application;
import com.example.database.data.Constants;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


import static com.example.database.data.Constants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@SpringBootTest(classes = {ControllerApplicationTests.class, Application.class})
@Transactional
@ExtendWith(MockitoExtension.class)
@Sql({
        "/data.sql"
})
public class UserControllerTest {

    private final MockMvc mockMvc;

    @Test
    public void testAddUser_SuccessfulCreation() throws Exception {
        String requestContent = "{\"name\":\"Test\",\"surname\":\"Surname\",\"patronymic\":\"Patronymic\"," +
                "\"email\":\"test@email.com\",\"role\":\"Administrator\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddUser_InvalidEmailFormat() throws Exception {
        String requestContent = "{\"name\":\"Test\",\"surname\":\"Surname\",\"patronymic\":\"Patronymic\"," +
                "\"email\":\"invalid_email\",\"role\":\"Administrator\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddUser_MissingRequiredFields() throws Exception {
        String requestContent = "{\"name\":\"\",\"surname\":\"\",\"patronymic\":\"\"," +
                "\"email\":\"\",\"role\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddUser_InvalidData() throws Exception {
        String requestContent = "{\"name\":\"Test42\",\"surname\":\"46352\",\"patronymic\":\"Patronymic\"," +
                "\"email\":\"test@example.com\",\"role\":\"Administrator\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.USERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void testGetUsersByPage_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Constants.USERS_URL + BY_PAGE_2)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content.length()").value(6));
    }

    @Test
    public void testGetUsersByPage_DefaultPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Constants.USERS_URL + BY_PAGE_1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content.length()").value(10));
    }


    @Test
    public void testGetUsersByPage_EmptyResult() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(Constants.USERS_URL + BY_PAGE_3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content.length()").value(0));
    }
}

