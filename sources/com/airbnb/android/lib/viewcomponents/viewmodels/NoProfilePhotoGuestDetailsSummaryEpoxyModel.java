package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.NoProfilePhotoDetailsSummary;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class NoProfilePhotoGuestDetailsSummaryEpoxyModel extends AirEpoxyModel<NoProfilePhotoDetailsSummary> {
    OnClickListener clickListener;
    User guest;
    boolean hideReviewsText = false;
    boolean showNoProfilePhoto;

    public void bind(NoProfilePhotoDetailsSummary row) {
        String str = null;
        super.bind(row);
        Context context = row.getContext();
        if (this.guest != null) {
            row.setTitleText(this.guest.getName());
            row.setSubtitleText(this.guest.getLocation());
            if (!this.showNoProfilePhoto) {
                str = this.guest.getPictureUrl();
            }
            row.setUserImageUrl(str);
            row.setExtraText(generateUserExtraText(context));
            if (this.guest.isVerifiedId()) {
                if (this.showNoProfilePhoto) {
                    row.setTitleStatusIcon(C0880R.C0881drawable.user_profile_verified_id);
                    row.setUserStatusIcon(0);
                } else {
                    row.setUserStatusIcon(C0880R.C0881drawable.user_profile_verified_id);
                    row.setTitleStatusIcon(0);
                }
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
        if (!this.hideReviewsText) {
            sb.append(context.getResources().getQuantityString(C0880R.plurals.reviews, this.guest.getRevieweeCount(), new Object[]{Integer.valueOf(this.guest.getRevieweeCount())}));
        }
        if (this.guest.isVerifiedId()) {
            if (!this.hideReviewsText) {
                sb.append(context.getResources().getString(C0880R.string.bullet_with_space));
            }
            sb.append(context.getResources().getString(C0880R.string.user_profile_verified));
        }
        return sb.toString();
    }

    public void unbind(NoProfilePhotoDetailsSummary row) {
        super.unbind(row);
        row.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}
