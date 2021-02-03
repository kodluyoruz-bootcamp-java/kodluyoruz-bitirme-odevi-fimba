package org.fimba.warehousemanagmentsystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fimba.warehousemanagmentsystem.base.WarehouseAPIResponseHolder;
import org.fimba.warehousemanagmentsystem.convertor.ConvertToGETProductWarehouse;
import org.fimba.warehousemanagmentsystem.convertor.ConvertToProductWarehouseDTO;
import org.fimba.warehousemanagmentsystem.dao.ProductCRUDRepository;
import org.fimba.warehousemanagmentsystem.dao.ProductWarehouseRepository;
import org.fimba.warehousemanagmentsystem.dao.UserCRUDRepository;
import org.fimba.warehousemanagmentsystem.dao.WarehouseCRUDRepository;
import org.fimba.warehousemanagmentsystem.model.dto.ProductWarehouseDTO;
import org.fimba.warehousemanagmentsystem.model.entities.*;
import org.fimba.warehousemanagmentsystem.model.enums.WarehouseStatus;
import org.fimba.warehousemanagmentsystem.service.ProductWarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ProductWarehouseServiceImpl implements ProductWarehouseService {
    private final ProductWarehouseRepository productWarehouseRepository;
    private final ProductCRUDRepository productCRUDRepository;
    private final WarehouseCRUDRepository warehouseCRUDRepository;
    private final UserCRUDRepository userCRUDRepository;
    private final ConvertToProductWarehouseDTO convertToProductWarehouseDTO;
    private final ConvertToGETProductWarehouse convertToGETProductWarehouse;
    private final EntityManager em;




    @Override
    @Transactional
        public WarehouseAPIResponseHolder<ProductWarehouseDTO> add(ProductWarehouseDTO productWarehouseDTO){
            productWarehouseDTO.setTransactionDate(new Date());
          ProductWarehouse productWarehouse = new ProductWarehouse();

        ProductEntity productEntity = productCRUDRepository.getOne(productWarehouseDTO.getProductId());
        WarehouseEntity warehouseEntity = warehouseCRUDRepository.getOne(productWarehouseDTO.getWarehouseId());
        UserEntity userEntity = userCRUDRepository.getOne(productWarehouseDTO.getUserId());

          productWarehouse.setStockAmount(productWarehouseDTO.getStockAmount());
          productWarehouse.setTransactionDate(productWarehouseDTO.getTransactionDate());
        ProductWarehouseId productWarehouseId = new ProductWarehouseId(warehouseEntity.getId(),productEntity.getId(),userEntity.getId());

        productWarehouse.setProductWarehouseId(productWarehouseId);
        productWarehouse.setProduct(productEntity);
        productWarehouse.setWarehouse(warehouseEntity);
        productWarehouse.setUserEntity(userEntity);
        productWarehouseRepository.save(productWarehouse);

           return new WarehouseAPIResponseHolder<>(productWarehouseDTO, HttpStatus.OK);
        }

    @Override
    public WarehouseAPIResponseHolder<Collection<ProductWarehouseDTO>> getAll() {
        Collection<ProductWarehouse> productWarehouses = productWarehouseRepository.findAll();
        Collection<ProductWarehouseDTO> productWarehouseDTOs = new ArrayList<>();
        for(ProductWarehouse s : productWarehouses){
           productWarehouseDTOs.add(convertToGETProductWarehouse.convert(s));

        }


        return new WarehouseAPIResponseHolder<>(productWarehouseDTOs,HttpStatus.OK);
    }

    @Override
    public WarehouseAPIResponseHolder<Collection<ProductEntity>> listProducts(Long warehouseId) {

        return null;
    }

    @Override
    @Transactional
    public void transfer(Long fromId, Long toId) {
        WarehouseEntity warehouseEntity = warehouseCRUDRepository.getOne(fromId);
        WarehouseEntity warehouseEntity1 = warehouseCRUDRepository.getOne(toId);
        if (warehouseEntity.getStatus().equals(WarehouseStatus.ACTIVE) && warehouseEntity1.getStatus().equals(WarehouseStatus.ACTIVE)){
            Collection<ProductWarehouse> productWarehouses = em
                    .createQuery("select c from product_warehouse c where c.warehouse_id =:fromId")
                    .setParameter("fromId",fromId)
                    .getResultList();


            for (ProductWarehouse p : productWarehouses){
                p.getProductWarehouseId().setWarehouseId(toId);
                p.setWarehouse(warehouseCRUDRepository.getOne(toId));
            }
        }

    }


}
