package com.rusinek.msscbeerservice.services.order;

import com.rusinek.brewery.model.events.ValidateOrderRequest;
import com.rusinek.brewery.model.events.ValidateOrderResult;
import com.rusinek.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.rusinek.msscbeerservice.config.JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE;

/**
 * Created by Adrian Rusinek on 08.04.2020
 **/
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator validator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        Boolean isValid = validator.validateOrder(validateOrderRequest.getBeerOrder());

        jmsTemplate.convertAndSend(VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .isValid(isValid)
                        .orderId(validateOrderRequest.getBeerOrder().getId())
                        .build());
    }

}
