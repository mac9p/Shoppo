package io.github.mac9p.shopapp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn
    private Order order;
    private Long productId;

}
