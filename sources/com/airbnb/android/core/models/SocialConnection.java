package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.models.generated.GenSocialConnection;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class SocialConnection extends GenSocialConnection {
    public static final Creator<SocialConnection> CREATOR = new Creator<SocialConnection>() {
        public SocialConnection[] newArray(int size) {
            return new SocialConnection[size];
        }

        public SocialConnection createFromParcel(Parcel source) {
            SocialConnection object = new SocialConnection();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("pic_url_large")
    public void setPicUrlLarge(String value) {
        this.mPicUrlLarge = prependHttpIfNeeded(value);
    }

    @JsonProperty("pic_url_small")
    public void setPicUrlSmall(String value) {
        this.mPicUrlSmall = prependHttpIfNeeded(value);
    }

    @WrappedObject("user")
    @JsonProperty("linking_user")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setLinkingUser(User user) {
        this.mLinkingUser = user;
    }

    public String getConnectionName() {
        String connectionName = this.mLinkingUser != null ? this.mLinkingUser.getFirstName() : getOfflineLinkingName();
        if (connectionName == null) {
            return "";
        }
        return connectionName;
    }

    private String prependHttpIfNeeded(String value) {
        return value.startsWith("//") ? "http:" + value : value;
    }
}
