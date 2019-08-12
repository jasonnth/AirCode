package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.util.SparseArrayCompat;
import com.airbnb.android.core.C0716R;

public enum PropertyType implements Parcelable {
    Apartment(C0716R.C0717drawable.icon_apartment_selected, C0716R.string.lys_property_type_apartment, C0716R.string.lys_location_prompt_apartment, C0716R.string.lys_rooms_and_beds_prompt_apartment, true, true, 1, true),
    House(C0716R.C0717drawable.icon_house_selected, C0716R.string.lys_property_type_house, C0716R.string.lys_location_prompt_house, C0716R.string.lys_rooms_and_beds_prompt_house, true, true, 2, true),
    BedAndBreakfast(C0716R.C0717drawable.icon_bed_and_breakfast_selected, C0716R.string.lys_property_type_bnb, C0716R.string.lys_location_prompt_bnb, C0716R.string.lys_rooms_and_beds_prompt_bnb, true, true, 3, true),
    Loft(0, C0716R.string.lys_property_type_loft, C0716R.string.lys_location_prompt_loft, C0716R.string.lys_rooms_and_beds_prompt_loft, false, true, 35, true),
    Cabin(0, C0716R.string.lys_property_type_cabin, C0716R.string.lys_location_prompt_cabin, C0716R.string.lys_rooms_and_beds_prompt_cabin, false, true, 4, true),
    Villa(0, C0716R.string.lys_property_type_villa, C0716R.string.lys_location_prompt_villa, C0716R.string.lys_rooms_and_beds_prompt_villa, false, true, 11, true),
    Castle(0, C0716R.string.lys_property_type_castle, C0716R.string.lys_location_prompt_castle, C0716R.string.lys_rooms_and_beds_prompt_castle, false, true, 5, true),
    Dorm(0, C0716R.string.lys_property_type_dorm, C0716R.string.lys_location_prompt_dorm, C0716R.string.lys_rooms_and_beds_prompt_dorm, false, true, 9, true),
    Treehouse(0, C0716R.string.lys_property_type_treehouse, C0716R.string.lys_location_prompt_treehouse, C0716R.string.lys_rooms_and_beds_prompt_treehouse, false, true, 6, true),
    Boat(0, C0716R.string.lys_property_type_boat, C0716R.string.lys_location_prompt_boat, C0716R.string.lys_rooms_and_beds_prompt_boat, false, true, 8, true),
    Plane(0, C0716R.string.lys_property_type_plane, C0716R.string.lys_location_prompt_plane, C0716R.string.lys_rooms_and_beds_prompt_plane, false, true, 28, true),
    RV(0, C0716R.string.lys_property_type_RV, C0716R.string.lys_location_prompt_rv, C0716R.string.lys_rooms_and_beds_prompt_rv, false, true, 32, true),
    Igloo(0, C0716R.string.lys_property_type_igloo, C0716R.string.lys_location_prompt_igloo, C0716R.string.lys_rooms_and_beds_prompt_igloo, false, true, 12, true),
    Lighthouse(0, C0716R.string.lys_property_type_lighthouse, C0716R.string.lys_location_prompt_lighthouse, C0716R.string.lys_rooms_and_beds_prompt_lighthouse, false, true, 10, true),
    Yurt(0, C0716R.string.lys_property_type_yurt, C0716R.string.lys_location_prompt_yurt, C0716R.string.lys_rooms_and_beds_prompt_yurt, false, true, 15, true),
    Tipi(0, C0716R.string.lys_property_type_tipi, C0716R.string.lys_location_prompt_tipi, C0716R.string.lys_rooms_and_beds_prompt_tipi, false, true, 16, true),
    Cave(0, C0716R.string.lys_property_type_cave, C0716R.string.lys_location_prompt_cave, C0716R.string.lys_rooms_and_beds_prompt_cave, false, true, 18, true),
    Island(0, C0716R.string.lys_property_type_island, C0716R.string.lys_location_prompt_island, C0716R.string.lys_rooms_and_beds_prompt_island, false, true, 19, true),
    Chalet(0, C0716R.string.lys_property_type_chalet, C0716R.string.lys_location_prompt_chalet, C0716R.string.lys_rooms_and_beds_prompt_chalet, false, true, 22, true),
    Earthhouse(0, C0716R.string.lys_property_type_earthhouse, C0716R.string.lys_location_prompt_earthhouse, C0716R.string.lys_rooms_and_beds_prompt_earthhouse, false, true, 23, true),
    Hut(0, C0716R.string.lys_property_type_hut, C0716R.string.lys_location_prompt_hut, C0716R.string.lys_rooms_and_beds_prompt_hut, false, true, 24, true),
    Train(0, C0716R.string.lys_property_type_train, C0716R.string.lys_location_prompt_train, C0716R.string.lys_rooms_and_beds_prompt_train, false, true, 25, true),
    Tent(0, C0716R.string.lys_property_type_tent, C0716R.string.lys_location_prompt_tent, C0716R.string.lys_rooms_and_beds_prompt_tent, false, true, 34, true),
    Other(0, C0716R.string.lys_property_type_other, C0716R.string.lys_location_prompt_other, C0716R.string.lys_rooms_and_beds_prompt_other, false, true, 33, true);
    
    public static final Creator<PropertyType> CREATOR = null;
    private static final SparseArrayCompat<PropertyType> PROPERTY_TYPE_ENUM_MAP = null;
    public final int iconId;
    public final int locationPromptId;
    public final int roomsAndBedsPromptId;
    public final int serverDescKey;
    public final boolean showWhenCollapsed;
    public final boolean showWhenExpanded;
    public final int titleId;
    public final boolean visible;

    static {
        PropertyType[] values = values();
        PROPERTY_TYPE_ENUM_MAP = new SparseArrayCompat<>(values.length);
        for (PropertyType type : values) {
            PROPERTY_TYPE_ENUM_MAP.put(type.serverDescKey, type);
        }
        CREATOR = new Creator<PropertyType>() {
            public PropertyType createFromParcel(Parcel source) {
                return PropertyType.values()[source.readInt()];
            }

            public PropertyType[] newArray(int size) {
                return new PropertyType[size];
            }
        };
    }

    private PropertyType(int iconId2, int titleId2, int locationPromptId2, int roomsAndBedsPromptId2, boolean showWhenCollapsed2, boolean showWhenExpanded2, int serverDescKey2, boolean visible2) {
        this.iconId = iconId2;
        this.titleId = titleId2;
        this.locationPromptId = locationPromptId2;
        this.roomsAndBedsPromptId = roomsAndBedsPromptId2;
        this.showWhenCollapsed = showWhenCollapsed2;
        this.showWhenExpanded = showWhenExpanded2;
        this.serverDescKey = serverDescKey2;
        this.visible = visible2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public static PropertyType getType(int index) {
        PropertyType[] values = values();
        if (index < 0 || index >= values.length) {
            return null;
        }
        return values[index];
    }

    public static PropertyType getTypeFromKey(int propertyTypeId) {
        PropertyType type = (PropertyType) PROPERTY_TYPE_ENUM_MAP.get(propertyTypeId);
        return type == null ? Other : type;
    }
}
