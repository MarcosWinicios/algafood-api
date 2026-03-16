package com.studies.algafood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INCOMPREHENSIBLE_MESSAGE("/incomprehensible-message", "Incomprehensible Message"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    BUSINESS_EXCEPTION("/business-error", "Business error"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),;


    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
