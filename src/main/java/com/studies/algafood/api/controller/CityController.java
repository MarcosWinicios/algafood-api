package com.studies.algafood.api.controller;

import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.City;
import com.studies.algafood.domain.repository.CityRepository;
import com.studies.algafood.domain.service.CityRegisterService;
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

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegisterService;

    @GetMapping
    public ResponseEntity<List<City>> list() {
        List<City> result = cityRepository.findAll();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{cityId}")
    public City find(@PathVariable Long cityId) {
        return this.cityRegisterService.findOrFail(cityId);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city) {
        try {
            city = this.cityRegisterService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).body(city);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public City update(@PathVariable Long cityId, @RequestBody City city) {
        City currentCity = this.cityRegisterService.findOrFail(cityId);
        BeanUtils.copyProperties(city, currentCity, "id");
        return this.cityRegisterService.save(currentCity);
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {
        this.cityRegisterService.remove(cityId);
    }
}
