package by.carlab.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_image_car")
public class ImageCar {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne (fetch=FetchType.EAGER)
    @JoinColumn (name="car_id")
    private Car car;

    @Lob
    @Column(name = "image",columnDefinition = "MEDIUMBLOB")
    private String image;
}
