package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.core.requests.CalendarRulesRequest;
import com.airbnb.android.core.requests.ListingPersonaRequest;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.airbnb.android.core.responses.ListingPersonaResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.adapters.RentHistoryAdapter;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import p032rx.Observer;

public class LYSRentHistoryFragment extends LYSBaseFragment {
    private RentHistoryAdapter adapter;
    final RequestListener<CalendarRulesResponse> calendarRulesListener = new C0699RL().onResponse(LYSRentHistoryFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSRentHistoryFragment$$Lambda$2.lambdaFactory$(this)).build();
    LYSJitneyLogger jitneyLogger;
    final RequestListener<ListingPersonaResponse> personaListener = new C0699RL().onResponse(LYSRentHistoryFragment$$Lambda$3.lambdaFactory$(this)).onError(LYSRentHistoryFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static LYSRentHistoryFragment newInstance() {
        return new LYSRentHistoryFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSRentHistoryFragment lYSRentHistoryFragment, CalendarRulesResponse response) {
        lYSRentHistoryFragment.controller.setCalendarRule(response.calendarRule);
        lYSRentHistoryFragment.controller.setShouldReloadCalendar(true);
        lYSRentHistoryFragment.finish();
    }

    static /* synthetic */ void lambda$new$1(LYSRentHistoryFragment lYSRentHistoryFragment, ListingPersonaResponse response) {
        lYSRentHistoryFragment.controller.getListing().setPersonaAnswer(response.listingPersonaInput);
        CalendarRulesRequest.forListingId(lYSRentHistoryFragment.controller.getListing().getId()).withListener((Observer) lYSRentHistoryFragment.calendarRulesListener).skipCache().execute(lYSRentHistoryFragment.requestManager);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new RentHistoryAdapter(savedInstanceState, LYSRentHistoryFragment$$Lambda$5.lambdaFactory$(this), this.controller.getListing().getExperiencePersonaAnswer());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setAdapter(this.adapter);
        AirButton airButton = this.nextButton;
        if (this.adapter.getRentHistoryAnswer() != null) {
            z = true;
        } else {
            z = false;
        }
        airButton.setEnabled(z);
        return view;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        save(canSaveChanges());
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.nextButton.isEnabled() && this.controller.getListing().getExperiencePersonaAnswer() != this.adapter.getRentHistoryAnswer();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    @OnClick
    public void clickNext() {
        this.userAction = UserAction.GoToNext;
        save(canSaveChanges());
    }

    private void save(boolean hasChanges) {
        boolean z = true;
        if (hasChanges) {
            setLoading(this.adapter);
            boolean hasSetRentHistory = this.controller.getListing().getExperiencePersonaAnswer() != null;
            long id = this.controller.getListing().getId();
            ListingPersonaAnswer rentHistoryAnswer = this.adapter.getRentHistoryAnswer();
            if (hasSetRentHistory) {
                z = false;
            }
            ListingPersonaRequest.updateRentHistory(id, rentHistoryAnswer, z).withListener((Observer) this.personaListener).execute(this.requestManager);
            return;
        }
        navigateInFlow(LYSStep.RentHistoryStep);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSRentHistory;
    }

    /* access modifiers changed from: private */
    public void onRentHistoryTypeSelected(ListingPersonaAnswer answer) {
        this.nextButton.setEnabled(true);
        this.jitneyLogger.logRentHistorySelectOptionEvent(String.valueOf(answer.getServerKey()), Long.valueOf(this.controller.getListing().getId()));
    }

    private void finish() {
        setLoadingFinished(true, this.adapter);
        navigateInFlow(LYSStep.RentHistoryStep);
    }

    /* access modifiers changed from: private */
    public void onError(NetworkException e) {
        setLoadingFinished(false, this.adapter);
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }
}
