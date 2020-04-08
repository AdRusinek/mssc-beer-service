package com.rusinek.msscbeerservice.services.order;

import com.rusinek.brewery.model.BeerOrderDto;
import com.rusinek.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrder) {

        AtomicInteger beerNotFound = new AtomicInteger();

        beerOrder.getBeerOrderLines().forEach(orderLine -> {
            if (beerRepository.findByUpc(orderLine.getUpc()) == null) {
                beerNotFound.incrementAndGet();
            }
        });

        return beerNotFound.get() == 0;
    }
}
