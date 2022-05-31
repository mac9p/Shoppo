package io.github.mac9p.shopapp.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private String postalCode;
    private String street;
    private Integer apartmentNumber;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;


}
