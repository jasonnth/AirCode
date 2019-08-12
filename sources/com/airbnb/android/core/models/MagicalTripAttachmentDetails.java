package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.TripTemplate.Type;
import com.airbnb.android.core.models.generated.GenMagicalTripAttachmentDetails;

public class MagicalTripAttachmentDetails extends GenMagicalTripAttachmentDetails {
    public static final Creator<MagicalTripAttachmentDetails> CREATOR = new Creator<MagicalTripAttachmentDetails>() {
        public MagicalTripAttachmentDetails[] newArray(int size) {
            return new MagicalTripAttachmentDetails[size];
        }

        public MagicalTripAttachmentDetails createFromParcel(Parcel source) {
            MagicalTripAttachmentDetails object = new MagicalTripAttachmentDetails();
            object.readFromParcel(source);
            return object;
        }
    };

    public Type getTemplateProductTypeEnum() {
        return Type.fromId(getTemplateProductType());
    }
}
