package com.jumio.analytics;

public enum UserAction {
    CHOOSE_OTHER_COUNTRY_SELECTED("ChooseOtherCountrySelected"),
    COUNTRY_SELECTED("CountrySelected"),
    DOCUMENT_SELECTED("DocumentSelected"),
    STYLE_SELECTED("StyleSelected"),
    SCAN_OPTIONS_COMPLETED("ScanOptionsCompleted"),
    SEARCH_STARTED("SearchStarted"),
    SCAN_TRIGGERED("ScanTriggered"),
    REFOCUS("Refocus"),
    HELP_SELECTED("HelpSelected"),
    FLASH("Flash"),
    CAMERA("Camera"),
    ORIENTATION_CHANGED("OrientationChanged"),
    FALLBACK("Fallback"),
    CONFIRM("Confirm"),
    BACK("Back"),
    RETRY("Retry"),
    CANCEL("Cancel"),
    CLOSE("Close"),
    NFC_NO_EPASSPORT("NFCNoEPassort"),
    NFC_EXTRACTION_STARTED("NFCExtractionStarted");
    
    private final String value;

    private UserAction(String value2) {
        this.value = value2;
    }

    public String toString() {
        return this.value;
    }
}
