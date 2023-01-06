package by.carlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_payment")
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "name_of_card")
    private String nameOfCard;

    @Transient
    private int securedCode;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @Column(name = "sum_to_pay")
    private double sumToPay;
}
