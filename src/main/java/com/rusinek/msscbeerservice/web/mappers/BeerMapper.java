package com.rusinek.msscbeerservice.web.mappers;

import com.rusinek.msscbeerservice.domain.Beer;
import com.rusinek.brewery.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * Created by Adrian Rusinek on 04.04.2020
 **/
@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
