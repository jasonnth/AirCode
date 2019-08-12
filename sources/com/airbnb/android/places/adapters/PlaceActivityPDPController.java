package com.airbnb.android.places.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.models.RecommendationSection;
import com.airbnb.android.core.models.Restaurant;
import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.viewcomponents.SimpleAirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RecommendationCardEpoxyModel_;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.ResyState;
import com.airbnb.android.places.viewmodels.LocalPerspectiveEpoxyModel_;
import com.airbnb.android.places.viewmodels.PhotoCarouselMarqueeEpoxyModel_;
import com.airbnb.android.places.viewmodels.PlaceActivityDetailsEpoxyModel_;
import com.airbnb.android.places.viewmodels.PlaceActivityHeaderEpoxyModel_;
import com.airbnb.android.places.viewmodels.PlaceInfoEpoxyModel_;
import com.airbnb.android.places.viewmodels.ResyPartnershipViewEpoxyModel_;
import com.airbnb.android.places.viewmodels.ResyRowEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.epoxy.Typed2AirEpoxyController;
import com.airbnb.p027n2.utils.LatLng;
import com.airbnb.p027n2.utils.MapOptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlaceActivityPDPController extends Typed2AirEpoxyController<PlaceActivity, ResyState> {
    public static final int MAP_ZOOM = 14;
    private final PlaceActivityPDPNavigationController adapterController;
    private final Context context;
    BasicRowEpoxyModel_ feedbackModel;
    EpoxyControllerLoadingModel_ loadingModel;
    LocalPerspectiveEpoxyModel_ localPerspectiveEpoxyModel;
    PhotoCarouselMarqueeEpoxyModel_ photoCarouselModel;
    PlaceActivityDetailsEpoxyModel_ placeActivityDetailsModel;
    PlaceActivityHeaderEpoxyModel_ placeActivityHeaderModel;
    PlaceInfoEpoxyModel_ placeInfoModel;
    DeprecatedCarouselEpoxyModel_<SimpleAirEpoxyAdapter> recommendedCarouselModel;
    MicroSectionHeaderEpoxyModel_ recommendedHeaderModel;
    LocalPerspectiveEpoxyModel_ restaurantInfoModel;
    ResyPartnershipViewEpoxyModel_ resyPartnershipModel;
    ResyRowEpoxyModel_ resyRowModel;

    public interface PlaceActivityPDPNavigationController {
        void goToCallPlace(String str);

        void goToFeedback(PlaceActivity placeActivity);

        void goToHours(Place place);

        void goToMap(Place place);

        void goToMapApp(Place place);

        void goToPlaceWebsite(String str, String str2);

        void onTapRecommendationItem(RecommendationItem recommendationItem, int i);

        void onTapResyChange();

        void onTapTimeSlot(RestaurantAvailability restaurantAvailability);
    }

    public PlaceActivityPDPController(PlaceActivityPDPNavigationController adapterController2, Context context2) {
        this.adapterController = adapterController2;
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(PlaceActivity placeActivity, ResyState resyState) {
        if (placeActivity == null) {
            this.photoCarouselModel.photoUrls(new ArrayList());
            add((EpoxyModel<?>[]) new EpoxyModel[]{this.photoCarouselModel, this.loadingModel});
            return;
        }
        Place place = placeActivity.getPlace();
        this.photoCarouselModel.photoUrls(placeActivity.getCoverPhotoUrls()).addTo(this);
        this.placeActivityHeaderModel.title(placeActivity.getTitle()).boldSubtitle(placeActivity.getBoldSubtitle()).helpText(placeActivity.getSubtitle()).actionKicker(placeActivity.getActionKicker()).addTo(this);
        ResyRowEpoxyModel_ changeClickListener = this.resyRowModel.changeClickListener(PlaceActivityPDPController$$Lambda$1.lambdaFactory$(this));
        PlaceActivityPDPNavigationController placeActivityPDPNavigationController = this.adapterController;
        placeActivityPDPNavigationController.getClass();
        changeClickListener.timeSlotClickListener(PlaceActivityPDPController$$Lambda$2.lambdaFactory$(placeActivityPDPNavigationController)).resyState(resyState).addIf(resyState.showResy(), (EpoxyController) this);
        this.placeActivityDetailsModel.items(placeActivity.getActivityAttributes()).addTo(this);
        this.localPerspectiveEpoxyModel.tagline(placeActivity.getInsiderTagline()).description(placeActivity.getDescription()).sectionTitle(this.context.getString(placeActivity.getMeetup() != null ? C7627R.string.places_meetup_about : C7627R.string.local_perspective)).user(placeActivity.getUser()).addTo(this);
        Restaurant restaurant = placeActivity.getRestaurant();
        if (restaurant != null && !TextUtils.isEmpty(restaurant.getAbout())) {
            this.restaurantInfoModel.sectionTitle(this.context.getString(C7627R.string.resy_about_restaurant, new Object[]{restaurant.getName()})).description(restaurant.getAbout()).addTo(this);
        }
        this.placeInfoModel.place(place).mapOptions(getMapOptions(place)).mapClickListener(PlaceActivityPDPController$$Lambda$3.lambdaFactory$(this, place)).addressClickListener(PlaceActivityPDPController$$Lambda$4.lambdaFactory$(this, place)).phoneClickListener(PlaceActivityPDPController$$Lambda$5.lambdaFactory$(this, place)).hoursClickListener(PlaceActivityPDPController$$Lambda$6.lambdaFactory$(this, place)).websiteClickListener(PlaceActivityPDPController$$Lambda$7.lambdaFactory$(this, place)).addTo(this);
        this.resyPartnershipModel.addIf(restaurant != null, (EpoxyController) this);
        RecommendationSection recommendationSection = placeActivity.getRecommendationSection();
        if (recommendationSection != null && Experiments.enableAndroidRelatedActivitiesCarousel()) {
            List<RecommendationItem> recommendationItems = recommendationSection.getItems();
            if (!ListUtils.isEmpty((Collection<?>) recommendationItems)) {
                this.recommendedHeaderModel.title(recommendationSection.getTitle()).showDivider(false).addTo(this);
                ArrayList<RecommendationCardEpoxyModel_> recommendationModels = new ArrayList<>();
                for (int i = 0; i < recommendationItems.size(); i++) {
                    recommendationModels.add(toEpoxyModel(i, (RecommendationItem) recommendationItems.get(i)));
                }
                SimpleAirEpoxyAdapter recommendedAdapter = new SimpleAirEpoxyAdapter(false);
                recommendedAdapter.setFilterDuplicates(true);
                recommendedAdapter.setModels(recommendationModels);
                this.recommendedCarouselModel.adapter((EpoxyAdapter) recommendedAdapter).addTo(this);
            } else {
                return;
            }
        }
        this.feedbackModel.titleRes(C7627R.string.places_feedback_button).clickListener(PlaceActivityPDPController$$Lambda$8.lambdaFactory$(this, placeActivity)).addTo(this);
    }

    private MapOptions getMapOptions(Place place) {
        return MapOptions.builder(AppLaunchUtils.isUserInChina()).center(LatLng.builder().lat(place.getLat()).lng(place.getLng()).build()).zoom(14).build();
    }

    private RecommendationCardEpoxyModel_ toEpoxyModel(int carouselPositionForLogging, RecommendationItem item) {
        return new RecommendationCardEpoxyModel_().recommendationItem(item).clickListener(PlaceActivityPDPController$$Lambda$9.lambdaFactory$(this, item, carouselPositionForLogging)).displayOptions(DisplayOptions.forRecommendationCard(this.context, DisplayType.Horizontal)).m5410id(item.getId());
    }
}
