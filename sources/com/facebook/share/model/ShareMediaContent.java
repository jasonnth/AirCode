package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ShareMediaContent extends ShareContent<ShareMediaContent, Builder> {
    public static final Creator<ShareMediaContent> CREATOR = new Creator<ShareMediaContent>() {
        public ShareMediaContent createFromParcel(Parcel in) {
            return new ShareMediaContent(in);
        }

        public ShareMediaContent[] newArray(int size) {
            return new ShareMediaContent[size];
        }
    };
    private final List<ShareMedia> media;

    public static class Builder extends com.facebook.share.model.ShareContent.Builder<ShareMediaContent, Builder> {
        /* access modifiers changed from: private */
        public final List<ShareMedia> media = new ArrayList();

        public Builder addMedium(ShareMedia medium) {
            ShareMedia mediumToAdd;
            if (medium != null) {
                if (medium instanceof SharePhoto) {
                    mediumToAdd = new com.facebook.share.model.SharePhoto.Builder().readFrom((SharePhoto) medium).build();
                } else if (medium instanceof ShareVideo) {
                    mediumToAdd = new com.facebook.share.model.ShareVideo.Builder().readFrom((ShareVideo) medium).build();
                } else {
                    throw new IllegalArgumentException("medium must be either a SharePhoto or ShareVideo");
                }
                this.media.add(mediumToAdd);
            }
            return this;
        }

        public Builder addMedia(List<ShareMedia> media2) {
            if (media2 != null) {
                for (ShareMedia medium : media2) {
                    addMedium(medium);
                }
            }
            return this;
        }

        public ShareMediaContent build() {
            return new ShareMediaContent(this);
        }

        /* Debug info: failed to restart local var, previous not found, register: 2 */
        public Builder readFrom(ShareMediaContent model) {
            if (model == null) {
                return this;
            }
            return ((Builder) super.readFrom(model)).addMedia(model.getMedia());
        }

        public Builder setMedia(List<ShareMedia> media2) {
            this.media.clear();
            addMedia(media2);
            return this;
        }
    }

    private ShareMediaContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.media = Collections.unmodifiableList(builder.media);
    }

    ShareMediaContent(Parcel in) {
        super(in);
        this.media = Arrays.asList((ShareMedia[]) in.readParcelableArray(ShareMedia.class.getClassLoader()));
    }

    public List<ShareMedia> getMedia() {
        return this.media;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeParcelableArray((ShareMedia[]) this.media.toArray(), flags);
    }
}
