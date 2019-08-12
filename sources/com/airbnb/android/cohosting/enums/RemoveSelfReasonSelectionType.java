package com.airbnb.android.cohosting.enums;

import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.intents.CohostingIntents.CohostReasonType;

public enum RemoveSelfReasonSelectionType implements CohostReasonSelectionType {
    Temporary(C5658R.string.cohosting_remove_self_reason_temporary, 1, false),
    NotEarningEnough(C5658R.string.cohosting_remove_self_reason_not_earning_enough, 2, false),
    TooDifficult(C5658R.string.cohosting_remove_self_reason_too_difficult, 3, false),
    NoLongerNeeded(C5658R.string.cohosting_remove_self_reason_no_longer_needed, 4, false),
    DontHaveTime(C5658R.string.cohosting_remove_self_reason_dont_have_time, 5, false),
    TooComplicated(C5658R.string.cohosting_remove_self_reason_too_complicated, 6, false),
    Other(C5658R.string.cohosting_remove_reason_other, 0, true);
    
    int reasonKey;
    int stringRes;
    boolean withPrivateFeedback;

    private RemoveSelfReasonSelectionType(int stringRes2, int reasonKey2, boolean withPrivateFeedback2) {
        this.stringRes = stringRes2;
        this.reasonKey = reasonKey2;
        this.withPrivateFeedback = withPrivateFeedback2;
    }

    public int getSelectionScreenReasonText() {
        return this.stringRes;
    }

    public int getReasonKey() {
        return this.reasonKey;
    }

    public String getSourceType() {
        return "Hosting";
    }

    public String getAction() {
        return "Resign";
    }

    public boolean withPrivateFeedback() {
        return this.withPrivateFeedback;
    }

    public int getPrivateFeedbackScreenPlaceholderStringRes() {
        return C5658R.string.cohosting_remove_self_private_feedback_placeholder;
    }

    public int getMessagePlaceholderStringRes() {
        return C5658R.string.cohosting_remove_self_message_placeholder;
    }

    public CohostReasonType getCohostReasonType() {
        return CohostReasonType.RemoveSelf;
    }
}
