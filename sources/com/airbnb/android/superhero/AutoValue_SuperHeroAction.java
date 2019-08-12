package com.airbnb.android.superhero;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_SuperHeroAction extends C$AutoValue_SuperHeroAction {
    public static final Creator<AutoValue_SuperHeroAction> CREATOR = new Creator<AutoValue_SuperHeroAction>() {
        public AutoValue_SuperHeroAction createFromParcel(Parcel in) {
            String str;
            Long l;
            String str2;
            Long l2;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                l = Long.valueOf(in.readLong());
            } else {
                l = null;
            }
            long readLong = in.readLong();
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                l2 = Long.valueOf(in.readLong());
            } else {
                l2 = null;
            }
            return new AutoValue_SuperHeroAction(str, l, readLong, str2, l2, (SuperHeroPostResponse) in.readParcelable(SuperHeroPostResponse.class.getClassLoader()));
        }

        public AutoValue_SuperHeroAction[] newArray(int size) {
            return new AutoValue_SuperHeroAction[size];
        }
    };

    AutoValue_SuperHeroAction(String destination, Long super_hero_message_id, long id, String text, Long destination_type, SuperHeroPostResponse post_response) {
        super(destination, super_hero_message_id, id, text, destination_type, post_response);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (destination() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(destination());
        }
        if (super_hero_message_id() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(super_hero_message_id().longValue());
        }
        dest.writeLong(mo11513id());
        if (text() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(text());
        }
        if (destination_type() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(destination_type().longValue());
        }
        dest.writeParcelable(post_response(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
