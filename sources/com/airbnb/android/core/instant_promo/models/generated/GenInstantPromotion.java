package com.airbnb.android.core.instant_promo.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.instant_promo.models.InstantPromotion.SurfaceType;
import com.airbnb.android.core.instant_promo.models.InstantPromotion.TemplateType;
import com.airbnb.android.core.instant_promo.models.InstantPromotionContent;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenInstantPromotion implements Parcelable {
    @JsonProperty("content")
    protected InstantPromotionContent mContent;
    @JsonProperty("dismiss_cap")
    protected int mDismissCap;
    @JsonProperty("erf")
    protected String mErf;
    @JsonProperty("erf_treatment")
    protected String mErfTreatment;
    @JsonProperty("expire_at")
    protected AirDateTime mExpiresAt;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("impression_cap")
    protected int mImpressionCap;
    @JsonProperty("is_capping_enabled")
    protected boolean mIsCappingEnabled;
    @JsonProperty("is_dismissable")
    protected boolean mIsDismissable;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("platform")
    protected String mPlatform;
    @JsonProperty("primary_cta_cap")
    protected int mPrimaryCtaCap;
    @JsonProperty("priority")
    protected int mPriority;
    @JsonProperty("show_promo")
    protected boolean mShowPromo;
    @JsonProperty("start_at")
    protected AirDateTime mStartsAt;
    @JsonProperty("surface")
    protected SurfaceType mSurface;
    @JsonProperty("template")
    protected TemplateType mTemplate;

    protected GenInstantPromotion(AirDateTime startsAt, AirDateTime expiresAt, InstantPromotionContent content, String name, String erf, String erfTreatment, String platform, SurfaceType surface, TemplateType template, boolean showPromo, boolean isDismissable, boolean isCappingEnabled, int dismissCap, int impressionCap, int primaryCtaCap, int priority, long id) {
        this();
        this.mStartsAt = startsAt;
        this.mExpiresAt = expiresAt;
        this.mContent = content;
        this.mName = name;
        this.mErf = erf;
        this.mErfTreatment = erfTreatment;
        this.mPlatform = platform;
        this.mSurface = surface;
        this.mTemplate = template;
        this.mShowPromo = showPromo;
        this.mIsDismissable = isDismissable;
        this.mIsCappingEnabled = isCappingEnabled;
        this.mDismissCap = dismissCap;
        this.mImpressionCap = impressionCap;
        this.mPrimaryCtaCap = primaryCtaCap;
        this.mPriority = priority;
        this.mId = id;
    }

    protected GenInstantPromotion() {
    }

    public AirDateTime getStartsAt() {
        return this.mStartsAt;
    }

    @JsonProperty("start_at")
    public void setStartsAt(AirDateTime value) {
        this.mStartsAt = value;
    }

    public AirDateTime getExpiresAt() {
        return this.mExpiresAt;
    }

    @JsonProperty("expire_at")
    public void setExpiresAt(AirDateTime value) {
        this.mExpiresAt = value;
    }

    public InstantPromotionContent getContent() {
        return this.mContent;
    }

    @JsonProperty("content")
    public void setContent(InstantPromotionContent value) {
        this.mContent = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getErf() {
        return this.mErf;
    }

    @JsonProperty("erf")
    public void setErf(String value) {
        this.mErf = value;
    }

    public String getErfTreatment() {
        return this.mErfTreatment;
    }

    @JsonProperty("erf_treatment")
    public void setErfTreatment(String value) {
        this.mErfTreatment = value;
    }

    public String getPlatform() {
        return this.mPlatform;
    }

    @JsonProperty("platform")
    public void setPlatform(String value) {
        this.mPlatform = value;
    }

    public SurfaceType getSurface() {
        return this.mSurface;
    }

    public TemplateType getTemplate() {
        return this.mTemplate;
    }

    public boolean isShowPromo() {
        return this.mShowPromo;
    }

    @JsonProperty("show_promo")
    public void setShowPromo(boolean value) {
        this.mShowPromo = value;
    }

    public boolean isDismissable() {
        return this.mIsDismissable;
    }

    @JsonProperty("is_dismissable")
    public void setIsDismissable(boolean value) {
        this.mIsDismissable = value;
    }

    public boolean isCappingEnabled() {
        return this.mIsCappingEnabled;
    }

    @JsonProperty("is_capping_enabled")
    public void setIsCappingEnabled(boolean value) {
        this.mIsCappingEnabled = value;
    }

    public int getDismissCap() {
        return this.mDismissCap;
    }

    @JsonProperty("dismiss_cap")
    public void setDismissCap(int value) {
        this.mDismissCap = value;
    }

    public int getImpressionCap() {
        return this.mImpressionCap;
    }

    @JsonProperty("impression_cap")
    public void setImpressionCap(int value) {
        this.mImpressionCap = value;
    }

    public int getPrimaryCtaCap() {
        return this.mPrimaryCtaCap;
    }

    @JsonProperty("primary_cta_cap")
    public void setPrimaryCtaCap(int value) {
        this.mPrimaryCtaCap = value;
    }

    public int getPriority() {
        return this.mPriority;
    }

    @JsonProperty("priority")
    public void setPriority(int value) {
        this.mPriority = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartsAt, 0);
        parcel.writeParcelable(this.mExpiresAt, 0);
        parcel.writeParcelable(this.mContent, 0);
        parcel.writeString(this.mName);
        parcel.writeString(this.mErf);
        parcel.writeString(this.mErfTreatment);
        parcel.writeString(this.mPlatform);
        parcel.writeSerializable(this.mSurface);
        parcel.writeSerializable(this.mTemplate);
        parcel.writeBooleanArray(new boolean[]{this.mShowPromo, this.mIsDismissable, this.mIsCappingEnabled});
        parcel.writeInt(this.mDismissCap);
        parcel.writeInt(this.mImpressionCap);
        parcel.writeInt(this.mPrimaryCtaCap);
        parcel.writeInt(this.mPriority);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mStartsAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mContent = (InstantPromotionContent) source.readParcelable(InstantPromotionContent.class.getClassLoader());
        this.mName = source.readString();
        this.mErf = source.readString();
        this.mErfTreatment = source.readString();
        this.mPlatform = source.readString();
        this.mSurface = (SurfaceType) source.readSerializable();
        this.mTemplate = (TemplateType) source.readSerializable();
        boolean[] bools = source.createBooleanArray();
        this.mShowPromo = bools[0];
        this.mIsDismissable = bools[1];
        this.mIsCappingEnabled = bools[2];
        this.mDismissCap = source.readInt();
        this.mImpressionCap = source.readInt();
        this.mPrimaryCtaCap = source.readInt();
        this.mPriority = source.readInt();
        this.mId = source.readLong();
    }
}
