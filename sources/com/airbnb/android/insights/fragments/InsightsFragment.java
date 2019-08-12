package com.airbnb.android.insights.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.LinearSnapHelper;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.SimpleOnScrollListener;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Insight.ButtonAction;
import com.airbnb.android.core.models.Insight.ConversionType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.InsightsRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.InsightsResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.insights.C6552R;
import com.airbnb.android.insights.InsightEpoxyModel.LoadingState;
import com.airbnb.android.insights.InsightEpoxyModel_;
import com.airbnb.android.insights.InsightsActivity;
import com.airbnb.android.insights.InsightsAnalytics;
import com.airbnb.android.insights.InsightsDataController;
import com.airbnb.android.insights.InsightsDataController.InsightsStateChangeListener;
import com.airbnb.android.insights.LastInsightEpoxyModel_;
import com.airbnb.android.insights.adapters.InsightsAdapter;
import com.airbnb.android.insights.adapters.InsightsAdapter.InsightEventListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ParcelableUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import p032rx.Observer;

public class InsightsFragment extends AirFragment implements InsightsStateChangeListener, InsightEventListener {
    private static final String ARG_LISTING = "listing";
    private static final String ARG_STORY_ID = "story_id";
    /* access modifiers changed from: private */
    public InsightsAdapter adapter;
    @BindView
    ViewGroup container;
    /* access modifiers changed from: private */
    public InsightsDataController dataController;
    @BindView
    AirTextView disclaimerText;
    @State
    LinkedHashMap<Insight, LoadingState> insightToLoadingStateMap = new LinkedHashMap<>();
    private InsightsAnalytics insightsAnalytics;
    final RequestListener<InsightsResponse> insightsRequestListener = new C0699RL().onResponse(InsightsFragment$$Lambda$1.lambdaFactory$(this)).onError(InsightsFragment$$Lambda$2.lambdaFactory$(this)).onComplete(InsightsFragment$$Lambda$3.lambdaFactory$(this)).build();
    private LinearLayoutManager layoutManager;
    public final RequestListener<ListingResponse> listingsListener = new C0699RL().onResponse(InsightsFragment$$Lambda$4.lambdaFactory$(this)).onError(InsightsFragment$$Lambda$5.lambdaFactory$(this)).build();
    @BindView
    LoadingView loader;
    @BindView
    RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public final SnackbarWrapper snackbar = new SnackbarWrapper();
    @BindView
    AirToolbar toolbar;
    private final SimpleOnScrollListener updateListingNameScrollListener = new SimpleOnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == 0) {
                InsightsFragment.this.updateListingName();
            }
        }
    };

    public static InsightsFragment newInstance(Listing listing) {
        return (InsightsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new InsightsFragment()).putParcelable("listing", listing)).build();
    }

    public static InsightsFragment newInstance(Listing listing, String storyId) {
        return (InsightsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new InsightsFragment()).putParcelable("listing", listing)).putString(ARG_STORY_ID, storyId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        View v = inflater.inflate(C6552R.layout.fragment_insights, container2, false);
        bindViews(v);
        this.dataController = ((InsightsActivity) getActivity()).getDataController();
        this.dataController.addListener(this);
        this.insightsAnalytics = ((InsightsActivity) getActivity()).getInsightsAnalytics();
        this.toolbar.setNavigationOnClickListener(InsightsFragment$$Lambda$6.lambdaFactory$(this));
        if (savedInstanceState == null) {
            Listing firstListing = (Listing) getArguments().getParcelable("listing");
            this.toolbar.setTitle((CharSequence) firstListing.getName());
            InsightsRequest.forListing(firstListing).withListener((Observer) this.insightsRequestListener).execute(this.requestManager);
        } else {
            toggleLoading(false);
        }
        setUpRecyclerView(savedInstanceState);
        return v;
    }

    private void setUpRecyclerView(Bundle savedInstanceState) {
        this.layoutManager = new LinearLayoutManager(getContext(), 0, false);
        this.recyclerView.setLayoutManager(this.layoutManager);
        new LinearSnapHelper().attachToRecyclerView(this.recyclerView);
        this.adapter = new InsightsAdapter(this);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addOnScrollListener(this.updateListingNameScrollListener);
        this.insightsAnalytics.setUpForInsightsFragment(this.recyclerView, this.layoutManager, this.adapter);
        if (savedInstanceState != null) {
            this.recyclerView.post(InsightsFragment$$Lambda$7.lambdaFactory$(this));
            Iterator<Insight> it = this.insightToLoadingStateMap.keySet().iterator();
            if (it.hasNext()) {
                Listing firstListing = ((Insight) it.next()).getListing();
                if (firstListing != null) {
                    this.toolbar.setTitle((CharSequence) firstListing.getName());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateListingName() {
        int pos = this.layoutManager.findFirstCompletelyVisibleItemPosition();
        if (pos != -1) {
            EpoxyModel<?> model = this.adapter.getModelAtPosition(pos);
            if (model instanceof InsightEpoxyModel_) {
                this.toolbar.setTitle((CharSequence) ((InsightEpoxyModel_) model).insight().getListing().getName());
            } else {
                this.toolbar.setTitle((CharSequence) null);
            }
        }
    }

    static /* synthetic */ void lambda$new$2(InsightsFragment insightsFragment, InsightsResponse response) {
        if (response.stories.isEmpty()) {
            insightsFragment.getActivity().finish();
            return;
        }
        if (!insightsFragment.dataController.isSingleInsightOnly()) {
            response.stories.add(insightsFragment.createDummyInsight(response));
        }
        insightsFragment.insightToLoadingStateMap.putAll(insightsFragment.setupNewInsights(response));
        insightsFragment.adapter.addInsights(insightsFragment.insightToLoadingStateMap);
    }

    public void onStateChange(LoadingState state, Insight insight, boolean hasError) {
        this.insightToLoadingStateMap.put(insight, state);
        this.adapter.updateStoryLoadingState(insight, state);
    }

    public void onSaveInstanceState(Bundle outState) {
        for (Entry<Insight, LoadingState> entry : this.insightToLoadingStateMap.entrySet()) {
            Insight insight = (Insight) entry.getKey();
            LoadingState state = (LoadingState) entry.getValue();
            if (state == LoadingState.PRIMARY_ACTION_LOADING) {
                state = LoadingState.DONE;
            } else if (state == LoadingState.UNDO_ACTION_LOADING) {
                state = LoadingState.DEFAULT;
            }
            this.insightToLoadingStateMap.put(insight, state);
        }
        super.onSaveInstanceState(outState);
    }

    public void showSnackbarError(NetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(this.container, e);
    }

    public void onDestroyView() {
        this.recyclerView.clearOnScrollListeners();
        this.dataController.removeListener(this);
        super.onDestroyView();
    }

    public void onPause() {
        this.requestManager.cancelRequests(this.insightsRequestListener);
        super.onPause();
    }

    /* access modifiers changed from: private */
    public void toggleLoading(boolean isLoading) {
        ViewLibUtils.setVisibleIf(this.loader, isLoading);
        ViewLibUtils.setVisibleIf(this.recyclerView, !isLoading);
    }

    public void onPrimaryButtonClicked(InsightEpoxyModel_ insightModel) {
        switch (insightModel.loadingState()) {
            case DEFAULT:
                handlePrimaryBeforeAction(insightModel);
                return;
            case DONE:
                if (this.dataController.isSingleInsightOnly()) {
                    loadListingsAndInsights();
                    return;
                } else {
                    this.recyclerView.smoothScrollToPosition(this.adapter.getModelPosition(insightModel) + 1);
                    return;
                }
            default:
                return;
        }
    }

    private void handlePrimaryBeforeAction(InsightEpoxyModel_ insightModel) {
        ButtonAction primaryBefore = insightModel.insight().getStoryConversionType().buttonBehavior.primaryBefore;
        switch (primaryBefore) {
            case SendRequestAction:
                this.dataController.handleUpdateRequest(insightModel.insight());
                return;
            case NothingAction:
                return;
            case RedirectAndDismissAction:
                handleRedirectAndDismiss(insightModel);
                return;
            default:
                BugsnagWrapper.notify((Throwable) new UnhandledStateException(primaryBefore));
                return;
        }
    }

    public void onDismissButtonClicked(InsightEpoxyModel_ insightModel) {
        if (this.dataController.isSingleInsightOnly()) {
            loadListingsAndInsights();
        } else {
            dismissCard(insightModel);
        }
        this.insightsAnalytics.trackDismiss(insightModel.insight());
    }

    public void onSecondaryButtonClicked(InsightEpoxyModel_ insightModel) {
        switch (insightModel.loadingState()) {
            case DEFAULT:
                handleSecondaryBeforeAction(insightModel);
                return;
            case DONE:
                handleSecondaryAfterAction(insightModel);
                return;
            default:
                return;
        }
    }

    private void handleSecondaryAfterAction(InsightEpoxyModel_ insightModel) {
        ButtonAction secondaryAfter = insightModel.insight().getStoryConversionType().buttonBehavior.secondaryAfter;
        switch (secondaryAfter) {
            case NothingAction:
                return;
            case UndoRequestAction:
                this.dataController.sendUndoRequest(insightModel.insight());
                return;
            default:
                BugsnagWrapper.notify((Throwable) new UnhandledStateException(secondaryAfter));
                return;
        }
    }

    private void handleSecondaryBeforeAction(InsightEpoxyModel_ insightModel) {
        ButtonAction secondaryBefore = insightModel.insight().getStoryConversionType().buttonBehavior.secondaryBefore;
        switch (secondaryBefore) {
            case NothingAction:
                return;
            case OpenScreenAction:
                ((InsightsParentFragment) getParentFragment()).showDetailsFragment(insightModel);
                return;
            default:
                BugsnagWrapper.notify((Throwable) new UnhandledStateException(secondaryBefore));
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onDisclaimerTextClicked() {
        ((InsightsParentFragment) getParentFragment()).showPricingDisclaimer();
    }

    public void onMoreListingsButtonClicked(final LastInsightEpoxyModel_ model) {
        this.insightsAnalytics.trackLastCardConversion(model.dummyInsight(), true);
        final Listing listingToFetch = model.nextListing();
        if (this.dataController.hasRetrievedListingInsights(listingToFetch.getId())) {
            this.recyclerView.smoothScrollToPosition(this.adapter.getModelPosition(model) + 1);
            return;
        }
        InsightsRequest.forListing(listingToFetch).withListener((Observer) new NonResubscribableRequestListener<InsightsResponse>() {
            public void onResponse(InsightsResponse response) {
                if (response.stories.isEmpty()) {
                    InsightsFragment.this.snackbar.view(InsightsFragment.this.getView()).title(C6552R.string.error_request, true).duration(0).buildAndShow();
                    return;
                }
                response.stories.add(InsightsFragment.this.createDummyInsight(response));
                LinkedHashMap<Insight, LoadingState> newInsightsToAdd = InsightsFragment.this.setupNewInsights(response);
                InsightsFragment.this.adapter.addInsights(newInsightsToAdd);
                InsightsFragment.this.insightToLoadingStateMap.putAll(newInsightsToAdd);
                InsightsFragment.this.adapter.updateLastCardLoadingState(model, false);
                if (listingToFetch != null) {
                    InsightsFragment.this.dataController.addRetrievedListingInsights(listingToFetch.getId());
                }
                InsightsFragment.this.recyclerView.smoothScrollToPosition(InsightsFragment.this.adapter.getModelPosition(model) + 1);
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                NetworkUtil.tryShowErrorWithSnackbar(InsightsFragment.this.container, error);
                InsightsFragment.this.adapter.updateLastCardLoadingState(model, false);
            }
        }).execute(this.requestManager);
        this.adapter.updateLastCardLoadingState(model, true);
    }

    /* access modifiers changed from: private */
    public LinkedHashMap<Insight, LoadingState> setupNewInsights(InsightsResponse response) {
        LinkedHashMap<Insight, LoadingState> insightsMap = new LinkedHashMap<>();
        for (int i = 0; i < response.stories.size(); i++) {
            Insight insight = (Insight) response.stories.get(i);
            insight.setGlobalPosition(this.insightToLoadingStateMap.size() + i);
            insight.setPosition(i);
            insightsMap.put(insight, LoadingState.DEFAULT);
        }
        return insightsMap;
    }

    /* access modifiers changed from: private */
    public Insight createDummyInsight(InsightsResponse response) {
        Insight dummyInsightForLastCard = (Insight) ParcelableUtils.cloneParcelable((Parcelable) response.stories.get(0));
        dummyInsightForLastCard.setStoryId(null);
        dummyInsightForLastCard.setListing(this.dataController.getNextListing());
        dummyInsightForLastCard.setBackendPosition(-1);
        return dummyInsightForLastCard;
    }

    public void leaveInsights(LastInsightEpoxyModel_ model) {
        this.insightsAnalytics.trackLastCardConversion(model.dummyInsight(), false);
        ((InsightsParentFragment) getParentFragment()).popBackStackOrFinish();
    }

    private void handleRedirectAndDismiss(InsightEpoxyModel_ model) {
        ConversionType type = model.insight().getStoryConversionType();
        switch (type) {
            case OpenListingPhotos:
                goToManageListingSettingAndDismiss(model, SettingDeepLink.Photos);
                return;
            case OpenListingAmenities:
                goToManageListingSettingAndDismiss(model, SettingDeepLink.Amenities);
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(type));
                return;
        }
    }

    private void goToManageListingSettingAndDismiss(InsightEpoxyModel_ model, SettingDeepLink linkType) {
        startActivity(ManageListingIntents.intentForExistingListingSetting(getContext(), model.insight().getListingId(), linkType));
        dismissCard(model);
    }

    private void dismissCard(InsightEpoxyModel_ insightModel) {
        int pos = this.adapter.getModelPosition(insightModel);
        EpoxyModel<?> prevModel = this.adapter.getModelAtPosition(pos - 1);
        EpoxyModel<?> nextModel = this.adapter.getModelAtPosition(pos + 1);
        EpoxyModel<?> nextNextModel = this.adapter.getModelAtPosition(pos + 2);
        if (prevModel == null && (nextModel instanceof LastInsightEpoxyModel_) && nextNextModel != null) {
            this.adapter.removeModel(nextModel);
            this.insightToLoadingStateMap.remove(((LastInsightEpoxyModel_) nextModel).dummyInsight());
        }
        this.adapter.removeModel(insightModel);
        this.insightToLoadingStateMap.remove(insightModel.insight());
        if ((prevModel instanceof LastInsightEpoxyModel_) && (nextModel instanceof LastInsightEpoxyModel_)) {
            this.adapter.removeModel(prevModel);
            this.insightToLoadingStateMap.remove(((LastInsightEpoxyModel_) prevModel).dummyInsight());
            this.recyclerView.postDelayed(InsightsFragment$$Lambda$8.lambdaFactory$(this, nextModel), 100);
        }
    }

    static /* synthetic */ void lambda$dismissCard$5(InsightsFragment insightsFragment, EpoxyModel nextModel) {
        if (insightsFragment.recyclerView != null) {
            insightsFragment.recyclerView.smoothScrollToPosition(insightsFragment.adapter.getModelPosition(nextModel));
        }
    }

    private void loadListingsAndInsights() {
        toggleLoading(true);
        this.adapter.clearModels();
        ListingRequest.forHostStatsPicker(AirbnbAccountManager.currentUserId(), 0, 10, this.listingsListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$6(InsightsFragment insightsFragment, ListingResponse response) {
        Iterator it = response.getListingsArrayList().iterator();
        while (it.hasNext()) {
            Listing listing = (Listing) it.next();
            listing.setListingNativeCurrency(listing.getListingCurrency());
        }
        insightsFragment.dataController.setAllListings(response.getListingsArrayList());
        insightsFragment.dataController.setFirstListingPosition(0);
        insightsFragment.dataController.setSingleInsightOnly(false);
        InsightsRequest.forListing((Listing) insightsFragment.dataController.getAllListings().get(0)).withListener((Observer) insightsFragment.insightsRequestListener).execute(insightsFragment.requestManager);
    }

    static /* synthetic */ void lambda$new$7(InsightsFragment insightsFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(insightsFragment.getView(), e);
        insightsFragment.toggleLoading(false);
    }
}
