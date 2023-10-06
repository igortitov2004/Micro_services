package com.example.eurekaclient.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {


    public Long id_cars;

    public String cost;

    public String mileage;

    public String weight;

    public String  color;
}
