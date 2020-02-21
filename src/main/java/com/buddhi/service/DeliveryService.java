package com.buddhi.service;

import com.buddhi.dto.DeliveryDto;
import com.buddhi.repository.DeliveryRepository;
import com.buddhi.model.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;

    @Cacheable("deliveries")
    public List<DeliveryDto> findAll(){
        log.info("deliveries: findAll");
        List<Delivery> deliveries = deliveryRepository.findAll();
        List<DeliveryDto> deliveryDtos = new ArrayList<>();
        for (Delivery delivery:deliveries){
            DeliveryDto deliveryDto = new DeliveryDto();
            copyProperties(delivery, deliveryDto);
            deliveryDtos.add(deliveryDto);
        }
        return deliveryDtos;
    }

    @Cacheable("delivery")
    public DeliveryDto findById(Long id){
        log.info("deliveries: findById");
        Delivery delivery = deliveryRepository.findById(id).orElse(null);
        DeliveryDto deliveryDto = new DeliveryDto();
        copyProperties(delivery,deliveryDto);
        return deliveryDto;
    }

    @Caching(evict = {
            @CacheEvict(value="delivery", allEntries=true),
            @CacheEvict(value="deliveries", allEntries=true)})
    public Delivery saveOrUpdate(DeliveryDto deliveryDto){
        log.info("deliveries: saveOrUpdate");
        Delivery delivery = Delivery.builder()
                .pickupName(deliveryDto.getPickupName())
//                .pickupAddress(deliveryDto.getPickupAddress())
//                .pickupDateTime(deliveryDto.getPickupDateTime())
//                .pickupContactNumbers(String.join(",", deliveryDto.getPickupContactNumbers()))
//                .pickupComment(deliveryDto.getPickupComment())
//                .dropName(deliveryDto.getDropName())
//                .dropAddress(deliveryDto.getDropAddress())
//                .dropContactNumbers(String.join(",", deliveryDto.getDropContactNumbers()))
                /*.dropComment(deliveryDto.getDropComment())*/
                .build();
        if(deliveryDto.getId()!=null){
            delivery.setId(deliveryDto.getId());
        }
        return deliveryRepository.save(delivery);
    }
}