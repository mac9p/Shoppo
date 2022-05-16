package io.github.mac9p.shopapp.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private Double price;
    private Integer quantity;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private Order order;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Product product;
}
