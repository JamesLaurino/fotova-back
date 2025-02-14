package com.fotova.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

//    @NotBlank
//    @Size(min = 3, max = 20)
    private String username;

    //@NotBlank
    //@Size(max = 50)
    //@Email(message = "Please provide a valid email address")
    private String email;

    //@NotBlank
    //@Size(max = 100)
    private String password;

}
