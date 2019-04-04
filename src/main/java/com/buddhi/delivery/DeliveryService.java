package com.buddhi.delivery;

import com.buddhi.util.SimulateSlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames={"deliveries"})
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    SimulateSlowService simulateSlowService;

    @Cacheable
    public List<Delivery> findAll(){
        simulateSlowService.getSlowData();
        return deliveryRepository.findAll();
    }

    public void saveOrUpdate(Delivery delivery){
        deliveryRepository.save(delivery);
    }
}
