package com.lightside.fg.domain;

import com.lightside.fg.exception.ApplicationException;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    @Column(name = "product_id", length = 36)
    private String productId;

    @Column(name = "primary_quantity", nullable = false, precision = 4, scale = 2)
    private Double primaryQuantity;

    @Column(name = "secondary_quantity", nullable = false, precision = 4, scale = 2)
    private Double secondaryQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "primary_uom", nullable = false)
    private UnitOfMeasure primaryUnitOfMeasure;

    @Enumerated(EnumType.STRING)
    @Column(name = "secondary_uom", nullable = false)
    private UnitOfMeasure secondaryUnitOfMeasure;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;


    @org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Generated(value = GenerationTime.ALWAYS)
    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Generated(value = GenerationTime.ALWAYS)
    @Column(name = "last_accessed_on")
    private Timestamp lastAccessedOn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    public BigDecimal getTotal() {
        return recalculateTotal();
    }


    private BigDecimal recalculateTotal() {
        BigDecimal cartItemTotal = price.multiply(BigDecimal.valueOf(primaryQuantity));

        if (null != secondaryQuantity && null != secondaryUnitOfMeasure) {

            switch (secondaryUnitOfMeasure) {

                case GM:
                    cartItemTotal = cartItemTotal.add(price.multiply(BigDecimal.valueOf(secondaryQuantity / 1000)));
                    break;
                case LBS:
                    cartItemTotal = cartItemTotal.add(price.multiply(BigDecimal.valueOf(secondaryQuantity)));
                    break;
                default:
                    throw new ApplicationException("unsupported.quantity", "unsupported.quantity");
//                    @TODO throw invalid quantity

            }
        }
        this.setTotal(cartItemTotal);
        return cartItemTotal;
    }
}

