package com.example.cars.controllers;


import com.example.cars.models.Car;
import com.example.cars.repositories.CarRepository;
import com.example.cars.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;
    @GetMapping("/show")
    public List<Car> showCars(@RequestParam (name = "color", required = false) String color){
       return carService.listCars(color);
    }
    @PostMapping("/delete")
    public void deleteCar(@RequestBody Long id_cars){
        carService.deleteCar(id_cars);
    }
    @GetMapping("/carById")
    public Car getCarById(@RequestParam(name="id_cars") Long id_cars){
        return carService.getCarById(id_cars);
    }
    @PostMapping("/edit")
    public void editStaff(@RequestBody Car car){
        carRepository.save(car);
    }

    @PostMapping("/save")
    public void saveStaff(@RequestBody Car car){
        carRepository.save(car);
    }


}
