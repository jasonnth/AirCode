package com.airbnb.android.identity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.collections.SheetState;

public class AccountVerificationOfflineIdFragment extends BaseAccountVerificationFragment {
    private final AccountVerificationOfflineIdListener accountVerificationOfflineIdListener = new AccountVerificationOfflineIdListener() {
        public void startIdCaptureFlow(GovernmentIdType governmentIdType) {
            AccountVerificationOfflineIdFragment.this.controller.getOfflineIdController().startIdCaptureFlow(governmentIdType);
        }

        public void clickedDocs() {
            AccountVerificationOfflineIdFragment.this.clickedDocs();
        }
    };
    AccountVerificationOfflineId accountVerificationOfflineIdView;

    public static AccountVerificationOfflineIdFragment newInstance(VerificationFlow verificationFlow) {
        return (AccountVerificationOfflineIdFragment) ((FragmentBundleBuilder) FragmentBundler.make(new AccountVerificationOfflineIdFragment()).putSerializable("arg_verification_flow", verificationFlow)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.controller.initOfflineIdController(savedInstanceState, this, this.requestManager, this.navigationAnalytics);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        this.accountVerificationOfflineIdView = new AccountVerificationOfflineId(getContext());
        this.accountVerificationOfflineIdView.setListener(this.accountVerificationOfflineIdListener);
        this.accountVerificationOfflineIdView.setVerificationFlow(getVerificationFlow());
        this.controller.getOfflineIdController().setView(this.accountVerificationOfflineIdView);
        return this.accountVerificationOfflineIdView;
    }

    public void onResume() {
        super.onResume();
        this.controller.getOfflineIdController().onResume();
        this.controller.setState(SheetState.Normal);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C6533R.C6536menu.scan_id_why, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6533R.C6535id.menu_why) {
            return super.onOptionsItemSelected(item);
        }
        clickedDocs();
        return true;
    }

    /* access modifiers changed from: private */
    public void clickedDocs() {
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "why_button");
        startActivity(HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.VERIFIED_ID_LEARN_MORE).toIntent());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.controller.getOfflineIdController().onActivityResult(requestCode, resultCode, data);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        this.controller.getOfflineIdController().onRequestPermissionsResult(requestCode, permissions2, grantResults);
    }

    /* access modifiers changed from: protected */
    public int getAirToolbarTheme() {
        return 2;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.getOfflineIdController().onSaveInstanceState(outState);
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationScanIdIntro;
    }
}
