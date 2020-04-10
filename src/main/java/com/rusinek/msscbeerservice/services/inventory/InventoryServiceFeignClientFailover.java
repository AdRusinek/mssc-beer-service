package com.rusinek.msscbeerservice.services.inventory;

import com.rusinek.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by Adrian Rusinek on 10.04.2020
 **/
@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient{

    private final InventoryFailoverFeignClient failoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        return failoverFeignClient.getOnHandInventory();
    }
}
