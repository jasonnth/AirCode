package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenActionCardCopy implements Parcelable {
    @JsonProperty("body")
    protected String mBody;
    @JsonProperty("confirmation_body")
    protected String mConfirmationBody;
    @JsonProperty("confirmation_title")
    protected String mConfirmationTitle;
    @JsonProperty("followup_cta")
    protected String mFollowupCta;
    @JsonProperty("followup_title")
    protected String mFollowupTitle;
    @JsonProperty("graphic_caption")
    protected String mGraphicCaption;
    @JsonProperty("legal_disclaimer")
    protected String mLegalDisclaimer;
    @JsonProperty("preview_cta")
    protected String mPreviewCta;
    @JsonProperty("primary_cta")
    protected String mPrimaryCta;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("undo")
    protected String mUndo;

    protected GenActionCardCopy(String body, String title, String primaryCta, String legalDisclaimer, String confirmationTitle, String confirmationBody, String previewCta, String graphicCaption, String followupTitle, String followupCta, String undo) {
        this();
        this.mBody = body;
        this.mTitle = title;
        this.mPrimaryCta = primaryCta;
        this.mLegalDisclaimer = legalDisclaimer;
        this.mConfirmationTitle = confirmationTitle;
        this.mConfirmationBody = confirmationBody;
        this.mPreviewCta = previewCta;
        this.mGraphicCaption = graphicCaption;
        this.mFollowupTitle = followupTitle;
        this.mFollowupCta = followupCta;
        this.mUndo = undo;
    }

    protected GenActionCardCopy() {
    }

    public String getBody() {
        return this.mBody;
    }

    @JsonProperty("body")
    public void setBody(String value) {
        this.mBody = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getPrimaryCta() {
        return this.mPrimaryCta;
    }

    @JsonProperty("primary_cta")
    public void setPrimaryCta(String value) {
        this.mPrimaryCta = value;
    }

    public String getLegalDisclaimer() {
        return this.mLegalDisclaimer;
    }

    @JsonProperty("legal_disclaimer")
    public void setLegalDisclaimer(String value) {
        this.mLegalDisclaimer = value;
    }

    public String getConfirmationTitle() {
        return this.mConfirmationTitle;
    }

    @JsonProperty("confirmation_title")
    public void setConfirmationTitle(String value) {
        this.mConfirmationTitle = value;
    }

    public String getConfirmationBody() {
        return this.mConfirmationBody;
    }

    @JsonProperty("confirmation_body")
    public void setConfirmationBody(String value) {
        this.mConfirmationBody = value;
    }

    public String getPreviewCta() {
        return this.mPreviewCta;
    }

    @JsonProperty("preview_cta")
    public void setPreviewCta(String value) {
        this.mPreviewCta = value;
    }

    public String getGraphicCaption() {
        return this.mGraphicCaption;
    }

    @JsonProperty("graphic_caption")
    public void setGraphicCaption(String value) {
        this.mGraphicCaption = value;
    }

    public String getFollowupTitle() {
        return this.mFollowupTitle;
    }

    @JsonProperty("followup_title")
    public void setFollowupTitle(String value) {
        this.mFollowupTitle = value;
    }

    public String getFollowupCta() {
        return this.mFollowupCta;
    }

    @JsonProperty("followup_cta")
    public void setFollowupCta(String value) {
        this.mFollowupCta = value;
    }

    public String getUndo() {
        return this.mUndo;
    }

    @JsonProperty("undo")
    public void setUndo(String value) {
        this.mUndo = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mBody);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mPrimaryCta);
        parcel.writeString(this.mLegalDisclaimer);
        parcel.writeString(this.mConfirmationTitle);
        parcel.writeString(this.mConfirmationBody);
        parcel.writeString(this.mPreviewCta);
        parcel.writeString(this.mGraphicCaption);
        parcel.writeString(this.mFollowupTitle);
        parcel.writeString(this.mFollowupCta);
        parcel.writeString(this.mUndo);
    }

    public void readFromParcel(Parcel source) {
        this.mBody = source.readString();
        this.mTitle = source.readString();
        this.mPrimaryCta = source.readString();
        this.mLegalDisclaimer = source.readString();
        this.mConfirmationTitle = source.readString();
        this.mConfirmationBody = source.readString();
        this.mPreviewCta = source.readString();
        this.mGraphicCaption = source.readString();
        this.mFollowupTitle = source.readString();
        this.mFollowupCta = source.readString();
        this.mUndo = source.readString();
    }
}
