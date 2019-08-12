package com.airbnb.android.core.enums;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.bugsnag.android.Severity;
import com.google.common.collect.FluentIterable;

public enum BedDetailType {
    King("king_bed", C0716R.string.bed_type_name_king, C0716R.C0717drawable.ic_bedtype_large_bed, C0716R.plurals.x_king_beds),
    Queen("queen_bed", C0716R.string.bed_type_name_queen, C0716R.C0717drawable.ic_bedtype_large_bed, C0716R.plurals.x_queen_beds),
    Double("double_bed", C0716R.string.bed_type_name_double, C0716R.C0717drawable.ic_bedtype_large_bed, C0716R.plurals.x_double_beds),
    Single("single_bed", C0716R.string.bed_type_name_single, C0716R.C0717drawable.ic_bedtype_single_bed, C0716R.plurals.x_single_beds),
    SofaBed("sofa_bed", C0716R.string.bed_type_name_sofa_bed, C0716R.C0717drawable.ic_bedtype_sofa_bed, C0716R.plurals.x_sofa_beds),
    Couch("couch", C0716R.string.bed_type_name_couch, C0716R.C0717drawable.ic_bedtype_couch, C0716R.plurals.x_couches),
    AirMattress("air_mattress", C0716R.string.bed_type_name_air_mattress, C0716R.C0717drawable.ic_bedtype_air_mattress, C0716R.plurals.x_air_mattresses),
    BunkBed("bunk_bed", C0716R.string.bed_type_name_bunk_bed, C0716R.C0717drawable.ic_bedtype_bunk_bed, C0716R.plurals.x_bunk_beds),
    FloorMattress("floor_mattress", C0716R.string.bed_type_name_floor_mattress, C0716R.C0717drawable.ic_bedtype_floor_mattress, C0716R.plurals.x_floor_mattresses),
    ToddlerBed("toddler_bed", C0716R.string.bed_type_name_toddler_bed, C0716R.C0717drawable.ic_bedtype_toddler_bed, C0716R.plurals.x_toddler_beds),
    Crib("crib", C0716R.string.bed_type_name_crib, C0716R.C0717drawable.ic_bedtype_crib, C0716R.plurals.x_cribs),
    WaterBed("water_bed", C0716R.string.bed_type_name_water_bed, C0716R.C0717drawable.ic_bedtype_water_bed, C0716R.plurals.x_water_beds),
    Hammock("hammock", C0716R.string.bed_type_name_hammock, C0716R.C0717drawable.ic_bedtype_hammock, C0716R.plurals.x_hammocks),
    Unknown("", C0716R.string.bed_type_name_other, C0716R.C0717drawable.ic_bedtype_large_bed, C0716R.plurals.x_other_beds);
    
    public static final int MAX_COUNT_PER_TYPE = 5;
    public final int iconRes;
    public final int nameRes;
    public final int pluralsRes;
    public final String serverDescKey;

    private BedDetailType(String serverDescKey2, int nameRes2, int iconRes2, int pluralsRes2) {
        this.serverDescKey = serverDescKey2;
        this.nameRes = nameRes2;
        this.iconRes = iconRes2;
        this.pluralsRes = pluralsRes2;
    }

    public static BedDetailType getTypeFromKey(String bedTypeKey) {
        BedDetailType result = (BedDetailType) FluentIterable.from((E[]) values()).firstMatch(BedDetailType$$Lambda$1.lambdaFactory$(bedTypeKey)).orNull();
        if (result == null) {
            BugsnagWrapper.notify((Throwable) new RuntimeException("Unexpected BedDetailType: " + bedTypeKey), Severity.WARNING);
        }
        return result;
    }

    public static BedDetailType getTypeFromKeyOrDefault(String bedTypeKey) {
        BedDetailType type = getTypeFromKey(bedTypeKey);
        return type != null ? type : Unknown;
    }
}
