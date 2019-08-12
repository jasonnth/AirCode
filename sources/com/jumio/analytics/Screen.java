package com.jumio.analytics;

public enum Screen {
    COUNTRY_LIST("CountryList"),
    SCAN("Scan"),
    HELP("Help"),
    CONFIRMATION("Confirmation"),
    SUBMISSION("Submission"),
    ERROR("Error"),
    SCAN_OPTIONS("ScanOptions");
    
    private final String value;

    private Screen(String value2) {
        this.value = value2;
    }

    public String toString() {
        return this.value;
    }
}
