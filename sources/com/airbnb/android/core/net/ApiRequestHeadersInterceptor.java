package com.airbnb.android.core.net;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.adtracking.GoogleAdvertisingIdProvider;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateData;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

public final class ApiRequestHeadersInterceptor implements Interceptor {
    private static final String HEADER_AFFILIATE_CAMPAIGN = "X-Airbnb-Affiliate-Campaign";
    private static final String HEADER_AFFILIATE_ID = "X-Airbnb-Affiliate-ID";
    private static final String HEADER_CELLULAR_TYPE = "X-Airbnb-Cellular-Type";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_DEVICE_COUNTRY = "X-Airbnb-Carrier-Country";
    private static final String HEADER_DEVICE_ID = "X-Airbnb-Device-ID";
    private static final String HEADER_GOOGLE_AD_ID = "X-Airbnb-Advertising-ID";
    private static final String HEADER_HOST = "Host";
    public static final String HEADER_METHOD_OVERRIDE = "X-HTTP-Method-Override";
    private static final String HEADER_NETWORK_TYPE = "X-Airbnb-Network-Type";
    public static final String HEADER_OAUTH = "X-Airbnb-OAuth-Token";
    public static final String HEADER_PREFETCH = "X-Airbnb-Prefetch";
    public static final String HEADER_SECURE_IDENTIFIER_KEY = "X-Airbnb-SID";
    private static final String HEADER_UA = "User-Agent";
    private final AirbnbAccountManager accountManager;
    private final AffiliateInfo affiliateInfo;
    private final AirbnbApi airbnbApi;
    private final String cellularType;
    private final String networkType;
    private final SharedPrefsHelper sharedPrefsHelper;
    private final AirbnbApiUrlMatcher urlMatcher;

    static class SafeHeadersBuilder {
        private final Builder builder;

        SafeHeadersBuilder(Builder builder2) {
            this.builder = builder2;
        }

        /* access modifiers changed from: 0000 */
        public SafeHeadersBuilder maybeSet(String name, String value) {
            if (!TextUtils.isEmpty(value)) {
                return set(name, value);
            }
            return this;
        }

        /* access modifiers changed from: 0000 */
        public SafeHeadersBuilder set(String name, String value) {
            this.builder.set(name, Util.toHumanReadableAscii(value));
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Headers build() {
            return this.builder.build();
        }
    }

    public ApiRequestHeadersInterceptor(Context context, AirbnbAccountManager accountManager2, AirbnbApi airbnbApi2, SharedPrefsHelper sharedPrefsHelper2, AffiliateInfo affiliateInfo2, AirbnbApiUrlMatcher urlMatcher2) {
        this.accountManager = accountManager2;
        this.airbnbApi = airbnbApi2;
        this.sharedPrefsHelper = sharedPrefsHelper2;
        this.affiliateInfo = affiliateInfo2;
        this.urlMatcher = urlMatcher2;
        this.cellularType = NetworkUtil.getCellularType(context);
        this.networkType = NetworkUtil.getNetworkType(context);
    }

    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (this.urlMatcher.matches(request.url())) {
            return chain.proceed(request.newBuilder().headers(appendHeaders(request)).build());
        }
        return chain.proceed(request);
    }

    private Headers appendHeaders(Request request) {
        SafeHeadersBuilder builder = new SafeHeadersBuilder(request.headers().newBuilder()).set(HEADER_UA, this.airbnbApi.getUserAgent()).set(HEADER_SECURE_IDENTIFIER_KEY, this.sharedPrefsHelper.getSecureIdentifier()).set(HEADER_NETWORK_TYPE, this.networkType).maybeSet(HEADER_DEVICE_ID, this.airbnbApi.getAndroidId()).maybeSet(HEADER_DEVICE_COUNTRY, this.airbnbApi.getDeviceCountry()).maybeSet(HEADER_OAUTH, this.accountManager.getAccessToken()).maybeSet(HEADER_GOOGLE_AD_ID, GoogleAdvertisingIdProvider.getGoogleAdvertisingId());
        if (AirbnbApi.devEndpointEnabled()) {
            builder.set(HEADER_HOST, "api.localhost.airbnb.com");
        }
        AffiliateData affiliateData = this.affiliateInfo.getAffiliateCampaignData();
        if (affiliateData != null) {
            builder.maybeSet(HEADER_AFFILIATE_CAMPAIGN, affiliateData.campaign());
            if (affiliateData.affiliateId() > 0) {
                builder.set(HEADER_AFFILIATE_ID, String.valueOf(affiliateData.affiliateId()));
            }
        }
        if (NetworkUtil.NETWORK_TYPE_CELLULAR.equals(this.networkType)) {
            builder.set(HEADER_CELLULAR_TYPE, this.cellularType);
        }
        return builder.build();
    }
}
