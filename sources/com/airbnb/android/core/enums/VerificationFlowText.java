package com.airbnb.android.core.enums;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.erf.Experiments;

public enum VerificationFlowText {
    Booking(C0716R.string.account_verifications_start_booking_header, C0716R.string.account_verification_provide_id, C0716R.string.account_verifications_start_booking_no_id_required, C0716R.string.account_verification_provide_id_desc, C0716R.string.verifications_email_description, C0716R.string.verifications_phone_explanation, C0716R.string.account_verification_offline_id_desc, C0716R.string.verifications_id_desc_v1_1, C0716R.string.account_verification_take_a_selfie, C0716R.string.account_verification_selfie_desc_v1_1, C0716R.string.account_verification_preview_desc, C0716R.string.verifications_complete_ready_to_book_no_period, C0716R.string.account_verifications_finish_desc_v1_1, C0716R.string.account_verifications_finish_booking, C0716R.string.verifications_photo_desc_booking_context, C0716R.string.account_verification_profile_photo_sucess_desc, C0716R.string.account_verification_review_your_selfie_subtitle, C0716R.string.account_verification_need_to_take_a_selfie),
    NonBooking(C0716R.string.account_verification_provide_your_id, C0716R.string.account_verification_provide_your_id, C0716R.string.account_verification_provide_id_desc_variant3, C0716R.string.account_verification_provide_id_desc_variant3, C0716R.string.verifications_email_description_non_booking_context, C0716R.string.verifications_phone_explanation_non_booking_context, C0716R.string.account_verification_offline_id_desc_non_booking_context, C0716R.string.verifications_id_desc_v1_1_non_booking_context, C0716R.string.account_verification_take_a_selfie, C0716R.string.account_verification_selfie_desc_v1_1_non_booking_context, C0716R.string.account_verification_preview_desc_non_booking_context, C0716R.string.verifications_complete_all_set, C0716R.string.account_verifications_finish_desc_v1_1_non_booking_context, C0716R.string.account_verifications_finish, C0716R.string.verifications_photo_desc_non_booking_context, C0716R.string.f1030xbce8a1ef, C0716R.string.f1031x4296f4d0, C0716R.string.account_verification_need_to_take_a_selfie),
    MagicalTripsBooking(C0716R.string.account_verifications_start_booking_header, C0716R.string.account_verification_complete_our_verification_process, C0716R.string.account_verifications_start_booking_no_id_required, C0716R.string.account_verification_mt_verification_process_requirement_desc, C0716R.string.verifications_email_description, C0716R.string.verifications_phone_explanation, C0716R.string.account_verification_offline_id_desc, C0716R.string.verifications_id_desc_v1_1, C0716R.string.account_verification_take_a_selfie, C0716R.string.account_verification_selfie_experience_desc, C0716R.string.account_verification_preview_desc, C0716R.string.verifications_complete_all_set, C0716R.string.account_verifications_finish_experience_desc, C0716R.string.account_verifications_go_to_itinerary, C0716R.string.verifications_photo_desc_booking_context, C0716R.string.account_verification_profile_photo_sucess_desc, C0716R.string.account_verification_selfie_experience_desc, C0716R.string.account_verification_need_to_take_a_selfie_mt_context),
    MagicalTripsGuest(C0716R.string.account_verifications_start_booking_header, C0716R.string.account_verification_complete_our_verification_process, C0716R.string.account_verifications_start_booking_no_id_required, C0716R.string.account_verification_mt_verification_process_requirement_desc, C0716R.string.verifications_email_description, C0716R.string.verifications_phone_explanation, C0716R.string.account_verification_offline_id_desc, C0716R.string.verifications_id_desc_v1_1, C0716R.string.account_verification_take_a_selfie, C0716R.string.account_verification_selfie_experience_confirm_spot_desc, C0716R.string.account_verification_preview_desc, C0716R.string.verifications_complete_all_set, C0716R.string.account_verifications_finish_experience_desc, C0716R.string.account_verifications_go_to_itinerary, C0716R.string.verifications_photo_desc_booking_context, C0716R.string.account_verification_profile_photo_sucess_desc, C0716R.string.account_verification_selfie_experience_desc, C0716R.string.account_verification_need_to_take_a_selfie_mt_context),
    HostRequired(C0716R.string.account_verifications_start_booking_header, C0716R.string.account_verification_provide_id, C0716R.string.account_verifications_start_booking_no_id_required, C0716R.string.account_verification_provide_id_host_required_desc, C0716R.string.verifications_email_description, C0716R.string.verifications_phone_explanation, C0716R.string.account_verification_offline_id_desc, C0716R.string.verifications_id_desc_v1_1, C0716R.string.account_verification_take_a_selfie, C0716R.string.account_verification_selfie_desc_v1_1, C0716R.string.account_verification_preview_desc, C0716R.string.verifications_complete_ready_to_book_no_period, C0716R.string.account_verifications_finish_desc_v1_1, C0716R.string.account_verifications_finish_booking, C0716R.string.verifications_photo_desc_booking_context, C0716R.string.account_verification_profile_photo_sucess_desc, C0716R.string.account_verification_review_your_selfie_subtitle, C0716R.string.account_verification_need_to_take_a_selfie),
    CohostInvitation(C0716R.string.verifications_cohost_invite_verification_title, C0716R.string.verifications_cohost_invite_verification_title, C0716R.string.verifications_cohost_invite_verification_description, C0716R.string.verifications_cohost_invite_verification_description, C0716R.string.verifications_email_description_non_booking_context, C0716R.string.verifications_phone_explanation_non_booking_context, C0716R.string.account_verification_offline_id_desc_non_booking_context, C0716R.string.verifications_id_desc_v1_1_non_booking_context, C0716R.string.account_verification_take_a_selfie, C0716R.string.account_verification_selfie_desc_v1_1_non_booking_context, C0716R.string.account_verification_preview_desc_non_booking_context, C0716R.string.verifications_complete_all_set, C0716R.string.account_verifications_finish_desc_v1_1_non_booking_context, C0716R.string.account_verifications_finish, C0716R.string.verifications_photo_desc_non_booking_context, C0716R.string.f1030xbce8a1ef, C0716R.string.f1031x4296f4d0, C0716R.string.account_verification_need_to_take_a_selfie),
    ProfileCompletion(C0716R.string.account_verifications_start_booking_header, C0716R.string.account_verification_provide_id, C0716R.string.account_verifications_start_booking_no_id_required, C0716R.string.account_verification_provide_id_desc, C0716R.string.verifications_email_description, C0716R.string.verifications_phone_explanation, C0716R.string.account_verification_offline_id_desc, C0716R.string.verifications_id_desc_v1_1, C0716R.string.account_verification_take_a_selfie, C0716R.string.account_verification_selfie_desc_v1_1, C0716R.string.account_verification_preview_desc, C0716R.string.verifications_complete_ready_to_book_no_period, C0716R.string.account_verifications_finish_desc_v1_1, C0716R.string.account_verifications_finish_booking, C0716R.string.verifications_photo_desc_booking_context, C0716R.string.account_verification_profile_photo_sucess_desc, C0716R.string.account_verification_review_your_selfie_subtitle, C0716R.string.account_verification_need_to_take_a_selfie);
    
    private final int emailSubtitle;
    private final int finishButtonLabel;
    private final int finishSubtitle;
    private final int finishTitle;
    private final int offlineIdSubtitle;
    private final int offlineIdSubtitleOneDotOne;
    private final int phoneSubtitle;
    private final int photoReviewSubtitle;
    private final int profilePhotoStartSubtitle;
    private final int profilePhotoSuccessSubtitle;
    private final int selfieOnlyIntroSubtitle;
    private final int selfieReviewSubtitle;
    private final int selfieSubtitle;
    private final int selfieTitle;
    private final int startSubtitle;
    private final int startSubtitleOneDotOne;
    private final int startTitle;
    private final int startTitleOneDotOne;

    private VerificationFlowText(int startTitle2, int startTitleOneDotOne2, int startSubtitle2, int startSubtitleOneDotOne2, int emailSubtitle2, int phoneSubtitle2, int offlineIdSubtitle2, int offlineIdSubtitleOneDotOne2, int selfieTitle2, int selfieSubtitle2, int photoReviewSubtitle2, int finishTitle2, int finishSubtitle2, int finishButtonLabel2, int profilePhotoStartSubtitle2, int profilePhotoSuccessSubtitle2, int selfieReviewSubtitle2, int selfieOnlyIntroSubtitle2) {
        this.startTitle = startTitle2;
        this.startTitleOneDotOne = startTitleOneDotOne2;
        this.startSubtitle = startSubtitle2;
        this.startSubtitleOneDotOne = startSubtitleOneDotOne2;
        this.emailSubtitle = emailSubtitle2;
        this.phoneSubtitle = phoneSubtitle2;
        this.offlineIdSubtitle = offlineIdSubtitle2;
        this.offlineIdSubtitleOneDotOne = offlineIdSubtitleOneDotOne2;
        this.selfieTitle = selfieTitle2;
        this.selfieSubtitle = selfieSubtitle2;
        this.photoReviewSubtitle = photoReviewSubtitle2;
        this.finishTitle = finishTitle2;
        this.finishSubtitle = finishSubtitle2;
        this.finishButtonLabel = finishButtonLabel2;
        this.profilePhotoStartSubtitle = profilePhotoStartSubtitle2;
        this.profilePhotoSuccessSubtitle = profilePhotoSuccessSubtitle2;
        this.selfieReviewSubtitle = selfieReviewSubtitle2;
        this.selfieOnlyIntroSubtitle = selfieOnlyIntroSubtitle2;
    }

    public String getStartTitle(Context context, String hostFirstName) {
        if (this == NonBooking || hostFirstName == null) {
            return context.getString(this.startTitle);
        }
        return context.getString(this.startTitle, new Object[]{hostFirstName});
    }

    public String getStartTitleOneDotOne(Context context, boolean selfieOnly, String hostFirstName) {
        if (selfieOnly) {
            return context.getString(this.selfieTitle);
        }
        if (this != CohostInvitation) {
            return context.getString(this.startTitleOneDotOne);
        }
        return context.getString(this.startTitleOneDotOne, new Object[]{hostFirstName});
    }

    public String getStartSubtitle(Context context, String userFirstName, boolean requiresOfflineIdOrSelfie) {
        if (this == NonBooking) {
            return context.getString(this.startSubtitle, new Object[]{userFirstName});
        } else if (requiresOfflineIdOrSelfie) {
            return context.getString(C0716R.string.account_verifications_start_booking_desc);
        } else {
            return context.getString(this.startSubtitle);
        }
    }

    public String getStartSubtitleOneDotOne(Context context, String userFirstName, String hostFirstName, boolean selfieOnly) {
        if (this == NonBooking) {
            return context.getString(this.startSubtitleOneDotOne, new Object[]{userFirstName});
        } else if (this == HostRequired) {
            return context.getString(this.startSubtitleOneDotOne, new Object[]{hostFirstName});
        } else if (selfieOnly) {
            return context.getString(this.selfieOnlyIntroSubtitle, new Object[]{userFirstName});
        } else {
            return context.getString(Experiments.showVariantVerificationIntro() ? C0716R.string.account_verification_provide_id_desc_variant2 : this.startSubtitleOneDotOne);
        }
    }

    public int getEmailSubtitle() {
        return this.emailSubtitle;
    }

    public int getPhoneSubtitle() {
        return this.phoneSubtitle;
    }

    public int getOfflineIdSubtitle() {
        return this.offlineIdSubtitle;
    }

    public int getOfflineIdSubtitleOneDotOne() {
        return this.offlineIdSubtitleOneDotOne;
    }

    public int getSelfieTitle() {
        return this.selfieTitle;
    }

    public int getSelfieSubtitle() {
        return this.selfieSubtitle;
    }

    public int getSelfieReviewSubtitle() {
        return this.selfieReviewSubtitle;
    }

    public String getFinishTitle(Context context, String userFirstName) {
        if (this == NonBooking) {
            return context.getString(this.finishTitle);
        }
        return context.getString(this.finishTitle, new Object[]{userFirstName});
    }

    public String getFinishSubtitle(Context context, String userFirstName, String hostFirstName) {
        if (this == NonBooking || hostFirstName == null) {
            return context.getString(this.finishSubtitle, new Object[]{userFirstName});
        }
        return context.getString(this.finishSubtitle, new Object[]{hostFirstName});
    }

    public int getFinishButtonLabel() {
        return this.finishButtonLabel;
    }

    public int getProfilePhotoStartSubtitle() {
        return this.profilePhotoStartSubtitle;
    }

    public int getProfilePhotoSuccessSubtitle() {
        return this.profilePhotoSuccessSubtitle;
    }
}
