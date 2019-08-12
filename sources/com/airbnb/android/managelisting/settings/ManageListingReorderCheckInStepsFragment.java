package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.requests.UpdateCheckInGuideRequest;
import com.airbnb.android.core.responses.CheckInGuideResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class ManageListingReorderCheckInStepsFragment extends ManageListingBaseFragment {
    private CheckInReorderStepsAdapter adapter;
    @State
    ArrayList<Long> currentStepOrdering;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<CheckInGuideResponse> reorderStepsListener = new C0699RL().onResponse(ManageListingReorderCheckInStepsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingReorderCheckInStepsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    public static ManageListingReorderCheckInStepsFragment create() {
        return new ManageListingReorderCheckInStepsFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingReorderCheckInStepsFragment manageListingReorderCheckInStepsFragment, CheckInGuideResponse response) {
        manageListingReorderCheckInStepsFragment.controller.setCheckInGuide(response.guide);
        manageListingReorderCheckInStepsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingReorderCheckInStepsFragment manageListingReorderCheckInStepsFragment, AirRequestNetworkException error) {
        manageListingReorderCheckInStepsFragment.saveButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingReorderCheckInStepsFragment.getView(), error);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<CheckInStep> steps = this.controller.getCheckInGuide().getSteps();
        if (this.currentStepOrdering != null) {
            steps = reorderSteps(steps, this.currentStepOrdering);
        }
        this.adapter = new CheckInReorderStepsAdapter(steps);
    }

    private List<CheckInStep> reorderSteps(List<CheckInStep> steps, List<Long> order) {
        ImmutableMap<Long, CheckInStep> stepsById = FluentIterable.from((Iterable<E>) steps).uniqueIndex(ManageListingReorderCheckInStepsFragment$$Lambda$3.lambdaFactory$());
        FluentIterable from = FluentIterable.from((Iterable<E>) order);
        stepsById.getClass();
        return from.transform(ManageListingReorderCheckInStepsFragment$$Lambda$4.lambdaFactory$(stepsById)).toList();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_check_in_steps, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        this.currentStepOrdering = new ArrayList<>(this.adapter.currentOrdering());
        super.onSaveInstanceState(outState);
    }

    @OnClick
    public void onSaveOrder() {
        if (!canSaveChanges()) {
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(AirButton.State.Loading);
        UpdateCheckInGuideRequest.forReorder(this.controller.getListingId(), this.adapter.currentOrdering()).withListener((Observer) this.reorderStepsListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingCheckinGuideReorderSteps;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return !FluentIterable.from((Iterable<E>) this.controller.getCheckInGuide().getSteps()).transform(ManageListingReorderCheckInStepsFragment$$Lambda$5.lambdaFactory$()).toList().equals(this.adapter.currentOrdering());
    }
}
