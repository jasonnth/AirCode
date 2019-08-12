package com.airbnb.android.cohosting.shared;

import com.airbnb.android.core.intents.CohostingIntents.CohostReasonType;
import java.io.Serializable;

public interface CohostReasonSelectionType extends Serializable {
    String getAction();

    CohostReasonType getCohostReasonType();

    int getMessagePlaceholderStringRes();

    int getPrivateFeedbackScreenPlaceholderStringRes();

    int getReasonKey();

    int getSelectionScreenReasonText();

    String getSourceType();

    boolean withPrivateFeedback();
}
