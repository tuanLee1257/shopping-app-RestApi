package com.project.study.ShoppingApp.controllers;

import com.project.study.ShoppingApp.config.JwtTokenProvider;
import com.project.study.ShoppingApp.models.AuthenticationRequest;
import com.project.study.ShoppingApp.models.AuthenticationResponse;
import com.project.study.ShoppingApp.models.user.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest loginRequest) {

        // Xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new AuthenticationResponse(jwt);
    }

//    @PostMapping("/register")
//    public AuthenticationResponse register(@RequestBody AuthenticationRequest registerRequest){
//
//        return new AuthenticationResponse(jwt);
//    }

}
