package com.airbnb.android.cohosting.activities;

import android.os.Bundle;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.enums.RemoveCohostReasonSelectionType;
import com.airbnb.android.cohosting.enums.RemoveSelfReasonSelectionType;
import com.airbnb.android.cohosting.fragments.CohostReasonMessageTextInputFragment;
import com.airbnb.android.cohosting.fragments.CohostReasonMessageTextInputFragment.Listener;
import com.airbnb.android.cohosting.fragments.CohostReasonPrivateFeedbackTextInputFragment;
import com.airbnb.android.cohosting.fragments.CohostReasonSelectionFragment;
import com.airbnb.android.cohosting.shared.CohostReasonSelectionType;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingReusableFlowJitneyLogger;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.intents.CohostingIntents.CohostReasonType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;

public class CohostReasonSelectionActivity extends CohostingBaseActivity implements Listener, CohostReasonPrivateFeedbackTextInputFragment.Listener, CohostReasonSelectionFragment.Listener {
    @State
    CohostReasonSelectionType cohostReasonSelectionType;
    @State
    CohostReasonType cohostReasonType;
    @State
    Listing listing;
    CohostingReusableFlowJitneyLogger logger;
    @State
    ListingManager manager;
    @State
    String privateFeedback;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        this.manager = (ListingManager) getIntent().getParcelableExtra(CohostingIntents.INTENT_EXTRA_LISTING_MANAGER);
        this.cohostReasonType = (CohostReasonType) getIntent().getSerializableExtra(CohostingIntents.INTENT_EXTRA_REASON_TYPE);
        this.listing = (Listing) getIntent().getParcelableExtra("listing");
        super.onCreate(savedInstanceState);
        setContentView(C5658R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            showFragment(CohostReasonSelectionFragment.newInstance(this.manager, getCohostReasonSelectionTypes(), this.listing.getId()));
        }
    }

    private ArrayList getCohostReasonSelectionTypes() {
        switch (this.cohostReasonType) {
            case RemoveCohost:
                return new ArrayList(Arrays.asList(RemoveCohostReasonSelectionType.values()));
            case RemoveSelf:
                return new ArrayList(Arrays.asList(RemoveSelfReasonSelectionType.values()));
            default:
                throw new IllegalStateException("not a valid reason type");
        }
    }

    private void goToPrivateFeedbackInput() {
        showFragment(CohostReasonPrivateFeedbackTextInputFragment.newInstanceForPrivateFeedback(this.cohostReasonSelectionType, this.manager, this.listing.getId()));
    }

    private void goToMessageInput() {
        this.logger.logNextClick(this.manager.getUser().getId(), Long.valueOf(this.listing.getId()), this.cohostReasonSelectionType.getSourceType(), this.cohostReasonSelectionType.getAction(), Long.valueOf((long) this.cohostReasonSelectionType.getReasonKey()));
        showFragment(CohostReasonMessageTextInputFragment.newInstanceForMessage(this.cohostReasonSelectionType, this.manager, this.listing.getId(), this.privateFeedback));
    }

    public void onCohostReasonSelection(CohostReasonSelectionType type) {
        this.cohostReasonSelectionType = type;
        if (type.withPrivateFeedback()) {
            goToPrivateFeedbackInput();
        } else {
            goToMessageInput();
        }
    }

    public void onSubmitPrivateFeedbackInput(String privateFeedback2) {
        this.privateFeedback = privateFeedback2;
        goToMessageInput();
    }

    public void onSubmitReasons() {
        setResult(-1);
        finish();
    }
}
