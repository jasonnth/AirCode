package com.airbnb.android.cohosting.activities;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterDataController;
import com.airbnb.android.cohosting.executors.CohostLeadsCenterActionExecutor;
import com.airbnb.android.cohosting.fragments.CohostLeadsCenterTabsFragment;
import com.airbnb.android.cohosting.fragments.CohostLeadsCenterWhatYouCanEarnFragment;

public class CohostLeadsCenterActivity extends CohostingBaseActivity {
    private final CohostLeadsCenterActionExecutor actionExecutor = new CohostLeadsCenterActionExecutor() {
        public void browseLeads() {
            CohostLeadsCenterActivity.this.showFragment(CohostLeadsCenterTabsFragment.create());
        }

        public void showNux() {
        }

        public void showWhatYouCanEarn(long inquiryId) {
            CohostLeadsCenterActivity.this.showFragment(CohostLeadsCenterWhatYouCanEarnFragment.create(inquiryId));
        }
    };
    protected CohostLeadsCenterDataController controller;

    public CohostLeadsCenterDataController getCohostLeadsCenterController() {
        return this.controller;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C5658R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        this.controller = new CohostLeadsCenterDataController(this, this.actionExecutor, savedInstanceState);
        this.actionExecutor.browseLeads();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.onSaveInstanceState(outState);
    }
}
