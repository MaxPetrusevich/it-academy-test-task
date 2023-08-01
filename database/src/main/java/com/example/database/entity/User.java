package com.example.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.example.database.data.Constants.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = TABLE_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    @NotBlank(message = BLANK_MESSAGE)
    @Pattern(regexp = SURNAME_PATTERN,message = CHECK_CORRECT)
    private String surname;

    @Column
    @NotBlank(message = BLANK_MESSAGE)
    @Pattern(regexp = NAME_PATTERN,message = CHECK_CORRECT)
    private String name;
    @Column
    @NotBlank(message = BLANK_MESSAGE)
    @Pattern(regexp = PATRONYMIC_PATTERN,message = CHECK_CORRECT)
    private String patronymic;
    @Column
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;
}
