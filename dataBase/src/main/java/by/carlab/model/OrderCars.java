package by.carlab.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_order_cars")
public class OrderCars {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn (name="car_id")
    private Car car;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn (name="order_id")
    private Order order;
}
