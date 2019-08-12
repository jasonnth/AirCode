package com.airbnb.android.checkin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class CheckInActionController extends AirEpoxyController {
    private boolean alreadyCheckedIn;
    StandardRowEpoxyModel_ contactHostRow;
    private final Context context;
    DocumentMarqueeEpoxyModel_ header;
    private final Listener listener;
    private final String phoneNumber;
    StandardRowEpoxyModel_ wifiRow;
    private final ListingWirelessInfo wirelessInfo;

    public interface Listener {
        void onContactHostClicked(String str);

        void onWifiClicked(ListingWirelessInfo listingWirelessInfo);
    }

    public CheckInActionController(Context context2, ListingWirelessInfo wirelessInfo2, String phoneNumber2, boolean alreadyCheckedIn2, Listener listener2) {
        this.context = context2;
        this.wirelessInfo = wirelessInfo2;
        this.phoneNumber = phoneNumber2;
        this.alreadyCheckedIn = alreadyCheckedIn2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.header.titleRes(getTitleTextRes()).captionRes(getCaptionTextRes()).addTo(this);
        if (!TextUtils.isEmpty(this.phoneNumber)) {
            this.contactHostRow.titleRes(C5618R.string.check_in_contact_host_row_name).subtitle((CharSequence) this.phoneNumber).rowDrawable(getPhoneIcon()).clickListener(CheckInActionController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        }
        if (this.wirelessInfo != null && !TextUtils.isEmpty(this.wirelessInfo.getWirelessSsid())) {
            this.wifiRow.titleRes(C5618R.string.check_in_wifi_row_name).subtitle((CharSequence) getWifiRowSubtitle()).rowDrawable(ContextCompat.getDrawable(this.context, C5618R.C5619drawable.n2_ic_am_wifi)).clickListener(CheckInActionController$$Lambda$2.lambdaFactory$(this)).addTo(this);
        }
    }

    private int getTitleTextRes() {
        if (this.alreadyCheckedIn) {
            return C5618R.string.check_in_final_screen_title_already_checked_in;
        }
        return C5618R.string.check_in_final_screen_title_finish_checking_in;
    }

    private int getCaptionTextRes() {
        if (this.alreadyCheckedIn) {
            return C5618R.string.check_in_final_screen_caption_already_checked_in;
        }
        return C5618R.string.check_in_final_screen_caption_finish_checking_in;
    }

    private String getWifiRowSubtitle() {
        StringBuilder builder = new StringBuilder(this.context.getString(C5618R.string.check_in_wifi_info_network_name, new Object[]{this.wirelessInfo.getWirelessSsid()}));
        if (!TextUtils.isEmpty(this.wirelessInfo.getWirelessPassword())) {
            builder.append("\n").append(this.context.getString(C5618R.string.check_in_wifi_info_network_password, new Object[]{this.wirelessInfo.getWirelessPassword()}));
        }
        return builder.toString();
    }

    private Drawable getPhoneIcon() {
        Drawable icon = DrawableCompat.wrap(this.context.getResources().getDrawable(C5618R.C5619drawable.icon_line_phone));
        DrawableCompat.setTint(icon.mutate(), ContextCompat.getColor(this.context, C5618R.color.n2_babu));
        return icon;
    }
}
