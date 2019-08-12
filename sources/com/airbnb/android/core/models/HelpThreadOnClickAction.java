package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.generated.GenHelpThreadOnClickAction;
import com.google.common.collect.FluentIterable;

public class HelpThreadOnClickAction extends GenHelpThreadOnClickAction {
    public static final Creator<HelpThreadOnClickAction> CREATOR = new Creator<HelpThreadOnClickAction>() {
        public HelpThreadOnClickAction[] newArray(int size) {
            return new HelpThreadOnClickAction[size];
        }

        public HelpThreadOnClickAction createFromParcel(Parcel source) {
            HelpThreadOnClickAction object = new HelpThreadOnClickAction();
            object.readFromParcel(source);
            return object;
        }
    };
    private Type type;

    public enum Type {
        Unsupported(""),
        ShowTypeAmenitiesPicker("amenities_picker"),
        ShowTypeAttachmentsUploader("attachments_uploader");
        
        private final String key;

        private Type(String key2) {
            this.key = key2;
        }

        public static Type forKey(String key2) {
            Type result = (Type) FluentIterable.m1283of(values()).firstMatch(HelpThreadOnClickAction$Type$$Lambda$1.lambdaFactory$(key2)).mo41059or(Unsupported);
            if (result == Unsupported) {
                BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Unsupported help thread action type: " + key2));
            }
            return result;
        }
    }

    public Type getType() {
        if (this.type == null) {
            this.type = Type.forKey(this.mTypeKey);
        }
        return this.type;
    }
}
