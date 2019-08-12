package com.airbnb.android.hostcalendar.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.NestedBusyDetail;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.NestedListingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.hostcalendar.C6418R;

public class NestedListingViewAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final NestedListingViewListener listener;
    DocumentMarqueeEpoxyModel_ marqueeModel = new DocumentMarqueeEpoxyModel_();

    public interface NestedListingViewListener {
        void onClickListing(NestedListing nestedListing);
    }

    public NestedListingViewAdapter(Context context2, NestedBusyDetail nestedBusyDetail, AirDate date, String hostNotes, NestedListingViewListener listener2) {
        this.context = context2;
        this.listener = listener2;
        NestedListing nestedListing = nestedBusyDetail.getNestedListing();
        String description = "";
        String type = nestedBusyDetail.getType();
        char c = 65535;
        switch (type.hashCode()) {
            case -1563081780:
                if (type.equals("reservation")) {
                    c = 0;
                    break;
                }
                break;
            case -1355543291:
                if (type.equals("turnover_days")) {
                    c = 2;
                    break;
                }
                break;
            case 1550245170:
                if (type.equals(NestedBusyDetail.NESTED_BUSY_TYPE_EXTERNAL_CALENDAR)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                description = context2.getString(AirDate.isInPast(date) ? C6418R.string.calendar_nested_listing_blocked_by_past_reservation : C6418R.string.calendar_nested_listing_blocked_by_reservation);
                break;
            case 1:
                description = context2.getString(AirDate.isInPast(date) ? C6418R.string.calendar_nested_listing_blocked_by_past_external_calendar : C6418R.string.calendar_nested_listing_blocked_by_external_calendar);
                break;
            case 2:
                description = context2.getString(AirDate.isInPast(date) ? C6418R.string.calendar_nested_listing_blocked_by_past_turnover_days : C6418R.string.calendar_nested_listing_blocked_by_turnover_days);
                break;
        }
        this.marqueeModel.titleText((CharSequence) date.formatDate(context2.getString(C6418R.string.hh_day_week_date_name_format))).captionText((CharSequence) !TextUtils.isEmpty(hostNotes) ? context2.getResources().getString(C6418R.string.calendar_update_note_display, new Object[]{hostNotes}) : "");
        addModel(this.marqueeModel);
        addModel(new StandardRowEpoxyModel_().title((CharSequence) description).titleMaxLine(10));
        NestedListingRowEpoxyModel_ nestedListingRow = new NestedListingRowEpoxyModel_().m5218id(nestedListing.getId()).title(nestedListing.getName()).subtitle(nestedListing.getRoomTypeForSubtitle(context2)).clickListener(NestedListingViewAdapter$$Lambda$1.lambdaFactory$(listener2, nestedListing));
        String imageUrl = nestedListing.getThumbnailUrl();
        if (!TextUtils.isEmpty(imageUrl)) {
            nestedListingRow.imageUrl(imageUrl);
        } else {
            nestedListingRow.imageDrawableRes(C6418R.C6419drawable.camera_icon_bg);
        }
        addModel(nestedListingRow);
    }

    public void notifyNotesChanged(String hostNotes) {
        String str;
        DocumentMarqueeEpoxyModel_ documentMarqueeEpoxyModel_ = this.marqueeModel;
        if (!TextUtils.isEmpty(hostNotes)) {
            str = this.context.getResources().getString(C6418R.string.calendar_update_note_display, new Object[]{hostNotes});
        } else {
            str = "";
        }
        documentMarqueeEpoxyModel_.captionText((CharSequence) str);
        notifyModelChanged(this.marqueeModel);
    }
}
