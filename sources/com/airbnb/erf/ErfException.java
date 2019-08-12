package com.airbnb.erf;

public class ErfException extends RuntimeException {
    private final ExperimentBuilder builder;

    public ErfException(ExperimentBuilder builder2, String message) {
        super("Failed to deliver experiment: '" + builder2.getExperimentName() + "'. " + message);
        this.builder = builder2;
    }

    public ErfException(String message) {
        super(message);
        this.builder = null;
    }
}
