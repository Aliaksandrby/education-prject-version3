package by.carlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "date_order")
    private Timestamp dateOrder;

    @Column(name = "date_complete_order")
    private Timestamp dateCompleteOrder;

    @Column(name = "time_in_order")
    private int timeInOrder;
}
