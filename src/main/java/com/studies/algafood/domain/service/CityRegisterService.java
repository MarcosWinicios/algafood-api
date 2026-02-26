package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.City;
import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {
    public static final String MSG_CITY_NOT_FOUND = "There is no city record with code %d";
    public static final String MSG_CITY_IN_USE = "The city at code %d cannot be removed because it is in use";

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRegisterService stateService;

    public City save(City city) {
        State state  = this.stateService.findOrFail(city.getState().getId());
        city.setState(state);
        return this.cityRepository.save(city);
    }

    public void remove(Long cityId) {
        try {
            if(!this.cityRepository.existsById(cityId)){
                throw new EntityNotFoundException(
                        String.format(MSG_CITY_NOT_FOUND, cityId)
                );
            }
            this.cityRepository.deleteById(cityId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_CITY_IN_USE, cityId)
            );
        }
    }

    public City findOrFail(Long cityId){
        return this.cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(MSG_CITY_NOT_FOUND, cityId)
                ));
    }
}
