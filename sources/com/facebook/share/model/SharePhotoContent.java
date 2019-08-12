package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SharePhotoContent extends ShareContent<SharePhotoContent, Builder> {
    public static final Creator<SharePhotoContent> CREATOR = new Creator<SharePhotoContent>() {
        public SharePhotoContent createFromParcel(Parcel in) {
            return new SharePhotoContent(in);
        }

        public SharePhotoContent[] newArray(int size) {
            return new SharePhotoContent[size];
        }
    };
    private final List<SharePhoto> photos;

    public static class Builder extends com.facebook.share.model.ShareContent.Builder<SharePhotoContent, Builder> {
        /* access modifiers changed from: private */
        public final List<SharePhoto> photos = new ArrayList();

        public Builder addPhoto(SharePhoto photo) {
            if (photo != null) {
                this.photos.add(new com.facebook.share.model.SharePhoto.Builder().readFrom(photo).build());
            }
            return this;
        }

        public Builder addPhotos(List<SharePhoto> photos2) {
            if (photos2 != null) {
                for (SharePhoto photo : photos2) {
                    addPhoto(photo);
                }
            }
            return this;
        }

        public SharePhotoContent build() {
            return new SharePhotoContent(this);
        }

        /* Debug info: failed to restart local var, previous not found, register: 2 */
        public Builder readFrom(SharePhotoContent model) {
            if (model == null) {
                return this;
            }
            return ((Builder) super.readFrom(model)).addPhotos(model.getPhotos());
        }

        public Builder setPhotos(List<SharePhoto> photos2) {
            this.photos.clear();
            addPhotos(photos2);
            return this;
        }
    }

    private SharePhotoContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.photos = Collections.unmodifiableList(builder.photos);
    }

    SharePhotoContent(Parcel in) {
        super(in);
        this.photos = Collections.unmodifiableList(com.facebook.share.model.SharePhoto.Builder.readPhotoListFrom(in));
    }

    public List<SharePhoto> getPhotos() {
        return this.photos;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        com.facebook.share.model.SharePhoto.Builder.writePhotoListTo(out, flags, this.photos);
    }
}
