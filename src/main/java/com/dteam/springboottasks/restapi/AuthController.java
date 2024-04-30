package com.dteam.springboottasks.restapi;

import com.dteam.springboottasks.exception.DuplicatedUserInfoException;
import com.dteam.springboottasks.model.User;
import com.dteam.springboottasks.repository.UserRepository;
import com.dteam.springboottasks.restapi.dto.AuthResponse;
import com.dteam.springboottasks.restapi.dto.LoginRequest;
import com.dteam.springboottasks.restapi.dto.SignUpRequest;
import com.dteam.springboottasks.security.TokenProvider;
import com.dteam.springboottasks.security.WebSecurityConfig;
import com.dteam.springboottasks.service.SignUpService;
import com.dteam.springboottasks.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final SignUpService signUpService;

    @PostMapping("/authenticate")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
        return new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
            throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpRequest.getUsername()));
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s already been used", signUpRequest.getEmail()));
        }

        User user = new User();

        userService.saveUser(mapSignUpRequestToUser(signUpRequest, user));
        signUpService.confirmEmailSigningUp(signUpRequest, user);

        String token = authenticateAndGetToken(signUpRequest.getUsername(), signUpRequest.getPassword());
        return new AuthResponse(token);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("emailToken") String emailToken) {
        return signUpService.confirmToken(emailToken);
    }

    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generate(authentication);
    }

    private User mapSignUpRequestToUser(SignUpRequest signUpRequest, User user) {
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(WebSecurityConfig.USER);
        user.setIsEmailConfirmed(false);
        return user;
    }
}
