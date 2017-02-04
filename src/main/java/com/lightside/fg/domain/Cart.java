package com.lightside.fg.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


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

    @Column(name = "record_id", length = 36)
    private String recordId;

    @Column(name = "item_count", nullable = false)
    private Integer itemCount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "ship_address_id" , length = 36)
    private String shipAddressId;


    @Column(name = "bill_address_id", length = 36)
    private String billAddressId;

    @Column(name = "currency_code" , length = 3)
    private String currencyCode;


    @Column(name = "payment_id" , length = 36)
    private String paymentId;


    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "last_accessed_on")
    private Timestamp lastAccessedOn;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartItem> cartItems;
}
