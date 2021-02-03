package org.fimba.warehousemanagmentsystem.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class ProductWarehouse {
    @EmbeddedId
    ProductWarehouseId productWarehouseId;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    ProductEntity product ;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_id")
    WarehouseEntity warehouse ;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    UserEntity userEntity;


   @Column(name = "stockAmount")
    private Long stockAmount;

    @Column(name = "transctionDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate = new Date();

    public ProductWarehouse(ProductEntity product, UserEntity user) {
        this.product = product;
        this.userEntity = user;
    }
}
