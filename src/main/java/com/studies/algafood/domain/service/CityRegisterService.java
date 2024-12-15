package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.City;
import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.CityRepository;
import com.studies.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city){
        State state = this.stateRepository.find(city.getState().getId());

        if(state == null){
            throw new EntityNotFoundException(
                    String.format("There is no state record with code %d", city.getState().getId())
            );
        }
        city.setState(state);
        return this.cityRepository.save(city);
    }

}
