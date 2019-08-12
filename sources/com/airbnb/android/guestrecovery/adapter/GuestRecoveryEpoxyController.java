package com.airbnb.android.guestrecovery.adapter;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.core.viewcomponents.models.InfoRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ListingsTrayEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.guestrecovery.C6392R;
import com.airbnb.android.guestrecovery.logging.GuestRecoveryLogger;
import com.airbnb.android.guestrecovery.utils.GuestRecoveryUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.components.TagsCollectionRow.TagRowItem;
import com.airbnb.p027n2.components.TagsCollectionRowModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GuestRecoveryEpoxyController extends AirEpoxyController {
    private AirbnbAccountManager accountManager;
    private final SimpleDateFormat checkInOutDateFormat;
    private Context context;
    SubsectionDividerEpoxyModel_ dividerModel;
    InfoRowEpoxyModel_ goToTripDetailsRowModel;
    private boolean hasSetReservation;
    private boolean hasSetSimilarListings;
    EpoxyControllerLoadingModel_ loadingModel;
    private GuestRecoveryLogger logger;
    DocumentMarqueeEpoxyModel_ marqueeModel;
    private Reservation reservation;
    private ReservationStatus reservationStatus;
    private List<SimilarListing> similarListings;
    ListingsTrayEpoxyModel_ similarListingsModel;
    TagsCollectionRowModel_ tagsCollectionRowModel;

    public GuestRecoveryEpoxyController(Context context2, Reservation reservation2, List<SimilarListing> similarListings2, ReservationStatus reservationStatus2, boolean hasSetSimilarListings2, boolean hasSetReservation2, GuestRecoveryLogger logger2, AirbnbAccountManager accountManager2) {
        this.context = context2;
        this.reservation = reservation2;
        this.similarListings = similarListings2;
        this.reservationStatus = reservationStatus2;
        this.hasSetSimilarListings = hasSetSimilarListings2;
        this.hasSetReservation = hasSetReservation2;
        this.logger = logger2;
        this.accountManager = accountManager2;
        this.checkInOutDateFormat = new SimpleDateFormat(context2.getString(C6392R.string.date_name_format));
    }

    public void setReservation(Reservation reservation2) {
        this.reservation = reservation2;
        this.hasSetReservation = true;
        if (reservation2 != null) {
            this.logger.rejectionImpression(GuestRecoveryUtils.getUserId(this.accountManager), GuestRecoveryUtils.getListingId(reservation2), GuestRecoveryUtils.getReservationCode(reservation2));
        }
    }

    public void setSimilarListings(ArrayList<SimilarListing> listings) {
        this.similarListings = listings;
        this.hasSetSimilarListings = true;
        if (!ListUtils.isEmpty((Collection<?>) listings)) {
            this.logger.similarListingsImpression(GuestRecoveryUtils.getUserId(this.accountManager), GuestRecoveryUtils.getListingId(this.reservation), GuestRecoveryUtils.getSimilarListingIds(this.similarListings), GuestRecoveryUtils.getReservationCode(this.reservation));
        }
    }

    public void handleError() {
        setReservation(this.reservation);
        setSimilarListings(new ArrayList());
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        boolean z;
        String cancellationCaption;
        CharSequence fromHtmlSafe;
        this.marqueeModel.titleRes(this.reservationStatus == ReservationStatus.Denied ? C6392R.string.rejection_title : C6392R.string.cancellation_title);
        if (this.reservation != null) {
            String location = this.reservation.getListing().getLocalizedCity();
            if (isHostCancelCouponAvailable()) {
                cancellationCaption = this.context.getString(C6392R.string.cancellation_subtitle_not_refunded, new Object[]{location, this.reservation.getHostCancellationRefundDetails().getCouponBonusFormatted()});
            } else {
                cancellationCaption = this.context.getString(C6392R.string.cancellation_subtitle_refunded, new Object[]{location});
            }
            DocumentMarqueeEpoxyModel_ documentMarqueeEpoxyModel_ = this.marqueeModel;
            if (this.reservationStatus == ReservationStatus.Denied) {
                fromHtmlSafe = this.context.getString(C6392R.string.rejection_subtitle, new Object[]{location});
            } else {
                fromHtmlSafe = TextUtil.fromHtmlSafe(cancellationCaption);
            }
            documentMarqueeEpoxyModel_.captionText(fromHtmlSafe);
            this.dividerModel.addTo(this);
            if (this.hasSetSimilarListings) {
                if (this.similarListings != null && !this.similarListings.isEmpty()) {
                    populateSimilarListingsModel();
                    populateTagsListModel();
                }
                populateGoToTripDetailsModel();
            }
        }
        EpoxyControllerLoadingModel_ epoxyControllerLoadingModel_ = this.loadingModel;
        if (!this.hasSetReservation || !this.hasSetSimilarListings) {
            z = true;
        } else {
            z = false;
        }
        epoxyControllerLoadingModel_.addIf(z, (EpoxyController) this);
    }

    private void populateSimilarListingsModel() {
        String cancellationSubtitle;
        CharSequence fromHtmlSafe;
        this.similarListingsModel.showSmallTitle(true).items(ListingTrayItem.fromSimilarListings(this.similarListings, C2813WishlistSource.HomeDetail));
        this.similarListingsModel.titleRes(this.reservationStatus == ReservationStatus.Denied ? C6392R.string.rejection_similar_title : C6392R.string.cancellation_similar_title);
        if (isHostCancelCouponAvailable()) {
            cancellationSubtitle = this.context.getString(C6392R.string.cancellation_similar_subtitle, new Object[]{this.reservation.getHostCancellationRefundDetails().getTotalRefundFormatted()});
        } else {
            cancellationSubtitle = this.context.getString(C6392R.string.cancellation_similar_subtitle_refunded);
        }
        ListingsTrayEpoxyModel_ listingsTrayEpoxyModel_ = this.similarListingsModel;
        if (this.reservationStatus == ReservationStatus.Denied) {
            fromHtmlSafe = this.context.getString(C6392R.string.rejection_similar_subtitle);
        } else {
            fromHtmlSafe = TextUtil.fromHtmlSafe(cancellationSubtitle);
        }
        listingsTrayEpoxyModel_.subtitle(fromHtmlSafe);
        this.similarListingsModel.carouselItemClickListener(GuestRecoveryEpoxyController$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$populateSimilarListingsModel$0(GuestRecoveryEpoxyController guestRecoveryEpoxyController, View view, ListingTrayItem item) {
        guestRecoveryEpoxyController.launchP3FromSimilarListings(item);
        guestRecoveryEpoxyController.logger.similarListingsClick(GuestRecoveryUtils.getUserId(guestRecoveryEpoxyController.accountManager), GuestRecoveryUtils.getListingId(guestRecoveryEpoxyController.reservation), GuestRecoveryUtils.getSimilarListingIds(guestRecoveryEpoxyController.similarListings), GuestRecoveryUtils.getReservationCode(guestRecoveryEpoxyController.reservation));
    }

    private void populateTagsListModel() {
        String string;
        ArrayList<TagRowItem> tagRow = new ArrayList<>();
        int guestCount = this.reservation.getGuestCount();
        Listing listing = this.reservation.getListing();
        tagRow.add(new TagRowItem(getFormattedDate(), C6392R.C6393drawable.n2_ic_dates));
        tagRow.add(new TagRowItem(this.context.getResources().getQuantityString(C6392R.plurals.guest_tag_count, guestCount, new Object[]{Integer.valueOf(guestCount)}), C6392R.C6393drawable.n2_ic_guest_icon));
        tagRow.add(new TagRowItem(listing.getLocalizedCity(), C6392R.C6393drawable.n2_ic_location));
        String roomType = listing.getRoomType();
        C6120RoomType roomTypeEnum = C6120RoomType.fromKey(roomType);
        if (roomTypeEnum != null) {
            tagRow.add(new TagRowItem(roomType, getRoomTypeIcon(roomTypeEnum)));
        }
        TagsCollectionRowModel_ showDivider = this.tagsCollectionRowModel.tags(tagRow).showDivider(true);
        if (this.reservationStatus == ReservationStatus.Denied) {
            string = this.context.getString(C6392R.string.rejection_tags_title);
        } else {
            string = this.context.getString(C6392R.string.cancellation_tags_title);
        }
        showDivider.title(string);
    }

    private void populateGoToTripDetailsModel() {
        String string;
        InfoRowEpoxyModel_ infoRowEpoxyModel_ = this.goToTripDetailsRowModel;
        if (this.reservationStatus == ReservationStatus.Denied) {
            string = this.context.getString(C6392R.string.rejection_details);
        } else {
            string = this.context.getString(C6392R.string.cancellation_details);
        }
        infoRowEpoxyModel_.title(string);
        this.goToTripDetailsRowModel.clickListener(GuestRecoveryEpoxyController$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$populateGoToTripDetailsModel$1(GuestRecoveryEpoxyController guestRecoveryEpoxyController, View v) {
        guestRecoveryEpoxyController.launchReactNativeRO(guestRecoveryEpoxyController.reservation.getConfirmationCode());
        guestRecoveryEpoxyController.logger.tripDetailsClick(GuestRecoveryUtils.getUserId(guestRecoveryEpoxyController.accountManager), GuestRecoveryUtils.getListingId(guestRecoveryEpoxyController.reservation), GuestRecoveryUtils.getReservationCode(guestRecoveryEpoxyController.reservation));
    }

    private void launchReactNativeRO(String confirmationCode) {
        this.context.startActivity(ReactNativeIntents.intentForItineraryCheckinCard(this.context, confirmationCode));
    }

    private void launchP3FromSimilarListings(ListingTrayItem item) {
        this.context.startActivity(P3ActivityIntents.withListingAndPricingQuoteAndPastReservationData(this.context, item.listing, item.price, this.reservation.getCheckinDate(), this.reservation.getCheckoutDate(), this.reservation.getGuestDetails()));
    }

    private boolean isHostCancelCouponAvailable() {
        if (this.reservation.getHostCancellationRefundDetails() != null) {
            return this.reservation.getHostCancellationRefundDetails().hasHostCancelCoupon();
        }
        return false;
    }

    private String getFormattedDate() {
        String checkInDate = this.reservation.getCheckinDate().formatDate((DateFormat) this.checkInOutDateFormat);
        String checkOutDate = this.reservation.getCheckoutDate().formatDate((DateFormat) this.checkInOutDateFormat);
        return this.context.getString(C6392R.string.dates_tag, new Object[]{checkInDate, checkOutDate});
    }

    private int getRoomTypeIcon(C6120RoomType roomType) {
        switch (roomType) {
            case PrivateRoom:
                return C6392R.C6393drawable.n2_ic_private_room;
            case SharedRoom:
                return C6392R.C6393drawable.n2_ic_shared_room;
            default:
                return C6392R.C6393drawable.n2_ic_entire_home;
        }
    }
}
