package com.example.eurekaclient.services;

import com.example.eurekaclient.DTO.AuthRequest;
import com.example.eurekaclient.DTO.AuthResponse;
import com.example.eurekaclient.DTO.CarDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {
    public AuthResponse register(AuthRequest request){

        WebClient webClient = WebClient.create();
        return webClient.post().uri("http://localhost:8082/auth/register").body(Mono.just(request),AuthRequest.class).retrieve().bodyToMono(AuthResponse.class).block();
    }
}
