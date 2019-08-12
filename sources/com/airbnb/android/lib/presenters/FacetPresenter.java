package com.airbnb.android.lib.presenters;

import android.content.res.Resources;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class FacetPresenter {
    public static void setFacetCount(TextView facetCountView, int count) {
        boolean valid;
        boolean z = true;
        if (count >= 0) {
            valid = true;
        } else {
            valid = false;
        }
        if (valid) {
            Resources res = facetCountView.getResources();
            if (count > 300) {
                facetCountView.setText(res.getString(C0880R.string.max_num_listings_count_in_parenthesis, new Object[]{Integer.valueOf(300)}));
            } else {
                facetCountView.setText(res.getString(C0880R.string.numeric_count_in_parenthesis, new Object[]{Integer.valueOf(count)}));
            }
        }
        if (valid) {
            z = false;
        }
        ViewUtils.setInvisibleIf(facetCountView, z);
    }
}
