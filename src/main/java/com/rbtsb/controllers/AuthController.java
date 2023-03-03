package com.rbtsb.controllers;

import com.rbtsb.entities.User;
import com.rbtsb.repositories.RoleRepository;
import com.rbtsb.repositories.UserRepository;
import com.rbtsb.dto.LoginDto;
import com.rbtsb.dto.SignUpDto;
import com.rbtsb.responses.ApiResponse;
import com.rbtsb.responses.BasicResponse;
import com.rbtsb.responses.LoginResponse;
import com.rbtsb.responses.SignUpResponse;
import com.rbtsb.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Api(tags = "Authentication")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getMobileOrEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        // set headers here
        response.addHeader("Access-Control-Expose-Headers", jwt);
        response.addHeader("tokenType", "Bearer");

        Optional<User> user = userRepository.findByMobileOrEmail(loginDto.getMobileOrEmail(), loginDto.getMobileOrEmail());

        if (response.getStatus() == 200) {
            return ResponseEntity.ok(LoginResponse.from(user.get()));
        } else if (response.getStatus() == 401) {
            BasicResponse basicResponse = new BasicResponse();
            basicResponse.setResult(false);
            basicResponse.setMessage("Email, Mobile or Password is not correct. Please check again.");

            return ResponseEntity.created(URI.create("basicResponse")).body(basicResponse);
        } else {
            BasicResponse basicResponse = new BasicResponse();
            basicResponse.setResult(false);
            basicResponse.setMessage("Something went wrong.");

            return ResponseEntity.created(URI.create("basicResponse")).body(basicResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "User Email is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByMobile(signUpDto.getMobile())) {
            return new ResponseEntity(new ApiResponse(false, "Mobile number already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpDto.getUserName(),
                signUpDto.getEmail(), signUpDto.getMobile(), signUpDto.getPassword(), signUpDto.isActive());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        Role userRole = roleRepository.findByName()
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        userRole.setStatusName("ACTIVE");

        user.setRoles(signUpDto.getRoles());
        userRepository.save(user);
        return ResponseEntity.ok(SignUpResponse.from(user));
    }
}