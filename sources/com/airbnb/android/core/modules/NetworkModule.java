package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.airrequest.AirRequest;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.BaseUrl;
import com.airbnb.airrequest.Interceptor;
import com.airbnb.airrequest.MockRequest;
import com.airbnb.airrequest.SingleFireRequestExecutor;
import com.airbnb.airrequest.Transformer;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.graphql.GraphistClient;
import com.airbnb.android.core.messaging.MessageStoreArchiveThreadRequest.TransformerFactory;
import com.airbnb.android.core.net.AirbnbApiUrlMatcher;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.net.ApplicationInterceptorsProvider;
import com.airbnb.android.core.net.ApplicationInterceptorsProvider.Impl;
import com.airbnb.android.core.net.LowBandwidthManager;
import com.airbnb.android.core.net.NetworkInterceptorsProvider;
import com.airbnb.android.core.net.NetworkMonitor;
import com.airbnb.android.core.net.OkHttpInitializer;
import com.airbnb.android.core.requests.CreateMessageRequest;
import com.airbnb.android.core.requests.ErfExperimentsRequest;
import com.airbnb.android.core.requests.InboxRequest;
import com.airbnb.android.core.requests.MessagingSyncRequest;
import com.airbnb.android.core.requests.ThreadRequest;
import com.airbnb.android.core.requests.base.AirRequestHeadersInterceptor;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.requests.base.ApiRequestQueryParamsInterceptor;
import com.airbnb.android.core.services.NetworkTimeProvider;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.geocoder.GeocoderBaseUrl;
import com.airbnb.android.utils.ConcurrentUtil.MainThreadExecutor;
import com.airbnb.rxgroups.ObservableManager;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.C3107Operation.Variables;
import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy;
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.sql.ApolloSqlHelper;
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.apache.commons.net.ntp.NTPUDPClient;
import retrofit2.CallAdapter.Factory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class NetworkModule {
    private final OkHttpInitializer okHttpInitializer;

    public NetworkModule() {
        this(OkHttpInitializer.IDENTITY);
    }

    public NetworkModule(OkHttpInitializer okHttpInitializer2) {
        this.okHttpInitializer = okHttpInitializer2;
    }

    /* access modifiers changed from: 0000 */
    public LowBandwidthManager provideLowBandwidthUtils(AirbnbPreferences preferences, Bus bus, NetworkMonitor networkMonitor) {
        return _provideLowBandwidthUtils(preferences, bus, networkMonitor);
    }

    /* access modifiers changed from: 0000 */
    public LowBandwidthManager _provideLowBandwidthUtils(AirbnbPreferences preferences, Bus bus, NetworkMonitor networkMonitor) {
        return new LowBandwidthManager(preferences, bus, networkMonitor);
    }

    /* access modifiers changed from: 0000 */
    public Cache provideCache(Context context) {
        return new Cache(new File(context.getCacheDir(), "okhttp"), 20971520);
    }

    /* access modifiers changed from: 0000 */
    public NetworkInterceptorsProvider provideNetworkInterceptors(ApiRequestHeadersInterceptor requestHeadersInterceptor) {
        return _provideNetworkInterceptors(requestHeadersInterceptor);
    }

    /* access modifiers changed from: 0000 */
    public ApplicationInterceptorsProvider provideApplicationInterceptors(BaseUrl apiBaseUrl, ApiRequestQueryParamsInterceptor queryParamsInterceptor) {
        return _provideApplicationInterceptors(apiBaseUrl, queryParamsInterceptor);
    }

    /* access modifiers changed from: protected */
    public ApplicationInterceptorsProvider _provideApplicationInterceptors(BaseUrl apiBaseUrl, ApiRequestQueryParamsInterceptor queryParamsInterceptor) {
        return new Impl(apiBaseUrl, queryParamsInterceptor);
    }

    static AirbnbApiUrlMatcher provideUrlMatcher(BaseUrl baseUrl) {
        return new AirbnbApiUrlMatcher(baseUrl);
    }

    /* access modifiers changed from: 0000 */
    public ApiRequestHeadersInterceptor provideApiRequestHeadersInterceptor(Context context, AirbnbAccountManager accountManager, AirbnbApi airbnbApi, SharedPrefsHelper sharedPrefsHelper, AffiliateInfo affiliateInfo, AirbnbApiUrlMatcher urlMatcher) {
        return new ApiRequestHeadersInterceptor(context, accountManager, airbnbApi, sharedPrefsHelper, affiliateInfo, urlMatcher);
    }

    /* access modifiers changed from: 0000 */
    public NetworkInterceptorsProvider _provideNetworkInterceptors(ApiRequestHeadersInterceptor requestHeadersInterceptor) {
        return new NetworkInterceptorsProvider.Impl(requestHeadersInterceptor);
    }

    /* access modifiers changed from: 0000 */
    public OkHttpClient provideOkHttpClient(Cache cache, NetworkInterceptorsProvider networkInterceptorsProvider, ApplicationInterceptorsProvider applicationInterceptorsProvider, CookieJar cookieJar) {
        return _provideOkHttpClient(cache, networkInterceptorsProvider, applicationInterceptorsProvider, cookieJar);
    }

    /* access modifiers changed from: 0000 */
    public OkHttpClient _provideOkHttpClient(Cache cache, NetworkInterceptorsProvider networkInterceptorsProvider, ApplicationInterceptorsProvider applicationInterceptorsProvider, CookieJar cookieJar) {
        Builder builder = new Builder().cache(cache).writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).connectTimeout(20, TimeUnit.SECONDS).cookieJar(cookieJar);
        builder.networkInterceptors().addAll((Collection) networkInterceptorsProvider.get());
        builder.interceptors().addAll((Collection) applicationInterceptorsProvider.get());
        return ((Builder) this.okHttpInitializer.call(builder)).build();
    }

    /* access modifiers changed from: 0000 */
    public BaseUrl provideEndpoint() {
        return _provideEndpoint();
    }

    /* access modifiers changed from: 0000 */
    public BaseUrl _provideEndpoint() {
        return NetworkModule$$Lambda$1.lambdaFactory$();
    }

    /* access modifiers changed from: 0000 */
    public GeocoderBaseUrl provideGeocoderRequestBaseUrl() {
        return _provideGeocoderRequestBaseUrl();
    }

    /* access modifiers changed from: 0000 */
    public GeocoderBaseUrl _provideGeocoderRequestBaseUrl() {
        return new GeocoderBaseUrl() {
            public HttpUrl url() {
                return HttpUrl.parse("https://maps.googleapis.com/");
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public Factory provideCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    /* access modifiers changed from: 0000 */
    public Retrofit.Builder provideRetrofitBuilder(OkHttpClient client, Factory callAdapterFactory, Executor callbackExecutor, ConverterFactory converterFactory, BaseUrl baseUrl) {
        return _provideRetrofitBuilder(client, callAdapterFactory, callbackExecutor, converterFactory, baseUrl);
    }

    /* access modifiers changed from: 0000 */
    public SingleFireRequestExecutor provideSingleFireRequestExecutor(AirRequestInitializer initializer) {
        return new SingleFireRequestExecutor(initializer);
    }

    /* access modifiers changed from: 0000 */
    public Retrofit.Builder _provideRetrofitBuilder(OkHttpClient client, Factory callAdapterFactory, Executor callbackExecutor, ConverterFactory converterFactory, BaseUrl baseUrl) {
        return NetworkUtil.provideRetrofitBuilder(client, callAdapterFactory, callbackExecutor, converterFactory, baseUrl);
    }

    /* access modifiers changed from: 0000 */
    public Executor provideRequestCallbackExecutor() {
        return new MainThreadExecutor();
    }

    /* access modifiers changed from: 0000 */
    public Retrofit provideRestAdapter(Retrofit.Builder builder) {
        return builder.build();
    }

    /* access modifiers changed from: 0000 */
    public NetworkMonitor provideNetworkMonitor(Context context) {
        return new NetworkMonitor(context);
    }

    /* access modifiers changed from: 0000 */
    public ObservableManager provideObservableManager() {
        return new ObservableManager();
    }

    /* access modifiers changed from: 0000 */
    public AirRequestHeadersInterceptor provideRequestHeaders(SharedPrefsHelper sharedPrefsHelper) {
        return new AirRequestHeadersInterceptor(sharedPrefsHelper);
    }

    /* access modifiers changed from: 0000 */
    public AirlockErrorHandler provideAirlockErrorHandler(Context context, AirbnbAccountManager accountManager, ObjectMapper objectMapper) {
        return new AirlockErrorHandler(context, accountManager, objectMapper);
    }

    /* access modifiers changed from: 0000 */
    public AirRequestInitializer provideAirRequestInitializer(Context context, Retrofit retrofit, AirRequestHeadersInterceptor headersInterceptor, ObservableManager observableManager, ExperimentsProvider experimentsProvider, AirlockErrorHandler airlockErrorHandler, LoggingContextFactory loggingContextFactory, SharedPrefsHelper sharedPrefsHelper) {
        return new AirRequestInitializer.Builder().retrofit(retrofit).enableDebugFeatures(BuildHelper.isDevelopmentBuild()).addInterceptorFactory(NetworkModule$$Lambda$3.lambdaFactory$(headersInterceptor)).addTransformerFactory(NetworkModule$$Lambda$4.lambdaFactory$(NetworkModule$$Lambda$2.lambdaFactory$(headersInterceptor, airlockErrorHandler, loggingContextFactory, context, sharedPrefsHelper))).addTransformerFactory(new TransformerFactory()).addTransformerFactory(new InboxRequest.TransformerFactory()).addTransformerFactory(new ThreadRequest.TransformerFactory()).addTransformerFactory(new ErfExperimentsRequest.TransformerFactory(experimentsProvider)).addTransformerFactory(new CreateMessageRequest.TransformerFactory()).addTransformerFactory(new MessagingSyncRequest.TransformerFactory()).addTransformerFactory(new MockRequest.TransformerFactory(context)).observableManager(observableManager).build();
    }

    static /* synthetic */ Interceptor lambda$provideAirRequestInitializer$2(AirRequestHeadersInterceptor headersInterceptor, Type t) {
        return headersInterceptor;
    }

    static /* synthetic */ Transformer lambda$provideAirRequestInitializer$3(Transformer defaultTransformer, AirRequest r, AirRequestInitializer i) {
        return defaultTransformer;
    }

    /* access modifiers changed from: 0000 */
    public ApiRequestQueryParamsInterceptor provideQueryParamsProvider(Context context, CurrencyFormatter currencyFormatter, BaseUrl apiBaseUrl) {
        return new ApiRequestQueryParamsInterceptor(context, currencyFormatter, apiBaseUrl);
    }

    public NetworkTimeProvider provideNetworkTimeProvider() {
        return _provideNetworkTimeProvider();
    }

    public NetworkTimeProvider _provideNetworkTimeProvider() {
        return new NetworkTimeProvider(new NTPUDPClient());
    }

    /* access modifiers changed from: 0000 */
    public ApolloClient provideApolloClient(Context context, OkHttpClient okHttpClient) {
        return ApolloClient.builder().serverUrl(AirbnbApi.API_ENDPOINT_URL + AirbnbApi.GRAPHQL_URL_PATH).okHttpClient(okHttpClient).normalizedCache(new LruNormalizedCacheFactory(EvictionPolicy.NO_EVICTION, new SqlNormalizedCacheFactory(ApolloSqlHelper.create(context, "ApolloDB"))), new CacheKeyResolver() {
            public CacheKey fromFieldRecordSet(Field field, Map<String, Object> recordSet) {
                if (recordSet.containsKey("id")) {
                    return CacheKey.from(recordSet.get("__typename") + "." + recordSet.get("id"));
                }
                return CacheKey.NO_KEY;
            }

            public CacheKey fromFieldArguments(Field field, Variables variables) {
                return CacheKey.NO_KEY;
            }
        }).build();
    }

    /* access modifiers changed from: 0000 */
    public GraphistClient provideGraphistClient(ApolloClient client) {
        return new GraphistClient(client);
    }
}
