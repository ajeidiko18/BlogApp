package com.aj.blogapp.users.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateUserRequest {
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String email;
}
