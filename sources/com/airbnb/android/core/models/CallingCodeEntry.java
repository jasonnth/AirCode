package com.airbnb.android.core.models;

public class CallingCodeEntry implements Comparable<CallingCodeEntry> {
    private final String callingCode;
    private final String countryCode;
    private final String displayCountryName;

    public CallingCodeEntry(String callingCode2, String countryCode2, String displayCountryName2) {
        this.callingCode = callingCode2;
        this.countryCode = countryCode2;
        this.displayCountryName = displayCountryName2;
    }

    public String getCallingCode() {
        return this.callingCode;
    }

    public String getDisplayCountryName() {
        return this.displayCountryName;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public int compareTo(CallingCodeEntry another) {
        return Integer.parseInt(this.callingCode) - Integer.parseInt(another.getCallingCode());
    }
}
