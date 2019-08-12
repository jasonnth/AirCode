package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;

public enum SpaceType {
    EntireHome(C0716R.C0717drawable.icon_entire_home_selected, C0716R.string.entire_place, C0716R.string.lys_entire_home_desc, C0716R.string.entire_place_in_country, C0716R.string.entire_place_in_city, C0716R.string.entire_place_in_neighborhood, C0716R.string.lys_property_type_prompt_entire_home, "entire_home"),
    PrivateRoom(C0716R.C0717drawable.icon_private_room_selected, C0716R.string.private_room, C0716R.string.lys_private_room_desc, C0716R.string.private_room_in_country, C0716R.string.private_room_in_city, C0716R.string.private_room_in_neighborhood, C0716R.string.lys_property_type_prompt_room, "private_room"),
    SharedSpace(C0716R.C0717drawable.icon_shared_space_selected, C0716R.string.shared_room, C0716R.string.lys_shared_space_desc, C0716R.string.shared_room_in_country, C0716R.string.shared_room_in_city, C0716R.string.shared_room_in_neighborhood, C0716R.string.lys_property_type_prompt_shared_space, "shared_room");
    
    public final int descId;
    public final int iconId;
    public final int inCityId;
    public final int inCountryId;
    public final int inNeighborhoodId;
    public final int promptId;
    public final String serverDescKey;
    public final int titleId;

    private SpaceType(int iconId2, int titleId2, int descId2, int inCountryId2, int inCityId2, int inNeighborhoodId2, int promptId2, String serverDescKey2) {
        this.iconId = iconId2;
        this.titleId = titleId2;
        this.descId = descId2;
        this.inCountryId = inCountryId2;
        this.inCityId = inCityId2;
        this.inNeighborhoodId = inNeighborhoodId2;
        this.promptId = promptId2;
        this.serverDescKey = serverDescKey2;
    }

    public static SpaceType getType(int index) {
        SpaceType[] values = values();
        if (index < 0 || index >= values.length) {
            return null;
        }
        return values[index];
    }

    public static SpaceType getTypeFromKey(String roomTypeKey) {
        SpaceType[] values;
        for (SpaceType space : values()) {
            if (space.serverDescKey.equals(roomTypeKey)) {
                return space;
            }
        }
        return null;
    }

    public static SpaceType getTypeFromKeyOrDefault(String roomTypeKey) {
        SpaceType type = getTypeFromKey(roomTypeKey);
        return type != null ? type : EntireHome;
    }
}
