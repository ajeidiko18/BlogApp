package com.aj.blogapp.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTests {

    @Autowired
    public UsersRepository usersRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    @Order(1)
    void can_create_users(){

        var user=UserEntity.builder()
                .username("ajay")
                .email("ajay@gmai.com")
                .build();
        usersRepository.save(user);

    }
    @Test
    @Order(2)
    void can_find_users(){
        var user=UserEntity.builder()
                .username("ajay")
                .email("ajay@gmai.com")
                .build();
        usersRepository.save(user);

        var users =usersRepository.findAll();

        Assertions.assertEquals(1,users.size());

    }
}
