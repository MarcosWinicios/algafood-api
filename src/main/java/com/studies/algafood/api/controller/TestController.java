package com.studies.algafood.api.controller;

import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping("/kitchens/by-name")
    public List<Kitchen> findKitchenByName(@RequestParam("name") String name){
        return kitchenRepository.findByName(name);
    }
}