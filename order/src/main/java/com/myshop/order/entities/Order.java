package com.myshop.order.entities;

import com.myshop.commonDtos.events.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_Order")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order extends AbstractMappedEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "order_id", unique = true, nullable = false, updatable = false)
    @SequenceGenerator(
            name =  "order_id_sequence",
            sequenceName = "order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    private Integer orderId;

    @Column(name = "order_number" , columnDefinition = "uuid")
    private UUID orderNumber;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_fee", columnDefinition = "decimal")
    private Double orderFee;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<OrderLine> orderLineItemsList;
}
