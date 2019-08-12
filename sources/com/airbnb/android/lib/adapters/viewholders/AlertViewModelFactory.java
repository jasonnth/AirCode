package com.airbnb.android.lib.adapters.viewholders;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import com.airbnb.android.airdate.AirDateConstants;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.core.models.DashboardAlert.DashboardAlertType;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.viewcomponents.viewmodels.ThreadPreviewEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class AlertViewModelFactory {

    public interface AlertClickListener {
        void handleClick(ThreadPreviewEpoxyModel_ threadPreviewEpoxyModel_, DashboardAlert dashboardAlert);
    }

    public static List<ThreadPreviewEpoxyModel_> createAlerts(Context context, List<DashboardAlert> alerts, AlertClickListener clickListener) {
        if (ListUtils.isEmpty((Collection<?>) alerts)) {
            return Lists.newArrayList();
        }
        return FluentIterable.from((Iterable<E>) alerts).filter(AlertViewModelFactory$$Lambda$1.lambdaFactory$()).transform(AlertViewModelFactory$$Lambda$2.lambdaFactory$(context, clickListener)).toList();
    }

    /* access modifiers changed from: private */
    public static boolean isSupportedAlert(DashboardAlert alert) {
        switch (alert.getAlertTypeEnum()) {
            case ActionNotfication:
                return DeepLinkUtils.isDeepLink(alert.getDeeplinkUrl());
            default:
                return true;
        }
    }

    /* access modifiers changed from: private */
    public static ThreadPreviewEpoxyModel_ createAlert(Context context, DashboardAlert alert, AlertClickListener clickListener) {
        if (alert.getAlertTypeEnum().matchesAny(DashboardAlertType.CohostingInvitationReceived, DashboardAlertType.CohostingInvitationAccepted, DashboardAlertType.CohostingInvitationExpired)) {
            return createCohostInviteAlert(context, alert, clickListener);
        }
        ThreadPreviewEpoxyModel_ model = createBaseAlertModel(alert, clickListener);
        model.titleText(getTitleText(alert)).subtitleText(getSubtitleText(alert)).multipleLineTitle(true).thirdRowText(createCompleteByText(context, alert.getExpiresAt(), alert.getAlertTypeEnum()));
        return model;
    }

    private static ThreadPreviewEpoxyModel_ createCohostInviteAlert(Context context, DashboardAlert alert, AlertClickListener clickListener) {
        ThreadPreviewEpoxyModel_ model = createBaseAlertModel(alert, clickListener);
        model.titleText(context.getString(C0880R.string.application_name)).subtitleText(alert.getTitleText()).thirdRowText(alert.getBodyText()).multipleLineTitle(true).fourthRowText(createCompleteByText(context, alert.getExpiresAt(), alert.getAlertTypeEnum()));
        return model;
    }

    private static ThreadPreviewEpoxyModel_ createBaseAlertModel(DashboardAlert alert, AlertClickListener clickListener) {
        boolean z = true;
        ThreadPreviewEpoxyModel_ shouldShowSquareImage = new ThreadPreviewEpoxyModel_(alert.getId()).imageUrls(Lists.newArrayList((E[]) new String[]{alert.getImageUrl()})).timeAgo(alert.getCreatedAt()).shouldShowSquareImage(DashboardAlertType.ActionNotfication.equals(alert.getAlertTypeEnum()));
        if (alert.isViewed()) {
            z = false;
        }
        ThreadPreviewEpoxyModel_ model = shouldShowSquareImage.showUnread(z);
        model.clickListener(AlertViewModelFactory$$Lambda$3.lambdaFactory$(clickListener, model, alert));
        return model;
    }

    private static String getTitleText(DashboardAlert alert) {
        return DashboardAlertType.ActionNotfication.equals(alert.getAlertTypeEnum()) ? alert.getHeaderText() : alert.getTitleText();
    }

    private static String getSubtitleText(DashboardAlert alert) {
        return DashboardAlertType.ActionNotfication.equals(alert.getAlertTypeEnum()) ? alert.getTitleText() : alert.getBodyText();
    }

    private static CharSequence createCompleteByText(Context context, AirDateTime expiration, DashboardAlertType type) {
        String dateString;
        if (expiration == null) {
            return null;
        }
        int dateColor = ContextCompat.getColor(context, C0880R.color.n2_arches);
        switch (type) {
            case CohostingInvitationReceived:
                dateString = expiration.getExpiresAtStringWithMaxTimeUnitOnly(context);
                dateColor = getDateColorForExpiration(expiration, context);
                break;
            default:
                SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(C0880R.string.date_name_format), Locale.getDefault());
                dateString = context.getString(C0880R.string.alert_complete_by, new Object[]{expiration.formatDate(dateFormat)});
                break;
        }
        return SpannableUtils.makeColoredString(dateString, dateColor);
    }

    private static int getDateColorForExpiration(AirDateTime expirationTime, Context context) {
        int minutes = expirationTime.minutesFrom(AirDateTime.now());
        return ContextCompat.getColor(context, minutes >= 0 && (((long) minutes) > AirDateConstants.MINUTES_PER_DAY ? 1 : (((long) minutes) == AirDateConstants.MINUTES_PER_DAY ? 0 : -1)) < 0 ? C0880R.color.n2_arches : C0880R.color.n2_text_color_muted);
    }
}
