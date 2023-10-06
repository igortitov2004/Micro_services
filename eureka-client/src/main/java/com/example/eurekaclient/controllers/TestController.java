package com.example.eurekaclient.controllers;

import com.example.eurekaclient.DTO.CarDTO;
import com.example.eurekaclient.services.MailSenderService;
import com.example.eurekaclient.services.TestService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class TestController {


    private final String carsDataUrl = "http://localhost:8082/eclient/main/test";
    private final TestService testService;
    private final MailSenderService mailSenderService;
    @GetMapping("/test")
    public String test(@RequestParam (name = "color", required = false) String color,Model model){
        model.addAttribute("cars",testService.getCars(color));
        return "cars";
    }
    @PostMapping("/deleteCar/{id_cars}")
    public String deleteCar(@PathVariable Long id_cars){
        WebClient webClient = WebClient.create();
        webClient.post().uri("http://localhost:8082/ecars/cars/delete").body(Mono.just(id_cars),Long.class).retrieve().toBodilessEntity().block();
        return "redirect:" + carsDataUrl;
    }
    @GetMapping("/edit/{id_cars}")
    public String startEditCar(@PathVariable(value = "id_cars") Long id_cars, Model model){
        WebClient webClient = WebClient.create();
        CarDTO car = webClient.get().uri("http://localhost:8082/ecars/cars/carById?id_cars="+id_cars).retrieve().bodyToMono(CarDTO.class).block();
        model.addAttribute("car",car);
        return "edit-car";
    }
    @PostMapping("/edit")
    public String editCar(CarDTO carDTO){
        WebClient webClient = WebClient.create();
        webClient.post().uri("http://localhost:8082/ecars/cars/edit").body(Mono.just(carDTO),CarDTO.class).retrieve().toBodilessEntity().block();
        return "redirect:"+carsDataUrl;
    }
    @GetMapping("/save")
    public String startCreateCar(){
        return "create-car";
    }
    @PostMapping("/save")
    public String createCar(CarDTO carDTO){
        WebClient webClient = WebClient.create();
        webClient.post().uri("http://localhost:8082/ecars/cars/save").body(Mono.just(carDTO),CarDTO.class).retrieve().toBodilessEntity().block();
        return "redirect:"+carsDataUrl;
    }

    @GetMapping("/sendMail")
    public String sendMail(){
        Runnable runnable = ()->{
            synchronized (this){
                try {
                    mailSenderService.sendEmail("igor-titov-2004@mail.ru","Предмет","Тело");
                } catch (MessagingException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread mailThread = new Thread(runnable);
        mailThread.start();
        return "redirect:"+carsDataUrl;
    }
}