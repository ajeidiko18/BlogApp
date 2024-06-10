package com.aj.blogapp.users;

import com.aj.blogapp.users.dtos.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;


    }

    public UserEntity createUser(CreateUserRequest req) {

        var newUser= UserEntity.builder().username(req.getUsername())
//                .password(req.getPassword()) // TODO encrypt the password
                .email(req.getEmail()).build();
        return usersRepository.save(newUser);
    }

    public UserEntity getuser(String username){

        return usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }
    public UserEntity getuser(Long userID){

        return usersRepository.findById(userID).orElseThrow(()->new UserNotFoundException(userID));
    }

    public UserEntity loginUser(String username,String password){
        var user = usersRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));

        // TODO match the password
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException{

        public UserNotFoundException(String username){

            super("User with username:" + username + "not found");
        }
        public UserNotFoundException(Long userId){

            super("User with user Id" + userId + "not found");
        }
    }
    
}
