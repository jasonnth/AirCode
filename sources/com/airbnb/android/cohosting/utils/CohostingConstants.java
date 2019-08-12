package com.airbnb.android.cohosting.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CohostingConstants {
    public static final String AMOUNT_CURRENCY = "amount_currency";
    public static final String CALL = "call";
    public static final String COHOST_INVITATION_ID_FIELD = "cohost_invitation_id";
    public static final String CONRACT_ID_FIELD = "contract_id";
    public static final int CONTRACT_STATUS_ACCEPTED = 2;
    public static final String COPY = "copy";
    public static final String EMAIL_FIELD = "email";
    public static final String FEE_TYPE = "fee_type";
    public static final String FIRST_NAME_FIELD = "first_name";
    public static final int FIRST_TAB_INDEX = 0;
    public static final String FIXED_FEE = "fixed_fee";
    public static final String INQUIRY_ID_FIELD = "inquiry_id";
    public static final int INVITATION_STATUS_DELETED = 3;
    public static final int INVITATION_STATUS_EXPIRED = 2;
    public static final int INVITATION_STATUS_PENDDING = 1;
    public static final String INVITE_CODE_FIELD = "invite_code";
    public static final String LISTINGS_FIELD = "listings";
    public static final String LISTING_ID_FIELD = "listing_id";
    public static final String LISTING_MANAGER_ID_FIELD = "listing_manager_id";
    public static final int MAP_CIRCLE_RADIUS_METERS = 800;
    public static final int MARKETPLACE_CONTRACT = 2;
    public static final String MINIMUM_FEE = "minimum_fee";
    public static final String PAY_CLEANING_FEES = "pay_cleaning_fees";
    public static final String PERCENTAGE_OF_SHARED_EARNINGS = "percent_of_shared_earnings";
    public static final int REQUEST_CODE_INVITE_FRIEND = 1002;
    public static final int REQUEST_CODE_PAYMENT_FEE_TYPE = 1001;
    public static final String TEXT = "text";
    public static final String THREAD_OTHER_USER_ID_FIELD = "thread_other_user_id";
    public static final int ZOOM_LEVEL = 14;

    public enum FeeType {
        Percent,
        FixedAmount
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PhoneOperation {
    }

    public static String[] getPhoneOperations() {
        return new String[]{"call", "text", COPY};
    }
}
