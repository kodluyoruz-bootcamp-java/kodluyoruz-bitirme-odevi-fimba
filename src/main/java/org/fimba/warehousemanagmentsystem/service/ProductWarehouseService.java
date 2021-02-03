package org.fimba.warehousemanagmentsystem.service;

import org.fimba.warehousemanagmentsystem.base.WarehouseAPIBaseService;
import org.fimba.warehousemanagmentsystem.base.WarehouseAPIResponseHolder;
import org.fimba.warehousemanagmentsystem.model.dto.ProductWarehouseDTO;
import org.fimba.warehousemanagmentsystem.model.dto.WarehouseDTO;
import org.fimba.warehousemanagmentsystem.model.entities.ProductEntity;
import org.fimba.warehousemanagmentsystem.model.entities.ProductWarehouse;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public interface ProductWarehouseService{
    WarehouseAPIResponseHolder<ProductWarehouseDTO> add(ProductWarehouseDTO productWarehouseDTO);

    WarehouseAPIResponseHolder<Collection<ProductWarehouseDTO>> getAll();

    ///HttpStatus transfer(Long fromId, Long toId);

    WarehouseAPIResponseHolder<Collection<ProductEntity>> listProducts(Long warehouseId);

    void transfer(Long fromId, Long toId);



}
