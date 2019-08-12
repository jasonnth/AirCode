package com.airbnb.android.lib.payments.quickpay.views;

import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import com.airbnb.android.core.CoreApplication;

public class QuickPayView {
    private final View payButton;
    private final RecyclerView recyclerView;

    public QuickPayView(View payButton2, RecyclerView recyclerView2) {
        this.payButton = payButton2;
        this.recyclerView = recyclerView2;
    }

    public void togglePayButtonVisibility() {
        if (shouldShowPayButton(this.recyclerView)) {
            showPayButton();
        } else {
            hidePayButton();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldShowPayButton(int recyclerViewItemCount, int recyclerViewLastVisibleItemPosition) {
        return recyclerViewLastVisibleItemPosition >= recyclerViewItemCount + -1;
    }

    private boolean shouldShowPayButton(RecyclerView recyclerView2) {
        if (!CoreApplication.instance(recyclerView2.getContext()).isTestApplication()) {
            return shouldShowPayButton(recyclerView2.getAdapter().getItemCount(), ((LinearLayoutManager) recyclerView2.getLayoutManager()).findLastVisibleItemPosition());
        }
        return true;
    }

    private void showPayButton() {
        this.payButton.setVisibility(0);
    }

    private void hidePayButton() {
        this.payButton.setVisibility(8);
    }
}
