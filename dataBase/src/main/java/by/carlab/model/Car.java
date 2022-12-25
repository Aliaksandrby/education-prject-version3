package by.carlab.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "is_ordered")
    private int isOrdered;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "car",
            cascade = CascadeType.REMOVE)
    private List<ImageCar> imageCarList;

    @OneToOne(mappedBy = "car")
    private Order order;
}