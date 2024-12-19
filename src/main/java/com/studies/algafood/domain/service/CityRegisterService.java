package com.studies.algafood.domain.service;

import com.studies.algafood.domain.exception.EntityInUseException;
import com.studies.algafood.domain.exception.EntityNotFoundException;
import com.studies.algafood.domain.model.City;
import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.CityRepository;
import com.studies.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city) {
        State state = this.stateRepository.findById(city.getState().getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("There is no state record with code %d", city.getState().getId())
                ));

        city.setState(state);
        return this.cityRepository.save(city);
    }

    public void remove(Long cityId) {
        try {
            this.cityRepository.remove(cityId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no state record with code %d", cityId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("The kitchen at code %d cannot be removed because it is in use", cityId)
            );
        }
    }
}
