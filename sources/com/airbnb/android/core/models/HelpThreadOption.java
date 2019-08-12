package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.HelpThreadOnClickAction.Type;
import com.airbnb.android.core.models.generated.GenHelpThreadOption;

public class HelpThreadOption extends GenHelpThreadOption {
    public static final Creator<HelpThreadOption> CREATOR = new Creator<HelpThreadOption>() {
        public HelpThreadOption[] newArray(int size) {
            return new HelpThreadOption[size];
        }

        public HelpThreadOption createFromParcel(Parcel source) {
            HelpThreadOption object = new HelpThreadOption();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasAttachments() {
        return this.mOnClick != null && this.mOnClick.getType() == Type.ShowTypeAttachmentsUploader;
    }
}
