package org.fimba.warehousemanagmentsystem.convertor;

import org.fimba.warehousemanagmentsystem.model.dto.ProductWarehouseDTO;
import org.fimba.warehousemanagmentsystem.model.entities.ProductWarehouse;
import org.springframework.stereotype.Component;

@Component
public class ConvertToProductWarehouseDTO {
    public ProductWarehouseDTO convert(ProductWarehouse productWarehouse){
        ProductWarehouseDTO productWarehouseDTO = new ProductWarehouseDTO();
        productWarehouseDTO.setWarehouseId(productWarehouse.getWarehouse().getId());
        productWarehouseDTO.setProductId(productWarehouse.getProduct().getId());
        productWarehouseDTO.setUserId(productWarehouse.getUserEntity().getId());
        productWarehouseDTO.setStockAmount(productWarehouse.getStockAmount());
        productWarehouseDTO.setTransactionDate(productWarehouse.getTransactionDate());

        return productWarehouseDTO;
    }
}
