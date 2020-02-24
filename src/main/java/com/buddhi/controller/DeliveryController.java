package com.buddhi.controller;

import com.buddhi.dto.DeliveryDto;
import com.buddhi.model.Delivery;
import com.buddhi.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/deliveries")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @PostMapping(path = "")
    public ResponseEntity<Long> createOrUpdateDelivery(@RequestBody DeliveryDto deliveryDto) {
        log.info("DeliveryController: createOrUpdateDelivery");
        Delivery delivery = deliveryService.saveOrUpdate(deliveryDto);
        return new ResponseEntity<>(delivery.getId(), HttpStatus.OK);
    }

    @GetMapping(path = "")
    public ResponseEntity<List<DeliveryDto>> getDeliveries() {
        log.info("DeliveryController: getDeliveries");
        List<DeliveryDto> deliveries = deliveryService.findAll();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DeliveryDto> getDelivery(@PathVariable Long id) {
        log.info("DeliveryController: getDelivery");
        DeliveryDto delivery = deliveryService.findById(id);
        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }
}