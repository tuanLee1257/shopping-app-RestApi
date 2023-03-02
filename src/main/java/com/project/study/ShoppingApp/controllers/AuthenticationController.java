package com.project.study.ShoppingApp.controllers;

import com.project.study.ShoppingApp.config.JwtTokenProvider;
import com.project.study.ShoppingApp.models.auth.AuthenticationRequest;
import com.project.study.ShoppingApp.models.auth.AuthenticationResponse;
import com.project.study.ShoppingApp.models.user.CustomUserDetails;
import com.project.study.ShoppingApp.models.user.Role;
import com.project.study.ShoppingApp.models.user.User;
import com.project.study.ShoppingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest loginRequest) {

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

    @PostMapping("/register")
    public AuthenticationResponse registerUser(@RequestBody AuthenticationRequest registerRequest) {
        User registerUser  = new User();;
        registerUser.setUsername(registerRequest.getUsername());
        registerUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        registerUser.setRole(Role.USER);
        userRepository.save(registerUser);
        String jwt = tokenProvider.generateToken(new CustomUserDetails(registerUser));
        return new AuthenticationResponse(jwt);
    }

    @PutMapping("/updateUser/{username}")
    public ResponseEntity<String> updateUser(@PathVariable("username")String username,@RequestBody User newUser) {
        User user = userRepository.findByUsername(username);
        if (user!= null){
            user.setDisplayName(newUser.getDisplayName());
            user.setAvatar(newUser.getAvatar());
            user.setRole(newUser.getRole());
            userRepository.save(user);
        return ResponseEntity.ok().body("Update successfull");
        }
        return ResponseEntity.badRequest().body("Update fail");
    }
}
