package com.rusinek.msscbeerservice.services.inventory;

import com.rusinek.msscbeerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

import static com.rusinek.msscbeerservice.services.inventory.BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH;

/**
 * Created by Adrian Rusinek on 10.04.2020
 **/
@FeignClient(name = "inventory-service")
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
