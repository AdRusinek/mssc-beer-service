package com.rusinek.msscbeerservice.services;

import com.rusinek.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * Created by Adrian Rusinek on 04.04.2020
 **/
public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
