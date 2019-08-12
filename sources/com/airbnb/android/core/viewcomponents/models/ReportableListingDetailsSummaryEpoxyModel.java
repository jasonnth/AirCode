package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.AndroidUtils;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.p027n2.components.ReportableDetailsSummary;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ReportableListingDetailsSummaryEpoxyModel extends AirEpoxyModel<ReportableDetailsSummary> {
    boolean businessReady;
    OnClickListener clickListener;
    User host;
    OnClickListener hostImageClickListener;
    Listing listing;
    OnClickListener reportTextClickListener;
    boolean reported;
    boolean showReport;

    public void bind(ReportableDetailsSummary row) {
        String str;
        String str2;
        super.bind(row);
        Context context = row.getContext();
        if (this.listing != null) {
            row.setTitleText(this.listing.getRoomType(context));
        } else {
            row.setTitleText(null);
        }
        if (this.host != null) {
            row.setSubtitleText(SpannableUtils.makeColoredSubstring(context.getString(C0716R.string.hosted_by_name, new Object[]{SpannableUtils.COLORED_SUBSTRING_TOKEN}), this.host.getName(), ContextCompat.getColor(context, C0716R.color.n2_text_color_actionable)));
            row.setUserImageUrl(this.host.getPictureUrl());
            if (this.host.isSuperhost()) {
                row.setUserStatusIcon(C0716R.C0717drawable.sh_badge);
            }
        } else {
            row.setSubtitleText(null);
            row.setUserImageUrl(null);
            row.setUserStatusIcon((Drawable) null);
        }
        if (this.businessReady) {
            str = context.getString(C0716R.string.business_ready);
        } else {
            str = null;
        }
        row.setLabelText(str);
        if (this.showReport) {
            str2 = context.getString(this.reported ? C0716R.string.reported_listing_text : C0716R.string.report_listing_link_text);
        } else {
            str2 = null;
        }
        row.setReportText(str2);
        row.setReported(this.reported);
        row.setReportTextClickListener(this.reported ? null : this.reportTextClickListener);
        row.setUserImageClickListener(this.hostImageClickListener);
        row.setSubtitleClickListener(this.hostImageClickListener);
        row.setOnClickListener(this.clickListener);
        if (this.clickListener != null) {
            row.setBackgroundResource(AndroidUtils.getResource(context));
        } else {
            row.setBackground(null);
        }
    }

    public void unbind(ReportableDetailsSummary row) {
        super.unbind(row);
        row.setOnClickListener(null);
    }

    public ReportableListingDetailsSummaryEpoxyModel reservation(Reservation reservation) {
        this.listing = reservation.getListing();
        this.host = reservation.getHost() != null ? reservation.getHost() : this.listing.getHost();
        return this;
    }

    public ReportableListingDetailsSummaryEpoxyModel listing(Listing listing2) {
        this.listing = listing2;
        this.businessReady = listing2.isBusinessTravelReady();
        this.host = listing2.getPrimaryHost();
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
