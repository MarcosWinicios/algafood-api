package com.studies.algafood.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public CityNotFoundException(String message){
        super(message);
    }

    public CityNotFoundException(Long stateId) {
        this(String.format("There is no city record with code %d", stateId));
    }

}
