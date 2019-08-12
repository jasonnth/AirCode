package com.airbnb.android.lib.tripassistant;

import com.airbnb.android.core.models.Attachment;

/* renamed from: com.airbnb.android.lib.tripassistant.$AutoValue_HelpThreadPhoto reason: invalid class name */
abstract class C$AutoValue_HelpThreadPhoto extends HelpThreadPhoto {
    private final Attachment attachment;
    private final String localPath;

    /* renamed from: com.airbnb.android.lib.tripassistant.$AutoValue_HelpThreadPhoto$Builder */
    static final class Builder extends com.airbnb.android.lib.tripassistant.HelpThreadPhoto.Builder {
        private Attachment attachment;
        private String localPath;

        Builder() {
        }

        private Builder(HelpThreadPhoto source) {
            this.localPath = source.localPath();
            this.attachment = source.attachment();
        }

        public com.airbnb.android.lib.tripassistant.HelpThreadPhoto.Builder localPath(String localPath2) {
            this.localPath = localPath2;
            return this;
        }

        public com.airbnb.android.lib.tripassistant.HelpThreadPhoto.Builder attachment(Attachment attachment2) {
            this.attachment = attachment2;
            return this;
        }

        public HelpThreadPhoto build() {
            return new AutoValue_HelpThreadPhoto(this.localPath, this.attachment);
        }
    }

    C$AutoValue_HelpThreadPhoto(String localPath2, Attachment attachment2) {
        this.localPath = localPath2;
        this.attachment = attachment2;
    }

    public String localPath() {
        return this.localPath;
    }

    public Attachment attachment() {
        return this.attachment;
    }

    public String toString() {
        return "HelpThreadPhoto{localPath=" + this.localPath + ", attachment=" + this.attachment + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HelpThreadPhoto)) {
            return false;
        }
        HelpThreadPhoto that = (HelpThreadPhoto) o;
        if (this.localPath != null ? this.localPath.equals(that.localPath()) : that.localPath() == null) {
            if (this.attachment == null) {
                if (that.attachment() == null) {
                    return true;
                }
            } else if (this.attachment.equals(that.attachment())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.localPath == null ? 0 : this.localPath.hashCode())) * 1000003;
        if (this.attachment != null) {
            i = this.attachment.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.lib.tripassistant.HelpThreadPhoto.Builder toBuilder() {
        return new Builder(this);
    }
}
