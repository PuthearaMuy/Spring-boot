package com.springboot.theara.security.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    @Schema(name = "username",description = "Username",example = "theara")
    private String username;
    @Schema(name = "password",description = "Password",example = "12345")
    private String password;
}
