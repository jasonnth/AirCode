package com.airbnb.android.lib.paidamenities.fragments.create;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.activities.CreateAmenityActivity.Flow;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class CreateAmenityLandingFragment extends BaseCreateAmenityFragment {
    private CreateAmenityLandingAdapter adapter;
    final RequestListener<ListingResponse> listingsRequestListener = new C0699RL().onResponse(CreateAmenityLandingFragment$$Lambda$1.lambdaFactory$(this)).onError(CreateAmenityLandingFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirButton nextButton;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public class CreateAmenityLandingAdapter extends AirEpoxyAdapter {
        private final DocumentMarqueeEpoxyModel_ documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        private final SimpleTextRowEpoxyModel_ paidAmenityParagraph = new SimpleTextRowEpoxyModel_();

        public CreateAmenityLandingAdapter() {
            this.documentMarqueeModel.titleRes(C0880R.string.paid_amenities_landing_title);
            this.paidAmenityParagraph.textRes(C0880R.string.paid_amenities_landing_paragraph).showDivider(false);
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.documentMarqueeModel, this.paidAmenityParagraph});
        }
    }

    public static CreateAmenityLandingFragment newInstance() {
        return new CreateAmenityLandingFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_create_amenity_landing, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new CreateAmenityLandingAdapter();
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        this.paidAmenityJitneyLogger.logHostAddClickService();
        if (this.flow == Flow.Normal) {
            this.nextButton.setState(State.Loading);
            ListingRequest.forCurrentUserListings().withListener((Observer) this.listingsRequestListener).execute(this.requestManager);
            return;
        }
        this.navigationController.doneWithSelectListing();
    }

    static /* synthetic */ void lambda$new$0(CreateAmenityLandingFragment createAmenityLandingFragment, ListingResponse response) {
        createAmenityLandingFragment.nextButton.setState(State.Normal);
        createAmenityLandingFragment.navigationController.doneWithFetchListings(response.getListings());
    }

    static /* synthetic */ void lambda$new$1(CreateAmenityLandingFragment createAmenityLandingFragment, AirRequestNetworkException error) {
        createAmenityLandingFragment.nextButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(createAmenityLandingFragment.getView(), error);
    }
}
