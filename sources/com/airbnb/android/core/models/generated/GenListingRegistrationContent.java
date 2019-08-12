package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ListingRegistrationContentSection;
import com.airbnb.android.core.models.ListingRegistrationExemptionFields;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public abstract class GenListingRegistrationContent implements Parcelable {
    @JsonProperty("email_label")
    protected String mEmailLabel;
    @JsonProperty("exemption_fields")
    protected ListingRegistrationExemptionFields mExemptionFields;
    @JsonProperty("existing_license_call_to_action")
    protected String mExistingLicenseCallToAction;
    @JsonProperty("explanation_steps")
    protected ArrayList<String> mExplanationSteps;
    @JsonProperty("final_call_to_action")
    protected String mFinalCallToAction;
    @JsonProperty("help_center_id")
    protected int mHelpCenterId;
    @JsonProperty("help_link_text")
    protected String mHelpLinkText;
    @JsonProperty("initial_overview_section")
    protected ListingRegistrationContentSection mInitialOverviewSection;
    @JsonProperty("intro_image_resource_url")
    protected String mIntroImageResourceUrl;
    @JsonProperty("intro_subtitle")
    protected String mIntroSubtitle;
    @JsonProperty("intro_title")
    protected String mIntroTitle;
    @JsonProperty("license_label")
    protected String mLicenseLabel;
    @JsonProperty("license_placeholder")
    protected String mLicensePlaceholder;
    @JsonProperty("license_step_subtitles")
    protected ArrayList<String> mLicenseStepSubtitles;
    @JsonProperty("license_step_title")
    protected String mLicenseStepTitle;
    @JsonProperty("license_subtitle")
    protected String mLicenseSubtitle;
    @JsonProperty("license_title")
    protected String mLicenseTitle;
    @JsonProperty("license_tooltip")
    protected String mLicenseTooltip;
    @JsonProperty("license_tooltip_title")
    protected String mLicenseTooltipTitle;
    @JsonProperty("name_label")
    protected String mNameLabel;
    @JsonProperty("next_call_to_action")
    protected String mNextCallToAction;
    @JsonProperty("next_steps_section")
    protected ListingRegistrationContentSection mNextStepsSection;
    @JsonProperty("next_subtitles")
    protected ArrayList<String> mNextSubtitles;
    @JsonProperty("next_title")
    protected String mNextTitle;
    @JsonProperty("post_submission_call_to_action")
    protected String mPostSubmissionCallToAction;
    @JsonProperty("pre_submission_call_to_action")
    protected String mPreSubmissionCallToAction;
    @JsonProperty("registration_field_label")
    protected String mRegistrationFieldLabel;
    @JsonProperty("registration_field_pending_value")
    protected String mRegistrationFieldPendingValue;
    @JsonProperty("registration_field_subtitle")
    protected String mRegistrationFieldSubtitle;
    @JsonProperty("review_disclaimer")
    protected String mReviewDisclaimer;
    @JsonProperty("review_help_link_text")
    protected String mReviewHelpLinkText;
    @JsonProperty("review_subtitle")
    protected String mReviewSubtitle;
    @JsonProperty("review_title")
    protected String mReviewTitle;
    @JsonProperty("step_subtitles")
    protected ArrayList<String> mStepSubtitles;
    @JsonProperty("step_title")
    protected String mStepTitle;
    @JsonProperty("terms_and_conditions")
    protected ListingRegistrationContentSection mTermsAndConditions;
    @JsonProperty("unregistered_call_to_action")
    protected String mUnregisteredCallToAction;
    @JsonProperty("unregistered_exempted_call_to_action")
    protected String mUnregisteredExemptedCallToAction;

    protected GenListingRegistrationContent(ArrayList<String> explanationSteps, ArrayList<String> licenseStepSubtitles, ArrayList<String> nextSubtitles, ArrayList<String> stepSubtitles, ListingRegistrationContentSection termsAndConditions, ListingRegistrationContentSection initialOverviewSection, ListingRegistrationContentSection nextStepsSection, ListingRegistrationExemptionFields exemptionFields, String emailLabel, String existingLicenseCallToAction, String finalCallToAction, String helpLinkText, String introImageResourceUrl, String introSubtitle, String introTitle, String licenseLabel, String licensePlaceholder, String licenseStepTitle, String licenseSubtitle, String licenseTitle, String licenseTooltip, String licenseTooltipTitle, String nameLabel, String nextCallToAction, String nextTitle, String postSubmissionCallToAction, String preSubmissionCallToAction, String registrationFieldLabel, String registrationFieldPendingValue, String registrationFieldSubtitle, String reviewDisclaimer, String reviewHelpLinkText, String reviewSubtitle, String reviewTitle, String stepTitle, String unregisteredCallToAction, String unregisteredExemptedCallToAction, int helpCenterId) {
        this();
        this.mExplanationSteps = explanationSteps;
        this.mLicenseStepSubtitles = licenseStepSubtitles;
        this.mNextSubtitles = nextSubtitles;
        this.mStepSubtitles = stepSubtitles;
        this.mTermsAndConditions = termsAndConditions;
        this.mInitialOverviewSection = initialOverviewSection;
        this.mNextStepsSection = nextStepsSection;
        this.mExemptionFields = exemptionFields;
        this.mEmailLabel = emailLabel;
        this.mExistingLicenseCallToAction = existingLicenseCallToAction;
        this.mFinalCallToAction = finalCallToAction;
        this.mHelpLinkText = helpLinkText;
        this.mIntroImageResourceUrl = introImageResourceUrl;
        this.mIntroSubtitle = introSubtitle;
        this.mIntroTitle = introTitle;
        this.mLicenseLabel = licenseLabel;
        this.mLicensePlaceholder = licensePlaceholder;
        this.mLicenseStepTitle = licenseStepTitle;
        this.mLicenseSubtitle = licenseSubtitle;
        this.mLicenseTitle = licenseTitle;
        this.mLicenseTooltip = licenseTooltip;
        this.mLicenseTooltipTitle = licenseTooltipTitle;
        this.mNameLabel = nameLabel;
        this.mNextCallToAction = nextCallToAction;
        this.mNextTitle = nextTitle;
        this.mPostSubmissionCallToAction = postSubmissionCallToAction;
        this.mPreSubmissionCallToAction = preSubmissionCallToAction;
        this.mRegistrationFieldLabel = registrationFieldLabel;
        this.mRegistrationFieldPendingValue = registrationFieldPendingValue;
        this.mRegistrationFieldSubtitle = registrationFieldSubtitle;
        this.mReviewDisclaimer = reviewDisclaimer;
        this.mReviewHelpLinkText = reviewHelpLinkText;
        this.mReviewSubtitle = reviewSubtitle;
        this.mReviewTitle = reviewTitle;
        this.mStepTitle = stepTitle;
        this.mUnregisteredCallToAction = unregisteredCallToAction;
        this.mUnregisteredExemptedCallToAction = unregisteredExemptedCallToAction;
        this.mHelpCenterId = helpCenterId;
    }

    protected GenListingRegistrationContent() {
    }

    public ArrayList<String> getExplanationSteps() {
        return this.mExplanationSteps;
    }

    @JsonProperty("explanation_steps")
    public void setExplanationSteps(ArrayList<String> value) {
        this.mExplanationSteps = value;
    }

    public ArrayList<String> getLicenseStepSubtitles() {
        return this.mLicenseStepSubtitles;
    }

    @JsonProperty("license_step_subtitles")
    public void setLicenseStepSubtitles(ArrayList<String> value) {
        this.mLicenseStepSubtitles = value;
    }

    public ArrayList<String> getNextSubtitles() {
        return this.mNextSubtitles;
    }

    @JsonProperty("next_subtitles")
    public void setNextSubtitles(ArrayList<String> value) {
        this.mNextSubtitles = value;
    }

    public ArrayList<String> getStepSubtitles() {
        return this.mStepSubtitles;
    }

    @JsonProperty("step_subtitles")
    public void setStepSubtitles(ArrayList<String> value) {
        this.mStepSubtitles = value;
    }

    public ListingRegistrationContentSection getTermsAndConditions() {
        return this.mTermsAndConditions;
    }

    @JsonProperty("terms_and_conditions")
    public void setTermsAndConditions(ListingRegistrationContentSection value) {
        this.mTermsAndConditions = value;
    }

    public ListingRegistrationContentSection getInitialOverviewSection() {
        return this.mInitialOverviewSection;
    }

    @JsonProperty("initial_overview_section")
    public void setInitialOverviewSection(ListingRegistrationContentSection value) {
        this.mInitialOverviewSection = value;
    }

    public ListingRegistrationContentSection getNextStepsSection() {
        return this.mNextStepsSection;
    }

    @JsonProperty("next_steps_section")
    public void setNextStepsSection(ListingRegistrationContentSection value) {
        this.mNextStepsSection = value;
    }

    public ListingRegistrationExemptionFields getExemptionFields() {
        return this.mExemptionFields;
    }

    @JsonProperty("exemption_fields")
    public void setExemptionFields(ListingRegistrationExemptionFields value) {
        this.mExemptionFields = value;
    }

    public String getEmailLabel() {
        return this.mEmailLabel;
    }

    @JsonProperty("email_label")
    public void setEmailLabel(String value) {
        this.mEmailLabel = value;
    }

    public String getExistingLicenseCallToAction() {
        return this.mExistingLicenseCallToAction;
    }

    @JsonProperty("existing_license_call_to_action")
    public void setExistingLicenseCallToAction(String value) {
        this.mExistingLicenseCallToAction = value;
    }

    public String getFinalCallToAction() {
        return this.mFinalCallToAction;
    }

    @JsonProperty("final_call_to_action")
    public void setFinalCallToAction(String value) {
        this.mFinalCallToAction = value;
    }

    public String getHelpLinkText() {
        return this.mHelpLinkText;
    }

    @JsonProperty("help_link_text")
    public void setHelpLinkText(String value) {
        this.mHelpLinkText = value;
    }

    public String getIntroImageResourceUrl() {
        return this.mIntroImageResourceUrl;
    }

    @JsonProperty("intro_image_resource_url")
    public void setIntroImageResourceUrl(String value) {
        this.mIntroImageResourceUrl = value;
    }

    public String getIntroSubtitle() {
        return this.mIntroSubtitle;
    }

    @JsonProperty("intro_subtitle")
    public void setIntroSubtitle(String value) {
        this.mIntroSubtitle = value;
    }

    public String getIntroTitle() {
        return this.mIntroTitle;
    }

    @JsonProperty("intro_title")
    public void setIntroTitle(String value) {
        this.mIntroTitle = value;
    }

    public String getLicenseLabel() {
        return this.mLicenseLabel;
    }

    @JsonProperty("license_label")
    public void setLicenseLabel(String value) {
        this.mLicenseLabel = value;
    }

    public String getLicensePlaceholder() {
        return this.mLicensePlaceholder;
    }

    @JsonProperty("license_placeholder")
    public void setLicensePlaceholder(String value) {
        this.mLicensePlaceholder = value;
    }

    public String getLicenseStepTitle() {
        return this.mLicenseStepTitle;
    }

    @JsonProperty("license_step_title")
    public void setLicenseStepTitle(String value) {
        this.mLicenseStepTitle = value;
    }

    public String getLicenseSubtitle() {
        return this.mLicenseSubtitle;
    }

    @JsonProperty("license_subtitle")
    public void setLicenseSubtitle(String value) {
        this.mLicenseSubtitle = value;
    }

    public String getLicenseTitle() {
        return this.mLicenseTitle;
    }

    @JsonProperty("license_title")
    public void setLicenseTitle(String value) {
        this.mLicenseTitle = value;
    }

    public String getLicenseTooltip() {
        return this.mLicenseTooltip;
    }

    @JsonProperty("license_tooltip")
    public void setLicenseTooltip(String value) {
        this.mLicenseTooltip = value;
    }

    public String getLicenseTooltipTitle() {
        return this.mLicenseTooltipTitle;
    }

    @JsonProperty("license_tooltip_title")
    public void setLicenseTooltipTitle(String value) {
        this.mLicenseTooltipTitle = value;
    }

    public String getNameLabel() {
        return this.mNameLabel;
    }

    @JsonProperty("name_label")
    public void setNameLabel(String value) {
        this.mNameLabel = value;
    }

    public String getNextCallToAction() {
        return this.mNextCallToAction;
    }

    @JsonProperty("next_call_to_action")
    public void setNextCallToAction(String value) {
        this.mNextCallToAction = value;
    }

    public String getNextTitle() {
        return this.mNextTitle;
    }

    @JsonProperty("next_title")
    public void setNextTitle(String value) {
        this.mNextTitle = value;
    }

    public String getPostSubmissionCallToAction() {
        return this.mPostSubmissionCallToAction;
    }

    @JsonProperty("post_submission_call_to_action")
    public void setPostSubmissionCallToAction(String value) {
        this.mPostSubmissionCallToAction = value;
    }

    public String getPreSubmissionCallToAction() {
        return this.mPreSubmissionCallToAction;
    }

    @JsonProperty("pre_submission_call_to_action")
    public void setPreSubmissionCallToAction(String value) {
        this.mPreSubmissionCallToAction = value;
    }

    public String getRegistrationFieldLabel() {
        return this.mRegistrationFieldLabel;
    }

    @JsonProperty("registration_field_label")
    public void setRegistrationFieldLabel(String value) {
        this.mRegistrationFieldLabel = value;
    }

    public String getRegistrationFieldPendingValue() {
        return this.mRegistrationFieldPendingValue;
    }

    @JsonProperty("registration_field_pending_value")
    public void setRegistrationFieldPendingValue(String value) {
        this.mRegistrationFieldPendingValue = value;
    }

    public String getRegistrationFieldSubtitle() {
        return this.mRegistrationFieldSubtitle;
    }

    @JsonProperty("registration_field_subtitle")
    public void setRegistrationFieldSubtitle(String value) {
        this.mRegistrationFieldSubtitle = value;
    }

    public String getReviewDisclaimer() {
        return this.mReviewDisclaimer;
    }

    @JsonProperty("review_disclaimer")
    public void setReviewDisclaimer(String value) {
        this.mReviewDisclaimer = value;
    }

    public String getReviewHelpLinkText() {
        return this.mReviewHelpLinkText;
    }

    @JsonProperty("review_help_link_text")
    public void setReviewHelpLinkText(String value) {
        this.mReviewHelpLinkText = value;
    }

    public String getReviewSubtitle() {
        return this.mReviewSubtitle;
    }

    @JsonProperty("review_subtitle")
    public void setReviewSubtitle(String value) {
        this.mReviewSubtitle = value;
    }

    public String getReviewTitle() {
        return this.mReviewTitle;
    }

    @JsonProperty("review_title")
    public void setReviewTitle(String value) {
        this.mReviewTitle = value;
    }

    public String getStepTitle() {
        return this.mStepTitle;
    }

    @JsonProperty("step_title")
    public void setStepTitle(String value) {
        this.mStepTitle = value;
    }

    public String getUnregisteredCallToAction() {
        return this.mUnregisteredCallToAction;
    }

    @JsonProperty("unregistered_call_to_action")
    public void setUnregisteredCallToAction(String value) {
        this.mUnregisteredCallToAction = value;
    }

    public String getUnregisteredExemptedCallToAction() {
        return this.mUnregisteredExemptedCallToAction;
    }

    @JsonProperty("unregistered_exempted_call_to_action")
    public void setUnregisteredExemptedCallToAction(String value) {
        this.mUnregisteredExemptedCallToAction = value;
    }

    public int getHelpCenterId() {
        return this.mHelpCenterId;
    }

    @JsonProperty("help_center_id")
    public void setHelpCenterId(int value) {
        this.mHelpCenterId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mExplanationSteps);
        parcel.writeStringList(this.mLicenseStepSubtitles);
        parcel.writeStringList(this.mNextSubtitles);
        parcel.writeStringList(this.mStepSubtitles);
        parcel.writeParcelable(this.mTermsAndConditions, 0);
        parcel.writeParcelable(this.mInitialOverviewSection, 0);
        parcel.writeParcelable(this.mNextStepsSection, 0);
        parcel.writeParcelable(this.mExemptionFields, 0);
        parcel.writeString(this.mEmailLabel);
        parcel.writeString(this.mExistingLicenseCallToAction);
        parcel.writeString(this.mFinalCallToAction);
        parcel.writeString(this.mHelpLinkText);
        parcel.writeString(this.mIntroImageResourceUrl);
        parcel.writeString(this.mIntroSubtitle);
        parcel.writeString(this.mIntroTitle);
        parcel.writeString(this.mLicenseLabel);
        parcel.writeString(this.mLicensePlaceholder);
        parcel.writeString(this.mLicenseStepTitle);
        parcel.writeString(this.mLicenseSubtitle);
        parcel.writeString(this.mLicenseTitle);
        parcel.writeString(this.mLicenseTooltip);
        parcel.writeString(this.mLicenseTooltipTitle);
        parcel.writeString(this.mNameLabel);
        parcel.writeString(this.mNextCallToAction);
        parcel.writeString(this.mNextTitle);
        parcel.writeString(this.mPostSubmissionCallToAction);
        parcel.writeString(this.mPreSubmissionCallToAction);
        parcel.writeString(this.mRegistrationFieldLabel);
        parcel.writeString(this.mRegistrationFieldPendingValue);
        parcel.writeString(this.mRegistrationFieldSubtitle);
        parcel.writeString(this.mReviewDisclaimer);
        parcel.writeString(this.mReviewHelpLinkText);
        parcel.writeString(this.mReviewSubtitle);
        parcel.writeString(this.mReviewTitle);
        parcel.writeString(this.mStepTitle);
        parcel.writeString(this.mUnregisteredCallToAction);
        parcel.writeString(this.mUnregisteredExemptedCallToAction);
        parcel.writeInt(this.mHelpCenterId);
    }

    public void readFromParcel(Parcel source) {
        this.mExplanationSteps = source.createStringArrayList();
        this.mLicenseStepSubtitles = source.createStringArrayList();
        this.mNextSubtitles = source.createStringArrayList();
        this.mStepSubtitles = source.createStringArrayList();
        this.mTermsAndConditions = (ListingRegistrationContentSection) source.readParcelable(ListingRegistrationContentSection.class.getClassLoader());
        this.mInitialOverviewSection = (ListingRegistrationContentSection) source.readParcelable(ListingRegistrationContentSection.class.getClassLoader());
        this.mNextStepsSection = (ListingRegistrationContentSection) source.readParcelable(ListingRegistrationContentSection.class.getClassLoader());
        this.mExemptionFields = (ListingRegistrationExemptionFields) source.readParcelable(ListingRegistrationExemptionFields.class.getClassLoader());
        this.mEmailLabel = source.readString();
        this.mExistingLicenseCallToAction = source.readString();
        this.mFinalCallToAction = source.readString();
        this.mHelpLinkText = source.readString();
        this.mIntroImageResourceUrl = source.readString();
        this.mIntroSubtitle = source.readString();
        this.mIntroTitle = source.readString();
        this.mLicenseLabel = source.readString();
        this.mLicensePlaceholder = source.readString();
        this.mLicenseStepTitle = source.readString();
        this.mLicenseSubtitle = source.readString();
        this.mLicenseTitle = source.readString();
        this.mLicenseTooltip = source.readString();
        this.mLicenseTooltipTitle = source.readString();
        this.mNameLabel = source.readString();
        this.mNextCallToAction = source.readString();
        this.mNextTitle = source.readString();
        this.mPostSubmissionCallToAction = source.readString();
        this.mPreSubmissionCallToAction = source.readString();
        this.mRegistrationFieldLabel = source.readString();
        this.mRegistrationFieldPendingValue = source.readString();
        this.mRegistrationFieldSubtitle = source.readString();
        this.mReviewDisclaimer = source.readString();
        this.mReviewHelpLinkText = source.readString();
        this.mReviewSubtitle = source.readString();
        this.mReviewTitle = source.readString();
        this.mStepTitle = source.readString();
        this.mUnregisteredCallToAction = source.readString();
        this.mUnregisteredExemptedCallToAction = source.readString();
        this.mHelpCenterId = source.readInt();
    }
}
