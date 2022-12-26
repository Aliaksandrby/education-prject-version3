package by.carlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    //@JoinColumn (name="user_id")
    private User user;

    @OneToOne
    //@JoinColumn (name="car_id")
    private Car car;

    @Column(name = "date_order")
    private Timestamp dateOrder;

    @Column(name = "date_complete_order")
    private Timestamp dateCompleteOrder;
}
