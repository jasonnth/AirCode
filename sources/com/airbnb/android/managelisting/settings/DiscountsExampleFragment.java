package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.models.LongTermPricingExample;
import com.airbnb.android.core.requests.LongTermPricingExampleRequest;
import com.airbnb.android.core.responses.LongTermPricingExampleResponse;
import com.airbnb.android.listing.adapters.DiscountsExampleAdapter;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import p032rx.Observer;

public class DiscountsExampleFragment extends ManageListingBaseFragment {
    private DiscountsExampleAdapter adapter = new DiscountsExampleAdapter();
    @State
    LongTermPricingExample example;
    final RequestListener<LongTermPricingExampleResponse> exampleRequestListener = new C0699RL().onResponse(DiscountsExampleFragment$$Lambda$1.lambdaFactory$(this)).onError(DiscountsExampleFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static DiscountsExampleFragment newInstance() {
        return new DiscountsExampleFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C7368R.layout.recyclerview_with_toolbar, container, false);
        bindViews(v);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setTheme(3);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        if (savedInstanceState == null) {
            fetchExample();
        } else {
            this.adapter.setExampleData(this.example, ListingTextUtils.getListingCurrency(this.controller.getListing()));
        }
        return v;
    }

    /* access modifiers changed from: 0000 */
    public void fetchExample() {
        new LongTermPricingExampleRequest(this.controller.getListing().getId()).withListener((Observer) this.exampleRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$0(DiscountsExampleFragment discountsExampleFragment, LongTermPricingExampleResponse response) {
        discountsExampleFragment.example = response.getExample();
        discountsExampleFragment.adapter.setExampleData(discountsExampleFragment.example, ListingTextUtils.getListingCurrency(discountsExampleFragment.controller.getListing()));
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
