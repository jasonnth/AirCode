package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.core.viewcomponents.models.EntryMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.epoxy.Typed2AirEpoxyController;

/* renamed from: com.airbnb.android.p3.P3SummaryAndSpaceDescriptionFragment */
public class P3SummaryAndSpaceDescriptionFragment extends P3BaseFragment {
    @BindView
    RecyclerView recyclerView;
    private final SummaryEpoxyController summaryEpoxyController = new SummaryEpoxyController();
    @BindView
    AirToolbar toolbar;

    /* renamed from: com.airbnb.android.p3.P3SummaryAndSpaceDescriptionFragment$SummaryEpoxyController */
    private static class SummaryEpoxyController extends Typed2AirEpoxyController<Listing, P3State> {
        public SummaryEpoxyController() {
            disableAutoDividers();
        }

        /* access modifiers changed from: protected */
        public void buildModels(Listing listing, P3State state) {
            new EntryMarqueeEpoxyModel_().m4572id((CharSequence) "marquee").title(C7532R.string.details).addTo(this);
            if (!state.showTranslatedSections() || TextUtils.isEmpty(state.translatedSectionedListingDescription().getDescription())) {
                if (!TextUtils.isEmpty(listing.getSummary())) {
                    new SimpleTextRowEpoxyModel_().m5580id((CharSequence) "summary").text(listing.getSummary()).textIsSelectable(true).addTo(this);
                }
                addModels(listing.getSpace(), C7532R.string.lys_the_space);
                addModels(listing.getAccess(), C7532R.string.lys_guest_access_title);
                addModels(listing.getInteraction(), C7532R.string.lys_interaction_with_guests_title);
                addModels(listing.getNeighborhoodOverview(), C7532R.string.ml_field_neighborhood_overview);
                addModels(listing.getTransit(), C7532R.string.lys_neighborhood_getting_around_title);
                addModels(listing.getNotes(), C7532R.string.lys_extra_details_other_title);
                addModels(listing.getLicense(), C7532R.string.license_or_registration_number);
                return;
            }
            new SimpleTextRowEpoxyModel_().m5580id((CharSequence) "translated description").text(state.translatedSectionedListingDescription().getDescription()).addTo(this);
        }

        private void addModels(String text, int headerStringRes) {
            if (!TextUtils.isEmpty(text)) {
                new SimpleTextRowEpoxyModel_().m5581id((CharSequence) "subsection header", (long) headerStringRes).textRes(headerStringRes).plus().addTo(this);
                new SimpleTextRowEpoxyModel_().m5581id((CharSequence) "subsection text", (long) headerStringRes).text(text).textIsSelectable(true).addTo(this);
            }
        }
    }

    public static P3SummaryAndSpaceDescriptionFragment newInstance() {
        return new P3SummaryAndSpaceDescriptionFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.p3_generic_recyclerview_with_toolbar, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.summaryEpoxyController.getAdapter());
        updateAdapter();
        return view;
    }

    private void updateAdapter() {
        this.summaryEpoxyController.setData(this.controller.getState().listing(), this.controller.getState());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ListingDetails;
    }

    public void onStateChanged() {
        updateAdapter();
    }
}
