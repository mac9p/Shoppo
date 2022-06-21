package io.github.mac9p.shopapp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @PositiveOrZero
    private Double unitPrice;
    @URL
    private String imageUrl;
    @Column(name = "active")
    private Boolean isActive;
    @PositiveOrZero
    private Integer unitsInStock;
    @CreationTimestamp
    private LocalDate dateCreated;
    @UpdateTimestamp
    private LocalDate lastUpdated;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private ProductCategory productCategory;


    public Product(
                   String sku,
                   String name,
                   String description,
                   Double unitPrice,
                   String imageUrl,
                   Boolean isActive,
                   Integer unitsInStock,
                   ProductCategory productCategory) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.unitsInStock = unitsInStock;
        this.productCategory = productCategory;
    }


}
