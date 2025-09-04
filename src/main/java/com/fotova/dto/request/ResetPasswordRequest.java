package com.fotova.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ResetPasswordRequest {
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "New password cannot be empty")
    private String newPassword;
}
