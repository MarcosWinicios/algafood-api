package com.studies.algafood.api.controller;

import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import com.studies.algafood.domain.service.KitchenRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenRegisterService kitchenRegisterService;

    @GetMapping
    public ResponseEntity<List<Kitchen>> list() {
        List<Kitchen> result = this.kitchenRepository.findAll();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{kitchenId}")
    public Kitchen find(@PathVariable Long kitchenId) {
        return this.kitchenRegisterService.findOrFail(kitchenId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Kitchen> add(@RequestBody Kitchen kitchen) {
        kitchen = this.kitchenRegisterService.save(kitchen);

        return ResponseEntity.created(URI.create("localhost:8080/kitchens/" + kitchen.getId())).body(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public Kitchen update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        Kitchen currentKitchen = this.kitchenRegisterService.findOrFail(kitchenId);
        BeanUtils.copyProperties(kitchen, currentKitchen, "id");
        return this.kitchenRegisterService.save(currentKitchen);
    }


    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long kitchenId) {
        this.kitchenRegisterService.remove(kitchenId);
    }
}
