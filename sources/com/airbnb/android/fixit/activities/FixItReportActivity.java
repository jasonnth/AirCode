package com.airbnb.android.fixit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.FixItIntents;
import com.airbnb.android.core.models.FixItItem;
import com.airbnb.android.core.requests.FixItReportRequest;
import com.airbnb.android.core.responses.FixItReportResponse;
import com.airbnb.android.fixit.C6380R;
import com.airbnb.android.fixit.fragments.FixItDataController;
import com.airbnb.android.fixit.fragments.FixItItemCommentFragment;
import com.airbnb.android.fixit.fragments.FixItItemFragment;
import com.airbnb.android.fixit.fragments.FixItReportFragment;
import p032rx.Observer;

public class FixItReportActivity extends AirActivity {
    private FixItDataController dataController;
    final RequestListener<FixItReportResponse> fixItReportListener = new C0699RL().onResponse(FixItReportActivity$$Lambda$1.lambdaFactory$(this)).onError(FixItReportActivity$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    FrameLayout rootContainer;

    static /* synthetic */ void lambda$new$0(FixItReportActivity fixItReportActivity, FixItReportResponse response) {
        fixItReportActivity.dataController.setReport(response.getReport());
        fixItReportActivity.dataController.setLoading(false);
    }

    public void onCreate(Bundle savedInstanceState) {
        this.dataController = new FixItDataController(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(C6380R.layout.activity_fix_it_report);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null && !hasFixItReportRequest()) {
            fetchFixItReport(getIntent().getLongExtra(FixItIntents.INTENT_EXTRA_REPORT_ID, 0));
            showReport(getIntent().getStringExtra("listing_name"));
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.dataController = null;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.dataController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public FixItDataController getDataController() {
        return this.dataController;
    }

    public void showItem(FixItItem item) {
        showFragment(FixItItemFragment.create(item));
    }

    public void showItemComment(FixItItem item) {
        showModal(FixItItemCommentFragment.create(item));
    }

    private void showFragment(Fragment fragment) {
        showFragment(fragment, C6380R.C6382id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    private void showModal(Fragment fragment) {
        showModal(fragment, C6380R.C6382id.content_container, C6380R.C6382id.modal_container, true);
    }

    private void showReport(String listingName) {
        showFragment(FixItReportFragment.create(listingName));
    }

    private void fetchFixItReport(long reportId) {
        FixItReportRequest.forReportId(reportId).withListener((Observer) this.fixItReportListener).execute(this.requestManager);
        this.dataController.setLoading(true);
    }

    private boolean hasFixItReportRequest() {
        return this.requestManager.hasRequest((BaseRequestListener<T>) this.fixItReportListener, FixItReportRequest.class);
    }
}
