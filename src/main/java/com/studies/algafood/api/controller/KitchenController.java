package com.studies.algafood.api.controller;

import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
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
    public ResponseEntity<Kitchen> find(@PathVariable Long kitchenId) {
        Kitchen kitchen = this.kitchenRepository.findById(kitchenId).orElse(null);

        if (kitchen != null) {
            return ResponseEntity.ok(kitchen);
        }

//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Kitchen> add(@RequestBody Kitchen kitchen) {
        kitchen = this.kitchenRegisterService.save(kitchen);

        return ResponseEntity.created(URI.create("localhost:8080/kitchens/" + kitchen.getId())).body(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        Kitchen currentKitchen = this.kitchenRepository.findById(kitchenId).orElse(null);

        if (currentKitchen != null) {
            BeanUtils.copyProperties(kitchen, currentKitchen, "id");
            currentKitchen= this.kitchenRegisterService.save(currentKitchen);
            return ResponseEntity.ok(currentKitchen);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<?> delete(@PathVariable Long kitchenId) {
        try {
            this.kitchenRegisterService.remove(kitchenId);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
