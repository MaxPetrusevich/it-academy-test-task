package com.example.service.dto;

import com.example.database.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.example.database.data.Constants.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    @NotBlank(message = BLANK_MESSAGE)
    @Pattern(regexp = SURNAME_PATTERN,message = CHECK_CORRECT)
    private String surname;
    @NotBlank(message = BLANK_MESSAGE)
    @Pattern(regexp = NAME_PATTERN,message = CHECK_CORRECT)
    private String name;
    @NotBlank(message = BLANK_MESSAGE)
    @Pattern(regexp = PATRONYMIC_PATTERN,message = CHECK_CORRECT)
    private String patronymic;
    @NotBlank(message = EMAIL_BLANK_MESSAGE)
    @Email(message = CHECK_CORRECT_EMAIL)
    private String email;
    @NotNull
    private Role role;
}


