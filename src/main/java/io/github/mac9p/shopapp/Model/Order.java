package io.github.mac9p.shopapp.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderPostNumber;
    private Double totalPrice;
    private Integer totalQuantity;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;
    @OneToOne
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;
    private String orderStatus;
    @CreationTimestamp
    private LocalDate dateCreated;
    @UpdateTimestamp
    private LocalDate lastUpdated;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;
}
