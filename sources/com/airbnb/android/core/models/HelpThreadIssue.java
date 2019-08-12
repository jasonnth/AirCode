package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHelpThreadIssue;
import com.google.common.collect.FluentIterable;

public class HelpThreadIssue extends GenHelpThreadIssue {
    public static final Creator<HelpThreadIssue> CREATOR = new Creator<HelpThreadIssue>() {
        public HelpThreadIssue[] newArray(int size) {
            return new HelpThreadIssue[size];
        }

        public HelpThreadIssue createFromParcel(Parcel source) {
            HelpThreadIssue object = new HelpThreadIssue();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HelpThreadIssue that = (HelpThreadIssue) o;
        if (this.mId == that.getId()) {
            return this.mNodes.equals(that.getNodes());
        }
        return false;
    }

    public int hashCode() {
        return (this.mNodes.hashCode() * 31) + ((int) (this.mId ^ (this.mId >>> 32)));
    }

    public boolean hasNodeWithAttachments() {
        return FluentIterable.from((Iterable<E>) getNodes()).anyMatch(HelpThreadIssue$$Lambda$1.lambdaFactory$());
    }
}
