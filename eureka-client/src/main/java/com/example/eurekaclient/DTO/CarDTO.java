package com.example.eurekaclient.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    public Long id_cars;

    @Pattern(regexp = "^[0-9]+", message = "Стоимость должен быть больше 0")
    @Size(min = 0,max = 6,message = "Стоимость должна находится в интервале от 0 до 999999")
    public String cost;


    @Pattern(regexp = "^[0-9]+", message = "Пробег должен быть больше 0")
    @Size(min = 0,max = 7,message = "Пробег должен находится в интервале от 0 до 9999999")
    public String mileage;

    @Pattern(regexp = "^[0-9]+", message = "Вес должен быть больше 0")
    @Size(min = 4,max = 4,message = "Вес должен находится в интервале от 1000 до 9999")
    public String weight;


    @Pattern(regexp = "^\\p{InCyrillic}+", message = "Цвет должен содержать только символы киррилицы")
    public String color;
}
