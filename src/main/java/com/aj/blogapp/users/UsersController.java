package com.aj.blogapp.users;

import com.aj.blogapp.common.dtos.ErrorResponse;
import com.aj.blogapp.users.dtos.UserRequest;
import com.aj.blogapp.users.dtos.CreateUserResponse;
import com.aj.blogapp.users.dtos.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<CreateUserResponse> signupUser(@RequestBody UserRequest request){
        UserEntity savedUser = usersService.createUser(request);
        URI savedUserUri = URI.create("/users/"+savedUser.getId());
        var userResponse = modelMapper.map(savedUser, CreateUserResponse.class);
        return ResponseEntity.created(savedUserUri)
                .body(userResponse);

    }

    @PostMapping("/login")
    ResponseEntity<CreateUserResponse> loginUser(@RequestBody LoginUserRequest request){
        UserEntity savedUser = usersService.loginUser(request.getUsername(),request.getPassword());
        var userResponse = modelMapper.map(savedUser, CreateUserResponse.class);
        return ResponseEntity.ok(userResponse);
    }

    @ExceptionHandler({UsersService.UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception e){
        String message;
        HttpStatus status;
        if(e instanceof UsersService.UserNotFoundException){
            message =e.getMessage();
            status = HttpStatus.NOT_FOUND;

        }
        else{
            message ="Something went wrong";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse response= ErrorResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.status(status).body(response);
    }

}
