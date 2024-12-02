package com.studies.algafood.api.controller;

import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping
    public List<Kitchen> list(){
        return kitchenRepository.list();
    }

    @GetMapping("/{kitchenId}")
    public Kitchen find(@PathVariable Long kitchenId){
        return this.kitchenRepository.find(kitchenId);
    }

}
