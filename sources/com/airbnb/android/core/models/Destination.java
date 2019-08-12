package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenDestination;
import com.airbnb.p027n2.primitives.imaging.ImageSize;

public class Destination extends GenDestination {
    public static final Creator<Destination> CREATOR = new Creator<Destination>() {
        public Destination[] newArray(int size) {
            return new Destination[size];
        }

        public Destination createFromParcel(Parcel source) {
            Destination object = new Destination();
            object.readFromParcel(source);
            return object;
        }
    };

    public void trimForDestinationCard() {
        if (this.mPicture != null) {
            this.mPicture.retainSize(ImageSize.LandscapeLarge);
        }
    }
}
