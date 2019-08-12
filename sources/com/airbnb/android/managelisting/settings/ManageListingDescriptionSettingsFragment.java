package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingDescriptionSettingsFragment extends ManageListingBaseFragment {
    private static final int SUBTITLE_MAX_LINE_COUNT = 2;
    private Adapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    private class Adapter extends AirEpoxyAdapter {
        private final StandardRowEpoxyModel_ gettingAroundModel = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_description_settings_getting_around_title).subTitleMaxLine(2).clickListener(ManageListingDescriptionSettingsFragment$Adapter$$Lambda$7.lambdaFactory$(this));
        private final StandardRowEpoxyModel_ guestAccessModel = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_description_settings_guest_access_title).subTitleMaxLine(2).clickListener(ManageListingDescriptionSettingsFragment$Adapter$$Lambda$3.lambdaFactory$(this));
        private final StandardRowEpoxyModel_ interactionWithGuestsModel = new StandardRowEpoxyModel_().title(C7368R.string.f10095x41bddfdc).subTitleMaxLine(2).clickListener(ManageListingDescriptionSettingsFragment$Adapter$$Lambda$4.lambdaFactory$(this));
        private final StandardRowEpoxyModel_ neighborhoodOverviewModel = new StandardRowEpoxyModel_().title(C7368R.string.f10093x23a628f2).subTitleMaxLine(2).clickListener(ManageListingDescriptionSettingsFragment$Adapter$$Lambda$6.lambdaFactory$(this));
        private final StandardRowEpoxyModel_ otherThingsToNoteModel = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_description_settings_other_things_to_note_title).subTitleMaxLine(2).clickListener(ManageListingDescriptionSettingsFragment$Adapter$$Lambda$5.lambdaFactory$(this));
        private final StandardRowEpoxyModel_ summaryModel = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_description_settings_description_title).subTitleMaxLine(2).clickListener(ManageListingDescriptionSettingsFragment$Adapter$$Lambda$1.lambdaFactory$(this));
        private final SectionHeaderEpoxyModel_ theNeighborhoodSectionHeaderModel = new SectionHeaderEpoxyModel_().titleRes(C7368R.string.f10097xbe08f3e6);
        private final StandardRowEpoxyModel_ theSpaceModel = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_description_settings_the_space_title).subTitleMaxLine(2).clickListener(ManageListingDescriptionSettingsFragment$Adapter$$Lambda$2.lambdaFactory$(this));
        private final DocumentMarqueeEpoxyModel_ titleModel = new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_description_settings_title);
        private final SectionHeaderEpoxyModel_ yourPlaceSectionHeaderModel = new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_description_settings_your_place_section_header);

        Adapter() {
            super(true);
            enableDiffing();
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.titleModel, this.summaryModel});
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.yourPlaceSectionHeaderModel, this.theSpaceModel, this.guestAccessModel, this.interactionWithGuestsModel, this.otherThingsToNoteModel});
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.theNeighborhoodSectionHeaderModel, this.neighborhoodOverviewModel, this.gettingAroundModel});
        }

        public void setListing(Listing listing) {
            setSubtitle(this.summaryModel, listing.getSummary(), C7368R.string.manage_listing_description_settings_description_subtitle);
            setSubtitle(this.theSpaceModel, listing.getSpace(), C7368R.string.manage_listing_description_settings_the_space_subtitle);
            setSubtitle(this.guestAccessModel, listing.getAccess(), C7368R.string.manage_listing_description_settings_guest_access_subtitle);
            setSubtitle(this.interactionWithGuestsModel, listing.getInteraction(), C7368R.string.f10094x638c7014);
            setSubtitle(this.otherThingsToNoteModel, listing.getNotes(), C7368R.string.f10096x2d1ea5c8);
            setSubtitle(this.neighborhoodOverviewModel, listing.getNeighborhoodOverview(), C7368R.string.f10092x79dd873e);
            setSubtitle(this.gettingAroundModel, listing.getTransit(), C7368R.string.manage_listing_description_settings_getting_around_subtitle);
            notifyModelsChanged();
        }

        private void setSubtitle(StandardRowEpoxyModel_ model, String currentValue, int fallbackText) {
            boolean hasValue = !TextUtils.isEmpty(currentValue);
            if (!hasValue) {
                currentValue = ManageListingDescriptionSettingsFragment.this.getContext().getString(fallbackText);
            }
            model.subtitle((CharSequence) currentValue).actionText(hasValue ? C7368R.string.edit : C7368R.string.add);
        }
    }

    static ManageListingDescriptionSettingsFragment create() {
        return new ManageListingDescriptionSettingsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new Adapter();
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void dataUpdated() {
        super.dataUpdated();
        this.adapter.setListing(this.controller.getListing());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingDescriptionSettings;
    }
}
