package com.music.MusicCatalog.Controller.auth;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.music.MusicCatalog.DTO.request.RoleRequest;
import com.music.MusicCatalog.DTO.request.UserRequest;
import com.music.MusicCatalog.DTO.response.JwtAuthenticationResponse;
import com.music.MusicCatalog.DTO.response.UserResponse;
import com.music.MusicCatalog.Repository.UserRepository;
import com.music.MusicCatalog.Service.UserService;
import com.music.MusicCatalog.security.JwtTokenProvider;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "API pour l'authentification")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager; 
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;


    /***
     * Register a new user
     * @param userRequest The user creation request
     * @return The created user response
     */
    @Operation(summary = "Enregistrer un nouvel utilisateur", description = "Enregistrer un nouvel utilisateur")
    @ApiResponse(responseCode = "200", description = "Utilisateur enregistré avec succès")
    @ApiResponse(responseCode = "400", description = "Corps de la requête invalide - Erreurs de validation")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody  UserRequest userRequest) {
    	
    
    	
        userService.registerUser(userRequest);
        return ResponseEntity.ok(new UserResponse());
    }
    
      @PostMapping("/login")
      @Operation(summary = "Se connecter", description = "Se connecter")
      @ApiResponse(responseCode = "200", description = "Utilisateur connecté avec succès")
      @ApiResponse(responseCode = "401", description = "Identifiants incorrects")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(
        @Valid @RequestBody UserRequest userRequest
    ) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userRequest.getUsername(),
                    userRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            JwtAuthenticationResponse jwt = new JwtAuthenticationResponse();
            jwt.setAccessToken(tokenProvider.generateToken(authentication));
            return ResponseEntity.ok(jwt);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


}


