package com.airbnb.jitney.event.logging.FilterType.p101v1;

/* renamed from: com.airbnb.jitney.event.logging.FilterType.v1.FilterType */
public enum C2142FilterType {
    Location(1),
    Dates(2),
    Guests(3),
    RoomTypes(4),
    PriceRange(5),
    InstantBook(6),
    MoreFilters(7),
    ExperienceType(8),
    CategoriesInterests(9),
    SocialGoodExperiences(10),
    Amenity(11),
    Beds(12),
    Bedrooms(13),
    Bathrooms(14);
    
    public final int value;

    private C2142FilterType(int value2) {
        this.value = value2;
    }
}
