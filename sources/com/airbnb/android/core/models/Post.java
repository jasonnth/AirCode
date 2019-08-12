package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.models.generated.GenPost;
import com.airbnb.android.core.utils.ImageUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;

public class Post extends GenPost {
    public static final Creator<Post> CREATOR = new Creator<Post>() {
        public Post[] newArray(int size) {
            return new Post[size];
        }

        public Post createFromParcel(Parcel source) {
            Post object = new Post();
            object.readFromParcel(source);
            return object;
        }
    };
    public static String IMAGE_TYPE = ContentFrameworkAnalytics.IMAGE;
    private boolean firstPostOfTheSender;
    @JsonProperty("send_state")
    protected SendState sendState = SendState.None;

    public enum ContentType {
        TEXT("text"),
        IMAGE(ContentFrameworkAnalytics.IMAGE);
        
        private final String type;

        private ContentType(String type2) {
            this.type = type2;
        }

        public String getType() {
            return this.type;
        }
    }

    public enum SendState {
        Sending,
        Failed,
        None
    }

    @JsonProperty("send_state")
    public void setSendState(SendState sendState2) {
        this.sendState = sendState2;
    }

    public SendState getSendState() {
        return this.sendState;
    }

    public boolean isSendInProgress() {
        return this.sendState == SendState.Sending;
    }

    public boolean didSendFail() {
        return this.sendState == SendState.Failed;
    }

    @WrappedObject("special_offer")
    @JsonProperty("special_offer")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setSpecialOffer(SpecialOffer specialOffer) {
        this.mSpecialOffer = specialOffer;
    }

    @JsonProperty("status")
    public void setStatus(String statusText) {
        this.mStatus = statusText == null ? null : ReservationStatus.findStatus(statusText);
    }

    public String getMessage(String defaultMessage) {
        return TextUtils.isEmpty(this.mMessage) ? defaultMessage : this.mMessage;
    }

    public void setCreatedAt(AirDateTime date) {
        this.mCreatedAt = date;
    }

    public boolean hasDates() {
        return (this.mCheckinDate == null || this.mCheckoutDate == null) ? false : true;
    }

    public boolean hasSpecialOffer() {
        return this.mSpecialOffer != null;
    }

    public boolean hasValidStatus() {
        return this.mStatus != null || hasSpecialOffer() || hasDates();
    }

    public void setTranslatedMessage(String value) {
        this.mTranslatedMessage = value;
        this.mTranslatedVersionAvailable = null;
    }

    public boolean shouldShowFlagged() {
        return getUserFlag() != null && !getUserFlag().isRedacted();
    }

    public boolean hasCompleteDates() {
        return (this.mCheckinDate == null || this.mCheckoutDate == null) ? false : true;
    }

    public void setFirstPostOfTheSender(boolean value) {
        this.firstPostOfTheSender = value;
    }

    public boolean isFirstPostOfTheSender() {
        return this.firstPostOfTheSender;
    }

    public Boolean isTranslatedVersionAvailable() {
        if (this.mTranslatedVersionAvailable == null) {
            this.mTranslatedVersionAvailable = Boolean.valueOf(!TextUtils.isEmpty(getTranslatedMessage()) && !getTranslatedMessage().equals(getMessage()));
        }
        return this.mTranslatedVersionAvailable;
    }

    public String getImageAttachmentUrl() {
        if (this.mAttachmentImages == null || this.mAttachmentImages.size() != 1) {
            return null;
        }
        return ((AttachmentImage) this.mAttachmentImages.get(0)).getUrl();
    }

    public ContentType getContentType() {
        return !TextUtils.isEmpty(this.mAttachmentType) ? ContentType.IMAGE : ContentType.TEXT;
    }

    public static Post createTextPost(String content, long userId, long postId, SendState state) {
        Post post = new Post();
        post.setCreatedAt(AirDateTime.now());
        post.setUserId(userId);
        post.setId(postId);
        post.setMessage(content);
        post.setSendState(state);
        return post;
    }

    public static Post createStatusPost(AirDateTime time, String text) {
        Post post = new Post();
        post.setCreatedAt(time);
        post.setInlineStatusText(text);
        post.setId(AirDateTime.now().getMillis());
        return post;
    }

    public static Post createImagePost(String imagePath, long userId, long postId, SendState state) {
        Post post = new Post();
        post.setCreatedAt(AirDateTime.now());
        post.setUserId(userId);
        post.setId(postId);
        post.setSendState(state);
        post.setAttachmentImages(ImmutableList.m1285of(ImageUtils.createAttachmentImage(imagePath)));
        post.setAttachmentFallbackUrl(imagePath);
        post.setAttachmentType(ContentType.IMAGE.getType());
        return post;
    }

    public int hashCode() {
        return ((int) (this.mId ^ (this.mId >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.mId != ((Post) obj).mId) {
            return false;
        }
        return true;
    }
}
