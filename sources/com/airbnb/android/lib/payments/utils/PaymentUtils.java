package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.erf.Experiments;
import com.google.common.collect.FluentIterable;
import java.util.EnumSet;
import java.util.List;

public class PaymentUtils {
    public static PaymentOption getDefaultPaymentOption(List<PaymentOption> paymentOptions) {
        PaymentOption defaultOption = (PaymentOption) FluentIterable.from((Iterable<E>) paymentOptions).filter(PaymentUtils$$Lambda$1.lambdaFactory$()).firstMatch(PaymentUtils$$Lambda$2.lambdaFactory$()).orNull();
        return defaultOption != null ? defaultOption : (PaymentOption) paymentOptions.get(0);
    }

    public static List<PaymentOption> getExistingPaymentOptions(List<PaymentOption> unfilteredPaymentOptions) {
        return FluentIterable.from((Iterable<E>) unfilteredPaymentOptions).filter(PaymentUtils$$Lambda$3.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$getExistingPaymentOptions$2(PaymentOption paymentOption) {
        return paymentOption.isExistingInstrument() || paymentOption.isBusinessTravelPaymentOption();
    }

    public static boolean isAddPaymentFlowEnabledFor(BillProductType productType, String countryCode) {
        boolean isBrazil = countryCode.equals(AirbnbConstants.COUNTRY_CODE_BRAZIL);
        boolean isBrazilFlowEnabled = FeatureToggles.isAddPaymentBrazilEnabled();
        boolean isIndia = countryCode.equals(AirbnbConstants.COUNTRY_CODE_INDIA);
        boolean isIndiaFlowEnabled = Experiments.showPayUFlow();
        if (isBrazil && !isBrazilFlowEnabled) {
            return false;
        }
        if (isIndia && (!isIndiaFlowEnabled || productType == BillProductType.PaidAmenity)) {
            return false;
        }
        if (productType != BillProductType.GiftCredit || countryCode.equals("US")) {
            return true;
        }
        return false;
    }

    public static boolean isValidPaymentOption(PaymentOption paymentOption) {
        return paymentOption != null && (paymentOption.isExistingInstrument() || paymentOption.isBusinessTravelPaymentOption());
    }

    public static PaymentOption getBusinessPaymentOption(List<PaymentOption> paymentOptions) {
        return (PaymentOption) FluentIterable.from((Iterable<E>) paymentOptions).firstMatch(PaymentUtils$$Lambda$4.lambdaFactory$()).orNull();
    }

    public static PaymentOption getPaymentOptionByPaymentMethodType(List<PaymentOption> paymentOptions, PaymentMethodType paymentMethodType) {
        return (PaymentOption) FluentIterable.from((Iterable<E>) paymentOptions).firstMatch(PaymentUtils$$Lambda$5.lambdaFactory$(paymentMethodType)).orNull();
    }

    public static List<PaymentMethodType> getPaymentMethodTypesFromPaymentOptions(List<PaymentOption> paymentOptions) {
        return getUniqueSkeletonPaymentMethods(paymentOptions);
    }

    public static boolean shouldShowNewAddPaymentMethodFlow() {
        return Experiments.launchNewAddPaymentMethodFlow();
    }

    private static List<PaymentMethodType> getUniqueSkeletonPaymentMethods(List<PaymentOption> paymentOptions) {
        return FluentIterable.from((Iterable<E>) paymentOptions).filter(PaymentUtils$$Lambda$6.lambdaFactory$()).transform(PaymentUtils$$Lambda$7.lambdaFactory$()).toSet().asList();
    }

    static /* synthetic */ boolean lambda$getUniqueSkeletonPaymentMethods$5(PaymentOption paymentOption) {
        return isSkeletonPaymentMethod(paymentOption) && isEligiblePaymentMethod(PaymentMethodType.findByServerKey(paymentOption.getPaymentMethodType()));
    }

    private static boolean isSkeletonPaymentMethod(PaymentOption input) {
        return !input.isExistingInstrument();
    }

    private static boolean isEligiblePaymentMethod(PaymentMethodType paymentMethodType) {
        return EnumSet.of(PaymentMethodType.Alipay, new PaymentMethodType[]{PaymentMethodType.CreditCard, PaymentMethodType.DigitalRiverCreditCard, PaymentMethodType.PayPal, PaymentMethodType.PayU, PaymentMethodType.AndroidPay}).contains(paymentMethodType);
    }
}
