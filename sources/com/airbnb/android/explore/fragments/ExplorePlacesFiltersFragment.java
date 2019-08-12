package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.PlaceFilters.OnPlaceSearchFiltersChangedListener;
import com.airbnb.android.core.p008mt.models.ExplorePlacesTopCategory;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.viewcomponents.viewmodels.ExploreInlineFiltersToggleRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.SimpleEpoxyModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public class ExplorePlacesFiltersFragment extends BaseExploreFragment implements OnPlaceSearchFiltersChangedListener {
    private FiltersAdapter adapter;
    /* access modifiers changed from: private */
    public PlaceFilters placesSearchFilters;
    @BindView
    RecyclerView recyclerView;
    @BindView
    FixedActionFooter searchButton;
    @BindView
    AirToolbar toolbar;

    private final class FiltersAdapter extends AirEpoxyAdapter {
        private final ExploreInlineFiltersToggleRowEpoxyModel_ artsAndCultureModel = createTopCategoryModel(ExplorePlacesTopCategory.ArtsAndCulture, true);
        private final ExploreInlineFiltersToggleRowEpoxyModel_ drinksModel = createTopCategoryModel(ExplorePlacesTopCategory.DrinksAndNightlife, true);
        private final ExploreInlineFiltersToggleRowEpoxyModel_ entertainmentAndActivitiesModel = createTopCategoryModel(ExplorePlacesTopCategory.EntertainmentAndActivities, false);
        private final ExploreInlineFiltersToggleRowEpoxyModel_ foodSceneModel = createTopCategoryModel(ExplorePlacesTopCategory.FoodScene, true);
        private final ExploreInlineFiltersToggleRowEpoxyModel_ parksAndNatureModel = createTopCategoryModel(ExplorePlacesTopCategory.ParksAndNature, true);
        private final ExploreInlineFiltersToggleRowEpoxyModel_ shoppingModel = createTopCategoryModel(ExplorePlacesTopCategory.Shopping, true);
        private final ExploreInlineFiltersToggleRowEpoxyModel_ sightSeeingModel = createTopCategoryModel(ExplorePlacesTopCategory.Sightseeing, true);
        private final AirEpoxyModel typesHeaderModel = new MicroSectionHeaderEpoxyModel_().titleRes(C0857R.string.experience_types).showDivider(false);
        private final ExploreInlineFiltersToggleRowEpoxyModel_ wellnessModel = createTopCategoryModel(ExplorePlacesTopCategory.Wellness, true);

        public FiltersAdapter(String currencySymbol) {
            enableDiffing();
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{new ToolbarSpacerEpoxyModel_(), new SimpleEpoxyModel(C0857R.layout.view_holder_full_width_divider), new MicroSectionHeaderEpoxyModel_().isBold(true).titleRes(C0857R.string.explore_places_categories_header).showDivider(false), this.foodSceneModel, this.drinksModel, this.artsAndCultureModel, this.parksAndNatureModel, this.sightSeeingModel, this.wellnessModel, this.shoppingModel, this.entertainmentAndActivitiesModel});
        }

        private ExploreInlineFiltersToggleRowEpoxyModel_ createTopCategoryModel(ExplorePlacesTopCategory category, boolean withDivider) {
            return new ExploreInlineFiltersToggleRowEpoxyModel_().titleRes(category.getStringRes()).checkChangedListener(ExplorePlacesFiltersFragment$FiltersAdapter$$Lambda$1.lambdaFactory$(this, category)).showDivider(withDivider);
        }

        /* access modifiers changed from: private */
        public void updateFromSearchFilters() {
            this.drinksModel.checked(ExplorePlacesFiltersFragment.this.placesSearchFilters.hasCateory(ExplorePlacesTopCategory.DrinksAndNightlife));
            this.artsAndCultureModel.checked(ExplorePlacesFiltersFragment.this.placesSearchFilters.hasCateory(ExplorePlacesTopCategory.ArtsAndCulture));
            this.parksAndNatureModel.checked(ExplorePlacesFiltersFragment.this.placesSearchFilters.hasCateory(ExplorePlacesTopCategory.ParksAndNature));
            this.sightSeeingModel.checked(ExplorePlacesFiltersFragment.this.placesSearchFilters.hasCateory(ExplorePlacesTopCategory.Sightseeing));
            this.foodSceneModel.checked(ExplorePlacesFiltersFragment.this.placesSearchFilters.hasCateory(ExplorePlacesTopCategory.FoodScene));
            this.wellnessModel.checked(ExplorePlacesFiltersFragment.this.placesSearchFilters.hasCateory(ExplorePlacesTopCategory.Wellness));
            this.shoppingModel.checked(ExplorePlacesFiltersFragment.this.placesSearchFilters.hasCateory(ExplorePlacesTopCategory.Shopping));
            this.entertainmentAndActivitiesModel.checked(ExplorePlacesFiltersFragment.this.placesSearchFilters.hasCateory(ExplorePlacesTopCategory.EntertainmentAndActivities));
            notifyModelsChanged();
        }
    }

    public static ExplorePlacesFiltersFragment newInstance() {
        return new ExplorePlacesFiltersFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0857R.layout.fragment_explore_inline_filters, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.placesSearchFilters == null) {
            this.placesSearchFilters = PlaceFilters.cloneFrom(this.dataController.getPlacesSearchFilters(), false);
        }
        this.adapter = new FiltersAdapter(new CurrencyFormatter(getContext(), this.mAccountManager, this.mPreferences).getLocalCurrencySymbol());
        this.recyclerView.setAdapter(this.adapter);
    }

    public void onStart() {
        super.onStart();
        updateSearchButton();
        this.adapter.updateFromSearchFilters();
        this.placesSearchFilters.addChangeListener(this);
    }

    public void onStop() {
        this.placesSearchFilters.removeChangeListener(this);
        super.onStop();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0857R.C0859id.reset_all) {
            return super.onOptionsItemSelected(item);
        }
        this.placesSearchFilters.reset();
        return true;
    }

    public void onPlaceSearchFiltersChanged() {
        onUpdate();
    }

    public void onTabMetadataUpdated(String tabId) {
        if (Tab.PLACES.getTabId().equals(tabId)) {
            onUpdate();
        }
    }

    private void onUpdate() {
        this.adapter.updateFromSearchFilters();
        updateSearchButton();
    }

    @OnClick
    public void onViewPlacesClicked() {
        this.dataController.cancelMetadataRequest();
        this.dataController.setPlacesSearchFiltersAndFetchTab(this.placesSearchFilters);
        this.exploreNavigationController.popBackStack();
    }

    private void updateSearchButton() {
        this.searchButton.setButtonEnabled(true);
        if (this.dataController.isFetchingTabMetaData()) {
            this.searchButton.setButtonLoading(true);
        } else if (this.dataController.findTab(Tab.PLACES).hasError()) {
            this.searchButton.setButtonLoading(false);
            this.searchButton.setButtonText(C0857R.string.explore_network_error_homes_filter_primary_button);
        } else {
            this.searchButton.setButtonText((CharSequence) getString(C0857R.string.view_places));
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ExploreFilters;
    }
}
