package com.airbnb.android.superhero;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDateTime;
import java.util.ArrayList;

final class AutoValue_SuperHeroMessage extends C$AutoValue_SuperHeroMessage {
    public static final Creator<AutoValue_SuperHeroMessage> CREATOR = new Creator<AutoValue_SuperHeroMessage>() {
        public AutoValue_SuperHeroMessage createFromParcel(Parcel in) {
            Long l;
            long readLong = in.readLong();
            AirDateTime airDateTime = (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader());
            AirDateTime airDateTime2 = (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader());
            Double d = in.readInt() == 0 ? Double.valueOf(in.readDouble()) : null;
            Double d2 = in.readInt() == 0 ? Double.valueOf(in.readDouble()) : null;
            Long l2 = in.readInt() == 0 ? Long.valueOf(in.readLong()) : null;
            String str = in.readInt() == 0 ? in.readString() : null;
            String str2 = in.readInt() == 0 ? in.readString() : null;
            ArrayList readArrayList = in.readArrayList(String.class.getClassLoader());
            boolean z = in.readInt() == 1;
            ArrayList readArrayList2 = in.readArrayList(SuperHeroAction.class.getClassLoader());
            long readLong2 = in.readLong();
            if (in.readInt() == 0) {
                l = Long.valueOf(in.readLong());
            } else {
                l = null;
            }
            return new AutoValue_SuperHeroMessage(readLong, airDateTime, airDateTime2, d, d2, l2, str, str2, readArrayList, z, readArrayList2, readLong2, l);
        }

        public AutoValue_SuperHeroMessage[] newArray(int size) {
            return new AutoValue_SuperHeroMessage[size];
        }
    };

    AutoValue_SuperHeroMessage(long id, AirDateTime starts_at, AirDateTime ends_at, Double lat, Double lng, Long radius, String dismiss_text, String hero_type_string, ArrayList<String> messages, boolean should_takeover, ArrayList<SuperHeroAction> hero_actions, long status, Long trigger_type) {
        super(id, starts_at, ends_at, lat, lng, radius, dismiss_text, hero_type_string, messages, should_takeover, hero_actions, status, trigger_type);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        dest.writeLong(mo11531id());
        dest.writeParcelable(starts_at(), flags);
        dest.writeParcelable(ends_at(), flags);
        if (lat() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeDouble(lat().doubleValue());
        }
        if (lng() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeDouble(lng().doubleValue());
        }
        if (radius() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(radius().longValue());
        }
        if (dismiss_text() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(dismiss_text());
        }
        if (hero_type_string() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(hero_type_string());
        }
        dest.writeList(messages());
        if (should_takeover()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        dest.writeList(hero_actions());
        dest.writeLong(status());
        if (trigger_type() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeLong(trigger_type().longValue());
    }

    public int describeContents() {
        return 0;
    }
}
