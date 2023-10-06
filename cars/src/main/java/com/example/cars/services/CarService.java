package com.example.cars.services;


import com.example.cars.models.Car;
import com.example.cars.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    public List<Car> listCars(String color){
        if(color!=null) return carRepository.findCarByColorContaining(color);
        return carRepository.findAll();
    }
    public void deleteCar(Long id_cars){
        carRepository.deleteById(id_cars);
    }
    public Car getCarById(Long id_cars){
        return carRepository.findById(id_cars).orElse(null);
    }


}
