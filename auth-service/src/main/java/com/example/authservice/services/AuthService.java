package com.example.authservice.services;

import com.example.authservice.entities.AuthRequest;
import com.example.authservice.entities.AuthResponse;
import com.example.authservice.entities.UserVO;
import com.example.authservice.exeptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtUtil jwtUtil;
    public AuthResponse register(AuthRequest request) {
//        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserVO userVO = new UserVO(null,request.getEmail(),request.getPassword(),null);
        WebClient webClient = WebClient.create();
        userVO=webClient.post().uri("http://localhost:8082/users/checkAuth").body(Mono.just(userVO),UserVO.class).retrieve().bodyToMono(UserVO.class).block();
        if(userVO==null){
            throw new UserNotFoundException();
        }
        String accessToken = jwtUtil.generate(userVO.getEmail(),userVO.getPassword(),"ACCESS");
        String refreshToken = jwtUtil.generate(userVO.getEmail(),userVO.getPassword(),"REFRESH");
        return new AuthResponse(accessToken,refreshToken);
    }

}
