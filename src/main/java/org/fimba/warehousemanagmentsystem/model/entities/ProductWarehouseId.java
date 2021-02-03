package org.fimba.warehousemanagmentsystem.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Getter
@Setter
public class ProductWarehouseId implements Serializable {
     @Column(name = "warehouse_Id")
     private Long warehouseId;
     @Column(name = "product_Id")
     private Long productId;
     @Column(name = "user_Id")
     private Long userId;

     public ProductWarehouseId() {
     }

     public ProductWarehouseId(Long warehouseId, Long productId, Long userId) {
          this.warehouseId = warehouseId;
          this.productId = productId;
          this.userId = userId;
     }


}
