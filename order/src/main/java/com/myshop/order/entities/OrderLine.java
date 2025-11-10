package com.myshop.order.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "t_order_line")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderLine extends AbstractMappedEntity implements Serializable  {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_id", unique = true, nullable = false, updatable = false)
    private Integer orderLineId;

    @Column(name = "sku_code")
    private String skuCode;

    @Column(name = "price_unit", columnDefinition = "decimal")
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;
}
