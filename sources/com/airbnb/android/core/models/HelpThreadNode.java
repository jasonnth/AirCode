package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHelpThreadNode;

public class HelpThreadNode extends GenHelpThreadNode {
    public static final Creator<HelpThreadNode> CREATOR = new Creator<HelpThreadNode>() {
        public HelpThreadNode[] newArray(int size) {
            return new HelpThreadNode[size];
        }

        public HelpThreadNode createFromParcel(Parcel source) {
            HelpThreadNode object = new HelpThreadNode();
            object.readFromParcel(source);
            return object;
        }
    };

    public HelpThreadOption getSelection() {
        for (HelpThreadOption option : this.mHelpOptions) {
            if (this.mSelectedOption.equals(option.getValue())) {
                return option;
            }
        }
        throw new IllegalStateException("No matching option: " + this.mSelectedOption);
    }

    public boolean hasSelectedOption() {
        return getSelectedOption() != null;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HelpThreadNode that = (HelpThreadNode) o;
        if (this.mIsCurrent != that.isCurrent() || this.mId != that.getId()) {
            return false;
        }
        if (this.mSelectedOption != null) {
            z = this.mSelectedOption.equals(that.getSelectedOption());
        } else if (that.getSelectedOption() != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i = 0;
        if (this.mSelectedOption != null) {
            result = this.mSelectedOption.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.mIsCurrent) {
            i = 1;
        }
        return ((i2 + i) * 31) + ((int) (this.mId ^ (this.mId >>> 32)));
    }

    public boolean hasAttachments() {
        return hasSelectedOption() && getSelection().hasAttachments();
    }
}
