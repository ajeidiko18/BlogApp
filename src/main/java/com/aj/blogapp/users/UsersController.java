package com.aj.blogapp.users;

import com.aj.blogapp.users.dtos.CreateUserRequest;
import com.aj.blogapp.users.dtos.CreateUserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private ModelMapper modelMapper;

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("")
    ResponseEntity<CreateUserResponse> signupUser(@RequestBody CreateUserRequest request){
        UserEntity savedUser= usersService.createUser(request);
        URI savedUserUri =URI.create("/users/"+savedUser.getId());
        var userResponse = modelMapper.map(savedUser, CreateUserResponse.class);
        return ResponseEntity.created(savedUserUri)
                .body(userResponse);

    }

    @PostMapping("/login")
    public void login(@RequestBody CreateUserRequest request){

    }

}
