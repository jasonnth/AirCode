package com.airbnb.android.core.analytics;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.User;
import com.airbnb.jitney.event.logging.Identity.p118v1.IdentitySemanticEvent.Builder;
import com.airbnb.jitney.event.logging.IdentityAction.p119v1.IdentityActionType;
import com.airbnb.jitney.event.logging.IdentityActor.p120v1.IdentityActorType;
import com.airbnb.jitney.event.logging.IdentityVerification.p125v1.IdentityVerificationType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

public class IdentityJitneyLogger extends BaseLogger {
    private final ObjectMapper mapper;

    @JsonInclude(Include.NON_NULL)
    private static class ExtraInfo {
        public String element;
        public String experiments;
        @JsonProperty("frozen_reason")
        public String frozenReason;
        public String listingId;
        public String page;
        @JsonProperty("previous_verifications")
        public String previousVerifications;
        public String vendor;
        @JsonProperty("verification_flow")
        public String verificationFlow;

        private ExtraInfo() {
        }

        public String getPage() {
            return this.page;
        }

        public ExtraInfo setPage(String page2) {
            this.page = page2;
            return this;
        }

        public String getElement() {
            return this.element;
        }

        public ExtraInfo setElement(String element2) {
            this.element = element2;
            return this;
        }

        public String getPreviousVerifications() {
            return this.previousVerifications;
        }

        public ExtraInfo setPreviousVerifications(String previousVerifications2) {
            this.previousVerifications = previousVerifications2;
            return this;
        }

        public String getVendor() {
            return this.vendor;
        }

        public ExtraInfo setVendor(String vendor2) {
            this.vendor = vendor2;
            return this;
        }

        public String getVerificationFlow() {
            return this.verificationFlow;
        }

        public ExtraInfo setVerificationFlow(String verificationFlow2) {
            this.verificationFlow = verificationFlow2;
            return this;
        }

        public String getListingId() {
            return this.listingId;
        }

        public ExtraInfo setListingId(String listingId2) {
            this.listingId = listingId2;
            return this;
        }

        public String getExperiments() {
            return this.experiments;
        }

        public ExtraInfo setExperiments(String experiments2) {
            this.experiments = experiments2;
            return this;
        }

        public String getFrozenReason() {
            return this.frozenReason;
        }

        public ExtraInfo setFrozenReason(String frozenReason2) {
            this.frozenReason = frozenReason2;
            return this;
        }
    }

    public enum Element {
        navigation_button_continue,
        button_help,
        intro_link_help,
        navigation_button_cancel,
        button_add_photo,
        option_use_facebook_profile_photo,
        option_take_a_photo,
        option_choose_from_library,
        button_change_photo,
        navigation_button_back,
        jumio_button_back,
        button_take_photo,
        back_button,
        button_change_country,
        confirmation_code_text,
        option_change_number,
        option_send_code_again,
        option_call_instead,
        email_adress_text,
        button_resend_email,
        button_change_email,
        navigation_button_finish
    }

    public enum Page {
        flow_start,
        all_set_intro,
        verification_error_intro,
        provide_id_intro,
        selfie_only_intro,
        pending_intro,
        provide_photo_upload,
        profile_photo_review,
        phone_entry,
        phone_confirmation,
        email_entry,
        email_check,
        id_intro,
        id_upload,
        jumio_id_scan,
        mitek_id_scan,
        selfie_intro,
        selfie_camera,
        review_your_photos,
        flow_completion,
        device_not_supported
    }

    public IdentityJitneyLogger(LoggingContextFactory loggingContextFactory, ObjectMapper mapper2) {
        super(loggingContextFactory);
        this.mapper = mapper2;
    }

    public void logClick(VerificationFlow flow, User user, IdentityVerificationType verificationType, Page page, Element element) {
        publish(logIdentitySemanticEvent(verificationType, C2451Operation.Click, IdentityActionType.ATTEMPTED, IdentityActorType.CLIENT, flow).extra_info(getExtraInfo(flow, user, page, element)));
    }

    public void logSubmitted(VerificationFlow flow, User user, IdentityVerificationType verificationType, Page page, Element element) {
        publish(logIdentitySemanticEvent(verificationType, C2451Operation.Submit, IdentityActionType.SUBMITTED, IdentityActorType.SERVER, flow).extra_info(getExtraInfo(flow, user, page, element)));
    }

    public void logApproveReject(VerificationFlow flow, User user, IdentityVerificationType verificationType, Page page, Element element, boolean isApproved) {
        publish(logIdentitySemanticEvent(verificationType, C2451Operation.Submit, isApproved ? IdentityActionType.APPROVED_FINAL : IdentityActionType.REJECTED_FINAL, IdentityActorType.SERVER, flow).extra_info(getExtraInfo(flow, user, page, element)));
    }

    public void logAttemptedGovernmentIdUpload(VerificationFlow flow, User user, String vendor) {
        publish(logIdentitySemanticEvent(IdentityVerificationType.GOVERNMENT_ID, C2451Operation.Submit, IdentityActionType.SUBMITTED, IdentityActorType.USER, flow).extra_info(getExtraInfo(flow, user, Page.id_upload, null, vendor)));
    }

    public void logIdentityStartFlow(VerificationFlow flow, User user, Long listingId, String reservationFrozenReason, Boolean replaceVerifiedIdWithIdentity, Boolean identityOnP4) {
        String extraInfoString;
        String name;
        String obj;
        String l;
        String[] experiments = new String[2];
        StringBuilder append = new StringBuilder().append("android_replace_verified_id_with_identity_v2=");
        String str = replaceVerifiedIdWithIdentity == null ? "N/A" : replaceVerifiedIdWithIdentity.booleanValue() ? "replaceVerifiedIdWithIdentity" : "control";
        experiments[0] = append.append(str).toString();
        StringBuilder append2 = new StringBuilder().append("android_identity_on_p4_v2=");
        String str2 = identityOnP4 == null ? "N/A" : identityOnP4.booleanValue() ? "identityOnP4" : "control";
        experiments[1] = append2.append(str2).toString();
        try {
            ExtraInfo page = new ExtraInfo().setPage(Page.flow_start.name());
            if (flow == null) {
                name = null;
            } else {
                name = flow.name();
            }
            ExtraInfo verificationFlow = page.setVerificationFlow(name);
            if (user == null) {
                obj = null;
            } else {
                obj = user.getVerifications().toString();
            }
            ExtraInfo previousVerifications = verificationFlow.setPreviousVerifications(obj);
            if (listingId == null) {
                l = null;
            } else {
                l = listingId.toString();
            }
            extraInfoString = this.mapper.writeValueAsString(previousVerifications.setListingId(l).setExperiments(Arrays.toString(experiments)).setFrozenReason(reservationFrozenReason));
        } catch (JsonProcessingException e) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException(e));
            extraInfoString = "";
        }
        publish(logIdentitySemanticEvent(null, C2451Operation.Impression, IdentityActionType.REQUESTED, IdentityActorType.CLIENT, flow).extra_info(extraInfoString));
    }

    public void logImpression(VerificationFlow flow, User user, IdentityVerificationType verificationType, Page page) {
        publish(logIdentitySemanticEvent(verificationType, C2451Operation.Impression, IdentityActionType.RENDERED, IdentityActorType.CLIENT, flow).extra_info(getExtraInfo(flow, user, page, null)));
    }

    private Builder logIdentitySemanticEvent(IdentityVerificationType verificationType, C2451Operation operation, IdentityActionType action, IdentityActorType actor, VerificationFlow flow) {
        boolean isHandOffFlow;
        Builder verification = new Builder(context(), operation, action, actor).verification(verificationType);
        if (flow == null) {
            isHandOffFlow = false;
        } else {
            isHandOffFlow = flow.isHandOffFlow();
        }
        return verification.is_mobile_handoff(Boolean.valueOf(isHandOffFlow));
    }

    private String getExtraInfo(VerificationFlow flow, User user, Page page, Element element) {
        return getExtraInfo(flow, user, page, element, null);
    }

    private String getExtraInfo(VerificationFlow flow, User user, Page page, Element element, String vendor) {
        String name;
        String obj;
        String name2;
        String name3;
        if (page == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Page should never be null."));
        }
        try {
            ExtraInfo extraInfo = new ExtraInfo();
            if (flow == null) {
                name = null;
            } else {
                name = flow.name();
            }
            ExtraInfo verificationFlow = extraInfo.setVerificationFlow(name);
            if (user == null) {
                obj = null;
            } else {
                obj = user.getVerifications().toString();
            }
            ExtraInfo previousVerifications = verificationFlow.setPreviousVerifications(obj);
            if (page == null) {
                name2 = null;
            } else {
                name2 = page.name();
            }
            ExtraInfo page2 = previousVerifications.setPage(name2);
            if (element == null) {
                name3 = null;
            } else {
                name3 = element.name();
            }
            ExtraInfo element2 = page2.setElement(name3);
            if (vendor == null) {
                vendor = null;
            }
            return this.mapper.writeValueAsString(element2.setVendor(vendor));
        } catch (JsonProcessingException e) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException(e));
            return "";
        }
    }
}
