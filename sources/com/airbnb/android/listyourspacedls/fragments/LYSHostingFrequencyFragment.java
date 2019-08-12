package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequency;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.requests.DemandBasedPricingRequest;
import com.airbnb.android.core.requests.ListingPersonaRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import com.airbnb.android.core.responses.ListingPersonaResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.adapters.LYSHostingFrequencyAdapter;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.Lists;
import java.util.List;
import p032rx.Observer;

public class LYSHostingFrequencyFragment extends LYSBaseFragment {
    private LYSHostingFrequencyAdapter adapter;
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(LYSHostingFrequencyFragment$$Lambda$3.lambdaFactory$(this)).onError(LYSHostingFrequencyFragment$$Lambda$4.lambdaFactory$(this)).buildWithoutResubscription();
    final RequestListener<CalendarRulesResponse> calendarRulesListener = new C0699RL().onResponse(LYSHostingFrequencyFragment$$Lambda$5.lambdaFactory$(this)).onError(LYSHostingFrequencyFragment$$Lambda$6.lambdaFactory$(this)).build();
    LYSJitneyLogger jitneyLogger;
    final RequestListener<ListingPersonaResponse> personaListener = new C0699RL().onResponse(LYSHostingFrequencyFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSHostingFrequencyFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static LYSHostingFrequencyFragment newInstance() {
        return new LYSHostingFrequencyFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new LYSHostingFrequencyAdapter(LYSHostingFrequencyFragment$$Lambda$7.lambdaFactory$(this), this.controller.getListing(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.adapter);
        updateNextButtonEnabled();
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.HostingFrequencyStep);
            return;
        }
        Listing listing = this.controller.getListing();
        ListingPersonaAnswer newAnswer = this.adapter.getSelectedValue();
        ListingPersonaRequest personaRequest = (ListingPersonaRequest) ListingPersonaRequest.updateOccupany(listing.getId(), newAnswer, listing.getOccupancyPersonaAnswer() == null).skipCache();
        setLoading(this.adapter);
        DesiredHostingFrequency newFrequency = ListingPersonaAnswer.getHostingFrequencyFromAnswer(newAnswer);
        if (newFrequency == null) {
            personaRequest.withListener((Observer) this.personaListener).execute(this.requestManager);
            return;
        }
        List<BaseRequestV2<?>> requests = Lists.newArrayList();
        requests.add((DemandBasedPricingRequest) DemandBasedPricingRequest.updateDesiredHostingFrequency(newFrequency, this.controller.getListing().getId()).skipCache());
        requests.add(personaRequest);
        new AirBatchRequest(requests, this.batchListener).execute(this.requestManager);
    }

    @OnClick
    public void clickNext() {
        this.userAction = UserAction.GoToNext;
        onSaveActionPressed();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return isAnswerValid() && !this.adapter.getSelectedValue().equals(this.controller.getListing().getOccupancyPersonaAnswer());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSHostingFrequency;
    }

    private void updateNextButtonEnabled() {
        ((AirButton) Check.notNull(this.nextButton)).setEnabled(isAnswerValid());
    }

    private boolean isAnswerValid() {
        return this.adapter.getSelectedValue() != null;
    }

    /* access modifiers changed from: private */
    public void onFrequencySelected(ListingPersonaAnswer answer) {
        updateNextButtonEnabled();
        DesiredHostingFrequency frequency = ListingPersonaAnswer.getHostingFrequencyFromAnswer(answer);
        if (frequency != null) {
            this.jitneyLogger.logHostingFrequencySelectOptionEvent(frequency.name(), Long.valueOf(this.controller.getListing().getId()));
        }
    }

    private void fetchCalendarRules() {
        CalendarRulesRequest.forListingId(this.controller.getListing().getId()).withListener((Observer) this.calendarRulesListener).skipCache().execute(this.requestManager);
    }

    private void finish() {
        setLoadingFinished(true, this.adapter);
        navigateInFlow(LYSStep.HostingFrequencyStep);
    }

    /* access modifiers changed from: private */
    public void onError(NetworkException e) {
        setLoadingFinished(false, this.adapter);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }

    static /* synthetic */ void lambda$new$0(LYSHostingFrequencyFragment lYSHostingFrequencyFragment, ListingPersonaResponse response) {
        lYSHostingFrequencyFragment.controller.getListing().setPersonaAnswer(response.listingPersonaInput);
        lYSHostingFrequencyFragment.fetchCalendarRules();
    }

    static /* synthetic */ void lambda$new$1(LYSHostingFrequencyFragment lYSHostingFrequencyFragment, AirBatchResponse response) {
        DemandBasedPricingResponse pricingResponse = (DemandBasedPricingResponse) response.singleResponse(DemandBasedPricingResponse.class);
        lYSHostingFrequencyFragment.controller.getListing().setPersonaAnswer(((ListingPersonaResponse) response.singleResponse(ListingPersonaResponse.class)).listingPersonaInput);
        lYSHostingFrequencyFragment.controller.setDynamicPricingControls(pricingResponse.getPricingControl());
        lYSHostingFrequencyFragment.fetchCalendarRules();
    }

    static /* synthetic */ void lambda$new$2(LYSHostingFrequencyFragment lYSHostingFrequencyFragment, CalendarRulesResponse response) {
        lYSHostingFrequencyFragment.controller.setCalendarRule(response.calendarRule);
        lYSHostingFrequencyFragment.controller.setShouldReloadCalendar(true);
        lYSHostingFrequencyFragment.finish();
    }
}
