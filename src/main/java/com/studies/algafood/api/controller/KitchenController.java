package com.studies.algafood.api.controller;

import com.studies.algafood.api.model.KitchensXmlWrapper;
import com.studies.algafood.domain.model.Kitchen;
import com.studies.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @GetMapping
    public ResponseEntity<List<Kitchen>> list(){
        List<Kitchen> result = this.kitchenRepository.list();
        return ResponseEntity.ok(result);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<KitchensXmlWrapper> listXML(){
        List<Kitchen> result = this.kitchenRepository.list();
        KitchensXmlWrapper resultXml = new KitchensXmlWrapper(result);
        return ResponseEntity.ok(resultXml);
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> find(@PathVariable Long kitchenId){
        Kitchen kitchen = this.kitchenRepository.find(kitchenId);

        if(kitchen != null){
            return ResponseEntity.ok(kitchen);
        }

//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.notFound().build();
    }


}
