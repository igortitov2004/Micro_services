package com.example.cars.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cars")
    public Long id_cars;
    @Column(name="cost")
    public String cost;

    @Column(name="mileage")
    public String mileage;

    @Column(name="weight")
    public String weight;
    @Column(name="color")
    public String  color;
}
