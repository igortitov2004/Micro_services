package com.example.authservice.services;

import com.example.authservice.entities.AuthRequest;
import com.example.authservice.entities.AuthResponse;
import com.example.authservice.entities.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtUtil jwtUtil;
    public AuthResponse register(AuthRequest request) {
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserVO userVO = new UserVO(null,request.getEmail(),request.getPassword(),"USER");
        String accessToken = jwtUtil.generate(userVO.getEmail(),userVO.getPassword(),"ACCESS");
        String refreshToken = jwtUtil.generate(userVO.getEmail(),userVO.getPassword(),"REFRESH");
        return new AuthResponse(accessToken,refreshToken);
    }

}
