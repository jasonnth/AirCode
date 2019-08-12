package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.NewHostPromoRequest;
import com.airbnb.android.core.responses.NewHostPromoResponse;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.controllers.LYSNewHostDiscountController;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSNewHostDiscountFragment extends LYSBaseFragment {
    private static final int PERCENTAGE_OFF = 15;
    private LYSNewHostDiscountController epoxyController;
    final RequestListener<NewHostPromoResponse> promoListener = new C0699RL().onResponse(LYSNewHostDiscountFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSNewHostDiscountFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSNewHostDiscountFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static LYSNewHostDiscountFragment newInstance() {
        return new LYSNewHostDiscountFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.epoxyController = new LYSNewHostDiscountController(getContext(), this.controller.isNewHostPromoEnabled(), 15, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        showTip(C7251R.string.lys_dls_new_host_discount_tip_text, LYSNewHostDiscountFragment$$Lambda$4.lambdaFactory$(this));
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.epoxyController.requestModelBuild();
        return view;
    }

    public void onDestroyView() {
        this.recyclerView.setAdapter(null);
        super.onDestroyView();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.controller.isNewHostPromoEnabled() != this.epoxyController.isEnabled();
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (canSaveChanges()) {
            setLoading(this.epoxyController);
            NewHostPromoRequest.forLYSUpdate(this.controller.getListing().getId(), this.epoxyController.isEnabled(), this.controller.isNewHostPromoEnabled() != null).withListener((Observer) this.promoListener).execute(this.requestManager);
            return;
        }
        navigateInFlow(LYSStep.NewHostDiscount);
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        onSaveActionPressed();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSNewHostDiscount;
    }

    /* access modifiers changed from: private */
    public void showDiscountInfo() {
        this.controller.showTipModal(C7251R.string.lys_dls_new_host_discount_tip_title, C7251R.string.lys_dls_new_host_discount_tip_body, NavigationTag.LYSNewHostDiscount);
    }

    static /* synthetic */ void lambda$new$1(LYSNewHostDiscountFragment lYSNewHostDiscountFragment, NewHostPromoResponse response) {
        lYSNewHostDiscountFragment.controller.setNewHostPromoEnabled(response.isEnabled());
        lYSNewHostDiscountFragment.navigateInFlow(LYSStep.NewHostDiscount);
    }
}
