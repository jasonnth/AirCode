package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.HeroMarquee;

public class PurchaseAmenitySuccessFragment extends BasePurchaseAmenityFragment {
    @BindView
    HeroMarquee heroMarquee;
    @BindView
    AirToolbar toolbar;

    public static PurchaseAmenitySuccessFragment newInstance() {
        return new PurchaseAmenitySuccessFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_purchase_amenity_success, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(PurchaseAmenitySuccessFragment$$Lambda$1.lambdaFactory$(this));
        setUpHeroMarquee();
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment, View v) {
        purchaseAmenitySuccessFragment.paidAmenityJitneyLogger.logGuestAddClickFinalizeExit();
        purchaseAmenitySuccessFragment.navigationController.doneWithPurchaseAmenity();
    }

    private void setUpHeroMarquee() {
        this.heroMarquee.setFirstButtonText(C0880R.string.purchase_amenities_talk_to_host_button_text);
        this.heroMarquee.setFirstButtonClickListener(PurchaseAmenitySuccessFragment$$Lambda$2.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonText(C0880R.string.purchase_amenities_request_another_service_button_text);
        this.heroMarquee.setSecondButtonClickListener(PurchaseAmenitySuccessFragment$$Lambda$3.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setUpHeroMarquee$1(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment, View v) {
        purchaseAmenitySuccessFragment.paidAmenityJitneyLogger.logGuestAddClickFinalizeMessage();
        purchaseAmenitySuccessFragment.goToMessageThread();
    }

    static /* synthetic */ void lambda$setUpHeroMarquee$2(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment, View v) {
        purchaseAmenitySuccessFragment.paidAmenityJitneyLogger.logGuestAddClickFinalizeAnother();
        purchaseAmenitySuccessFragment.navigationController.displayRequestAnotherService();
    }

    private void goToMessageThread() {
        startActivity(ThreadFragmentIntents.newIntent(getActivity(), this.threadId, InboxType.Guest, null, ROLaunchSource.ExtraService));
        this.navigationController.doneWithPurchaseAmenity();
    }
}
