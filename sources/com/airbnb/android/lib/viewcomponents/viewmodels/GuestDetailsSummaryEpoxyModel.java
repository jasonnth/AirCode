package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class GuestDetailsSummaryEpoxyModel extends AirEpoxyModel<UserDetailsActionRow> {
    OnClickListener clickListener;
    User guest;

    public void bind(UserDetailsActionRow row) {
        super.bind(row);
        Context context = row.getContext();
        if (this.guest != null) {
            row.setTitleText(this.guest.getName());
            row.setSubtitleText(this.guest.getLocation());
            row.setUserImageUrl(this.guest.getPictureUrl());
            row.setExtraText(generateUserExtraText(context));
            if (this.guest.isVerifiedId()) {
                row.setUserStatusIcon(C0880R.C0881drawable.user_profile_verified_id);
            }
        } else {
            row.setTitleText(null);
            row.setSubtitleText(null);
            row.setUserImageUrl(null);
            row.setExtraText(null);
        }
        row.setOnClickListener(this.clickListener);
    }

    private String generateUserExtraText(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getResources().getQuantityString(C0880R.plurals.reviews, this.guest.getRevieweeCount(), new Object[]{Integer.valueOf(this.guest.getRevieweeCount())}));
        if (this.guest.isVerifiedId()) {
            sb.append(context.getResources().getString(C0880R.string.bullet_with_space));
            sb.append(context.getResources().getString(C0880R.string.user_profile_verified));
        }
        return sb.toString();
    }

    public void unbind(UserDetailsActionRow row) {
        super.unbind(row);
        row.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
