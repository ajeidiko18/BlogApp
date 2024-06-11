package com.aj.blogapp.users.dtos;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class LoginUserRequest {
    @NonNull
    String username;
    @NonNull
    String password;
}
