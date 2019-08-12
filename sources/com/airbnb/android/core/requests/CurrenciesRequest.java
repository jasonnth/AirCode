package com.airbnb.android.core.requests;

import android.content.Context;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.models.Currency;
import com.airbnb.android.core.responses.CurrenciesResponse;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.utils.AirbnbConstants;
import com.google.common.collect.FluentIterable;
import java.lang.reflect.Type;
import java.util.List;

public class CurrenciesRequest extends BaseRequestV2<CurrenciesResponse> {
    CurrencyFormatter currencyFormatter;
    AirbnbPreferences preferences;

    public CurrenciesRequest(Context context) {
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
    }

    public String getPath() {
        return "currencies";
    }

    public AirResponse<CurrenciesResponse> transformResponse(AirResponse<CurrenciesResponse> response) {
        Currency selectedCurrency = new Currency();
        selectedCurrency.setCode(this.currencyFormatter.getLocalCurrencyString());
        if (((CurrenciesResponse) response.body()).currencies.contains(selectedCurrency)) {
            this.preferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_CURRENCY, selectedCurrency.getCode()).apply();
        } else {
            ((CurrenciesResponse) response.body()).requiresCurrencyChange = true;
        }
        return response;
    }

    public long getCacheTimeoutMs() {
        return DateHelper.ONE_MONTH_MILLIS;
    }

    public long getCacheOnlyTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return CurrenciesResponse.class;
    }

    public static List<Currency> filterIfIndianRegion(List<Currency> currencies) {
        if (AppLaunchUtils.isIndiaRegion()) {
            return FluentIterable.from((Iterable<E>) currencies).filter(CurrenciesRequest$$Lambda$1.lambdaFactory$()).toList();
        }
        return currencies;
    }
}
