package com.studies.algafood.api.controller;

import com.studies.algafood.api.model.KitchensXmlWrapper;
import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping
    public ResponseEntity<List<Kitchen>> list() {
        List<Kitchen> result = this.kitchenRepository.list();
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KitchensXmlWrapper> listXML() {
        List<Kitchen> result = this.kitchenRepository.list();
        KitchensXmlWrapper resultXml = new KitchensXmlWrapper(result);
        return ResponseEntity.ok(resultXml);
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> find(@PathVariable Long kitchenId) {
        Kitchen kitchen = this.kitchenRepository.find(kitchenId);

        if (kitchen != null) {
            return ResponseEntity.ok(kitchen);
        }

//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Kitchen> add(@RequestBody Kitchen kitchen) {
        kitchen = this.kitchenRepository.save(kitchen);

        return ResponseEntity.created(URI.create("localhost:8080/kitchens/" + kitchen.getId())).body(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        Kitchen currentKitchen = this.kitchenRepository.find(kitchenId);

        if (currentKitchen != null) {
            BeanUtils.copyProperties(kitchen, currentKitchen, "id");
            currentKitchen = this.kitchenRepository.save(currentKitchen);

            return ResponseEntity.ok(currentKitchen);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Void> delete(@PathVariable Long kitchenId) {

        try {
            Kitchen kitchen = this.kitchenRepository.find(kitchenId);

            if (kitchen != null) {
                this.kitchenRepository.remove(kitchen);
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
