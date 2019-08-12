package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareVideoContent extends ShareContent<ShareVideoContent, Builder> implements ShareModel {
    public static final Creator<ShareVideoContent> CREATOR = new Creator<ShareVideoContent>() {
        public ShareVideoContent createFromParcel(Parcel in) {
            return new ShareVideoContent(in);
        }

        public ShareVideoContent[] newArray(int size) {
            return new ShareVideoContent[size];
        }
    };
    private final String contentDescription;
    private final String contentTitle;
    private final SharePhoto previewPhoto;
    private final ShareVideo video;

    public static final class Builder extends com.facebook.share.model.ShareContent.Builder<ShareVideoContent, Builder> {
        /* access modifiers changed from: private */
        public String contentDescription;
        /* access modifiers changed from: private */
        public String contentTitle;
        /* access modifiers changed from: private */
        public SharePhoto previewPhoto;
        /* access modifiers changed from: private */
        public ShareVideo video;

        public Builder setContentDescription(String contentDescription2) {
            this.contentDescription = contentDescription2;
            return this;
        }

        public Builder setContentTitle(String contentTitle2) {
            this.contentTitle = contentTitle2;
            return this;
        }

        public Builder setPreviewPhoto(SharePhoto previewPhoto2) {
            SharePhoto build;
            if (previewPhoto2 == null) {
                build = null;
            } else {
                build = new com.facebook.share.model.SharePhoto.Builder().readFrom(previewPhoto2).build();
            }
            this.previewPhoto = build;
            return this;
        }

        public Builder setVideo(ShareVideo video2) {
            if (video2 != null) {
                this.video = new com.facebook.share.model.ShareVideo.Builder().readFrom(video2).build();
            }
            return this;
        }

        public ShareVideoContent build() {
            return new ShareVideoContent(this);
        }

        /* Debug info: failed to restart local var, previous not found, register: 2 */
        public Builder readFrom(ShareVideoContent model) {
            if (model == null) {
                return this;
            }
            return ((Builder) super.readFrom(model)).setContentDescription(model.getContentDescription()).setContentTitle(model.getContentTitle()).setPreviewPhoto(model.getPreviewPhoto()).setVideo(model.getVideo());
        }
    }

    private ShareVideoContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.contentDescription = builder.contentDescription;
        this.contentTitle = builder.contentTitle;
        this.previewPhoto = builder.previewPhoto;
        this.video = builder.video;
    }

    ShareVideoContent(Parcel in) {
        super(in);
        this.contentDescription = in.readString();
        this.contentTitle = in.readString();
        com.facebook.share.model.SharePhoto.Builder previewPhotoBuilder = new com.facebook.share.model.SharePhoto.Builder().readFrom(in);
        if (previewPhotoBuilder.getImageUrl() == null && previewPhotoBuilder.getBitmap() == null) {
            this.previewPhoto = null;
        } else {
            this.previewPhoto = previewPhotoBuilder.build();
        }
        this.video = new com.facebook.share.model.ShareVideo.Builder().readFrom(in).build();
    }

    public String getContentDescription() {
        return this.contentDescription;
    }

    public String getContentTitle() {
        return this.contentTitle;
    }

    public SharePhoto getPreviewPhoto() {
        return this.previewPhoto;
    }

    public ShareVideo getVideo() {
        return this.video;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(this.contentDescription);
        out.writeString(this.contentTitle);
        out.writeParcelable(this.previewPhoto, 0);
        out.writeParcelable(this.video, 0);
    }
}
