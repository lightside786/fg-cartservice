package com.lightside.fg.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the CartS database table.
 */
@Entity
@Table(name = "CART_ITEM")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "productId")
@ToString(exclude = "cart")
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "record_id", length = 36)
    private String recordId;

    @Column(name = "product_id" , length = 36)
    private String productId;

    @Column(name = "quantity", nullable = false, precision = 4, scale = 2)
    private Float quantity;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;


    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "last_accessed_on")
    private Timestamp lastAccessedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;


}
