package com.aj.blogapp.users;

import com.aj.blogapp.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

    @Autowired UsersService userService;



    @Test
    void can_create_users(){
        var user = userService.createUser(new CreateUserRequest(
                "Ajay",
                "ajay",
                "ajay@gmail.com"
        ));

        Assertions.assertNotNull(user);
        Assertions.assertEquals("Ajay",user.getUsername());
    }
}
