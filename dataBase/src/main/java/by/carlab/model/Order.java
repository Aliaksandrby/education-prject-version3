package by.carlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "order")
    private List<OrderCars> orderCarsList;

    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="user_id")
    private User user;
}
