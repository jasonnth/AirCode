package com.airbnb.android.explore.data;

public class AutocompleteData {
    private String filterValue;
    private String location;
    private String placeId;
    private int position;
    private String source;

    public static AutocompleteData create(String location2, String placeId2, String source2, int rank, String filterValue2) {
        return new AutocompleteData(location2, placeId2, source2, rank, filterValue2);
    }

    private AutocompleteData(String location2, String placeId2, String source2, int position2, String filterValue2) {
        this.location = location2;
        this.placeId = placeId2;
        this.source = source2;
        this.position = position2;
        this.filterValue = filterValue2;
    }

    public String getLocation() {
        return this.location;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public String getSource() {
        return this.source;
    }

    public int getPosition() {
        return this.position;
    }

    public String getFilterValue() {
        return this.filterValue;
    }
}
