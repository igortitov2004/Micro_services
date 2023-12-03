package com.example.eurekaclient.controllers;

import com.example.eurekaclient.DTO.AuthRequest;
import com.example.eurekaclient.DTO.CarDTO;
import com.example.eurekaclient.services.MailSenderService;
import com.example.eurekaclient.services.TestService;
import com.example.eurekaclient.services.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.ws.rs.HeaderParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class ClientController {
    private final String carsDataUrl = "http://localhost:8082/main/test";
    private final TestService testService;
    private final MailSenderService mailSenderService;
    private final UserService userService;
    String jwtToken;
    @PostMapping ("/login")
    public String login(AuthRequest request,Model model){
         jwtToken = userService.register(request).getAccessToken();
         model.addAttribute("token",jwtToken);
        return "token";
    }
    @GetMapping("/startLogin")
    public String testTok(){
        return "test-token";
    }
    @GetMapping("/test")
    public String test(@RequestParam (name = "color", required = false) String color,Model model){
        model.addAttribute("cars",testService.getCars(color,jwtToken));
        return "ura/cars";
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
    @GetMapping("/startSave")
    public String startCreateCar(Model model){
        {
            CarDTO startCar = new CarDTO();
            startCar.setColor("");
            startCar.setCost("0");
            startCar.setMileage("0");
            startCar.setWeight("1000");
            model.addAttribute("carDetail",startCar);
        }
        return "create-car";
    }
    @PostMapping("/save")
    public String createCar(@Valid @ModelAttribute("carDetail") CarDTO carDTO,BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("err",result.hasErrors());
            return "create-car";
        }
        WebClient webClient = WebClient.create();
        webClient.post().uri("http://localhost:8082/ecars/cars/save").body(Mono.just(carDTO),CarDTO.class).retrieve().toBodilessEntity().block();
        return "redirect:"+carsDataUrl;
    }
    @GetMapping("/sendMail")
    public String sendMail(@RequestParam(name = "email") String email){
        Runnable runnable = ()->{
            synchronized (this){
                try {
                    mailSenderService.sendEmail(email,"Предмет","Тело");
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
