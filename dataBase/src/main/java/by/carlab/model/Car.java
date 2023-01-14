package by.carlab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_car")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private int year;

    @Column(name = "engine_description")
    private String engineDescription;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "price")
    private double price;

    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private List<Order> orderList;

    @JsonIgnore
    @Column(name = "is_order")
    private int isOrder;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "car",
            cascade = CascadeType.REMOVE)
    private List<ImageCar> imageCarList;
}