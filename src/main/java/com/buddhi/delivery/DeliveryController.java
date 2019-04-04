package com.buddhi.delivery;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
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
    public ResponseEntity<Object> createOrUpdateDelivery(@RequestBody DeliveryDto deliveryDto){
        Delivery delivery = Delivery.builder()
                .pickupName(deliveryDto.getPickupName())
                .pickupAddress(deliveryDto.getPickupAddress())
                .pickupDateTime(deliveryDto.getPickupDateTime())
                .pickupContactNumbers(String.join(",",deliveryDto.getPickupContactNumbers()))
                .pickupComment(deliveryDto.getPickupComment())
                .dropName(deliveryDto.getDropName())
                .dropAddress(deliveryDto.getDropAddress())
                .dropContactNumbers(String.join(",",deliveryDto.getDropContactNumbers()))
                .dropComment(deliveryDto.getDropComment()).build();
        if(deliveryDto.getId()==null){//create new delivery
            deliveryService.saveOrUpdate(delivery);
        } else {//update delivery
            delivery.setId(deliveryDto.getId());
            deliveryService.saveOrUpdate(delivery);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "")
    public ResponseEntity<List<Delivery>> getDeliveries(){
        List<Delivery> deliveries = deliveryService.findAll();
        if(deliveries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Delivery>>(deliveries,HttpStatus.OK);
    }
}
