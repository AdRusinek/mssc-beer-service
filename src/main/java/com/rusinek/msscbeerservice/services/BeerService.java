package com.rusinek.msscbeerservice.services;

import com.rusinek.brewery.model.BeerDto;
import com.rusinek.brewery.model.BeerPagedList;
import com.rusinek.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * Created by Adrian Rusinek on 04.04.2020
 **/
public interface BeerService {
    BeerDto getById(UUID beerId, Boolean showInventoryOnHand);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getByUpc(String upc);
}
