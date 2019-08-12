package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.ExperienceFilters.OnExperienceSearchFiltersChangedListener;
import com.airbnb.android.core.p008mt.models.C6213ProductType;
import com.airbnb.android.core.p008mt.models.PrimaryCategory;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.viewcomponents.viewmodels.ExploreInlineFiltersToggleRowEpoxyModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.FullSectionDividerEpoxyModel_;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.airbnb.p027n2.primitives.AirmojiEnum;

public class ExploreExperiencesFiltersFragment extends BaseExploreFragment implements OnExperienceSearchFiltersChangedListener {
    private FiltersEpoxyController epoxyController;
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    FixedActionFooter searchButton;
    /* access modifiers changed from: private */
    public ExperienceFilters searchFilters;
    @BindView
    AirToolbar toolbar;

    private final class FiltersEpoxyController extends AirEpoxyController {
        private FiltersEpoxyController() {
        }

        /* access modifiers changed from: protected */
        public void buildModels() {
            boolean z;
            new ToolbarSpacerEpoxyModel_().m5712id((CharSequence) "toolbar").addTo(this);
            new FullSectionDividerEpoxyModel_().m5941id((CharSequence) "divider").addTo(this);
            new MicroSectionHeaderEpoxyModel_().m5170id((long) C0857R.string.experience_types).titleRes(C0857R.string.experience_types).showDivider(false).addTo(this);
            new ExploreInlineFiltersToggleRowEpoxyModel_().m5855id((long) C0857R.string.experiences_filter_immersions).titleRes(C0857R.string.experiences_filter_immersions).subtitleRes(C0857R.string.experiences_filter_immersions_subtitle).checked(ExploreExperiencesFiltersFragment.this.searchFilters.hasProductType(C6213ProductType.IMMERSION)).checkChangedListener(C6342x9b91a909.lambdaFactory$(this)).showDivider(true).addTo(this);
            new ExploreInlineFiltersToggleRowEpoxyModel_().m5855id((long) C0857R.string.experiences_filter_experiences).titleRes(C0857R.string.experiences_filter_experiences).subtitleRes(C0857R.string.experiences_filter_experiences_subtitle).checked(ExploreExperiencesFiltersFragment.this.searchFilters.hasProductType(C6213ProductType.EXPERIENCE)).checkChangedListener(C6343x9b91a90a.lambdaFactory$(this)).showDivider(true);
            new MicroSectionHeaderEpoxyModel_().m5170id((long) C0857R.string.experience_categories).titleRes(C0857R.string.experience_categories).showDivider(true).addTo(this);
            new ExploreInlineFiltersToggleRowEpoxyModel_().m5857id((CharSequence) "social_good").title(socialGoodOnlyFilterTitle()).checked(ExploreExperiencesFiltersFragment.this.searchFilters.isExperienceSocialGoodOnly().booleanValue()).checkChangedListener(C6344x9b91a90b.lambdaFactory$(this)).showDivider(true).addTo(this);
            PrimaryCategory[] categories = PrimaryCategory.values();
            for (int i = 0; i < categories.length; i++) {
                PrimaryCategory primaryCategory = categories[i];
                if (i != categories.length - 1) {
                    z = true;
                } else {
                    z = false;
                }
                addCategoryEpoxyModel(primaryCategory, z);
            }
        }

        private void addCategoryEpoxyModel(PrimaryCategory category, boolean showDivider) {
            String categoryName = ExploreExperiencesFiltersFragment.this.getResources().getString(category.titleRes);
            new ExploreInlineFiltersToggleRowEpoxyModel_().m5858id((CharSequence) categoryName, (long) category.titleRes).title(categoryName).checked(ExploreExperiencesFiltersFragment.this.searchFilters.hasPrimaryCategory(category)).checkChangedListener(C6345x9b91a90c.lambdaFactory$(this, category)).m5855id((long) category.f1083id).showDivider(showDivider).addTo(this);
        }

        private String socialGoodOnlyFilterTitle() {
            return ExploreExperiencesFiltersFragment.this.getString(C0857R.string.experiences_filter_social_good_only) + " " + AirmojiEnum.AIRMOJI_SOCIAL_IMPACT_RIBBON.character;
        }
    }

    public static ExploreExperiencesFiltersFragment newInstance() {
        return new ExploreExperiencesFiltersFragment();
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
        if (this.searchFilters == null) {
            this.searchFilters = ExperienceFilters.cloneFrom(this.dataController.getExperienceSearchFilters(), false);
        }
        this.epoxyController = new FiltersEpoxyController();
        this.recyclerView.setEpoxyController(this.epoxyController);
    }

    public void onStart() {
        super.onStart();
        updateSearchButton();
        this.epoxyController.requestModelBuild();
        this.searchFilters.addChangeListener(this);
    }

    public void onStop() {
        this.searchFilters.removeChangeListener(this);
        super.onStop();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0857R.C0859id.reset_all) {
            return super.onOptionsItemSelected(item);
        }
        resetSearchFilters();
        return true;
    }

    @OnClick
    public void onViewExperiencesClicked() {
        this.dataController.cancelMetadataRequest();
        this.dataController.setExperienceSearchFiltersAndFetchTab(this.searchFilters);
        this.exploreNavigationController.popBackStack();
    }

    /* access modifiers changed from: protected */
    public void resetSearchFilters() {
        this.searchFilters.resetToDefaults();
    }

    public void onSearchParamsUpdated() {
        postUpdateSearchButtonAndFilters();
    }

    public void onExperienceSearchFiltersChanged() {
        this.dataController.fetchExperienceTabMedataDebounced(this.searchFilters);
        postUpdateSearchButtonAndFilters();
    }

    public void onTabMetadataUpdated(String tabId) {
        if (Tab.EXPERIENCE.getTabId().equals(tabId)) {
            postUpdateSearchButtonAndFilters();
        }
    }

    private void postUpdateSearchButtonAndFilters() {
        this.recyclerView.post(ExploreExperiencesFiltersFragment$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$postUpdateSearchButtonAndFilters$0(ExploreExperiencesFiltersFragment exploreExperiencesFiltersFragment) {
        if (exploreExperiencesFiltersFragment.recyclerView != null) {
            exploreExperiencesFiltersFragment.updateSearchButton();
            exploreExperiencesFiltersFragment.epoxyController.requestModelBuild();
        }
    }

    private void updateSearchButton() {
        if (this.dataController.isFetchingTabMetaData()) {
            this.searchButton.setButtonLoading(true);
        } else if (this.dataController.findTab(Tab.EXPERIENCE).hasError()) {
            this.searchButton.setButtonLoading(false);
            this.searchButton.setButtonText(C0857R.string.explore_network_error_experiences_filter_primary_button);
        } else {
            String buttonText = getString(C0857R.string.view_experiences);
            int experiencesCount = this.dataController.getExperiencesCount();
            if (experiencesCount != -1) {
                if (experiencesCount > 100) {
                    buttonText = getString(C0857R.string.view_x_experiences_max, Integer.valueOf(100));
                } else {
                    buttonText = getResources().getQuantityString(C0857R.plurals.view_x_experiences, experiencesCount, new Object[]{Integer.valueOf(experiencesCount)});
                }
            }
            this.searchButton.setButtonLoading(false);
            this.searchButton.setButtonText((CharSequence) buttonText);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ExploreFilters;
    }
}
