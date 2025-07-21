package com.fotova.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fotova.dto.client.ClientAddressDto;
import com.fotova.entity.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Integer id;
    private String username;
    private String email;
    private ClientAddressDto address;

    @JsonProperty("is_verified")
    private Boolean isActive;

    private List<ERole> roles;
}
