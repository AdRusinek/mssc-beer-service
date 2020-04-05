package com.rusinek.msscbeerservice.services.inventory;

import java.util.UUID;

/**
 * Created by Adrian Rusinek on 05.04.2020
 **/
public interface BeerInventoryService {

    Integer getOnHandInventory(UUID beerId);
}
