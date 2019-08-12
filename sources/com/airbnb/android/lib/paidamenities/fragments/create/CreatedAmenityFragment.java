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
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.enums.PaidAmenityStatus;
import com.airbnb.android.lib.paidamenities.requests.UpdatePaidAmenityRequest;
import com.airbnb.android.lib.paidamenities.responses.PaidAmenityResponse;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;
import p032rx.Observer;

public class CreatedAmenityFragment extends BaseCreateAmenityFragment {
    private AmenityTermsAndServiceAdapter adapter;
    final RequestListener<PaidAmenityResponse> createPaidAmenityRequestListener = new C0699RL().onResponse(CreatedAmenityFragment$$Lambda$1.lambdaFactory$(this)).onError(CreatedAmenityFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    PaidAmenity createdAmenity;
    @BindView
    AirButton finishButton;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirTextView serviceTerm;
    @BindView
    AirToolbar toolbar;
    final RequestListener<PaidAmenityResponse> updatePaidAmenityRequestListener = new C0699RL().onResponse(CreatedAmenityFragment$$Lambda$3.lambdaFactory$(this)).onError(CreatedAmenityFragment$$Lambda$4.lambdaFactory$(this)).build();

    public class AmenityTermsAndServiceAdapter extends AirEpoxyAdapter {
        private final SimpleTextRowEpoxyModel_ contentRowModel = new SimpleTextRowEpoxyModel_();
        private final DocumentMarqueeEpoxyModel_ documentMarqueeModel = new DocumentMarqueeEpoxyModel_();
        private final LoadingRowEpoxyModel_ loadingRowModel = new LoadingRowEpoxyModel_();

        public AmenityTermsAndServiceAdapter() {
            configureDocumentMarquee();
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.documentMarqueeModel, this.loadingRowModel});
        }

        private void configureDocumentMarquee() {
            this.documentMarqueeModel.titleRes(C0880R.string.paid_amenities_created_amenity_title).captionRes(C0880R.string.paid_amenities_created_amenity_caption);
        }

        /* access modifiers changed from: private */
        public void showContentBody() {
            hideModel(this.loadingRowModel);
            this.contentRowModel.textRes(C0880R.string.paid_amenities_created_amenity_tips).showDivider(false);
            addModel(this.contentRowModel);
        }
    }

    public static CreatedAmenityFragment newInstance() {
        return new CreatedAmenityFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_created_paid_amenity, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new AmenityTermsAndServiceAdapter();
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.createdAmenity == null) {
            softCreateAmenity();
        } else {
            setupContent();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        this.paidAmenityJitneyLogger.logHostAddClickFinalize();
        acceptTermsAndUpdateAmenityStatus();
    }

    private void softCreateAmenity() {
        getCreatePaidAmenityRequest().withListener((Observer) this.createPaidAmenityRequestListener).execute(this.requestManager);
    }

    private void acceptTermsAndUpdateAmenityStatus() {
        this.finishButton.setState(AirButton.State.Loading);
        UpdatePaidAmenityRequest.forPaidAmenity(this.createdAmenity.getId(), PaidAmenityStatus.LISTED).withListener((Observer) this.updatePaidAmenityRequestListener).execute(this.requestManager);
    }

    private void setupContent() {
        this.adapter.showContentBody();
        this.finishButton.setVisibility(0);
        this.serviceTerm.setOnClickListener(CreatedAmenityFragment$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void launchPaidAmenityTermsOfService() {
        startActivity(WebViewIntentBuilder.newBuilder(getActivity(), this.createdAmenity.getDisclosure_url()).toIntent());
    }

    static /* synthetic */ void lambda$new$1(CreatedAmenityFragment createdAmenityFragment, PaidAmenityResponse response) {
        createdAmenityFragment.createdAmenity = response.paidAmenity;
        createdAmenityFragment.setupContent();
    }

    static /* synthetic */ void lambda$new$3(CreatedAmenityFragment createdAmenityFragment, PaidAmenityResponse response) {
        createdAmenityFragment.finishButton.setState(AirButton.State.Normal);
        createdAmenityFragment.startActivity(PaidAmenityIntents.hostAmenitiesIntent(createdAmenityFragment.getContext(), createdAmenityFragment.paidAmenityDetails.listingId().longValue()));
        createdAmenityFragment.navigationController.doneCreatingAmenity();
    }

    static /* synthetic */ void lambda$new$4(CreatedAmenityFragment createdAmenityFragment, AirRequestNetworkException error) {
        createdAmenityFragment.finishButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(createdAmenityFragment.getView(), error);
    }
}
