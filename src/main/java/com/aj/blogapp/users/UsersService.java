package com.aj.blogapp.users;

import com.aj.blogapp.users.dtos.UserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity createUser(UserRequest u) {
        UserEntity newUser = modelMapper.map(u, UserEntity.class);
        // TODO encrypt the password
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

            super("User with username: " + username + " not found");
        }
        public UserNotFoundException(Long userId){

            super("User with user Id: " + userId + " not found");
        }
    }
    
}
