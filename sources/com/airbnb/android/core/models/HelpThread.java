package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHelpThread;

public class HelpThread extends GenHelpThread {
    public static final Creator<HelpThread> CREATOR = new Creator<HelpThread>() {
        public HelpThread[] newArray(int size) {
            return new HelpThread[size];
        }

        public HelpThread createFromParcel(Parcel source) {
            HelpThread object = new HelpThread();
            object.readFromParcel(source);
            return object;
        }
    };

    public long getHighestMajorVersion() {
        long version = 0;
        for (HelpThreadIssue issue : getIssues()) {
            if (issue.getTreeMajorVersion() > version) {
                version = issue.getTreeMajorVersion();
            }
        }
        return version;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HelpThread that = (HelpThread) o;
        if (this.mId != that.mId) {
            return false;
        }
        if (this.mIssues != null) {
            return this.mIssues.equals(that.mIssues);
        }
        if (that.mIssues != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.mIssues != null ? this.mIssues.hashCode() : 0) * 31) + ((int) (this.mId ^ (this.mId >>> 32)));
    }
}
