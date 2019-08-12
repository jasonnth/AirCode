package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.requests.FetchAllPaidAmenitiesRequest;
import com.airbnb.android.lib.paidamenities.requests.FetchAllPaidAmenitiesRequest.Format;
import com.airbnb.android.lib.paidamenities.responses.FetchAllPaidAmenitiesResponse;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class PurchaseAmenityLandingFragment extends BasePurchaseAmenityFragment {
    public static final String TAG = PurchaseAmenityLandingFragment.class.getSimpleName();
    private PurchaseAmenityLandingAdapter adapter;
    final RequestListener<FetchAllPaidAmenitiesResponse> fetchAllPaidAmenitiesRequestListener = new C0699RL().onResponse(PurchaseAmenityLandingFragment$$Lambda$1.lambdaFactory$(this)).onError(PurchaseAmenityLandingFragment$$Lambda$2.lambdaFactory$(this)).onComplete(PurchaseAmenityLandingFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    AirButton nextButton;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public class PurchaseAmenityLandingAdapter extends AirEpoxyAdapter {
        private final DocumentMarqueeEpoxyModel_ documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        private final SimpleTextRowEpoxyModel_ paidAmenityParagraphModel = new SimpleTextRowEpoxyModel_();

        public PurchaseAmenityLandingAdapter() {
            this.documentMarqueeModel.titleRes(C0880R.string.purchase_amenities_landing_title);
            this.paidAmenityParagraphModel.textRes(C0880R.string.purchase_amenities_landing_paragraph).showDivider(false);
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.documentMarqueeModel, this.paidAmenityParagraphModel});
        }
    }

    public static PurchaseAmenityLandingFragment newInstance() {
        return new PurchaseAmenityLandingFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_purchase_amenity_landing, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new PurchaseAmenityLandingAdapter();
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    @OnClick
    public void onClickNextButton() {
        this.nextButton.setState(State.Loading);
        this.paidAmenityJitneyLogger.logGuestAddClickShowServices();
        fetchAvailablePaidAmenities();
    }

    /* access modifiers changed from: private */
    public void restoreButtonState() {
        this.nextButton.setState(State.Normal);
    }

    private void fetchAvailablePaidAmenities() {
        FetchAllPaidAmenitiesRequest.forListingId(this.listingId, Format.DetailView).withListener((Observer) this.fetchAllPaidAmenitiesRequestListener).execute(this.requestManager);
    }
}
