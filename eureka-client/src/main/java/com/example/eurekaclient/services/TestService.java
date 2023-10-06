package com.example.eurekaclient.services;


import com.example.eurekaclient.DTO.CarDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.*;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    public List<CarDTO> getCars(String color){
        String url;
        if(color!=null){
            url="http://localhost:8082/cars/show"+"?color="+color;
        }else{
            url="http://localhost:8082/cars/show";
        }
        WebClient.Builder builder = WebClient.builder();
        return builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CarDTO>>() {
                }).block();
    }

}
