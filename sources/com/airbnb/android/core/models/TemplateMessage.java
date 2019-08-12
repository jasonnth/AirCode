package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTemplateMessage;

public class TemplateMessage extends GenTemplateMessage {
    public static final Creator<TemplateMessage> CREATOR = new Creator<TemplateMessage>() {
        public TemplateMessage[] newArray(int size) {
            return new TemplateMessage[size];
        }

        public TemplateMessage createFromParcel(Parcel source) {
            TemplateMessage object = new TemplateMessage();
            object.readFromParcel(source);
            return object;
        }
    };
    private boolean isEditable = true;

    public void setIsEditable(boolean isEditable2) {
        this.isEditable = isEditable2;
    }

    public boolean getIsEditable() {
        return this.isEditable;
    }
}
