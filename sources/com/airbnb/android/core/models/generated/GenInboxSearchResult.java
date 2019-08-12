package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.AttributedText;
import com.airbnb.android.core.models.PostV2;
import com.airbnb.android.core.models.Thread;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenInboxSearchResult implements Parcelable {
    @JsonProperty("attributed_texts")
    protected List<AttributedText> mAttributedTexts;
    @JsonProperty("message")
    protected PostV2 mMessage;
    @JsonProperty("thread")
    protected Thread mThread;

    protected GenInboxSearchResult(List<AttributedText> attributedTexts, PostV2 message, Thread thread) {
        this();
        this.mAttributedTexts = attributedTexts;
        this.mMessage = message;
        this.mThread = thread;
    }

    protected GenInboxSearchResult() {
    }

    public List<AttributedText> getAttributedTexts() {
        return this.mAttributedTexts;
    }

    @JsonProperty("attributed_texts")
    public void setAttributedTexts(List<AttributedText> value) {
        this.mAttributedTexts = value;
    }

    public PostV2 getMessage() {
        return this.mMessage;
    }

    @JsonProperty("message")
    public void setMessage(PostV2 value) {
        this.mMessage = value;
    }

    public Thread getThread() {
        return this.mThread;
    }

    @JsonProperty("thread")
    public void setThread(Thread value) {
        this.mThread = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mAttributedTexts);
        parcel.writeParcelable(this.mMessage, 0);
        parcel.writeParcelable(this.mThread, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mAttributedTexts = source.createTypedArrayList(AttributedText.CREATOR);
        this.mMessage = (PostV2) source.readParcelable(PostV2.class.getClassLoader());
        this.mThread = (Thread) source.readParcelable(Thread.class.getClassLoader());
    }
}
