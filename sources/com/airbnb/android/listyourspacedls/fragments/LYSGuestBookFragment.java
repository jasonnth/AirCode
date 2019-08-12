package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSJitneyLogger;
import com.airbnb.android.listyourspacedls.ListYourSpaceDLSGraph;
import com.airbnb.android.listyourspacedls.adapters.HowGuestsBookAdapter;
import com.airbnb.android.listyourspacedls.adapters.HowGuestsBookAdapter.Listener;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import p032rx.Observer;

public class LYSGuestBookFragment extends LYSBaseFragment implements Listener {
    private HowGuestsBookAdapter howGuestsBookAdapter;
    LYSJitneyLogger jitneyLogger;
    @State
    String previousInstantBookCategory;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSGuestBookFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSGuestBookFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSGuestBookFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static LYSGuestBookFragment newInstance() {
        return new LYSGuestBookFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSGuestBookFragment lYSGuestBookFragment, SimpleListingResponse response) {
        lYSGuestBookFragment.controller.setListing(response.listing);
        lYSGuestBookFragment.previousInstantBookCategory = response.listing.getInstantBookingAllowedCategory();
        lYSGuestBookFragment.navigateInFlow(LYSStep.HowGuestsBookStep);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState == null) {
            this.previousInstantBookCategory = this.controller.getListing().getInstantBookingAllowedCategory();
        }
        this.howGuestsBookAdapter = new HowGuestsBookAdapter(this, this.controller.isInstantBook());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ((ListYourSpaceDLSGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (this.comingFromBackstack) {
            this.previousInstantBookCategory = this.controller.getListing().getInstantBookingAllowedCategory();
        }
        this.recyclerView.setAdapter(this.howGuestsBookAdapter);
        return view;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.HowGuestsBookStep);
        } else {
            updateInstantBookingAllowedCategory();
        }
    }

    @OnClick
    public void clickNext() {
        this.userAction = UserAction.GoToNext;
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.HowGuestsBookStep);
        } else {
            updateInstantBookingAllowedCategory();
        }
    }

    private void updateInstantBookingAllowedCategory() {
        setLoading(null);
        UpdateListingRequest.forFieldLYS(this.controller.getListing().getId(), ListingRequestConstants.JSON_INSTANT_BOOK_VISIBILITY_KEY, getInstantBookAllowedCategory().serverKey).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    private InstantBookingAllowedCategory getInstantBookAllowedCategory() {
        return this.controller.isInstantBook() ? InstantBookingAllowedCategory.EveryOne : InstantBookingAllowedCategory.Off;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return !TextUtils.equals(this.previousInstantBookCategory, this.controller.getListing().getInstantBookingAllowedCategory());
    }

    public void setIsInstantBook(boolean instantBookOn) {
        if (instantBookOn) {
            this.controller.setInstantBookAllowedCategory(InstantBookingAllowedCategory.EveryOne);
        } else {
            this.controller.showRequestToBookChecklistModal();
        }
        this.jitneyLogger.logSwitchBookingSettings(Long.valueOf(this.controller.getListing().getId()), instantBookOn);
    }

    /* access modifiers changed from: protected */
    public void onDiscard() {
        this.controller.setInstantBookAllowedCategory(InstantBookingAllowedCategory.fromKey(this.previousInstantBookCategory));
        super.onDiscard();
    }

    public void dataUpdated() {
        if (this.howGuestsBookAdapter != null) {
            this.howGuestsBookAdapter.updateModels(this.controller.isInstantBook());
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSHowGuestsBook;
    }
}
