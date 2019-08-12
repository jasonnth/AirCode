package com.kount.api;

enum SoftError {
    SERVICE_UNAVAILABLE("not_available"),
    UNEXPECTED("unexpected"),
    SKIPPED("skipped"),
    PERMISSION_DENIED("permission_denied");
    
    private final String name;

    private SoftError(String name2) {
        this.name = name2;
    }

    public String toString() {
        return this.name;
    }
}
