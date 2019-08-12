package com.airbnb.android.p011p3;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.viewcomponents.models.CarouselModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.P3ReviewsRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.airbnb.android.p011p3.models.Floor;
import com.airbnb.android.p011p3.models.Room;
import com.airbnb.android.p011p3.models.RoomPhoto;
import com.airbnb.p027n2.components.decide.select.SelectAmenityItemModel_;
import com.airbnb.p027n2.components.decide.select.SelectBulletListModel_;
import com.airbnb.p027n2.components.decide.select.SelectHomeRoomItemModel_;
import com.airbnb.p027n2.components.decide.select.SelectHomeSummaryRowModel_;
import com.airbnb.p027n2.components.decide.select.SelectHostRowModel_;
import com.airbnb.p027n2.components.decide.select.SelectIconBulletRowModel_;
import com.airbnb.p027n2.components.decide.select.SelectMapInterstitialModel_;
import com.airbnb.p027n2.components.decide.select.SelectMarqueeModel_;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.airbnb.p027n2.utils.MapOptions;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.android.p3.SelectPDPEpoxyController */
public class SelectPDPEpoxyController extends BaseP3EpoxyController {
    private static final int MAP_ZOOM_LEVEL = 14;
    private static final NumCarouselItemsShown NUM_AMENITY_ITEMS_SHOWN = NumCarouselItemsShown.forPhoneWithDefaultScaling(2.3f);
    private static final NumCarouselItemsShown NUM_ROOMS_ITEMS_SHOWN = NumCarouselItemsShown.forPhoneWithDefaultScaling(1.7f);
    private static final int ROOM_CAROUSEL_ITEM_COUNT = 3;
    SelectIconBulletRowModel_ airportLocationBulletRowModel;
    CarouselModel_ amenitiesCarouselModel;
    SectionHeaderEpoxyModel_ amenitiesHeaderModel;
    TextRowEpoxyModel_ descriptionModel;
    LinkActionRowEpoxyModel_ exploreRoomsLinkModel;
    MicroSectionHeaderEpoxyModel_ flexibleCancellationHeaderModel;
    LinkActionRowEpoxyModel_ flexibleCancellationLinkModel;
    TextRowEpoxyModel_ flexibleCancellationTextModel;
    LinkActionRowEpoxyModel_ hostDetailsLinkModel;
    SectionHeaderEpoxyModel_ hostHeaderModel;
    SelectHostRowModel_ hostRowModel;
    MicroSectionHeaderEpoxyModel_ houseRulesHeaderModel;
    LinkActionRowEpoxyModel_ houseRulesLinkModel;
    TextRowEpoxyModel_ houseRulesTextModel;
    SelectIconBulletRowModel_ locationBulletRowModel;
    SelectMapInterstitialModel_ mapEpoxyModel;
    SectionHeaderEpoxyModel_ mapHeaderModel;
    SelectMarqueeModel_ marqueeModel;
    SectionHeaderEpoxyModel_ policiesHeaderModel;
    P3ReviewsRowEpoxyModel_ reviewsRowEpoxyModel;
    CarouselModel_ roomsCarouselModel;
    SelectBulletListModel_ selectDescriptionBulletsModel;
    SectionHeaderEpoxyModel_ selectDescriptionHeaderModel;
    LinkActionRowEpoxyModel_ selectDescriptionLinkModel;
    SelectHomeSummaryRowModel_ summaryRowModel;
    TextRowEpoxyModel_ tagsRowModel;

    public SelectPDPEpoxyController(Context context, ListingDetailsController controller, BusinessTravelAccountManager businessTravelAccountManager, Bundle savedInstanceState) {
        super(context, controller, businessTravelAccountManager, savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        Listing listing = this.controller.getState().listing();
        if (listing != null) {
            this.marqueeModel.title(listing.getName()).image(listing.getPhoto()).homeTourClickListener(SelectPDPEpoxyController$$Lambda$1.lambdaFactory$(this));
            this.descriptionModel.text(listing.getDescription());
            this.summaryRowModel.guestsText("Sleeps 2").bedroomsText("2 bedrooms").bedsText("2 beds").bathroomsText("1.5 bath");
            this.roomsCarouselModel.models(getCarouselRoomItemModels());
            this.exploreRoomsLinkModel.clickListener(SelectPDPEpoxyController$$Lambda$2.lambdaFactory$(this)).text("Explore every room TODO");
            this.selectDescriptionHeaderModel.title("What to expect from every Select home");
            List<String> bulletPoints = new ArrayList<>();
            bulletPoints.add("Professionally inspected");
            bulletPoints.add("Easy self check-in");
            bulletPoints.add("Standard set of amenitites");
            bulletPoints.add("Priority Airbnb support");
            this.selectDescriptionBulletsModel.bulletPoints(bulletPoints);
            this.selectDescriptionLinkModel.text("Learn more about Airbnb Select");
            this.amenitiesHeaderModel.title("Equipped with TODO amenities");
            List<SelectAmenityItemModel_> amenityModels = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                amenityModels.add(new SelectAmenityItemModel_().mo11716id((long) i).image(new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large")).numCarouselItemsShown(NUM_AMENITY_ITEMS_SHOWN).amenityName("Amenity"));
            }
            this.amenitiesCarouselModel.models(amenityModels).showDivider(true);
            this.mapHeaderModel.title("Located in hip and artsy Silver Lake, LA");
            this.tagsRowModel.text("Trendy · Artsy · Views · Sunny · Spread Out · Bungalows · Quiet");
            addMapRow();
            this.airportLocationBulletRowModel.drawableResource(C7532R.C7533drawable.n2_ic_am_dog).text("Distance from Los Angeles Intl. Airport (LAX) - 39 min by car");
            this.locationBulletRowModel.drawableResource(C7532R.C7533drawable.n2_ic_location).text("Exact location provided after booking");
            setUpAndAddReviewRow(this.reviewsRowEpoxyModel);
            this.policiesHeaderModel.title("Policies and Requirements");
            this.flexibleCancellationHeaderModel.title("Flexible cancellation");
            this.flexibleCancellationTextModel.text("Cancel up to 5 days before your trip and get a full refund, including serice fees.");
            this.flexibleCancellationLinkModel.text("See details").showDivider(false);
            this.houseRulesHeaderModel.title("House Rules");
            this.houseRulesTextModel.text("Self check-in after 4pm\nNot suitable for events or parties");
            this.houseRulesLinkModel.text("Read all rules");
            this.hostHeaderModel.title("Hosted by TODO");
            this.hostRowModel.title("Joined April 2015").descriptionText("Your host will be on-site and available for anything you need during your stay.").contactHostButtonText("Contact host").hostImage(new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=profile_x_medium"));
            this.hostDetailsLinkModel.text("Learn more about your TODO");
        }
    }

    private void addMapRow() {
        this.mapEpoxyModel.mapOptions(MapOptions.builder(AppLaunchUtils.isUserInChina()).center(this.controller.getState().listing().getLatLng()).zoom(14).build());
    }

    private List<SelectHomeRoomItemModel_> getCarouselRoomItemModels() {
        List<SelectHomeRoomItemModel_> roomModels = new ArrayList<>(3);
        for (Floor floor : this.controller.getMockHomeLayout().floors()) {
            for (Room room : floor.rooms()) {
                if (roomModels.size() < 3) {
                    roomModels.add(new SelectHomeRoomItemModel_().mo11716id(room.mo11086id()).image(new SimpleImage(((RoomPhoto) room.photos().get(0)).largeUrl(), ((RoomPhoto) room.photos().get(0)).previewEncodedPng())).roomTitle(room.name()).roomDescription(TextUtils.join(this.context.getResources().getString(C7532R.string.comma_separator), room.highlights())).numCarouselItemsShown(NUM_ROOMS_ITEMS_SHOWN).onClickListener(SelectPDPEpoxyController$$Lambda$3.lambdaFactory$(this, room)));
                }
            }
        }
        return roomModels;
    }

    /* access modifiers changed from: protected */
    public String getTagForModelId(long id) {
        if (id == this.marqueeModel.mo11715id()) {
            return "select_marquee";
        }
        if (id == this.descriptionModel.mo11715id()) {
            return "listing_description";
        }
        if (id == this.summaryRowModel.mo11715id()) {
            return "select_summary_row";
        }
        if (id == this.roomsCarouselModel.mo11715id() || id == this.exploreRoomsLinkModel.mo11715id()) {
            return "explore_rooms_carousel";
        }
        if (id == this.amenitiesHeaderModel.mo11715id() || id == this.amenitiesCarouselModel.mo11715id()) {
            return "amenities_carouselm";
        }
        if (id == this.reviewsRowEpoxyModel.mo11715id()) {
            return VerificationsAdapter.VERIFICATION_REVIEWS;
        }
        return null;
    }
}
