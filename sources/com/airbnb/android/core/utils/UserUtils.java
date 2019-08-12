package com.airbnb.android.core.utils;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.User;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class UserUtils {
    public static String generateNamesString(Context context, List<User> users) {
        Check.notEmpty(users);
        List<String> names = FluentIterable.from((Iterable<E>) users).transform(UserUtils$$Lambda$1.lambdaFactory$()).toList();
        switch (users.size()) {
            case 1:
                return (String) names.get(0);
            case 2:
                return context.getString(C0716R.string.list_of_names_2, new Object[]{names.get(0), names.get(1)});
            case 3:
                return context.getString(C0716R.string.list_of_names_3, new Object[]{names.get(0), names.get(1), names.get(2)});
            case 4:
                return context.getString(C0716R.string.list_of_names_4, new Object[]{names.get(0), names.get(1), names.get(2), names.get(3)});
            case 5:
                return context.getString(C0716R.string.list_of_names_5, new Object[]{names.get(0), names.get(1), names.get(2), names.get(3), names.get(4)});
            case 6:
                return context.getString(C0716R.string.list_of_names_6, new Object[]{names.get(0), names.get(1), names.get(2), names.get(3), names.get(4), names.get(5)});
            case 7:
                return context.getString(C0716R.string.list_of_names_7, new Object[]{names.get(0), names.get(1), names.get(2), names.get(3), names.get(4), names.get(5), names.get(6)});
            case 8:
                return context.getString(C0716R.string.list_of_names_8, new Object[]{names.get(0), names.get(1), names.get(2), names.get(3), names.get(4), names.get(5), names.get(6), names.get(7)});
            default:
                return context.getString(C0716R.string.list_of_names_more, new Object[]{names.get(0), names.get(1), names.get(2), names.get(3), names.get(4), names.get(5), names.get(6), names.get(7), Integer.valueOf(names.size() - 8)});
        }
    }

    public static String getHostUserSuspensionText(Context context, User user) {
        if (!user.isSuspended()) {
            return null;
        }
        int listingsCount = user.getListingsCount();
        String endDate = user.getSuspensionEndDate();
        return context.getResources().getQuantityString(TextUtils.isEmpty(endDate) ? C0716R.plurals.listings_temporarily_suspended : C0716R.plurals.listings_temporarily_suspended_until, listingsCount, new Object[]{endDate});
    }
}
