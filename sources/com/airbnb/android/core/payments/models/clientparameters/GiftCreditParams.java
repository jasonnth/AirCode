package com.airbnb.android.core.payments.models.clientparameters;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftCreditParams {
    @JsonProperty("recipient_email")
    String recipientEmail;
    @JsonProperty("recipient_message")
    String recipientMessage;
    @JsonProperty("recipient_name")
    String recipientName;
    @JsonProperty("template_attributes")
    TemplateAttributes templateAttributes;

    private static class TemplateAttributes {
        @JsonProperty("category_type")
        String categoryType;
        @JsonProperty("locale")
        String locale;
        @JsonProperty("overlay_id")
        long overlayId;
        @JsonProperty("video_id")
        long videoId;

        private TemplateAttributes(GiftCardClientParameters giftCardClientParameters) {
            this.categoryType = giftCardClientParameters.categoryType();
            this.locale = giftCardClientParameters.locale();
            this.overlayId = giftCardClientParameters.overlayId();
            this.videoId = giftCardClientParameters.videoId();
        }
    }

    public GiftCreditParams(GiftCardClientParameters giftCardClientParameters) {
        this.recipientMessage = giftCardClientParameters.recipientMessage();
        this.recipientEmail = giftCardClientParameters.recipientEmail();
        this.recipientName = giftCardClientParameters.recipientName();
        this.templateAttributes = new TemplateAttributes(giftCardClientParameters);
    }
}
