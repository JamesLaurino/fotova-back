package com.fotova.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    //@NotBlank
   // @Size(max = 100)
   // @Email(message = "Please provide a valid email address")
    private String email;

//    @NotBlank
//    @Size(max = 100)
    private String password;
}