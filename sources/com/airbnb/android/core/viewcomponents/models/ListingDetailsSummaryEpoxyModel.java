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
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ListingDetailsSummaryEpoxyModel extends AirEpoxyModel<UserDetailsActionRow> {
    boolean businessReady;
    OnClickListener clickListener;
    User host;
    OnClickListener hostImageClickListener;
    Listing listing;

    public void bind(UserDetailsActionRow row) {
        super.bind(row);
        Context context = row.getContext();
        if (this.listing != null) {
            row.setTitleText(this.listing.getRoomType(context));
        } else {
            row.setTitleText(null);
        }
        if (this.host != null) {
            String hostedByTokenized = context.getString(C0716R.string.hosted_by_name, new Object[]{SpannableUtils.COLORED_SUBSTRING_TOKEN});
            if (this.hostImageClickListener != null) {
                row.setSubtitleText(SpannableUtils.makeColoredSubstring(hostedByTokenized, this.host.getName(), ContextCompat.getColor(context, C0716R.color.n2_text_color_actionable)));
            } else {
                row.setSubtitleText(context.getString(C0716R.string.hosted_by_name, new Object[]{this.host.getName()}));
            }
            row.setUserImageUrl(this.host.getPictureUrl());
            if (this.host.isSuperhost()) {
                row.setUserStatusIcon(C0716R.C0717drawable.sh_badge);
            }
        } else {
            row.setSubtitleText(context.getString(C0716R.string.hosted_by_name, new Object[]{""}));
            row.showPlaceholderImage();
            row.setUserStatusIcon((Drawable) null);
        }
        if (this.businessReady) {
            row.setLabelText(context.getString(C0716R.string.business_ready));
        }
        row.setUserImageClickListener(this.hostImageClickListener);
        row.setSubtitleClickListener(this.hostImageClickListener);
        row.setOnClickListener(this.clickListener);
        if (this.clickListener != null) {
            row.setBackgroundResource(AndroidUtils.getResource(context));
        } else {
            row.setBackground(null);
        }
    }

    public void unbind(UserDetailsActionRow row) {
        super.unbind(row);
        row.setOnClickListener(null);
    }

    public ListingDetailsSummaryEpoxyModel reservation(Reservation reservation) {
        this.listing = reservation.getListing();
        this.host = reservation.getHost() != null ? reservation.getHost() : this.listing.getHost();
        return this;
    }

    public ListingDetailsSummaryEpoxyModel listing(Listing listing2) {
        this.listing = listing2;
        this.host = listing2.getPrimaryHost();
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
