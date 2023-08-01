package com.example.database.repositoryTest;

import com.example.database.DatabaseApplication;
import com.example.database.DatabaseApplicationTests;
import com.example.database.entity.User;
import com.example.database.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;


import static org.assertj.core.api.Assertions.assertThat;

/*@Sql({
        "/data.sql"
})*/
@SpringBootTest(classes = DatabaseApplicationTests.class)
public class UserRepositoryTest {
    private final UserRepository repository;
    @Autowired
    public UserRepositoryTest(UserRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAllByPageableTest() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("email"));
        Page<User> result = repository.findAll(pageable);
        System.out.println(result.getContent());
        assertThat(result).hasSize(2);
        assertThat(result.getContent().get(0).getEmail())
                .isEqualTo("test10@email.test");
    }
}
