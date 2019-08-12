package com.airbnb.android.explore.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.beta.models.guidebook.GuidebookItem;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.enums.FilterRemovalSuggestionType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.instant_promo.InstantPromotionManager;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.Destination;
import com.airbnb.android.core.models.ExplorePromotion;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ExploreSection.ResultType;
import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.core.models.GiftCardPromotion;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.PricingQuote.RateType;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.models.SearchResult;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.models.UrgencyMessage;
import com.airbnb.android.core.models.UrgencyMessageData;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.BannerContainerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DestinationCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.GiftCardPromoEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.GuidebookItemEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.HomeCardChinaEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PosterCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RecommendationCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RecommendationRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SearchParamsRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimilarPlaylistCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.UrgencyEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreComponent.Builder;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.android.explore.presenters.ExploreFeedItemPresenter;
import com.airbnb.android.explore.presenters.ExploreFeedItemPresenter.GoldenTicketEpoxyModel;
import com.airbnb.android.explore.viewcomponents.viewmodels.FilterRemovalSuggestionCardEpoxyModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.FilterSuggestionCardEpoxyModel_;
import com.airbnb.android.lib.ExploreBindings;
import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.components.GiftCardPromo;
import com.airbnb.p027n2.components.Interstitial;
import com.airbnb.p027n2.components.PlaceCard;
import com.airbnb.p027n2.components.PosterCard;
import com.airbnb.p027n2.components.RecommendationCardSquare;
import com.airbnb.p027n2.components.RecommendationRow;
import com.airbnb.p027n2.components.SearchParamsRow;
import com.airbnb.p027n2.utils.ScrollDirectionListener;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class BaseExploreAdapter extends AirEpoxyAdapter {
    public static final int HEADER_POSITION = 0;
    public static final int PHONE_SPAN = 2;
    public static final int TABLET_SPAN = 12;
    protected final Activity activity;
    BusinessTravelAccountManager businessTravelAccountManager;
    protected final ExploreDataController dataController;
    InstantPromotionManager instantPromotionManager;
    protected final ExploreJitneyLogger logger;
    protected final ExploreNavigationController navController;
    private final RecycledViewPool recycledViewPool;

    /* access modifiers changed from: protected */
    public abstract void getPosterCardClickListener(View view, TripTemplate tripTemplate);

    /* access modifiers changed from: protected */
    public abstract List<EpoxyModel<?>> postProcessListings(ExploreSection exploreSection, ExploreSection exploreSection2, int i, int i2, DisplayType displayType, List<EpoxyModel<?>> list);

    /* access modifiers changed from: protected */
    public abstract boolean shouldShowHeader(ExploreSection exploreSection);

    public BaseExploreAdapter(Activity activity2, ExploreDataController dataController2, ExploreNavigationController navController2, ExploreJitneyLogger logger2, RecycledViewPool recycledViewPool2) {
        this(activity2, null, dataController2, navController2, logger2, recycledViewPool2);
    }

    public BaseExploreAdapter(Activity activity2, DebugSettings debugSettings, ExploreDataController dataController2, ExploreNavigationController navController2, ExploreJitneyLogger logger2, RecycledViewPool recycledViewPool2) {
        this.activity = activity2;
        this.dataController = dataController2;
        this.navController = navController2;
        this.logger = logger2;
        this.recycledViewPool = recycledViewPool2;
        enableDiffing();
        setFilterDuplicates(true);
        ((Builder) ((ExploreBindings) CoreApplication.instance(activity2).componentProvider()).exploreComponentProvider().get()).build().inject(this);
    }

    /* access modifiers changed from: protected */
    public void populateWithSections(List<ExploreSection> sections, boolean isForP2) {
        ExploreSection previousSection = null;
        int listingOffset = 0;
        int sectionIndex = 0;
        for (ExploreSection section : sections) {
            if (!isSectionAContinuation(section, previousSection)) {
                listingOffset = 0;
            }
            int sectionIndex2 = sectionIndex + 1;
            this.models.addAll(buildModelsForSection(section, previousSection, listingOffset, sectionIndex, isForP2));
            if (section.getResultType() == ResultType.Listings) {
                listingOffset += section.getListings().size();
            }
            previousSection = section;
            sectionIndex = sectionIndex2;
        }
    }

    /* access modifiers changed from: protected */
    public List<EpoxyModel<?>> buildModelsForSection(ExploreSection section, ExploreSection previousSection, int listingOffset, int sectionIndex, boolean isForP2) {
        String title;
        DisplayType displayType = getDisplayType(section);
        switch (section.getResultType()) {
            case Experiences:
                List<EpoxyModel<?>> experienceItems = new ArrayList<>(section.getTripTemplates().size());
                for (TripTemplate template : section.getTripTemplates()) {
                    experienceItems.add(buildTripTemplateModel(template, displayType, sectionIndex));
                }
                return transformItemsForDisplayType(experienceItems, section, previousSection, sectionIndex);
            case Destinations:
                List<EpoxyModel<?>> destinations = new ArrayList<>(section.getDestinations().size());
                for (Destination destination : section.getDestinations()) {
                    destinations.add(buildDestinationModel(destination, displayType));
                }
                return transformItemsForDisplayType(destinations, section, previousSection, sectionIndex);
            case Promotions:
                ArrayList arrayList = new ArrayList(section.getPromotions().size());
                for (ExplorePromotion explorePromotion : section.getPromotions()) {
                    arrayList.add(buildPromotionModel(explorePromotion));
                }
                return arrayList;
            case GiftCardPromotions:
                ArrayList arrayList2 = new ArrayList(section.getGiftCardPromotions().size());
                for (GiftCardPromotion giftCardPromotion : section.getGiftCardPromotions()) {
                    arrayList2.add(buildGiftCardPromotionModel(giftCardPromotion));
                }
                return transformItemsForVertical(arrayList2, section, null);
            case GuidebookItems:
                ArrayList arrayList3 = new ArrayList(section.getGuidebookItems().size());
                for (GuidebookItem item : section.getGuidebookItems()) {
                    arrayList3.add(buildGuidebookItemModel(item, displayType, sectionIndex));
                }
                return transformItemsForDisplayType(arrayList3, section, previousSection, sectionIndex);
            case Listings:
                List<EpoxyModel<?>> homeCardItems = new ArrayList<>(section.getListings().size());
                if (!isForP2 || !ChinaUtils.useHomeCardChina()) {
                    boolean isPrecisionBroaderThanCity = isPrecisionBroaderThanCity();
                    for (SearchResult result : section.getListings()) {
                        homeCardItems.add(buildListingModel(result, displayType, isPrecisionBroaderThanCity, sectionIndex));
                    }
                } else {
                    for (SearchResult result2 : section.getListings()) {
                        homeCardItems.add(buildListingModelChina(result2, section.isCancellationFullyRefundable(), sectionIndex));
                    }
                }
                return postProcessListings(section, previousSection, listingOffset, sectionIndex, displayType, homeCardItems);
            case RecommendationItems:
                if (DisplayType.Horizontal.equals(displayType)) {
                    ArrayList arrayList4 = new ArrayList(section.getRecommendationItems().size());
                    for (RecommendationItem item2 : section.getRecommendationItems()) {
                        arrayList4.add(buildRecommendationCardModel(item2, displayType));
                    }
                    return transformItemsForDisplayType(arrayList4, section, previousSection, sectionIndex);
                }
                List<List<RecommendationItem>> listOfGroupings = RecommendationItem.collectRecommendationItemRowGroupings(section.getRecommendationItems(), section.getDisplayLayout());
                ArrayList arrayList5 = new ArrayList(listOfGroupings.size());
                int i = 0;
                while (i < listOfGroupings.size()) {
                    arrayList5.add(buildRecommendationRowModel((List) listOfGroupings.get(i), displayType, sectionIndex, i == listOfGroupings.size() + -1));
                    i++;
                }
                return transformItemsForDisplayType(arrayList5, section, previousSection, sectionIndex);
            case Banners:
                if (!FeatureToggles.arePlaylistsEnabled()) {
                    return new ArrayList();
                }
                ArrayList arrayList6 = new ArrayList(Collections.singletonList(new BannerContainerEpoxyModel_().banners(section.getBanners()).m4329id((CharSequence) section.getSectionId()).bannerClickListener(BaseExploreAdapter$$Lambda$1.lambdaFactory$(this, section))));
                return arrayList6;
            case FilterRemovalSuggestions:
                if (!FeatureToggles.showFilterRemovalSuggestions()) {
                    return new ArrayList();
                }
                ArrayList arrayList7 = new ArrayList(Collections.singletonList(new FilterRemovalSuggestionCardEpoxyModel_().title(section.getTitle()).subtitle(section.getSubtitle()).noTopPadding(sectionIndex == 0).items(section.getFilterRemovalSuggestionItems()).m5893id((CharSequence) section.getTitle()).carouselItemClickListener(BaseExploreAdapter$$Lambda$2.lambdaFactory$(this))));
                return arrayList7;
            case InstantPromos:
                if (Trebuchet.launch(TrebuchetKeys.INSTANT_PROMO)) {
                    return this.instantPromotionManager.getForYouTabEpoxyModelsAndLogErfExposure(section.getInstantPromotions());
                }
                return new ArrayList();
            case Playlists:
                ArrayList arrayList8 = new ArrayList(section.getRecommendationItems().size());
                for (RecommendationItem item3 : section.getRecommendationItems()) {
                    arrayList8.add(buildSimilarPlaylistModel(item3, displayType));
                }
                return transformItemsForDisplayType(arrayList8, section, previousSection, sectionIndex);
            case FilterSuggestions:
                if (!FeatureToggles.showFilterSuggestions()) {
                    return new ArrayList();
                }
                EpoxyModel[] epoxyModelArr = new EpoxyModel[1];
                FilterSuggestionCardEpoxyModel_ filterSuggestionCardEpoxyModel_ = new FilterSuggestionCardEpoxyModel_();
                if (TextUtils.isEmpty(section.getTitle())) {
                    title = this.activity.getString(C0857R.string.filter_suggestion_refine_search);
                } else {
                    title = section.getTitle();
                }
                epoxyModelArr[0] = filterSuggestionCardEpoxyModel_.title(title).items(section.getFilterSuggestionItems()).m5905id((CharSequence) section.getSectionId()).carouselItemClickListener(BaseExploreAdapter$$Lambda$3.lambdaFactory$(this));
                return Arrays.asList(epoxyModelArr);
            case UrgencyMessages:
                ArrayList arrayList9 = new ArrayList(section.getUrgencyMessages().size());
                for (UrgencyMessageData message : section.getUrgencyMessages()) {
                    if (FeatureToggles.canShowExperiencesUrgencyMessage(message.getType())) {
                        arrayList9.add(buildUrgencyModel(message));
                    }
                }
                return transformItemsForDisplayType(arrayList9, section, previousSection, sectionIndex);
            case LastSearchParams:
                ArrayList arrayList10 = new ArrayList(section.getLastSearchParams().size());
                for (SavedSearch each : section.getLastSearchParams()) {
                    arrayList10.add(buildLastSearchParamsModel(each));
                }
                return transformItemsForDisplayType(arrayList10, section, previousSection, sectionIndex);
            default:
                return new ArrayList();
        }
    }

    /* access modifiers changed from: protected */
    public List<EpoxyModel<?>> transformItemsForDisplayType(List<EpoxyModel<?>> items, ExploreSection section, ExploreSection previousSection, int sectionIndex) {
        switch (getDisplayType(section)) {
            case Horizontal:
                return transformItemsForCarousel(items, section, sectionIndex);
            case Vertical:
            case Grid:
            case Magazine:
                return transformItemsForVertical(items, section, previousSection);
            default:
                return new ArrayList();
        }
    }

    private List<EpoxyModel<?>> transformItemsForCarousel(List<EpoxyModel<?>> items, ExploreSection section, final int sectionIndex) {
        EpoxyModel<?> carouselLoadingModel = getCarouselLoader(section);
        final String sectionTitle = section.getTitle();
        long carouselId = section.cachedHashCode();
        if (!shouldShowHeader(section)) {
            MTExperiencesCarouselAdapter adapter = new MTExperiencesCarouselAdapter(carouselLoadingModel);
            adapter.setModels(items);
            return Collections.singletonList(new DeprecatedCarouselEpoxyModel_().adapter((EpoxyAdapter) adapter).m4510id(carouselId).viewPool(this.recycledViewPool));
        }
        OnScrollListener onCarouselScrollListener = new ScrollDirectionListener() {
            /* access modifiers changed from: protected */
            public void onScrollEnd(RecyclerView recyclerView, String scrollType) {
                BaseExploreAdapter.this.logger.trackOnCarouselScroll(scrollType, recyclerView, sectionIndex, sectionTitle);
            }
        };
        EpoxyModel<?> headerModel = getHeaderModel(section);
        DeprecatedCarouselEpoxyModel<MTExperiencesCarouselAdapter> carouselModel = new DeprecatedCarouselEpoxyModel_().adapter((EpoxyAdapter) new MTExperiencesCarouselAdapter(carouselLoadingModel)).viewPool(this.recycledViewPool).onScrollListener(onCarouselScrollListener).m4510id(carouselId);
        ((MTExperiencesCarouselAdapter) carouselModel.adapter()).setModels(items);
        return ImmutableList.m1286of(headerModel, carouselModel);
    }

    private List<EpoxyModel<?>> transformItemsForVertical(List<EpoxyModel<?>> items, ExploreSection section, ExploreSection previousSection) {
        if (!isSectionAContinuation(section, previousSection) && shouldShowHeader(section)) {
            items.add(0, getHeaderModel(section));
        }
        return items;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.airbnb.epoxy.EpoxyModel] */
    /* JADX WARNING: type inference failed for: r0v1, types: [com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_] */
    /* JADX WARNING: type inference failed for: r0v2, types: [com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v1, names: [headerEpoxyModel], types: [com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_]
      assigns: [com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_, com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_]
      uses: [com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_, com.airbnb.epoxy.EpoxyModel, com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_]
      mth insns count: 44
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.airbnb.epoxy.EpoxyModel getHeaderModel(com.airbnb.android.core.models.ExploreSection r6) {
        /*
            r5 = this;
            r2 = 0
            if (r6 == 0) goto L_0x005b
            com.airbnb.android.core.models.ExploreSeeAllInfo r3 = r6.getSeeAllInfo()
            if (r3 == 0) goto L_0x005b
            r1 = 1
        L_0x000a:
            com.airbnb.android.core.models.ExploreSection$ResultType r3 = com.airbnb.android.core.models.ExploreSection.ResultType.RecommendationItems
            com.airbnb.android.core.models.ExploreSection$ResultType r4 = r6.getResultType()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0022
            com.airbnb.android.core.models.ExploreSection$ResultType r3 = com.airbnb.android.core.models.ExploreSection.ResultType.Playlists
            com.airbnb.android.core.models.ExploreSection$ResultType r4 = r6.getResultType()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x005d
        L_0x0022:
            com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_ r3 = new com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_
            r3.<init>()
            java.lang.String r4 = r6.getTitle()
            com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_ r3 = r3.title(r4)
            java.lang.String r4 = r6.getSubtitle()
            com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_ r3 = r3.description(r4)
            java.lang.String r4 = r6.getSectionId()
            com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_ r3 = r3.sectionId(r4)
            com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_ r2 = r3.showDivider(r2)
            java.lang.String r3 = r6.getSectionId()
            com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_ r0 = r2.m4560id(r3)
            if (r1 == 0) goto L_0x005a
            int r2 = com.airbnb.android.explore.C0857R.string.see_all
            com.airbnb.android.core.viewcomponents.models.EditorialSectionHeaderEpoxyModel_ r2 = r0.buttonTextRes(r2)
            android.view.View$OnClickListener r3 = r5.getSeeAllOnClickListener(r6)
            r2.buttonOnClickListener(r3)
        L_0x005a:
            return r0
        L_0x005b:
            r1 = r2
            goto L_0x000a
        L_0x005d:
            com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_ r3 = new com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_
            r3.<init>()
            java.lang.String r4 = r6.getTitle()
            com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_ r3 = r3.title(r4)
            java.lang.String r4 = r6.getSubtitle()
            com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_ r3 = r3.description(r4)
            java.lang.String r4 = r6.getSectionId()
            com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_ r3 = r3.sectionId(r4)
            com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_ r2 = r3.showDivider(r2)
            java.lang.String r3 = r6.getSectionId()
            com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_ r0 = r2.m5172id(r3)
            if (r1 == 0) goto L_0x005a
            int r2 = com.airbnb.android.explore.C0857R.string.see_all
            com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_ r2 = r0.buttonTextRes(r2)
            android.view.View$OnClickListener r3 = r5.getSeeAllOnClickListener(r6)
            r2.buttonOnClickListener(r3)
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.explore.adapters.BaseExploreAdapter.getHeaderModel(com.airbnb.android.core.models.ExploreSection):com.airbnb.epoxy.EpoxyModel");
    }

    private EpoxyModel<?> getCarouselLoader(ExploreSection section) {
        switch (section.getResultType()) {
            case Experiences:
                return new PosterCardEpoxyModel_().displayOptions(DisplayOptions.forPosterCard(this.activity, DisplayType.Horizontal)).loading(true);
            case Destinations:
                return new DestinationCardEpoxyModel_().loading(true).displayOptions(DisplayOptions.forDestinationCard(this.activity, DisplayType.Horizontal));
            case Promotions:
                return new InterstitialEpoxyModel_();
            case GiftCardPromotions:
                return new GiftCardPromoEpoxyModel_();
            case GuidebookItems:
                return new GuidebookItemEpoxyModel().loading(true);
            case Listings:
                return new HomeCardEpoxyModel_().displayOptions(DisplayOptions.forHomeCard(this.activity, DisplayType.Horizontal)).loading(true).showDivider(false);
            case RecommendationItems:
                return new RecommendationRowEpoxyModel_().loading(true);
            case Banners:
                return new BannerContainerEpoxyModel_();
            default:
                throw new IllegalArgumentException("Explore carousel created with unknown ResultType");
        }
    }

    private OnClickListener getSeeAllOnClickListener(ExploreSection section) {
        return BaseExploreAdapter$$Lambda$4.lambdaFactory$(this, section);
    }

    static /* synthetic */ void lambda$getSeeAllOnClickListener$3(BaseExploreAdapter baseExploreAdapter, ExploreSection section, View v) {
        ExploreSeeAllInfo seeAllInfo = section.getSeeAllInfo();
        baseExploreAdapter.logger.clickSeeAll(seeAllInfo.getTabId(), seeAllInfo.hasQuery(), seeAllInfo.getQuery().getLocation());
        baseExploreAdapter.dataController.setSeeAllInfo(seeAllInfo);
    }

    /* access modifiers changed from: protected */
    public boolean isSectionAContinuation(ExploreSection section, ExploreSection previousSection) {
        return previousSection != null && previousSection.getResultType() == section.getResultType() && previousSection.getDisplayType() == section.getDisplayType() && isTitleAContinuation(section.getTitle(), previousSection.getTitle());
    }

    private boolean isTitleAContinuation(String sectionTitle, String previousSectionTitle) {
        return TextUtils.isEmpty(sectionTitle) || sectionTitle.equals(previousSectionTitle);
    }

    private EpoxyModel<PosterCard> buildTripTemplateModel(TripTemplate template, DisplayType displayType, int sectionIndex) {
        return new PosterCardEpoxyModel_().tripTemplate(template).clickListener(BaseExploreAdapter$$Lambda$5.lambdaFactory$(this, template)).displayOptions(DisplayOptions.forPosterCard(this.activity, displayType)).wishListableData(addDetailsToWishlistableData(WishListableData.forTrip(template))).m5305id((CharSequence) String.format("%02d", new Object[]{Integer.valueOf(sectionIndex)}), template.getId());
    }

    private WishListableData addDetailsToWishlistableData(WishListableData.Builder data) {
        return data.source(C2813WishlistSource.Explore).searchSessionId(this.dataController.getSearchSessionId()).guestDetails(this.dataController.getTopLevelSearchParams().guestDetails()).checkIn(this.dataController.getTopLevelSearchParams().checkInDate()).checkOut(this.dataController.getTopLevelSearchParams().checkOutDate()).allowAutoAdd(true).build();
    }

    private EpoxyModel<PlaceCard> buildGuidebookItemModel(GuidebookItem guidebookItem, DisplayType displayType, int sectionIndex) {
        return new GuidebookItemEpoxyModel().guidebookItem(guidebookItem).cardClickListener(BaseExploreAdapter$$Lambda$6.lambdaFactory$(this, guidebookItem)).displayOptions(DisplayOptions.forPlaceCard(this.activity, displayType)).showBottomSpace(false).m4680id((CharSequence) guidebookItem.getBoldSubtitle() + guidebookItem.getNonBoldSubtitle() + sectionIndex);
    }

    static /* synthetic */ void lambda$buildGuidebookItemModel$5(BaseExploreAdapter baseExploreAdapter, GuidebookItem guidebookItem, View view) {
        baseExploreAdapter.logger.placesClick(guidebookItem.getId());
        baseExploreAdapter.navController.handleGuidebookItemClick(guidebookItem, baseExploreAdapter.dataController.getUserLocation(), baseExploreAdapter.logger);
    }

    private EpoxyModel<RecommendationRow> buildRecommendationRowModel(List<RecommendationItem> recommendationItems, DisplayType displayType, int sectionIndex, boolean showBottomSpace) {
        return new RecommendationRowEpoxyModel_().recommendationItems(recommendationItems).displayOptions(DisplayOptions.forRecommendationRow(this.activity, displayType)).clickListener(BaseExploreAdapter$$Lambda$7.lambdaFactory$(this, recommendationItems)).showBottomSpace(showBottomSpace).m5424id((CharSequence) String.valueOf(((RecommendationItem) recommendationItems.get(0)).getId()) + sectionIndex);
    }

    private EpoxyModel<RecommendationCardSquare> buildRecommendationCardModel(RecommendationItem item, DisplayType displayType) {
        return new RecommendationCardEpoxyModel_().recommendationItem(item).clickListener(BaseExploreAdapter$$Lambda$8.lambdaFactory$(this, item)).displayOptions(DisplayOptions.forRecommendationCard(this.activity, displayType)).m5410id(item.getId());
    }

    private EpoxyModel<Interstitial> buildPromotionModel(ExplorePromotion promotion) {
        InterstitialEpoxyModel_ model = new InterstitialEpoxyModel_().text(promotion.getTitle()).caption(promotion.getSubtitle()).buttonText(promotion.getCta()).style(promotion.getStyle()).m4968id((CharSequence) promotion.getTitle());
        model.buttonClickListener(BaseExploreAdapter$$Lambda$9.lambdaFactory$(this, promotion, model));
        return model;
    }

    private EpoxyModel<GiftCardPromo> buildGiftCardPromotionModel(GiftCardPromotion promotion) {
        return new GiftCardPromoEpoxyModel_().imageUrl(promotion.getImageUrl()).displayOptions(DisplayOptions.forGiftCardPromoCard(this.activity, DisplayType.Vertical)).isTablet(MiscUtils.isTabletScreen(this.activity)).m4632id((CharSequence) promotion.getImageUrl()).onClickListener(BaseExploreAdapter$$Lambda$10.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void handlePromotionCallToAction(ExplorePromotion promotion, InterstitialEpoxyModel_ model) {
        switch (promotion.getCallToAction()) {
            case TurnOnGps:
                this.dataController.askForGPSPermission();
                return;
            case OptInNotification:
                this.dataController.optIntoNotification(promotion);
                promotion.setCta(null);
                String successString = this.activity.getResources().getString(C0857R.string.opt_in_notification_success);
                promotion.setTitle(successString);
                model.text(successString);
                model.buttonText(null);
                notifyModelsChanged();
                return;
            case ExperienceCategory:
                this.navController.showFilters();
                return;
            case AddDate:
                this.navController.onDatesRowClicked(null);
                return;
            case AddLocation:
                this.navController.onLocationRowClicked(null);
                return;
            default:
                return;
        }
    }

    private EpoxyModel<SearchParamsRow> buildLastSearchParamsModel(SavedSearch savedSearch) {
        Resources res = this.activity.getResources();
        SearchParams searchParams = savedSearch.getSearchParams();
        String formattedDate = this.activity.getResources().getString(C0857R.string.explore_anytime);
        if (searchParams.getCheckin() != null) {
            if (searchParams.getCheckout() != null) {
                formattedDate = searchParams.getCheckin().getDateSpanString((Context) this.activity, searchParams.getCheckout());
            } else {
                formattedDate = searchParams.getCheckin().getDateString(this.activity);
            }
        }
        StringBuilder sb = new StringBuilder(formattedDate);
        int numGuests = searchParams.getGuests();
        if (numGuests > 0) {
            String formattedGuests = res.getQuantityString(C0857R.plurals.x_guests, numGuests, new Object[]{Integer.valueOf(numGuests)});
            sb.append(" · ");
            sb.append(formattedGuests);
        }
        return new SearchParamsRowEpoxyModel_().location(searchParams.getLocation()).clickListener(BaseExploreAdapter$$Lambda$11.lambdaFactory$(this, savedSearch)).details(sb.toString());
    }

    private HomeCardEpoxyModel_ buildListingModel(SearchResult searchResult, DisplayType displayType, boolean isPrecisionBroaderThanCity, int sectionIndex) {
        Listing listing = searchResult.getListing();
        WishListableData wishListableData = addDetailsToWishlistableData(WishListableData.forHome(listing));
        boolean inCarousel = displayType == DisplayType.Horizontal;
        PricingQuote pricingQuote = searchResult.getPricingQuote();
        boolean showDiscount = (pricingQuote.getMonthlyPriceFactor().hasDiscount() || pricingQuote.getWeeklyPriceFactor().hasDiscount()) && pricingQuote.getRateType() == RateType.Monthly && Experiments.showDiscountsOnP2();
        HomeCardEpoxyModel_ model = new HomeCardEpoxyModel_().listing(listing).isPrecisionBroaderThanCity(isPrecisionBroaderThanCity).pricingQuote(pricingQuote).wishListableData(wishListableData).showDivider(false).showDiscountAmount(showDiscount).showListingTitle(!inCarousel || this.dataController.getTopLevelSearchParams().hasSearchTerm()).showLocation(!this.dataController.getTopLevelSearchParams().hasSearchTerm()).clickListener(BaseExploreAdapter$$Lambda$12.lambdaFactory$(this, listing, pricingQuote)).m4729id((CharSequence) String.format("%02d", new Object[]{Integer.valueOf(sectionIndex)}), listing.getId()).allowBusinessTravelReady(this.businessTravelAccountManager.isVerifiedBusinessTraveler());
        model.displayOptions(DisplayOptions.forHomeCard(this.activity, displayType));
        return model;
    }

    private HomeCardChinaEpoxyModel_ buildListingModelChina(SearchResult searchResult, boolean cancellationFullyRefundable, int sectionIndex) {
        boolean z = true;
        Listing listing = searchResult.getListing();
        WishListableData wishListableData = addDetailsToWishlistableData(WishListableData.forHome(listing));
        PricingQuote pricingQuote = searchResult.getPricingQuote();
        HomeCardChinaEpoxyModel_ allowBusinessTravelReady = new HomeCardChinaEpoxyModel_().listing(listing).pricingQuote(pricingQuote).wishListableData(wishListableData).clickListener(BaseExploreAdapter$$Lambda$13.lambdaFactory$(this, listing, pricingQuote)).m4717id((CharSequence) String.format("%02d", new Object[]{Integer.valueOf(sectionIndex)}), listing.getId()).allowBusinessTravelReady(this.businessTravelAccountManager.isVerifiedBusinessTraveler());
        if (!cancellationFullyRefundable || !listing.isFullyRefundable()) {
            z = false;
        }
        return allowBusinessTravelReady.fullyRefundable(z);
    }

    private boolean isPrecisionBroaderThanCity() {
        if (this.dataController.getTopLevelSearchParams().hasMapBounds()) {
            return this.dataController.getTopLevelSearchParams().mapBounds().isBroaderThanCity();
        }
        if (this.dataController.hasHomesMetadata()) {
            return this.dataController.getHomesMetadata().isPrecisionBroaderThanCity();
        }
        return false;
    }

    private EpoxyModel<?> buildDestinationModel(Destination destination, DisplayType displayType) {
        return new DestinationCardEpoxyModel_().photoUrl(destination.getPicture().getLargeUrl()).titleText(destination.getDisplayName()).cardClickListener(BaseExploreAdapter$$Lambda$14.lambdaFactory$(this, destination)).displayOptions(DisplayOptions.forDestinationCard(this.activity, displayType)).m4524id((CharSequence) destination.getDisplayName());
    }

    private EpoxyModel<?> buildSimilarPlaylistModel(RecommendationItem item, DisplayType displayType) {
        return new SimilarPlaylistCardEpoxyModel_().recommendationItem(item).displayOptions(DisplayOptions.forHomeCard(this.activity, displayType)).clickListener(BaseExploreAdapter$$Lambda$15.lambdaFactory$(this, item)).m5566id(item.getId());
    }

    private EpoxyModel<?> buildUrgencyModel(UrgencyMessageData item) {
        UrgencyMessage message = item.getMessage();
        return new UrgencyEpoxyModel_().title(message.getHeadline()).subtitle(message.getBody()).type(item.getType()).contextualMessage(message.getContextualMessage()).showDivider(true).m5748id((CharSequence) item.getMessage().getHeadline());
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position, List<Object> payloads) {
        if (model instanceof GoldenTicketEpoxyModel) {
            ExploreFeedItemPresenter.trackGoldenTicketImpression(model, this.logger);
        } else if (model instanceof UrgencyEpoxyModel_) {
            this.logger.p2UrgencyImpression((UrgencyEpoxyModel_) model);
        } else if (model instanceof EditorialSectionHeaderEpoxyModel_) {
            this.logger.sectionImpression(((EditorialSectionHeaderEpoxyModel_) model).sectionId());
        } else if (model instanceof MicroSectionHeaderEpoxyModel_) {
            this.logger.sectionImpression(((MicroSectionHeaderEpoxyModel_) model).sectionId());
        } else if (model instanceof FilterSuggestionCardEpoxyModel_) {
            this.logger.filterSuggestionImpression((FilterSuggestionCardEpoxyModel_) model);
        } else if (model instanceof FilterRemovalSuggestionCardEpoxyModel_) {
            this.logger.filterRemovalSuggestionsImpression((FilterRemovalSuggestionCardEpoxyModel_) model, this.activity.getResources());
        }
        super.onModelBound(holder, model, position, payloads);
    }

    private DisplayType getDisplayType(ExploreSection section) {
        return adjustDisplayTypeForTablet(section.getDisplayType());
    }

    private DisplayType adjustDisplayTypeForTablet(DisplayType displayType) {
        if (displayType != DisplayType.Vertical || !MiscUtils.isTabletScreen(this.activity)) {
            return displayType;
        }
        return DisplayType.Grid;
    }

    /* access modifiers changed from: private */
    public void handleRecommendationItemClick(RecommendationItem item) {
        handleRecommendationItemClick(item, 0);
    }

    /* access modifiers changed from: private */
    public void handleRecommendationItemClick(RecommendationItem item, int videoPosition) {
        this.navController.handleRecommendationItemClick(item, this.dataController.getTopLevelSearchParams(), this.logger, this.dataController.getForYouMetaData(), videoPosition);
        String sectionId = this.dataController.getSectionIdForRecommendationItem(item);
        ExploreJitneyLogger exploreJitneyLogger = this.logger;
        long id = item.getId();
        String str = item.getItemType().itemType;
        if (sectionId == null) {
            sectionId = "";
        }
        exploreJitneyLogger.recommendationItemClick(id, str, sectionId);
    }

    /* access modifiers changed from: private */
    public void launchP3ForListing(Listing listing, PricingQuote pricingQuote, View v) {
        this.logger.homeClick(listing.getId());
        this.navController.launchP3(v, listing, pricingQuote, this.dataController.getTopLevelSearchParams(), this.dataController.getSearchSessionId(), P3Arguments.FROM_EXPLORE);
    }

    /* access modifiers changed from: protected */
    public void onFilterRemovalSuggestionPillClicked(FilterRemovalSuggestionItem filter) {
        if (filter.getType() == FilterRemovalSuggestionType.Amenity) {
            Amenity amenity = Amenity.forId(filter.getId());
            if (amenity != null) {
                this.dataController.onAmenityRemovalSuggestionClicked(amenity);
                this.logger.amenityRemovalSuggestionClick(amenity, this.activity.getResources());
            }
        } else if (filter.getType() == FilterRemovalSuggestionType.InstantBook) {
            this.dataController.onInstantBookRemovalSuggestionClicked();
            this.logger.instantBookRemovalSuggestionClick(this.activity.getResources());
        }
    }

    /* access modifiers changed from: protected */
    public void onFilterSuggestionPillClicked(FilterSuggestionItem suggestionItem) {
        this.dataController.onFilterSuggestionPillClicked(suggestionItem);
        this.logger.filterSuggestionClick(suggestionItem);
    }
}
