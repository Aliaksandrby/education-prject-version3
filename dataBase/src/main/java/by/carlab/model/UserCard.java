package by.carlab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "t_user_card")
public class UserCard {

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

    @Column(name = "code")
    private int code;

    @Column(name = "name_card")
    private String nameOfTheCard;

    @OneToOne
    @JoinColumn (name="user_id")
    private User user;

}
