package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.models.generated.GenHelpThreadDisplayElement;
import com.google.common.collect.FluentIterable;

public class HelpThreadDisplayElement extends GenHelpThreadDisplayElement {
    public static final Creator<HelpThreadDisplayElement> CREATOR = new Creator<HelpThreadDisplayElement>() {
        public HelpThreadDisplayElement[] newArray(int size) {
            return new HelpThreadDisplayElement[size];
        }

        public HelpThreadDisplayElement createFromParcel(Parcel source) {
            HelpThreadDisplayElement object = new HelpThreadDisplayElement();
            object.readFromParcel(source);
            return object;
        }
    };
    private Type type;

    public enum Type {
        Unsupported(""),
        Message("message"),
        LinkAndDialog("link_and_dialog"),
        Map(P3Arguments.FROM_MAP);
        
        private final String key;

        private Type(String key2) {
            this.key = key2;
        }

        public static Type forKey(String key2) {
            return (Type) FluentIterable.m1283of(values()).firstMatch(HelpThreadDisplayElement$Type$$Lambda$1.lambdaFactory$(key2)).mo41059or(Unsupported);
        }
    }

    public Type getType() {
        if (this.type == null) {
            this.type = Type.forKey(this.mDisplayType);
        }
        return this.type;
    }
}
