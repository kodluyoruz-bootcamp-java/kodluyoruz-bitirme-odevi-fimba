package org.fimba.warehousemanagmentsystem.convertor;

import org.fimba.warehousemanagmentsystem.model.dto.ProductWarehouseDTO;
import org.fimba.warehousemanagmentsystem.model.entities.ProductWarehouse;
import org.springframework.stereotype.Component;

@Component
public class ConvertToGETProductWarehouse {

    public ProductWarehouseDTO convert(ProductWarehouse productWarehouse){
        ProductWarehouseDTO productWarehouseDTO = new ProductWarehouseDTO();
        productWarehouseDTO.setProductEntity(productWarehouse.getProduct());
        productWarehouseDTO.setUserEntity(productWarehouse.getUserEntity());
        productWarehouseDTO.setWarehouseEntity(productWarehouse.getWarehouse());
        productWarehouseDTO.setStockAmount(productWarehouse.getStockAmount());
        productWarehouseDTO.setTransactionDate(productWarehouse.getTransactionDate());
        return productWarehouseDTO;
    }
}
