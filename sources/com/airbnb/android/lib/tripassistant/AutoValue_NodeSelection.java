package com.airbnb.android.lib.tripassistant;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.models.HelpThreadNode;
import com.airbnb.android.core.models.HelpThreadOption;

final class AutoValue_NodeSelection extends C$AutoValue_NodeSelection {
    public static final Creator<AutoValue_NodeSelection> CREATOR = new Creator<AutoValue_NodeSelection>() {
        public AutoValue_NodeSelection createFromParcel(Parcel in) {
            return new AutoValue_NodeSelection((HelpThreadIssue) in.readParcelable(HelpThreadIssue.class.getClassLoader()), (HelpThreadNode) in.readParcelable(HelpThreadNode.class.getClassLoader()), (HelpThreadOption) in.readParcelable(HelpThreadOption.class.getClassLoader()));
        }

        public AutoValue_NodeSelection[] newArray(int size) {
            return new AutoValue_NodeSelection[size];
        }
    };

    AutoValue_NodeSelection(HelpThreadIssue issue, HelpThreadNode node, HelpThreadOption option) {
        super(issue, node, option);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(issue(), flags);
        dest.writeParcelable(node(), flags);
        dest.writeParcelable(option(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
