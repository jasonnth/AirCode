package com.airbnb.android.lib.payments.dagger;

import android.content.Context;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.identity.IdentityClient;
import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter.BrazilPaymentInputFormatter;
import com.airbnb.android.lib.payments.creditcard.validation.CreditCardValidator;
import com.airbnb.android.lib.payments.digitalriver.DigitalRiverApi;
import com.airbnb.android.lib.payments.digitalriver.DigitalRiverTokenizer;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestFactory;
import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapterFactory;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayAdapterFactory;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayRowFactory;
import com.airbnb.android.lib.payments.quickpay.paymentredirect.PaymentRedirectCoordinator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

public class QuickPayModule {
    static final String DIGITAL_RIVER_JS_FILE = "digital_river_encryption.js";

    /* access modifiers changed from: 0000 */
    public BillPriceQuoteRequestFactory provideQuickPayRequestsFactory(AirbnbAccountManager accountManager) {
        return new BillPriceQuoteRequestFactory(accountManager);
    }

    /* access modifiers changed from: 0000 */
    public QuickPayRowFactory provideQuickPayRowFactory(Context context) {
        return new QuickPayRowFactory(context);
    }

    /* access modifiers changed from: 0000 */
    public QuickPayAdapterFactory provideQuickPayAdapterFactory(Context context, QuickPayRowFactory quickPayRowFactory) {
        return new QuickPayAdapterFactory(context, quickPayRowFactory);
    }

    /* access modifiers changed from: 0000 */
    public BraintreeFactory provideBraintreeFactory() {
        return new BraintreeFactory();
    }

    /* access modifiers changed from: 0000 */
    public CreditCardValidator provideCreditCardValidator() {
        return new CreditCardValidator();
    }

    /* access modifiers changed from: 0000 */
    public IdentityClient provideIdentityClient() {
        return new IdentityClient();
    }

    /* access modifiers changed from: 0000 */
    public PaymentOptionFactory providePaymentOptionFactory() {
        return new PaymentOptionFactory();
    }

    /* access modifiers changed from: 0000 */
    public PaymentOptionsAdapterFactory providePaymentOptionsAdapterFactory(Context context) {
        return new PaymentOptionsAdapterFactory(context);
    }

    /* access modifiers changed from: 0000 */
    public PaymentRedirectCoordinator providePaymentRedirectCoordinator() {
        return new PaymentRedirectCoordinator();
    }

    /* access modifiers changed from: 0000 */
    public BrazilPaymentInputFormatter provideBrazilPaymentInputFormatter(PhoneNumberUtil phoneNumberUtil) {
        return new BrazilPaymentInputFormatter(phoneNumberUtil);
    }

    /* access modifiers changed from: 0000 */
    public DigitalRiverApi provideDigitalRiverApi(Context context, ObjectMapper objectMapper) {
        return _provideDigitalRiverApi(context, objectMapper);
    }

    /* access modifiers changed from: protected */
    public DigitalRiverApi _provideDigitalRiverApi(Context context, ObjectMapper objectMapper) {
        return new DigitalRiverTokenizer(context, objectMapper, DIGITAL_RIVER_JS_FILE);
    }
}
