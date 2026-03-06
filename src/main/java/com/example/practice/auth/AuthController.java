package com.example.practice.auth;

import com.example.practice.auth.dto.FormLoginRequest;
import com.example.practice.auth.dto.FormLoginResponse;
import com.example.practice.auth.dto.JwtRefreshRequest;
import com.example.practice.auth.dto.JwtRefreshResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody FormLoginRequest formLoginRequest){
        String accessToken = jwtTokenProvider.createAccessToken(formLoginRequest.loginId());
        String refreshToken = jwtTokenProvider.createRefreshToken(formLoginRequest.loginId());
        return ResponseEntity.ok(new FormLoginResponse(accessToken,refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody JwtRefreshRequest jwtRefreshRequest){
        if(jwtTokenProvider.validateToken(jwtRefreshRequest.refreshToken())){
            String username = jwtTokenProvider.getUsername(jwtRefreshRequest.refreshToken());
            String newAcessToken = jwtTokenProvider.createAccessToken(username);
            return ResponseEntity.ok(new JwtRefreshResponse(newAcessToken, jwtRefreshRequest.refreshToken()));
        }
        return ResponseEntity.status(401).body("Invalid refresh token");
    }
}
