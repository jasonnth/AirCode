package com.braintreepayments.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.activities.GiftCardsActivity;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.BrowserSwitchException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.interfaces.PayPalApprovalCallback;
import com.braintreepayments.api.interfaces.PayPalApprovalHandler;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.internal.AppHelper;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.internal.ManifestValidator;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PayPalAccountBuilder;
import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PayPalConfiguration;
import com.braintreepayments.api.models.PayPalPaymentResource;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.PostalAddress;
import com.facebook.internal.AnalyticsEvents;
import com.paypal.android.sdk.onetouch.core.AuthorizationRequest;
import com.paypal.android.sdk.onetouch.core.BillingAgreementRequest;
import com.paypal.android.sdk.onetouch.core.CheckoutRequest;
import com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore;
import com.paypal.android.sdk.onetouch.core.Request;
import com.paypal.android.sdk.onetouch.core.Result;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import com.paypal.android.sdk.onetouch.core.sdk.PayPalScope;
import com.paypal.android.sdk.onetouch.core.sdk.PendingRequest;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.PushActivity;

public class PayPal {
    public static final String SCOPE_ADDRESS = PayPalScope.ADDRESS.getScopeUri();
    public static final String SCOPE_EMAIL = PayPalScope.EMAIL.getScopeUri();
    public static final String SCOPE_FUTURE_PAYMENTS = PayPalScope.FUTURE_PAYMENTS.getScopeUri();
    protected static boolean sFuturePaymentsOverride = false;

    public static void authorizeAccount(final BraintreeFragment fragment, final List<String> additionalScopes) {
        fragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (!configuration.isPayPalEnabled()) {
                    fragment.postCallback((Exception) new BraintreeException("PayPal is not enabled"));
                } else if (!PayPal.isManifestValid(fragment)) {
                    fragment.sendAnalyticsEvent("paypal.invalid-manifest");
                    fragment.postCallback((Exception) new BraintreeException("BraintreeBrowserSwitchActivity missing, incorrectly configured in AndroidManifest.xml or another app defines the same browser switch url as this app. See https://developers.braintreepayments.com/guides/client-sdk/android/v2#browser-switch for the correct configuration"));
                } else if (!configuration.getPayPal().shouldUseBillingAgreement() || PayPal.sFuturePaymentsOverride) {
                    fragment.sendAnalyticsEvent("paypal.future-payments.selected");
                    AuthorizationRequest request = PayPal.getAuthorizationRequest(fragment);
                    if (additionalScopes != null) {
                        for (String scope : additionalScopes) {
                            request.withScopeValue(scope);
                        }
                    }
                    PayPal.startPayPal(fragment, request, null);
                } else {
                    PayPal.requestBillingAgreement(fragment, new PayPalRequest());
                }
            }
        });
    }

    public static void requestBillingAgreement(BraintreeFragment fragment, PayPalRequest request) {
        requestBillingAgreement(fragment, request, null);
    }

    public static void requestBillingAgreement(BraintreeFragment fragment, PayPalRequest request, PayPalApprovalHandler handler) {
        if (request.getAmount() == null) {
            fragment.sendAnalyticsEvent("paypal.billing-agreement.selected");
            requestOneTimePayment(fragment, request, true, handler);
            return;
        }
        fragment.postCallback((Exception) new BraintreeException("There must be no amount specified for the Billing Agreement flow"));
    }

    private static void requestOneTimePayment(final BraintreeFragment fragment, final PayPalRequest paypalRequest, final boolean isBillingAgreement, final PayPalApprovalHandler handler) {
        final HttpResponseCallback callback = new HttpResponseCallback() {
            public void success(String responseBody) {
                Request request;
                try {
                    String redirectUrl = Uri.parse(PayPalPaymentResource.fromJson(responseBody).getRedirectUrl()).buildUpon().appendQueryParameter("useraction", paypalRequest.getUserAction()).toString();
                    if (isBillingAgreement) {
                        request = PayPal.getBillingAgreementRequest(fragment, redirectUrl);
                    } else {
                        request = PayPal.getCheckoutRequest(fragment, redirectUrl);
                    }
                    PayPal.startPayPal(fragment, request, handler);
                } catch (JSONException e) {
                    fragment.postCallback((Exception) e);
                }
            }

            public void failure(Exception e) {
                fragment.postCallback(e);
            }
        };
        fragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (!configuration.isPayPalEnabled()) {
                    fragment.postCallback((Exception) new BraintreeException("PayPal is not enabled"));
                } else if (!PayPal.isManifestValid(fragment)) {
                    fragment.sendAnalyticsEvent("paypal.invalid-manifest");
                    fragment.postCallback((Exception) new BraintreeException("BraintreeBrowserSwitchActivity missing, incorrectly configured in AndroidManifest.xml or another app defines the same browser switch url as this app. See https://developers.braintreepayments.com/guides/client-sdk/android/v2#browser-switch for the correct configuration"));
                } else {
                    try {
                        PayPal.persistPayPalRequest(fragment.getApplicationContext(), paypalRequest);
                        PayPal.createPaymentResource(fragment, paypalRequest, isBillingAgreement, callback);
                    } catch (BraintreeException | ErrorWithResponse | JSONException ex) {
                        fragment.postCallback(ex);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void createPaymentResource(BraintreeFragment fragment, PayPalRequest request, boolean isBillingAgreement, HttpResponseCallback callback) throws JSONException, ErrorWithResponse, BraintreeException {
        String currencyCode = request.getCurrencyCode();
        if (currencyCode == null) {
            currencyCode = fragment.getConfiguration().getPayPal().getCurrencyIsoCode();
        }
        CheckoutRequest checkoutRequest = getCheckoutRequest(fragment, null);
        JSONObject parameters = new JSONObject().put("return_url", checkoutRequest.getSuccessUrl()).put("cancel_url", checkoutRequest.getCancelUrl());
        if (fragment.getAuthorization() instanceof ClientToken) {
            parameters.put("authorization_fingerprint", ((ClientToken) fragment.getAuthorization()).getAuthorizationFingerprint());
        } else {
            parameters.put("client_key", fragment.getAuthorization().toString());
        }
        if (!isBillingAgreement) {
            parameters.put(GiftCardsActivity.EVENT_DATA_PARAM_GIFT_AMOUNT, request.getAmount()).put("currency_iso_code", currencyCode).put("intent", request.getIntent()).put("offer_paypal_credit", request.shouldOfferCredit());
        } else if (!TextUtils.isEmpty(request.getBillingAgreementDescription())) {
            parameters.put("description", request.getBillingAgreementDescription());
        }
        JSONObject experienceProfile = new JSONObject();
        experienceProfile.put("no_shipping", !request.isShippingAddressRequired());
        experienceProfile.put("landing_page_type", request.getLandingPageType());
        String displayName = request.getDisplayName();
        if (TextUtils.isEmpty(displayName)) {
            displayName = fragment.getConfiguration().getPayPal().getDisplayName();
        }
        experienceProfile.put("brand_name", displayName);
        if (request.getLocaleCode() != null) {
            experienceProfile.put("locale_code", request.getLocaleCode());
        }
        if (request.getShippingAddressOverride() != null) {
            experienceProfile.put("address_override", true);
            PostalAddress shippingAddress = request.getShippingAddressOverride();
            parameters.put(PostalAddress.LINE_1_KEY, shippingAddress.getStreetAddress());
            parameters.put(PostalAddress.LINE_2_KEY, shippingAddress.getExtendedAddress());
            parameters.put("city", shippingAddress.getLocality());
            parameters.put("state", shippingAddress.getRegion());
            parameters.put(PostalAddress.POSTAL_CODE_UNDERSCORE_KEY, shippingAddress.getPostalCode());
            parameters.put("country_code", shippingAddress.getCountryCodeAlpha2());
            parameters.put(PostalAddress.RECIPIENT_NAME_UNDERSCORE_KEY, shippingAddress.getRecipientName());
        } else {
            experienceProfile.put("address_override", false);
        }
        parameters.put("experience_profile", experienceProfile);
        fragment.getHttpClient().post("/v1/" + (isBillingAgreement ? "paypal_hermes/setup_billing_agreement" : "paypal_hermes/create_payment_resource"), parameters.toString(), callback);
    }

    /* access modifiers changed from: private */
    public static void startPayPal(final BraintreeFragment fragment, Request request, PayPalApprovalHandler handler) {
        persistRequest(fragment.getApplicationContext(), request);
        PayPalApprovalCallback callback = null;
        if (handler == null) {
            handler = getDefaultApprovalHandler(fragment);
        } else {
            callback = new PayPalApprovalCallback() {
            };
        }
        handler.handleApproval(request, callback);
    }

    private static PayPalApprovalHandler getDefaultApprovalHandler(final BraintreeFragment fragment) {
        return new PayPalApprovalHandler() {
            public void handleApproval(Request request, PayPalApprovalCallback paypalApprovalCallback) {
                PendingRequest pendingRequest = PayPalOneTouchCore.getStartIntent(fragment.getApplicationContext(), request);
                if (pendingRequest.isSuccess() && pendingRequest.getRequestTarget() == RequestTarget.wallet) {
                    PayPal.sendAnalyticsForPayPal(fragment, request, true, RequestTarget.wallet);
                    fragment.startActivityForResult(pendingRequest.getIntent(), 13591);
                } else if (!pendingRequest.isSuccess() || pendingRequest.getRequestTarget() != RequestTarget.browser) {
                    PayPal.sendAnalyticsForPayPal(fragment, request, false, null);
                } else {
                    PayPal.sendAnalyticsForPayPal(fragment, request, true, RequestTarget.browser);
                    fragment.browserSwitch(13591, pendingRequest.getIntent());
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public static void sendAnalyticsForPayPal(BraintreeFragment fragment, Request request, boolean success, RequestTarget target) {
        String eventFragment = "";
        if (request instanceof CheckoutRequest) {
            if (!success) {
                eventFragment = "paypal-single-payment.initiate.failed";
            } else if (target == RequestTarget.browser) {
                eventFragment = "paypal-single-payment.webswitch.initiate.started";
            } else if (target == RequestTarget.wallet) {
                eventFragment = "paypal-single-payment.appswitch.initiate.started";
            }
        } else if (!success) {
            eventFragment = "paypal-future-payments.initiate.failed";
        } else if (target == RequestTarget.browser) {
            eventFragment = "paypal-future-payments.webswitch.initiate.started";
        } else if (target == RequestTarget.wallet) {
            eventFragment = "paypal-future-payments.appswitch.initiate.started";
        }
        fragment.sendAnalyticsEvent(eventFragment);
    }

    protected static void onActivityResult(BraintreeFragment fragment, int resultCode, Intent data) {
        String type;
        Request request = getPersistedRequest(fragment.getApplicationContext());
        if (resultCode != -1 || data == null || request == null) {
            if (request != null) {
                type = request.getClass().getSimpleName().toLowerCase();
            } else {
                type = "unknown";
            }
            fragment.sendAnalyticsEvent("paypal." + type + ".canceled");
            if (resultCode != 0) {
                fragment.postCancelCallback(13591);
                return;
            }
            return;
        }
        boolean isAppSwitch = isAppSwitch(data);
        Result result = PayPalOneTouchCore.parseResponse(fragment.getApplicationContext(), request, data);
        switch (result.getResultType()) {
            case Error:
                fragment.postCallback((Exception) new BrowserSwitchException(result.getError().getMessage()));
                sendAnalyticsEventForSwitchResult(fragment, request, isAppSwitch, "failed");
                return;
            case Cancel:
                sendAnalyticsEventForSwitchResult(fragment, request, isAppSwitch, "canceled");
                fragment.postCancelCallback(13591);
                return;
            case Success:
                onSuccess(fragment, data, request, result);
                sendAnalyticsEventForSwitchResult(fragment, request, isAppSwitch, AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED);
                return;
            default:
                return;
        }
    }

    private static void onSuccess(final BraintreeFragment fragment, Intent data, Request request, Result result) {
        TokenizationClient.tokenize(fragment, parseResponse(getPersistedPayPalRequest(fragment.getApplicationContext()), request, result, data), new PaymentMethodNonceCallback() {
            public void success(PaymentMethodNonce paymentMethodNonce) {
                if ((paymentMethodNonce instanceof PayPalAccountNonce) && ((PayPalAccountNonce) paymentMethodNonce).getCreditFinancing() != null) {
                    fragment.sendAnalyticsEvent("paypal-single-payment.credit.accepted");
                }
                fragment.postCallback(paymentMethodNonce);
            }

            public void failure(Exception exception) {
                fragment.postCallback(exception);
            }
        });
    }

    private static PayPalAccountBuilder parseResponse(PayPalRequest paypalRequest, Request request, Result result, Intent intent) {
        PayPalAccountBuilder paypalAccountBuilder = new PayPalAccountBuilder().clientMetadataId(request.getClientMetadataId());
        if ((request instanceof CheckoutRequest) && paypalRequest != null) {
            paypalAccountBuilder.intent(paypalRequest.getIntent());
        }
        if (isAppSwitch(intent)) {
            paypalAccountBuilder.source("paypal-app");
        } else {
            paypalAccountBuilder.source("paypal-browser");
        }
        JSONObject payload = result.getResponse();
        try {
            JSONObject clientJson = payload.getJSONObject("client");
            JSONObject response = payload.getJSONObject("response");
            if ("mock".equalsIgnoreCase(clientJson.getString("client")) && response.getString("code") != null && !(request instanceof CheckoutRequest)) {
                payload.put("response", new JSONObject().put("code", "fake-code:" + ((AuthorizationRequest) request).getScopeString()));
            }
        } catch (JSONException e) {
        }
        paypalAccountBuilder.oneTouchCoreData(payload);
        return paypalAccountBuilder;
    }

    private static void sendAnalyticsEventForSwitchResult(BraintreeFragment fragment, Request request, boolean isAppSwitch, String eventFragment) {
        fragment.sendAnalyticsEvent(String.format("%s.%s.%s", new Object[]{request instanceof CheckoutRequest ? "paypal-single-payment" : "paypal-future-payments", isAppSwitch ? "appswitch" : "webswitch", eventFragment}));
    }

    static CheckoutRequest getCheckoutRequest(BraintreeFragment fragment, String redirectUrl) {
        CheckoutRequest request = ((CheckoutRequest) populateRequestData(fragment, new CheckoutRequest())).approvalURL(redirectUrl);
        if (redirectUrl != null) {
            request.pairingId(fragment.getApplicationContext(), Uri.parse(redirectUrl).getQueryParameter("token"));
        }
        return request;
    }

    static BillingAgreementRequest getBillingAgreementRequest(BraintreeFragment fragment, String redirectUrl) {
        BillingAgreementRequest request = ((BillingAgreementRequest) populateRequestData(fragment, new BillingAgreementRequest())).approvalURL(redirectUrl);
        if (redirectUrl != null) {
            request.pairingId(fragment.getApplicationContext(), Uri.parse(redirectUrl).getQueryParameter("ba_token"));
        }
        return request;
    }

    static AuthorizationRequest getAuthorizationRequest(BraintreeFragment fragment) {
        return ((AuthorizationRequest) populateRequestData(fragment, new AuthorizationRequest(fragment.getApplicationContext()))).privacyUrl(fragment.getConfiguration().getPayPal().getPrivacyUrl()).userAgreementUrl(fragment.getConfiguration().getPayPal().getUserAgreementUrl()).withScopeValue(SCOPE_FUTURE_PAYMENTS).withScopeValue(SCOPE_EMAIL).withAdditionalPayloadAttribute("client_token", fragment.getAuthorization().toString());
    }

    private static <T extends Request> T populateRequestData(BraintreeFragment fragment, T request) {
        String environment;
        PayPalConfiguration paypalConfiguration = fragment.getConfiguration().getPayPal();
        String environment2 = paypalConfiguration.getEnvironment();
        char c = 65535;
        switch (environment2.hashCode()) {
            case -1548612125:
                if (environment2.equals("offline")) {
                    c = 1;
                    break;
                }
                break;
            case 3322092:
                if (environment2.equals("live")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                environment = "live";
                break;
            case 1:
                environment = "mock";
                break;
            default:
                environment = paypalConfiguration.getEnvironment();
                break;
        }
        String clientId = paypalConfiguration.getClientId();
        if (clientId == null && "mock".equals(environment)) {
            clientId = "FAKE-PAYPAL-CLIENT-ID";
        }
        request.environment(environment).clientId(clientId).cancelUrl(fragment.getReturnUrlScheme(), BaseAnalytics.CANCEL).successUrl(fragment.getReturnUrlScheme(), "success");
        return request;
    }

    private static boolean isAppSwitch(Intent data) {
        return data.getData() == null;
    }

    /* access modifiers changed from: private */
    public static void persistPayPalRequest(Context context, PayPalRequest paypalRequest) {
        Parcel parcel = Parcel.obtain();
        paypalRequest.writeToParcel(parcel, 0);
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY", Base64.encodeToString(parcel.marshall(), 0)).apply();
    }

    private static void persistRequest(Context context, Request request) {
        Parcel parcel = Parcel.obtain();
        request.writeToParcel(parcel, 0);
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString("com.braintreepayments.api.PayPal.REQUEST_KEY", Base64.encodeToString(parcel.marshall(), 0)).putString("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY", request.getClass().getSimpleName()).apply();
    }

    /* JADX INFO: finally extract failed */
    private static PayPalRequest getPersistedPayPalRequest(Context context) {
        SharedPreferences prefs = BraintreeSharedPreferences.getSharedPreferences(context);
        try {
            byte[] requestBytes = Base64.decode(prefs.getString("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY", ""), 0);
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(requestBytes, 0, requestBytes.length);
            parcel.setDataPosition(0);
            PayPalRequest payPalRequest = (PayPalRequest) PayPalRequest.CREATOR.createFromParcel(parcel);
            prefs.edit().remove("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY").apply();
            return payPalRequest;
        } catch (Exception e) {
            prefs.edit().remove("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY").apply();
            return null;
        } catch (Throwable th) {
            prefs.edit().remove("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY").apply();
            throw th;
        }
    }

    private static Request getPersistedRequest(Context context) {
        SharedPreferences prefs = BraintreeSharedPreferences.getSharedPreferences(context);
        try {
            byte[] requestBytes = Base64.decode(prefs.getString("com.braintreepayments.api.PayPal.REQUEST_KEY", ""), 0);
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(requestBytes, 0, requestBytes.length);
            parcel.setDataPosition(0);
            String type = prefs.getString("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY", "");
            if (AuthorizationRequest.class.getSimpleName().equals(type)) {
                return (Request) AuthorizationRequest.CREATOR.createFromParcel(parcel);
            }
            if (BillingAgreementRequest.class.getSimpleName().equals(type)) {
                Request request = (Request) BillingAgreementRequest.CREATOR.createFromParcel(parcel);
                prefs.edit().remove("com.braintreepayments.api.PayPal.REQUEST_KEY").remove("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY").apply();
                return request;
            } else if (CheckoutRequest.class.getSimpleName().equals(type)) {
                Request request2 = (Request) CheckoutRequest.CREATOR.createFromParcel(parcel);
                prefs.edit().remove("com.braintreepayments.api.PayPal.REQUEST_KEY").remove("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY").apply();
                return request2;
            } else {
                prefs.edit().remove("com.braintreepayments.api.PayPal.REQUEST_KEY").remove("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY").apply();
                return null;
            }
        } catch (Exception e) {
        } finally {
            prefs.edit().remove("com.braintreepayments.api.PayPal.REQUEST_KEY").remove("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY").apply();
        }
    }

    /* access modifiers changed from: private */
    public static boolean isManifestValid(BraintreeFragment fragment) {
        Intent intent = new Intent("android.intent.action.VIEW").setData(Uri.parse(fragment.getReturnUrlScheme() + "://")).addCategory(PushActivity.CATEGORY_1).addCategory("android.intent.category.BROWSABLE");
        ActivityInfo activityInfo = ManifestValidator.getActivityInfo(fragment.getApplicationContext(), BraintreeBrowserSwitchActivity.class);
        return activityInfo != null && activityInfo.launchMode == 2 && AppHelper.isIntentAvailable(fragment.getApplicationContext(), intent);
    }
}
