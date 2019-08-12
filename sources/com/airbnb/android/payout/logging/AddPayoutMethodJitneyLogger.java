package com.airbnb.android.payout.logging;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.payout.create.PayoutFormRuleType;
import com.airbnb.android.payout.models.PayoutInfoFormType;
import com.airbnb.jitney.event.logging.GibraltarInstrumentResponse.p103v1.C2144GibraltarInstrumentResponse;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodError.p191v1.C2546PayoutMethodError;
import com.airbnb.jitney.event.logging.PayoutMethodSelectAction.p192v1.C2547PayoutMethodSelectAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import com.airbnb.jitney.event.logging.PayoutMethodType.p194v1.C2549PayoutMethodType;
import com.airbnb.jitney.event.logging.Payouts.p195v1.PayoutsPayoutMethodGibraltarInstrumentCallEvent;
import com.airbnb.jitney.event.logging.Payouts.p195v1.PayoutsPayoutMethodSelectNativeEvent;
import com.airbnb.jitney.event.logging.Payouts.p195v1.PayoutsPayoutMethodSetupNativeEvent.Builder;
import com.airbnb.jitney.event.logging.Payouts.p197v3.PayoutsPayoutMethodErrorNativeEvent;

public class AddPayoutMethodJitneyLogger extends BaseLogger {
    public AddPayoutMethodJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void payoutMethodSetup(String currency, C2548PayoutMethodSetupPage payoutMethodSetupPage, PayoutInfoFormType payoutInfoFormType, C2545PayoutMethodAction payoutMethodAction) {
        publish(new Builder(context(), currency, payoutMethodSetupPage, payoutInfoFormTypeToMethodType(payoutInfoFormType), payoutMethodAction));
    }

    public void payoutMethodSelect(C2547PayoutMethodSelectAction payoutMethodSelectAction) {
        publish(new PayoutsPayoutMethodSelectNativeEvent.Builder(context(), payoutMethodSelectAction));
    }

    public void payoutMethodGibraltarInstrumentCall(String currency, PayoutInfoFormType payoutInfoFormType, C2144GibraltarInstrumentResponse gibraltarInstrumentResponse) {
        publish(new PayoutsPayoutMethodGibraltarInstrumentCallEvent.Builder(context(), currency, payoutInfoFormTypeToMethodType(payoutInfoFormType), gibraltarInstrumentResponse));
    }

    public void payoutMethodError(PayoutFormRuleType payoutFormRuleType, String payoutMethodSetupSection, PayoutInfoFormType payoutInfoFormType, String billingCoutnry, String currency) {
        publish(new PayoutsPayoutMethodErrorNativeEvent.Builder(context(), payoutFormRuleTypeToPayoutMethodError(payoutFormRuleType), payoutMethodSetupSection, payoutInfoFormTypeToMethodType(payoutInfoFormType), billingCoutnry, currency));
    }

    private C2549PayoutMethodType payoutInfoFormTypeToMethodType(PayoutInfoFormType payoutInfoFormType) {
        switch (payoutInfoFormType) {
            case BankAccount:
                return C2549PayoutMethodType.BankDeposit;
            case InternationalWire:
                return C2549PayoutMethodType.InternationalWire;
            case VaCuba:
                return C2549PayoutMethodType.Vacuba;
            case PayPal:
                return C2549PayoutMethodType.PayPal;
            case PayoneerPrepaid:
                return C2549PayoutMethodType.PayoneerDebit;
            case WesternUnion:
                return C2549PayoutMethodType.WesternUnion;
            default:
                return C2549PayoutMethodType.BankDeposit;
        }
    }

    private C2546PayoutMethodError payoutFormRuleTypeToPayoutMethodError(PayoutFormRuleType type) {
        switch (type) {
            case REGEX:
                return C2546PayoutMethodError.InvalidField;
            case REQUIRE_CONFIRMATION:
                return C2546PayoutMethodError.ConfirmationNotMatch;
            case MAX_LENGTH:
            case MIN_LENGTH:
                return C2546PayoutMethodError.LengthNotMatch;
            case REQUIRED:
                return C2546PayoutMethodError.RequiredField;
            default:
                return C2546PayoutMethodError.InvalidField;
        }
    }
}
