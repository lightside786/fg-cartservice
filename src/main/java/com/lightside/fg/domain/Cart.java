package com.lightside.fg.domain;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the CartS database table.
 */
@Entity
@Table(name = "CART")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @Generated(value = GenerationTime.INSERT)
    @Column(name = "record_id", length = 36)
    private String recordId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Generated(value = GenerationTime.INSERT)
    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Generated(value = GenerationTime.INSERT)
    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "last_accessed_on")
    private Timestamp lastAccessedOn;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cart")
    @OrderBy("id ASC")
    private Collection<CartItem> cartItems;

    @Transient
    private Set<String> promotionIds;

    public BigDecimal getTotal() {
        return recalculateTotal();
    }


    public void addCartItem(CartItem cartItem) {
        if (this.getCartItems() == null) {
            this.cartItems = new HashSet<>();
        }
        this.cartItems.add(cartItem);
    }


    public BigDecimal recalculateTotal() {
        BigDecimal cartTotal = BigDecimal.ZERO;
        if (null != this.cartItems && this.cartItems.size() > 0) {
            for (CartItem cartItem : this.cartItems) {
                cartTotal = cartTotal.add(cartItem.getTotal());
            }
        }
        // @TODO
        //this.applyCartDiscount();
        return cartTotal;

    }


}
