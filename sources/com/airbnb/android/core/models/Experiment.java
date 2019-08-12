package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.erf.ErfExperiment;
import com.airbnb.android.core.models.generated.GenExperiment;

public class Experiment extends GenExperiment {
    public static final Creator<Experiment> CREATOR = new Creator<Experiment>() {
        public Experiment[] newArray(int size) {
            return new Experiment[size];
        }

        public Experiment createFromParcel(Parcel source) {
            Experiment object = new Experiment();
            object.readFromParcel(source);
            return object;
        }
    };

    public ErfExperiment toErfExperiment(long timestamp) {
        return new ErfExperiment(this.mExperimentName, this.mAssignedTreatment, this.mTreatments, this.mSubject, (long) this.mVersion, timestamp, this.mHoldoutName);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mExperimentName.equalsIgnoreCase(((Experiment) o).mExperimentName);
    }

    public int hashCode() {
        return this.mExperimentName.hashCode();
    }
}
