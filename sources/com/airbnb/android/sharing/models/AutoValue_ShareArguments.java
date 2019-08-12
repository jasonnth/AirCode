package com.airbnb.android.sharing.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ShareArguments extends C$AutoValue_ShareArguments {
    public static final Creator<AutoValue_ShareArguments> CREATOR = new Creator<AutoValue_ShareArguments>() {
        public AutoValue_ShareArguments createFromParcel(Parcel in) {
            String str;
            String readString = in.readString();
            Long valueOf = Long.valueOf(in.readLong());
            String readString2 = in.readString();
            String readString3 = in.readString();
            String str2 = in.readInt() == 0 ? in.readString() : null;
            Integer num = in.readInt() == 0 ? Integer.valueOf(in.readInt()) : null;
            String str3 = in.readInt() == 0 ? in.readString() : null;
            String str4 = in.readInt() == 0 ? in.readString() : null;
            String str5 = in.readInt() == 0 ? in.readString() : null;
            String str6 = in.readInt() == 0 ? in.readString() : null;
            String str7 = in.readInt() == 0 ? in.readString() : null;
            String str8 = in.readInt() == 0 ? in.readString() : null;
            String str9 = in.readInt() == 0 ? in.readString() : null;
            Integer num2 = in.readInt() == 0 ? Integer.valueOf(in.readInt()) : null;
            String str10 = in.readInt() == 0 ? in.readString() : null;
            String str11 = in.readInt() == 0 ? in.readString() : null;
            String str12 = in.readInt() == 0 ? in.readString() : null;
            String str13 = in.readInt() == 0 ? in.readString() : null;
            String str14 = in.readInt() == 0 ? in.readString() : null;
            String str15 = in.readInt() == 0 ? in.readString() : null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            return new AutoValue_ShareArguments(readString, valueOf, readString2, readString3, str2, num, str3, str4, str5, str6, str7, str8, str9, num2, str10, str11, str12, str13, str14, str15, str);
        }

        public AutoValue_ShareArguments[] newArray(int size) {
            return new AutoValue_ShareArguments[size];
        }
    };

    AutoValue_ShareArguments(String entryPoint, Long id, String url, String primaryImageUrl, String fbShareUrl, Integer productType, String location, String experienceTitle, String experienceDescription, String hostName, String guidebookTitle, String creatorOccupation, String creatorName, Integer numPlaces, String placeName, String placeType, String category, String detourTitle, String detourAbout, String detourDescription, String detourTips) {
        super(entryPoint, id, url, primaryImageUrl, fbShareUrl, productType, location, experienceTitle, experienceDescription, hostName, guidebookTitle, creatorOccupation, creatorName, numPlaces, placeName, placeType, category, detourTitle, detourAbout, detourDescription, detourTips);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(entryPoint());
        dest.writeLong(mo11468id().longValue());
        dest.writeString(url());
        dest.writeString(primaryImageUrl());
        if (fbShareUrl() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(fbShareUrl());
        }
        if (productType() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(productType().intValue());
        }
        if (location() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(location());
        }
        if (experienceTitle() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(experienceTitle());
        }
        if (experienceDescription() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(experienceDescription());
        }
        if (hostName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(hostName());
        }
        if (guidebookTitle() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(guidebookTitle());
        }
        if (creatorOccupation() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(creatorOccupation());
        }
        if (creatorName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(creatorName());
        }
        if (numPlaces() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(numPlaces().intValue());
        }
        if (placeName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(placeName());
        }
        if (placeType() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(placeType());
        }
        if (category() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(category());
        }
        if (detourTitle() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(detourTitle());
        }
        if (detourAbout() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(detourAbout());
        }
        if (detourDescription() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(detourDescription());
        }
        if (detourTips() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(detourTips());
    }

    public int describeContents() {
        return 0;
    }
}
