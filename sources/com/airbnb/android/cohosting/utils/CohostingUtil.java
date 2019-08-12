package com.airbnb.android.cohosting.utils;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableString;
import android.util.SparseIntArray;
import android.widget.TextView;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class CohostingUtil {
    private static final SparseIntArray invitationStatusToStringIdMap = new SparseIntArray() {
        {
            put(1, C5658R.string.status_pending);
            put(2, C5658R.string.status_timeout);
            put(3, C5658R.string.status_deleted);
        }
    };

    public static void setupTermsText(Context context, TextView view, int phraseId) {
        String termsLinkText = context.getString(C5658R.string.cohosting_invite_new_cohost_terms);
        SpannableString textWithLink = new SpannableString(TextUtil.fromHtmlSafe(context.getString(phraseId, new Object[]{termsLinkText})));
        TextView textView = view;
        ClickableLinkUtils.setupClickableTextView(textView, textWithLink.toString(), termsLinkText, ContextCompat.getColor(context, C5658R.color.n2_text_color_muted), C5658R.color.canonical_press_darken, CohostingUtil$$Lambda$1.lambdaFactory$(context), true);
    }

    public static void goToHelpCenterLink(Context context, int articleId) {
        context.startActivity(WebViewIntentBuilder.newBuilder(context, context.getString(C5658R.string.help_center_base_url) + "/article/" + articleId).title(C5658R.string.airbnb_help).authenticate().toIntent());
    }

    public static String getInvitationStatusStr(Context context, int invitationStatus) {
        return context.getString(invitationStatusToStringIdMap.get(invitationStatus));
    }

    public static String getInvitationExpirationTimeStr(Context context, AirDateTime date) {
        return context.getString(C5658R.string.cohosting_invitation_expiration_time, new Object[]{date.getDateString(context)});
    }

    public static String getCohostCreationTimeStr(Context context, AirDate date, CohostManagementDataController controller) {
        return context.getString(controller.isCurrentUserListingAdmin() ? C5658R.string.cohosting_cohost_creation_time_from_listing_admin_perspective : C5658R.string.cohosting_cohost_creation_time_from_cohost_perspective, new Object[]{date.getDateString(context)});
    }

    public static void goToResolutionCenter(Context context) {
        context.startActivity(WebViewIntentBuilder.newBuilder(context, context.getString(C5658R.string.resolution_center_landing_page)).title(C5658R.string.ro_resolution_center).authenticate().toIntent());
    }

    public static void openTermsOfService(Context context) {
        context.startActivity(WebViewIntentBuilder.newBuilder(context, context.getString(C5658R.string.cohosting_terms_url)).authenticate().toIntent());
    }

    public static ListingManager getPaidListingManager(List<ListingManager> listingManagers) {
        for (ListingManager manager : listingManagers) {
            if (manager.getContract() != null) {
                return manager;
            }
        }
        return null;
    }

    public static CohostInvitation getPaidCohostInvitation(List<CohostInvitation> cohostInvitations) {
        for (CohostInvitation invitation : cohostInvitations) {
            if (invitation.getCohostingContract() != null) {
                return invitation;
            }
        }
        return null;
    }

    public static boolean hasPaidListingManager(List<ListingManager> listingManagers) {
        return getPaidListingManager(listingManagers) != null;
    }

    public static boolean hasPaidCohostInvitation(List<CohostInvitation> cohostInvitations) {
        return getPaidCohostInvitation(cohostInvitations) != null;
    }

    public static boolean isCurrentUserListingAdmin(List<ListingManager> listingManagers, long currentUserId) {
        ListingManager manager = (ListingManager) FluentIterable.from((Iterable<E>) listingManagers).filter(CohostingUtil$$Lambda$2.lambdaFactory$(currentUserId)).first().orNull();
        Check.notNull(manager);
        return manager.isIsListingAdmin().booleanValue();
    }

    static /* synthetic */ boolean lambda$isCurrentUserListingAdmin$1(long currentUserId, ListingManager v) {
        return v.getUser().getId() == currentUserId;
    }
}
