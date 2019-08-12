package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.jitney.event.logging.MtProduct.p158v1.C2444MtProduct;
import com.fasterxml.jackson.annotation.JsonCreator;

/* renamed from: com.airbnb.android.core.mt.models.RecommendationItemType */
public enum RecommendationItemType implements Parcelable {
    Experience(ShareActivityIntents.ENTRY_POINT_EXPERIENCE, C2444MtProduct.Experience),
    InsiderGuidebook(ShareActivityIntents.ENTRY_POINT_GUIDEBOOK_INSIDER, C2444MtProduct.InsiderGuidebook),
    Detour(ShareActivityIntents.ENTRY_POINT_GUIDEBOOK_DETOUR, C2444MtProduct.DetourAudioWalk),
    Meetup("guidebook_meetup", C2444MtProduct.Meetup),
    Place(ShareActivityIntents.ENTRY_POINT_GUIDEBOOK_PLACE, C2444MtProduct.Place),
    GuidebookActivity("guidebook_activity", C2444MtProduct.Activity),
    Playlist(ShareActivityIntents.ENTRY_POINT_PLAYLIST, C2444MtProduct.Playlist),
    WebLinkContent("web_link_content", null),
    Home("home", null),
    Unknown("", null);
    
    public static final Creator<RecommendationItemType> CREATOR = null;
    public final String itemType;
    public final C2444MtProduct loggingType;

    static {
        CREATOR = new Creator<RecommendationItemType>() {
            public RecommendationItemType createFromParcel(Parcel source) {
                return RecommendationItemType.values()[source.readInt()];
            }

            public RecommendationItemType[] newArray(int size) {
                return new RecommendationItemType[size];
            }
        };
    }

    private RecommendationItemType(String itemType2, C2444MtProduct loggingType2) {
        this.itemType = itemType2;
        this.loggingType = loggingType2;
    }

    @JsonCreator
    public static RecommendationItemType from(String givenItemType) {
        RecommendationItemType[] values;
        for (RecommendationItemType type : values()) {
            if (type.itemType.equals(givenItemType)) {
                return type;
            }
        }
        return Unknown;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
