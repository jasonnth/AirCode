package com.airbnb.android.lib.presenters;

import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.lib.C0880R;

public class GuestIdentityTypePresenter {
    public static int getStringResId(Type type) {
        switch (type) {
            case ChineseNationalID:
                return C0880R.string.chinese_national_identification;
            case Passport:
                return C0880R.string.passport;
            default:
                throw new IllegalArgumentException("unknown identity type: " + type.name());
        }
    }
}
