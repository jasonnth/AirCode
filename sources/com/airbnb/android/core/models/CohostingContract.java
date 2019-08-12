package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.generated.GenCohostingContract;
import com.airbnb.p027n2.utils.IntegerNumberFormatHelper;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CohostingContract extends GenCohostingContract {
    public static final Creator<CohostingContract> CREATOR = new Creator<CohostingContract>() {
        public CohostingContract[] newArray(int size) {
            return new CohostingContract[size];
        }

        public CohostingContract createFromParcel(Parcel source) {
            CohostingContract object = new CohostingContract();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getFormattedHostingFee(Context context) {
        int percentage = getPercentage();
        long fixedAmount = getFixedAmount();
        String currency = getAmountCurrency();
        if (percentage == 0 && fixedAmount == 0) {
            return null;
        }
        List<String> list = new ArrayList<>();
        if (percentage > 0) {
            list.add(context.getString(C0716R.string.n2_percentage, new Object[]{String.valueOf(percentage)}));
        }
        if (fixedAmount > 0) {
            list.add(getFormattedFee(currency, fixedAmount));
        }
        return TextUtils.join("+", list);
    }

    public String getMinMaxFeeStr(Context context) {
        boolean hasMaxFee;
        boolean hasMinFee;
        if (getFormattedMaximumFee() != null) {
            hasMaxFee = true;
        } else {
            hasMaxFee = false;
        }
        if (getFormattedMinimumFee() != null) {
            hasMinFee = true;
        } else {
            hasMinFee = false;
        }
        if (hasMaxFee && hasMinFee) {
            return context.getString(C0716R.string.cohosting_share_of_earnings_min_and_max_fee, new Object[]{getFormattedMinimumFee(), getFormattedMaximumFee()});
        } else if (hasMinFee) {
            return context.getString(C0716R.string.cohosting_share_of_earnings_min_fee, new Object[]{getFormattedMinimumFee()});
        } else if (!hasMaxFee) {
            return null;
        } else {
            return context.getString(C0716R.string.cohosting_share_of_earnings_max_fee, new Object[]{getFormattedMaximumFee()});
        }
    }

    public String getFormattedMinimumFee() {
        if (getMinimumFee() > 0) {
            return getFormattedFee(getAmountCurrency(), getMinimumFee());
        }
        return null;
    }

    public String getFormattedMaximumFee() {
        if (getMaximumFee() > 0) {
            return getFormattedFee(getAmountCurrency(), getMaximumFee());
        }
        return null;
    }

    private String getFormattedFee(String currency, long value) {
        return IntegerNumberFormatHelper.forCurrency(Currency.getInstance(currency)).format(value);
    }
}
