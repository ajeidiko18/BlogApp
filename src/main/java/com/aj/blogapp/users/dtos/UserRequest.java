package com.aj.blogapp.users.dtos;

import lombok.*;


@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@Getter
public class UserRequest {
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String email;


}
