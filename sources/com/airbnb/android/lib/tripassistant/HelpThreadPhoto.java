package com.airbnb.android.lib.tripassistant;

import android.os.Parcelable;
import com.airbnb.android.core.models.Attachment;

public abstract class HelpThreadPhoto implements Parcelable {

    public static abstract class Builder {
        public abstract Builder attachment(Attachment attachment);

        public abstract HelpThreadPhoto build();

        public abstract Builder localPath(String str);
    }

    public abstract Attachment attachment();

    public abstract String localPath();

    public abstract Builder toBuilder();

    public static HelpThreadPhoto create(String localPath, Attachment attachment) {
        return new AutoValue_HelpThreadPhoto(localPath, attachment);
    }

    public static HelpThreadPhoto create(Attachment attachment) {
        return create(null, attachment);
    }

    public static HelpThreadPhoto create(String localPath) {
        return create(localPath, null);
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean hasPath() {
        return localPath() != null;
    }

    public boolean hasPath(String localPath) {
        return localPath.equals(localPath());
    }

    public boolean hasAttachment() {
        return attachment() != null;
    }

    public String getUrl() {
        return localPath() != null ? localPath() : attachment().getUrl();
    }
}
