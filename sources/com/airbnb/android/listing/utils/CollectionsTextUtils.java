package com.airbnb.android.listing.utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.enums.ReadyForSelectStatus;
import com.airbnb.android.core.models.HomeCollectionApplication;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.primitives.fonts.Font;
import org.joda.time.format.DateTimeFormat;

public final class CollectionsTextUtils {
    public static CharSequence getInspectionBookingDateTimeText(Context context, HomeCollectionApplication application) {
        if (application.getBookingStart() == null) {
            return null;
        }
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append(SpannableUtils.makeBoldedString(C7213R.string.collections_listing_scheduled_inspection_date_and_time, context));
        stringBuilder.append("\n");
        long dateMillis = application.getBookingStart().getMillis();
        String dateString = DateTimeFormat.forPattern(context.getString(C7213R.string.full_month_day_format)).print(dateMillis);
        String timeString = DateTimeFormat.forPattern(context.getString(C7213R.string.time_short_format_12_hour)).print(dateMillis);
        String dateTimeString = context.getString(C7213R.string.collections_listing_scheduled_inspection_date_and_time_at, new Object[]{dateString, timeString});
        stringBuilder.append(SpannableUtils.makeFontString(dateTimeString, context, Font.CircularLight), 0, dateTimeString.length());
        return stringBuilder;
    }

    public static CharSequence getInspectionBookingAddressText(Context context, HomeCollectionApplication application) {
        if (application.getAddress() == null) {
            return null;
        }
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append(SpannableUtils.makeBoldedString(C7213R.string.collections_listing_scheduled_inspection_address, context));
        stringBuilder.append("\n");
        String address = application.getAddress();
        stringBuilder.append(SpannableUtils.makeFontString(address, context, Font.CircularLight), 0, address.length());
        return stringBuilder;
    }

    public static int getStatusCaptionText(HomeCollectionApplication application) {
        switch (application.getStatus()) {
            case 0:
                return C7213R.string.collections_listing_invite_banner_message;
            case 4:
                return C7213R.string.collections_listing_inspection_completed_messeage;
            default:
                return 0;
        }
    }

    public static int getStatusButtonText(HomeCollectionApplication application) {
        switch (application.getStatus()) {
            case 0:
                return C7213R.string.collections_listing_invitation_button;
            default:
                return 0;
        }
    }

    public static int getCollectionsStatusLabel(HomeCollectionApplication application) {
        if (application == null) {
            return 0;
        }
        switch (application.getStatus()) {
            case 5:
                return C7213R.string.collections_listing_select_brand_name;
            default:
                return 0;
        }
    }

    public static int getGoToWebBannerCaptionText(ReadyForSelectStatus status) {
        switch (status) {
            case ReadyForSelect:
                return C7213R.string.collections_listing_ready_for_spotlight_not_ready_text;
            case PostReadyForSelect:
            case Select:
                return C7213R.string.collections_listing_edit_spotlight_not_ready_text;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(status));
                return 0;
        }
    }

    public static SpannableStringBuilder getBeloAsString(Context context) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        SpannableUtils.appendImageSpan(context, builder, C7213R.C7214drawable.ic_belo);
        builder.append(" ");
        return builder;
    }

    public static CharSequence getSwitcherBannerTitle(Context context, boolean selectMode) {
        SpannableStringBuilder builder = getBeloAsString(context);
        if (selectMode) {
            builder.append(context.getString(C7213R.string.collections_listing_select_brand_name_uppercase));
        }
        return builder;
    }

    public static CharSequence getSwitcherBannerMessage(Context context, boolean selectMode) {
        if (selectMode) {
            return context.getString(C7213R.string.collections_switcher_banner_select_mode_message);
        }
        return context.getString(C7213R.string.collections_switcher_banner_marketplace_mode_message);
    }

    public static int getSwitcherBannerActionText(Context context, boolean selectMode) {
        if (selectMode) {
            return C7213R.string.collections_switcher_banner_select_mode_action;
        }
        return C7213R.string.collections_switcher_banner_marketplace_mode_action;
    }
}
