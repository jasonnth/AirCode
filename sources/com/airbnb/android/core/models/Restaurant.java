package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.SpannableStringBuilder;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.generated.GenRestaurant;
import com.airbnb.p027n2.primitives.AirmojiEnum;
import com.airbnb.p027n2.utils.TextUtil;
import com.google.common.base.Strings;

public class Restaurant extends GenRestaurant {
    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }

        public Restaurant createFromParcel(Parcel source) {
            Restaurant object = new Restaurant();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final int TOTAL_STARS = 5;

    public CharSequence getStarsString(Context context) {
        boolean halfStar;
        int i;
        float rating = getRating();
        int fullStars = (int) rating;
        if (rating > ((float) fullStars)) {
            halfStar = true;
        } else {
            halfStar = false;
        }
        int i2 = 5 - fullStars;
        if (halfStar) {
            i = 1;
        } else {
            i = 0;
        }
        String stars = new SpannableStringBuilder().append(Strings.repeat(AirmojiEnum.AIRMOJI_CORE_STAR_FULL.character, fullStars)).append(halfStar ? AirmojiEnum.AIRMOJI_CORE_STAR_HALF_OUTLINE.character : "").append(Strings.repeat(AirmojiEnum.AIRMOJI_CORE_STAR_EMPTY.character, i2 - i)).toString();
        return TextUtil.makeColored(context, C0716R.color.n2_babu, context.getString(C0716R.string.resy_stars_on_resy, new Object[]{stars}), stars);
    }
}
