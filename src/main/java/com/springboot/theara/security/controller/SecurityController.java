package com.springboot.theara.security.controller;

import com.springboot.theara.security.UserDetailService;
import com.springboot.theara.security.model.JwtRequest;
import com.springboot.theara.security.model.JwtResponse;
import com.springboot.theara.security.utility.JwtUtility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Security Request",description = "This controller use for Request Token.")
public class SecurityController {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/")
    public String hello()
    {
        return "hello world";
    }
    @Operation(summary = "User for request token",description = "correct you username and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Request Token success",content = @Content(mediaType = "application/json",schema = @Schema(implementation = JwtResponse.class)))
            ,@ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)

    })

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),jwtRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e)
        {
            throw new Exception("Invalid Credentials",e);
        }
        final UserDetails userDetails=userDetailService.loadUserByUsername(jwtRequest.getUsername());
        final String token=jwtUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
