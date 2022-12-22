package by.carlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne  //(fetch=FetchType.EAGER)
    @JoinColumn (name="user_id")
    private User user;

    @OneToOne  //(fetch=FetchType.EAGER)
    @JoinColumn (name="car_id")
    private Car car;

    @Column(name = "date_now")
    private Date dateNow;
}
