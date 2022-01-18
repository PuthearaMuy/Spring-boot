package com.springboot.theara.security.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    @Schema(name = "jwtToken",description = "Token give from spring security.")
    private String jwtToken;
}
