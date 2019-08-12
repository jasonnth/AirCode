package com.airbnb.android.lib.payments.factories;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.GiftCreditParams;
import com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.airbnb.android.core.payments.models.clientparameters.ResyClientParameters;
import com.airbnb.android.lib.payments.networking.requests.PriceQuoteRequest;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.GiftCreditBillPriceQuoteRequestBody;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.HomesBillPriceQuoteRequestBody;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.HomesBillPriceQuoteRequestBody.Builder;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.PaidAmenityBillPriceQuoteRequestBody;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.ResyBillPriceQuoteRequestBody;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.PaymentParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ProductParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.ResyProduct;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesBusinessTravelProductParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesProductParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesRequestInfoParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.HomesReservationDetailsProductParam;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentifications;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.TripsBillPriceQuoteRequestBody;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.trips.TripsProductParam;
import com.airbnb.android.utils.AirbnbConstants;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.List;

public class BillPriceQuoteRequestFactory {
    private final AirbnbAccountManager accountManager;

    public BillPriceQuoteRequestFactory(AirbnbAccountManager accountManager2) {
        this.accountManager = accountManager2;
    }

    public PriceQuoteRequest createBillPriceQuoteRequest(QuickPayClientType clientType, PaymentOption paymentOption, QuickPayParameters quickPayParameters, boolean shouldIncludeAirbnbCredit, String displayCurrency) {
        String requestUserId = String.valueOf(this.accountManager.getCurrentUserId());
        String requestPaymentInstrumentId = String.valueOf(paymentOption.getLegacyInstrumentId());
        switch (clientType) {
            case PaidAmenity:
                PaidAmenityClientParameters paidAmenityClientParameters = (PaidAmenityClientParameters) quickPayParameters;
                return new PriceQuoteRequest(PaidAmenityBillPriceQuoteRequestBody.builder().userId(requestUserId).paymentInstrumentId(requestPaymentInstrumentId).displayCurrency(displayCurrency).isAirbnbCreditIncluded(shouldIncludeAirbnbCredit).paidAmenityId(paidAmenityClientParameters.paidAmenityId()).numberOfUnits(paidAmenityClientParameters.numberOfUnits()).reservationConfirmationCode(paidAmenityClientParameters.reservationConfirmationCode()).build());
            case GiftCard:
                GiftCardClientParameters giftCreditParameters = (GiftCardClientParameters) quickPayParameters;
                return new PriceQuoteRequest(GiftCreditBillPriceQuoteRequestBody.builder().userId(requestUserId).paymentInstrumentId(requestPaymentInstrumentId).displayCurrency(displayCurrency).nativeCurrency(displayCurrency).isAirbnbCreditIncluded(shouldIncludeAirbnbCredit).amountMicros(giftCreditParameters.getAmountMicros()).amountMicrosUsd(giftCreditParameters.getAmountMicros()).amountMicrosNative(giftCreditParameters.getAmountMicros()).payment2ProductType("Gift Credit").giftCreditParams(new GiftCreditParams(giftCreditParameters)).build());
            case ResyReservation:
                ResyClientParameters resyClientParameters = (ResyClientParameters) quickPayParameters;
                return new PriceQuoteRequest(ResyBillPriceQuoteRequestBody.builder().paymentParams(PaymentParam.builder().displayCurrency(displayCurrency).build()).products(Lists.newArrayList((E[]) new ProductParam[]{ResyProduct.builder().numberOfGuests(resyClientParameters.numberOfGuests()).resyReservationId(resyClientParameters.reservationId()).activityId(resyClientParameters.activityId()).build()})).build());
            default:
                throw new IllegalArgumentException("QuickPayClientType is invalid.");
        }
    }

    public PriceQuoteRequest createBillPriceQuoteRequestV2(BillPriceQuoteRequestParams requestParams) {
        Long l;
        String str = null;
        PaymentOption paymentOption = requestParams.paymentOption();
        PaymentMethodType paymentMethodType = PaymentMethodType.findByServerKey(paymentOption.getPaymentMethodType());
        switch (requestParams.clientType()) {
            case Homes:
                HomesClientParameters homesClientParameters = (HomesClientParameters) requestParams.quickPayParameters();
                Builder builder = HomesBillPriceQuoteRequestBody.builder();
                PaymentParam.Builder zipRetry = PaymentParam.builder().gibraltarInstrumentId(getGibraltarInstrumentId(requestParams)).method(paymentOption.getPaymentMethodType()).airbnbCredit(requestParams.includeAirbnbCredit()).userAgreedToCurrencyMismatch(Boolean.valueOf(requestParams.userAgreedToCurrencyMismatch())).displayCurrency(getDisplayCurrency(paymentMethodType, requestParams.displayCurrency())).country(getCountryFromPaymentMethodType(paymentMethodType)).zipRetry(requestParams.zipRetry());
                if (paymentOption.isBusinessTravelPaymentOption()) {
                    l = Long.valueOf(paymentOption.getBusinessEntityGroup().getId());
                } else {
                    l = null;
                }
                PaymentParam.Builder businessTravel = zipRetry.businessTravel(l);
                if (paymentMethodType == PaymentMethodType.DigitalRiverCreditCard) {
                    str = paymentOption.getCvvPayload();
                }
                return new PriceQuoteRequest(builder.paymentParams(businessTravel.instrumentParams(str).build()).products(Lists.newArrayList((E[]) new ProductParam[]{HomesProductParam.builder().code(homesClientParameters.reservationConfirmationCode()).couponCode(requestParams.couponCode()).requestInfo(HomesRequestInfoParam.builder().build()).businessTravel(HomesBusinessTravelProductParam.builder().businessTripNotes(homesClientParameters.businessTripNotes()).build()).reservationDetails(HomesReservationDetailsProductParam.builder().messageToHost(homesClientParameters.messageToHost()).build()).guestIdentifications(getHomesGuestIdentifications(homesClientParameters.guestIdentities())).build()})).build());
            case Trip:
                MagicalTripsClientParameters tripsClientParameters = (MagicalTripsClientParameters) requestParams.quickPayParameters();
                TripsBillPriceQuoteRequestBody.Builder builder2 = TripsBillPriceQuoteRequestBody.builder();
                PaymentParam.Builder zipRetry2 = PaymentParam.builder().gibraltarInstrumentId(getGibraltarInstrumentId(requestParams)).method(paymentOption.getPaymentMethodType()).airbnbCredit(requestParams.includeAirbnbCredit()).userAgreedToCurrencyMismatch(Boolean.valueOf(requestParams.userAgreedToCurrencyMismatch())).displayCurrency(getDisplayCurrency(paymentMethodType, requestParams.displayCurrency())).country(getCountryFromPaymentMethodType(paymentMethodType)).zipRetry(requestParams.zipRetry());
                if (paymentMethodType == PaymentMethodType.DigitalRiverCreditCard) {
                    str = paymentOption.getCvvPayload();
                }
                return new PriceQuoteRequest(builder2.paymentParams(zipRetry2.instrumentParams(str).build()).products(Lists.newArrayList((E[]) new ProductParam[]{TripsProductParam.builder().couponCode(requestParams.couponCode()).numberOfGuests(tripsClientParameters.guestCount()).secondaryGuestInfos(tripsClientParameters.secondaryGuests()).templateId(tripsClientParameters.templateId()).build()})).build());
            default:
                throw new IllegalArgumentException("BillProductType is invalid.");
        }
    }

    private Long getGibraltarInstrumentId(BillPriceQuoteRequestParams requestParams) {
        if (requestParams.paymentOption().getGibraltarInstrumentId() == 0) {
            return null;
        }
        return Long.valueOf(requestParams.paymentOption().getGibraltarInstrumentId());
    }

    private HomesGuestIdentifications getHomesGuestIdentifications(List<GuestIdentity> guestIdentities) {
        if (guestIdentities == null) {
            return null;
        }
        return HomesGuestIdentifications.builder().identifications(FluentIterable.from((Iterable<E>) guestIdentities).transform(BillPriceQuoteRequestFactory$$Lambda$1.lambdaFactory$()).toList()).build();
    }

    private String getCountryFromPaymentMethodType(PaymentMethodType paymentMethodType) {
        switch (paymentMethodType) {
            case iDEAL:
                return AirbnbConstants.COUNTRY_CODE_NETHERLANDS;
            case PayU:
                return AirbnbConstants.COUNTRY_CODE_INDIA;
            case Sofort:
                return AirbnbConstants.COUNTRY_CODE_GERMANY;
            default:
                return null;
        }
    }

    private String getDisplayCurrency(PaymentMethodType paymentMethodType, String defaultDisplayCurrency) {
        switch (paymentMethodType) {
            case iDEAL:
            case Sofort:
                return AirbnbConstants.CURRENCY_EURO;
            case PayU:
                return AirbnbConstants.CURRENCY_INDIA;
            default:
                return defaultDisplayCurrency;
        }
    }
}
