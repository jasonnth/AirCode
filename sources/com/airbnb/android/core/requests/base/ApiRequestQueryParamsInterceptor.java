package com.airbnb.android.core.requests.base;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.airrequest.BaseUrl;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.LanguageUtils;
import com.airbnb.android.utils.LocaleUtil;
import java.io.IOException;
import java.util.Locale;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRequestQueryParamsInterceptor implements Interceptor {
    private final BaseUrl apiBaseUrl;
    private final Context context;
    private final CurrencyFormatter currencyFormatter;

    public ApiRequestQueryParamsInterceptor(Context context2, CurrencyFormatter currencyFormatter2, BaseUrl apiBaseUrl2) {
        this.context = context2;
        this.currencyFormatter = currencyFormatter2;
        this.apiBaseUrl = apiBaseUrl2;
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (request.url().host().equals(this.apiBaseUrl.url().host())) {
            return chain.proceed(request.newBuilder().url(addQueryParametersToUrl(request)).build());
        }
        return chain.proceed(request);
    }

    private HttpUrl addQueryParametersToUrl(Request request) {
        String currency = request.url().queryParameter(AirbnbConstants.PREFS_CURRENCY);
        if (TextUtils.isEmpty(currency)) {
            currency = this.currencyFormatter.getLocalCurrencyString();
        }
        return request.url().newBuilder().addQueryParameter("client_id", AirbnbConstants.CLIENT_ID).addQueryParameter(AccountKitGraphConstants.PARAMETER_LOCALE, localeString()).addQueryParameter(AirbnbConstants.PREFS_CURRENCY, currency).build();
    }

    private String localeString() {
        Locale locale = LanguageUtils.getContextLocale(this.context);
        if (locale == null) {
            locale = Locale.US;
        }
        return LocaleUtil.getString(locale);
    }
}
