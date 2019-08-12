package com.airbnb.android.lib.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.utils.NumberUtils;
import com.bugsnag.android.MetaData;

public class PriceEditText extends NumberAndUnitsEditText {
    public PriceEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPrice(Integer price, String currency) {
        setPrice(price == null ? 0 : price.intValue(), currency);
    }

    public void setPrice(int priceInt, String currency) {
        setPrice(priceInt, currency, null);
    }

    public void setPrice(int priceInt, String currency, Long listingId) {
        String str;
        resetUnitViews();
        if (TextUtils.isEmpty(currency)) {
            MetaData metaData = new MetaData();
            if (listingId != null) {
                metaData.addToTab("USER", "listing_id", listingId);
            }
            StringBuilder append = new StringBuilder().append("currency is ");
            if (currency == null) {
                str = "null";
            } else {
                str = "empty";
            }
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException(append.append(str).toString()), metaData);
        } else {
            CurrencyFormatter currencyFormatter = AirbnbApplication.instance(getContext()).component().currencyHelper();
            currencyFormatter.setCurrency(currency, false, false);
            TextView priceSign = currencyFormatter.isSymbolPrefixed() ? this.unitsTagLeft : this.unitsTagRight;
            priceSign.setVisibility(0);
            priceSign.setText(currencyFormatter.getLocalCurrencySymbol());
        }
        this.valueEditText.setText((priceInt > 0 || !this.hideIfZero) ? String.valueOf(priceInt) : "");
    }

    public int getValue() {
        return NumberUtils.tryParseInt(this.valueEditText.getText().toString(), 0);
    }
}
