package com.airbnb.android.cohosting.enums;

import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.intents.CohostingIntents.CohostReasonType;

public enum RemoveCohostReasonSelectionType implements CohostReasonSelectionType {
    Temporary(C5658R.string.cohosting_remove_reason_temporary, 1, false),
    FeeTooHigh(C5658R.string.cohosting_remove_reason_fee_too_high, 2, false),
    Expectations(C5658R.string.cohosting_remove_reason_did_not_meet_expectations, 3, false),
    Unwanted(C5658R.string.cohosting_remove_reason_no_longer_needed, 4, false),
    NoLongerAble(C5658R.string.cohosting_remove_reason_no_longer_able, 5, false),
    Other(C5658R.string.cohosting_remove_reason_other, 0, true);
    
    int reasonKey;
    int stringRes;
    boolean withPrivateFeedback;

    private RemoveCohostReasonSelectionType(int stringRes2, int reasonKey2, boolean withPrivateFeedback2) {
        this.stringRes = stringRes2;
        this.reasonKey = reasonKey2;
        this.withPrivateFeedback = withPrivateFeedback2;
    }

    public int getSelectionScreenReasonText() {
        return this.stringRes;
    }

    public boolean withPrivateFeedback() {
        return this.withPrivateFeedback;
    }

    public int getPrivateFeedbackScreenPlaceholderStringRes() {
        return C5658R.string.cohosting_remove_private_feedback_placeholder;
    }

    public int getMessagePlaceholderStringRes() {
        return C5658R.string.cohosting_remove_message_placeholder;
    }

    public int getReasonKey() {
        return this.reasonKey;
    }

    public String getSourceType() {
        return "Hosting";
    }

    public String getAction() {
        return "Remove";
    }

    public CohostReasonType getCohostReasonType() {
        return CohostReasonType.RemoveCohost;
    }
}
