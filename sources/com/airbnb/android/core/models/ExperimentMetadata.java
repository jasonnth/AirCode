package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenExperimentMetadata;

public class ExperimentMetadata extends GenExperimentMetadata {
    public static final Creator<ExperimentMetadata> CREATOR = new Creator<ExperimentMetadata>() {
        public ExperimentMetadata[] newArray(int size) {
            return new ExperimentMetadata[size];
        }

        public ExperimentMetadata createFromParcel(Parcel source) {
            ExperimentMetadata object = new ExperimentMetadata();
            object.readFromParcel(source);
            return object;
        }
    };
}
