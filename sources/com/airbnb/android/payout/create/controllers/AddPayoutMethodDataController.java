package com.airbnb.android.payout.create.controllers;

import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.payout.PayoutFormManager;
import com.airbnb.android.payout.PayoutGraph;
import com.airbnb.android.payout.create.interfaces.AddPayoutMethodDataChangedListener;
import com.airbnb.android.payout.create.interfaces.AddPayoutMethodDataFacade;
import com.airbnb.android.payout.logging.AddPayoutMethodJitneyLogger;
import com.airbnb.android.payout.models.BankDepositAccountType;
import com.airbnb.android.payout.models.PayoutInfoForm;
import com.airbnb.android.payout.requests.CreatePayoutMethodRequest;
import com.airbnb.android.payout.requests.PayoutInfoFormRequest;
import com.airbnb.android.payout.requests.PayoutInfoFormResponse;
import com.airbnb.android.payout.requests.UserAddressRequest;
import com.airbnb.android.payout.requests.UserAddressResponse;
import com.airbnb.jitney.event.logging.GibraltarInstrumentResponse.p103v1.C2144GibraltarInstrumentResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import p032rx.Observer;

public class AddPayoutMethodDataController implements AddPayoutMethodDataFacade {
    private final AirbnbAccountManager accountManager;
    AddPayoutMethodJitneyLogger addPayoutMethodJitneyLogger;
    @State
    BankDepositAccountType bankAccountType;
    final RequestListener<PaymentInstrumentResponse> createPayoutMethodListener = new C0699RL().onResponse(AddPayoutMethodDataController$$Lambda$7.lambdaFactory$(this)).onError(AddPayoutMethodDataController$$Lambda$8.lambdaFactory$(this)).build();
    @State
    PaymentInstrument createdPaymentInstrument;
    private Set<AddPayoutMethodDataChangedListener> dataChangedListeners = Sets.newHashSet();
    final RequestListener<PayoutInfoFormResponse> fetchPayoutFormListener = new C0699RL().onError(AddPayoutMethodDataController$$Lambda$1.lambdaFactory$(this)).onResponse(AddPayoutMethodDataController$$Lambda$2.lambdaFactory$(this)).build();
    final RequestListener<PaymentInstrumentResponse> fetchRedirectUrlListener = new C0699RL().onResponse(AddPayoutMethodDataController$$Lambda$5.lambdaFactory$(this)).onError(AddPayoutMethodDataController$$Lambda$6.lambdaFactory$(this)).build();
    final RequestListener<UserAddressResponse> fetchUserAddressListener = new C0699RL().onResponse(AddPayoutMethodDataController$$Lambda$3.lambdaFactory$(this)).onError(AddPayoutMethodDataController$$Lambda$4.lambdaFactory$(this)).build();
    @State
    AirAddress payoutAddress;
    @State
    String payoutCountryCode;
    @State
    String payoutCurrency;
    private PayoutFormManager payoutFormManager;
    @State
    ArrayList<PayoutInfoForm> payoutInfoForms;
    private final RequestManager requestManager;
    @State
    PayoutInfoForm selectedPayoutInfoForm;
    @State
    ArrayList<AirAddress> userAddresses;

    public AddPayoutMethodDataController(RequestManager requestManager2, AirbnbAccountManager accountManager2, Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
        ((PayoutGraph) CoreApplication.instance().component()).inject(this);
        this.requestManager = requestManager2;
        this.accountManager = accountManager2;
        requestManager2.subscribe(this);
        if (this.selectedPayoutInfoForm != null) {
            initPayoutFormManagerIfNecessary(this.selectedPayoutInfoForm, savedState);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void fetchPayoutInfoForms(String countryCode) {
        if (this.payoutCountryCode != countryCode) {
            setPayoutCountryCode(countryCode);
            PayoutInfoFormRequest.withCountryCode(this.payoutCountryCode, this.accountManager.getCurrentUserId()).withListener((Observer) this.fetchPayoutFormListener).execute(this.requestManager);
        }
    }

    public void fetchUserAddress() {
        UserAddressRequest.forPayoutCountry(this.accountManager.getCurrentUserId(), this.payoutCountryCode).withListener((Observer) this.fetchUserAddressListener).execute(this.requestManager);
    }

    public void fetchRedirectUrl() {
        if (this.selectedPayoutInfoForm.isURLRedirect()) {
            switch (this.selectedPayoutInfoForm.payoutMethodType()) {
                case PayoneerPrepaid:
                    CreatePayoutMethodRequest.forPayoneerAPIRedirect(this.payoutCurrency, this.selectedPayoutInfoForm.payoutMethodType(), this.payoutAddress).withListener((Observer) this.fetchRedirectUrlListener).execute(this.requestManager);
                    return;
                default:
                    return;
            }
        }
    }

    public void addDataChangedListener(AddPayoutMethodDataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }

    public void removeDataChangedListener(AddPayoutMethodDataChangedListener listener) {
        this.dataChangedListeners.remove(listener);
    }

    public boolean hasAvailablePayoutMethods() {
        return this.payoutInfoForms != null && !this.payoutInfoForms.isEmpty();
    }

    public List<PayoutInfoForm> getPayoutInfoForms() {
        return this.payoutInfoForms;
    }

    public AirAddress getSelectedPayoutAddress() {
        return this.payoutAddress;
    }

    public void setSelectedPayoutInfoForm(PayoutInfoForm selectedPayoutInfoForm2) {
        initPayoutFormManagerIfNecessary(selectedPayoutInfoForm2, null);
        this.selectedPayoutInfoForm = selectedPayoutInfoForm2;
    }

    public PayoutInfoForm getSelectedPayoutInfoForm() {
        return this.selectedPayoutInfoForm;
    }

    public void setBankAccountType(BankDepositAccountType bankAccountType2) {
        Check.state(this.selectedPayoutInfoForm.isBankDeposit());
        this.bankAccountType = bankAccountType2;
    }

    public void setPayoutAddress(AirAddress payoutAddress2) {
        this.payoutAddress = payoutAddress2;
    }

    public BankDepositAccountType getBankAccountType() {
        return this.bankAccountType;
    }

    public boolean hasBankAccountType() {
        return this.bankAccountType != null;
    }

    public String getPayoutCountryCode() {
        return this.payoutCountryCode;
    }

    private void setPayoutCountryCode(String payoutCountryCode2) {
        this.payoutCountryCode = payoutCountryCode2;
    }

    public PayoutFormManager getPayoutFormManager() {
        return this.payoutFormManager;
    }

    private void initPayoutFormManagerIfNecessary(PayoutInfoForm payoutInfoFormType, Bundle savedState) {
        if (!payoutInfoFormType.isURLRedirect()) {
            this.payoutFormManager = new PayoutFormManager(payoutInfoFormType.payoutFormFields(), savedState);
        }
    }

    /* access modifiers changed from: private */
    public void onFetchPayoutInfoFormSuccess(List<PayoutInfoForm> payoutInfoForms2) {
        this.payoutInfoForms = Lists.newArrayList((Iterable<? extends E>) payoutInfoForms2);
        for (AddPayoutMethodDataChangedListener listener : this.dataChangedListeners) {
            listener.onFetchedAvailablePayoutMethods(payoutInfoForms2);
        }
    }

    /* access modifiers changed from: private */
    public void onFetchPayoutInfoFormError(AirRequestNetworkException error) {
        for (AddPayoutMethodDataChangedListener listener : this.dataChangedListeners) {
            listener.onFetchAvailablePayoutMethodError(error);
        }
    }

    /* access modifiers changed from: private */
    public void onFetchRedirectUrlError(AirRequestNetworkException error) {
        logCreatePayoutMethod(false);
        for (AddPayoutMethodDataChangedListener listener : this.dataChangedListeners) {
            listener.onFetchRedirectUrlError(error);
        }
    }

    /* access modifiers changed from: private */
    public void onCreatePayoutMethodError(AirRequestNetworkException error) {
        logCreatePayoutMethod(false);
        for (AddPayoutMethodDataChangedListener listener : this.dataChangedListeners) {
            listener.onCreatePayoutMethodError(error);
        }
    }

    /* access modifiers changed from: private */
    public void onFetchUserAddressSuccess(List<AirAddress> addresses) {
        this.userAddresses = Lists.newArrayList((Iterable<? extends E>) addresses);
        for (AddPayoutMethodDataChangedListener listener : this.dataChangedListeners) {
            listener.onFetchUserAddressSuccess(this.userAddresses);
        }
    }

    /* access modifiers changed from: private */
    public void onFetchUserAddressError(AirRequestNetworkException error) {
        for (AddPayoutMethodDataChangedListener listener : this.dataChangedListeners) {
            listener.onFetchUserAddressError(error);
        }
    }

    /* access modifiers changed from: private */
    public void onCreatePayoutMethodSuccess(PaymentInstrument payoutInstrument) {
        logCreatePayoutMethod(true);
        this.createdPaymentInstrument = payoutInstrument;
        for (AddPayoutMethodDataChangedListener listener : this.dataChangedListeners) {
            listener.onCreatePayoutMethodSuccess();
        }
    }

    /* access modifiers changed from: private */
    public void onFetchRedirectUrlSuccess(PaymentInstrument payoutInstrument) {
        logCreatePayoutMethod(true);
        this.createdPaymentInstrument = payoutInstrument;
        for (AddPayoutMethodDataChangedListener listener : this.dataChangedListeners) {
            listener.onFetchRedirectUrlSuccess();
        }
    }

    public boolean hasUserAddresses() {
        return this.userAddresses != null;
    }

    private void logCreatePayoutMethod(boolean success) {
        if (success) {
            this.addPayoutMethodJitneyLogger.payoutMethodGibraltarInstrumentCall(this.payoutCurrency, this.selectedPayoutInfoForm.payoutMethodType(), C2144GibraltarInstrumentResponse.Success);
        } else {
            this.addPayoutMethodJitneyLogger.payoutMethodGibraltarInstrumentCall(this.payoutCurrency, this.selectedPayoutInfoForm.payoutMethodType(), C2144GibraltarInstrumentResponse.Error);
        }
    }

    public List<AirAddress> getUserAddresses() {
        return this.userAddresses;
    }

    public String getPayoutCurrency() {
        return this.payoutCurrency;
    }

    public void setPayoutCurrency(String payoutCurrency2) {
        this.payoutCurrency = payoutCurrency2;
    }

    public String getRedirectUrl() {
        if (!this.selectedPayoutInfoForm.isURLRedirect() || this.createdPaymentInstrument == null) {
            return null;
        }
        switch (this.selectedPayoutInfoForm.payoutMethodType()) {
            case PayoneerPrepaid:
                return this.createdPaymentInstrument.getPayoneerAccountRedirectUrl();
            default:
                return null;
        }
    }

    public void createPayoutMethod() {
        CreatePayoutMethodRequest.forCreatePayoutMethod(this.payoutCurrency, this.selectedPayoutInfoForm.payoutMethodType(), this.payoutAddress, this.payoutFormManager.getPayoutInput()).withListener((Observer) this.createPayoutMethodListener).execute(this.requestManager);
    }
}
