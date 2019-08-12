package com.airbnb.android.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import com.airbnb.utils.R;
import java.text.DecimalFormat;

public final class ListingRatingUtils {
    private static DecimalFormat countFormatter;
    private static DecimalFormat ratingFormatter;

    public static CharSequence getRatingText(Context context, double rating, int count) {
        return getRatingText(context, rating, count, null);
    }

    public static CharSequence getRatingText(Context context, double rating, String price) {
        return getRatingText(context, rating, 0, price);
    }

    private static CharSequence getRatingText(Context context, double rating, int count, String price) {
        StringBuilder builder = new StringBuilder();
        if (rating > 0.0d) {
            builder.append(getRatingFormatter().format(rating));
            builder.append(" ");
            builder.append("�");
        } else {
            builder.append(context.getResources().getString(R.string.listing_selector_subtitle_no_ratings));
        }
        if (count > 0) {
            if (builder.length() > 0) {
                builder.append(context.getResources().getString(R.string.bullet_with_space));
            }
            String viewCountString = getCountFormatter().format((long) count);
            builder.append(context.getResources().getQuantityString(R.plurals.view_count_string, count, new Object[]{viewCountString}));
        }
        if (!TextUtils.isEmpty(price)) {
            if (builder.length() > 0) {
                builder.append(context.getResources().getString(R.string.bullet_with_space));
            }
            builder.append(price);
        }
        SpannableString spannable = new SpannableString(builder.toString());
        int index = builder.toString().indexOf("�");
        if (index != -1) {
            Drawable drawable = AppCompatResources.getDrawable(context, R.drawable.ic_babu_star_small);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            spannable.setSpan(new ImageSpan(drawable, 1), index, index + 1, 17);
        }
        return spannable;
    }

    private static DecimalFormat getRatingFormatter() {
        if (ratingFormatter == null) {
            ratingFormatter = new DecimalFormat("0.0");
        }
        return ratingFormatter;
    }

    private static DecimalFormat getCountFormatter() {
        if (countFormatter == null) {
            countFormatter = new DecimalFormat("###,###,###");
        }
        return countFormatter;
    }
}
