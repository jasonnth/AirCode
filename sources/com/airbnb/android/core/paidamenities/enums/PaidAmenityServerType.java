package com.airbnb.android.core.paidamenities.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.FluentIterable;

public enum PaidAmenityServerType implements Parcelable {
    AirportPickup(C0716R.string.paid_amenities_airport_pickup_service_title, C0716R.string.paid_amenities_airport_pickup_service_marquee_title, C0716R.string.paid_amenities_airport_pickup_service_hint_text, "airport pickup"),
    AirportDropoff(C0716R.string.paid_amenities_airport_dropoff_service_title, C0716R.string.paid_amenities_airport_dropoff_service_marquee_title, C0716R.string.paid_amenities_airport_dropoff_service_hint_text, "airport dropoff"),
    WashFoldLaundry(C0716R.string.paid_amenities_wash_and_fold_laundry_service_title, C0716R.string.paid_amenities_wash_and_fold_laundry_service_marquee_title, C0716R.string.paid_amenities_wash_and_fold_laundry_service_hint_text, "wash and fold laundry"),
    DryCleaning(C0716R.string.paid_amenities_dry_cleaning_service_title, C0716R.string.paid_amenities_dry_cleaning_service_marquee_title, C0716R.string.paid_amenities_dry_cleaning_service_hint_text, "dry cleaning"),
    FreshTowelsOrLinens(C0716R.string.paid_amenities_fresh_towel_or_linens_service_title, C0716R.string.paid_amenities_fresh_towel_or_linens_service_marquee_title, C0716R.string.paid_amenities_fresh_towel_or_linens_service_hint_text, "fresh towels linens"),
    HouseKeeping(C0716R.string.paid_amenities_housekeeping_service_title, C0716R.string.paid_amenities_housekeeping_service_marquee_title, C0716R.string.paid_amenities_housekeeping_service_hint_text, "housekeeping"),
    Unknown(-1, -1, -1, "");
    
    public static final Creator<PaidAmenityServerType> CREATOR = null;
    private final int hintBodyTextRes;
    private final int marqueeTitleRes;
    private final String serverKey;
    private final int titleRes;

    static {
        CREATOR = new Creator<PaidAmenityServerType>() {
            public PaidAmenityServerType createFromParcel(Parcel source) {
                return PaidAmenityServerType.values()[source.readInt()];
            }

            public PaidAmenityServerType[] newArray(int size) {
                return new PaidAmenityServerType[size];
            }
        };
    }

    private PaidAmenityServerType(int titleRes2, int marqueeTitleRes2, int hintBodyTextRes2, String serverKey2) {
        this.titleRes = titleRes2;
        this.marqueeTitleRes = marqueeTitleRes2;
        this.hintBodyTextRes = hintBodyTextRes2;
        this.serverKey = serverKey2;
    }

    @JsonValue
    public String getServerKey() {
        return this.serverKey;
    }

    public int getTitleRes() {
        return this.titleRes;
    }

    public int getMarqueeTitleRes() {
        return this.marqueeTitleRes;
    }

    public int getHintBodyTextRes() {
        return this.hintBodyTextRes;
    }

    public static PaidAmenityServerType findByServerKey(String serverKey2) {
        return (PaidAmenityServerType) FluentIterable.m1283of(values()).firstMatch(PaidAmenityServerType$$Lambda$1.lambdaFactory$(serverKey2)).mo41059or(Unknown);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
