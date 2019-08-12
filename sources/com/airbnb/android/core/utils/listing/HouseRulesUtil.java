package com.airbnb.android.core.utils.listing;

import android.content.Context;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.GuestControlType;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.utils.ListUtils;
import java.util.Collection;
import java.util.List;

public class HouseRulesUtil {
    public static String getDescription(Context context, GuestControls guestControls) {
        if (guestControls == null) {
            return null;
        }
        List<GuestControlType> rules = guestControls.getRules(false);
        if (ListUtils.isEmpty((Collection<?>) rules)) {
            return null;
        }
        switch (rules.size()) {
            case 1:
                return context.getString(C0716R.string.not_suitable_for_one, new Object[]{getRuleName(rules, 0, context)});
            case 2:
                return context.getString(C0716R.string.not_suitable_for_two, new Object[]{getRuleName(rules, 0, context), getRuleName(rules, 1, context)});
            case 3:
                return context.getString(C0716R.string.not_suitable_for_three, new Object[]{getRuleName(rules, 0, context), getRuleName(rules, 1, context), getRuleName(rules, 2, context)});
            case 4:
                return context.getString(C0716R.string.not_suitable_for_four, new Object[]{getRuleName(rules, 0, context), getRuleName(rules, 1, context), getRuleName(rules, 2, context), getRuleName(rules, 3, context)});
            case 5:
                return context.getString(C0716R.string.not_suitable_for_five, new Object[]{getRuleName(rules, 0, context), getRuleName(rules, 1, context), getRuleName(rules, 2, context), getRuleName(rules, 3, context), getRuleName(rules, 4, context)});
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Number of GuestControls is not valid: " + rules.size()));
                return null;
        }
    }

    private static String getRuleName(List<GuestControlType> rules, int index, Context context) {
        return context.getString(((GuestControlType) rules.get(index)).getLocalizedName()).toLowerCase();
    }
}
