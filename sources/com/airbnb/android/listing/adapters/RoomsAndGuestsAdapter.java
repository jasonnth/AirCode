package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.LegacyBedType;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineTipRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Objects;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.Iterator;
import java.util.List;

public class RoomsAndGuestsAdapter extends AirEpoxyAdapter implements InputAdapter {
    private static final BathroomType DEFAULT_BATHROOM_TYPE = BathroomType.PrivateBathroom;
    @State
    float bathroomCount;
    private final InlineInputRowEpoxyModel_ bathroomCountRow = new InlineInputRowEpoxyModel_().clickListener(RoomsAndGuestsAdapter$$Lambda$7.lambdaFactory$(this));
    private final InlineInputRowEpoxyModel_ bathroomPrivacyRow = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_rooms_and_guests_bathroom_privacy_setting).clickListener(RoomsAndGuestsAdapter$$Lambda$8.lambdaFactory$(this));
    @State
    BathroomType bathroomType;
    private final RadioRowModelManager<BathroomType> bathroomTypeRadioRowModelManager = new RadioRowModelManager<>(new com.airbnb.android.core.utils.RadioRowModelManager.Listener<BathroomType>() {
        public void onValueSelected(BathroomType value) {
            RoomsAndGuestsAdapter.this.bathroomType = value;
            RoomsAndGuestsAdapter.this.listener.logBathroomsSelectType(RoomsAndGuestsAdapter.this.bathroomType);
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            RoomsAndGuestsAdapter.this.notifyModelChanged(model);
        }
    });
    @State
    int bedCount;
    private final InlineInputRowEpoxyModel_ bedCountRow = new InlineInputRowEpoxyModel_().clickListener(RoomsAndGuestsAdapter$$Lambda$4.lambdaFactory$(this));
    private final LinkActionRowEpoxyModel_ bedDetailsRow = new LinkActionRowEpoxyModel_();
    private final InlineTipRowEpoxyModel_ bedDetailsTipRow = new InlineTipRowEpoxyModel_().withNoTopPaddingLayout().textRes(C7213R.string.manage_listing_rooms_and_guests_bed_details_tip);
    private final InlineInputRowEpoxyModel_ bedTypeRow = new InlineInputRowEpoxyModel_().clickListener(RoomsAndGuestsAdapter$$Lambda$5.lambdaFactory$(this));
    @State
    int bedroomCount;
    private final InlineInputRowEpoxyModel_ bedroomCountRow = new InlineInputRowEpoxyModel_().clickListener(RoomsAndGuestsAdapter$$Lambda$3.lambdaFactory$(this));
    private final Context context;
    @State
    LegacyBedType legacyBedType;
    /* access modifiers changed from: private */
    public final Listener listener;
    private final InlineInputRowEpoxyModel_ listingTypeRow = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_rooms_and_guests_listing_type_setting).clickListener(RoomsAndGuestsAdapter$$Lambda$1.lambdaFactory$(this));
    private final Mode mode;
    @State
    int personCapacity;
    private final InlineInputRowEpoxyModel_ personCapacityRow = new InlineInputRowEpoxyModel_().clickListener(RoomsAndGuestsAdapter$$Lambda$6.lambdaFactory$(this));
    private final InlineInputRowEpoxyModel_ propertyTypeRow = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.manage_listing_rooms_and_guests_property_type_setting).clickListener(RoomsAndGuestsAdapter$$Lambda$2.lambdaFactory$(this));
    @State
    int propertyTypeServerKey;
    @State
    int propertyTypeTitleRes;
    @State
    SpaceType spaceType;

    public interface Listener {
        void bedDetails();

        void logBathroomsSelectNum(float f);

        void logBathroomsSelectType(BathroomType bathroomType);

        void logBedroomsNumBeds(int i);

        void logBedroomsNumRooms(int i);

        void logBedroomsSelectBedType(LegacyBedType legacyBedType);

        void logBedroomsSelectNumGuests(int i);
    }

    public enum Mode {
        ManageListing,
        SelectManageListing,
        NonBathrooms,
        BathroomsOnly
    }

    private static boolean isManageListing(Mode mode2) {
        return mode2 == Mode.ManageListing || mode2 == Mode.SelectManageListing;
    }

    private static boolean isDefaultManageListing(Mode mode2) {
        return mode2 == Mode.ManageListing;
    }

    public RoomsAndGuestsAdapter(Mode mode2, Context context2, Listing listing, List<ListingRoom> bedDetails, Bundle savedInstanceState, Listener listener2) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        super(true);
        this.context = context2;
        this.mode = mode2;
        this.listener = listener2;
        enableDiffing();
        if (savedInstanceState == null) {
            this.spaceType = SpaceType.getTypeFromKeyOrDefault(listing.getRoomTypeKey());
            this.propertyTypeServerKey = listing.getPropertyTypeId();
            PropertyType propertyType = PropertyType.getTypeFromKey(listing.getPropertyTypeId());
            if (propertyType.serverDescKey == this.propertyTypeServerKey) {
                this.propertyTypeTitleRes = propertyType.titleId;
            }
            this.bedroomCount = listing.getBedrooms();
            this.bedCount = listing.getBedCount();
            this.legacyBedType = LegacyBedType.getTypeFromKeyOrDefault(listing.getBedTypeCategory());
            this.personCapacity = listing.getPersonCapacity();
            this.bathroomCount = listing.getBathrooms();
            this.bathroomType = (listing.getBathroomType() == null || getBathroomTypeTitleId(listing.getBathroomType()) == 0) ? DEFAULT_BATHROOM_TYPE : listing.getBathroomType();
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        if (this.bedCount == 0 && this.mode == Mode.NonBathrooms) {
            this.bedCount = 1;
        }
        this.listingTypeRow.show(isManageListing(this.mode));
        this.propertyTypeRow.show(isManageListing(this.mode));
        this.bedroomCountRow.show(isDefaultManageListing(this.mode) || this.mode == Mode.NonBathrooms).titleRes(isDefaultManageListing(this.mode) ? C7213R.string.manage_listing_rooms_and_guests_bedroom_count_setting : C7213R.string.lys_dls_bedroom_section_title);
        InlineInputRowEpoxyModel_ inlineInputRowEpoxyModel_ = this.bedCountRow;
        if (isDefaultManageListing(this.mode) || this.mode == Mode.NonBathrooms) {
            z = true;
        } else {
            z = false;
        }
        inlineInputRowEpoxyModel_.show(z).titleRes(isDefaultManageListing(this.mode) ? C7213R.string.manage_listing_rooms_and_guests_bed_count_setting : C7213R.string.lys_dls_bed_section_title);
        this.bedTypeRow.show(shouldShowBedTypeRow()).titleRes(isDefaultManageListing(this.mode) ? C7213R.string.manage_listing_rooms_and_guests_bed_type_setting : C7213R.string.lys_dls_bed_type_section_title);
        this.bedDetailsRow.clickListener(RoomsAndGuestsAdapter$$Lambda$9.lambdaFactory$(listener2));
        updateBedDetailsRows(bedDetails);
        InlineInputRowEpoxyModel_ inlineInputRowEpoxyModel_2 = this.personCapacityRow;
        if (isManageListing(this.mode) || this.mode == Mode.NonBathrooms) {
            z2 = true;
        } else {
            z2 = false;
        }
        inlineInputRowEpoxyModel_2.show(z2).titleRes(isManageListing(this.mode) ? C7213R.string.manage_listing_rooms_and_guests_person_capacity_setting : C7213R.string.lys_dls_how_many_guests_section_title);
        InlineInputRowEpoxyModel_ inlineInputRowEpoxyModel_3 = this.bathroomCountRow;
        if (isDefaultManageListing(this.mode) || this.mode == Mode.BathroomsOnly) {
            z3 = true;
        } else {
            z3 = false;
        }
        inlineInputRowEpoxyModel_3.show(z3).titleRes(isDefaultManageListing(this.mode) ? C7213R.string.manage_listing_rooms_and_guests_bathroom_count_setting : C7213R.string.lys_dls_bathroom_section_title);
        this.bathroomPrivacyRow.show(isDefaultManageListing(this.mode));
        Iterator it = FluentIterable.from((E[]) BathroomType.values()).filter(RoomsAndGuestsAdapter$$Lambda$10.lambdaFactory$(this)).iterator();
        while (it.hasNext()) {
            BathroomType type = (BathroomType) it.next();
            this.bathroomTypeRadioRowModelManager.addRow(new ToggleActionRowEpoxyModel_().titleRes(getBathroomTypeTitleId(type)), type);
        }
        RadioRowModelManager<BathroomType> radioRowModelManager = this.bathroomTypeRadioRowModelManager;
        if (this.mode == Mode.BathroomsOnly) {
            z4 = true;
        } else {
            z4 = false;
        }
        radioRowModelManager.show(z4).setSelectedValue(this.bathroomType);
        updateRows();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new DocumentMarqueeEpoxyModel_().titleRes(getTitleRes()), this.listingTypeRow, this.propertyTypeRow, this.personCapacityRow, this.bedroomCountRow, this.bedCountRow, this.bedTypeRow, this.bedDetailsRow, this.bedDetailsTipRow, this.bathroomCountRow, this.bathroomPrivacyRow});
        addModels(this.bathroomTypeRadioRowModelManager.getModels());
    }

    static /* synthetic */ boolean lambda$new$9(RoomsAndGuestsAdapter roomsAndGuestsAdapter, BathroomType type) {
        return roomsAndGuestsAdapter.getBathroomTypeTitleId(type) != 0;
    }

    public boolean hasChanged(Listing listing) {
        if (!Objects.equal(this.spaceType, SpaceType.getTypeFromKeyOrDefault(listing.getRoomTypeKey())) || !Objects.equal(Integer.valueOf(this.propertyTypeServerKey), Integer.valueOf(listing.getPropertyTypeId())) || !Objects.equal(Integer.valueOf(this.bedroomCount), Integer.valueOf(listing.getBedrooms())) || !Objects.equal(Integer.valueOf(this.bedCount), Integer.valueOf(listing.getBedCount())) || !Objects.equal(this.legacyBedType, LegacyBedType.getTypeFromKeyOrDefault(listing.getBedTypeCategory())) || !Objects.equal(Integer.valueOf(this.personCapacity), Integer.valueOf(listing.getPersonCapacity())) || !Objects.equal(Float.valueOf(this.bathroomCount), Float.valueOf(listing.getBathrooms())) || !Objects.equal(this.bathroomType, SanitizeUtils.defaultIfNull(listing.getBathroomType(), DEFAULT_BATHROOM_TYPE))) {
            return true;
        }
        return false;
    }

    public void updateBedDetailsRows(List<ListingRoom> bedDetails) {
        boolean hasActiveBedDetails;
        boolean z;
        boolean z2 = true;
        if (!ListUtils.ensureNotNull(bedDetails).isEmpty()) {
            hasActiveBedDetails = true;
        } else {
            hasActiveBedDetails = false;
        }
        LinkActionRowEpoxyModel_ textRes = this.bedDetailsRow.textRes(hasActiveBedDetails ? C7213R.string.manage_listing_rooms_and_guests_edit_bed_details_setting : C7213R.string.manage_listing_rooms_and_guests_bed_details_setting);
        if (!isDefaultManageListing(this.mode) || !FeatureToggles.showHostSideBedDetails()) {
            z = false;
        } else {
            z = true;
        }
        textRes.show(z);
        InlineTipRowEpoxyModel_ inlineTipRowEpoxyModel_ = this.bedDetailsTipRow;
        if (!this.bedDetailsRow.isShown() || hasActiveBedDetails) {
            z2 = false;
        }
        inlineTipRowEpoxyModel_.show(z2);
        notifyModelsChanged();
    }

    public void setInputEnabled(boolean enabled) {
        this.bathroomTypeRadioRowModelManager.setRowsEnabled(enabled);
        this.listingTypeRow.enabled(enabled);
        this.propertyTypeRow.enabled(enabled);
        this.bedroomCountRow.enabled(enabled);
        this.bedCountRow.enabled(enabled);
        this.bedTypeRow.enabled(enabled);
        this.bedDetailsRow.enabled(enabled);
        this.personCapacityRow.enabled(enabled);
        this.bathroomCountRow.enabled(enabled);
        this.bathroomPrivacyRow.enabled(enabled);
        notifyModelsChanged();
    }

    public Strap getSettings() {
        Strap strap = new Strap();
        if (this.listingTypeRow.isShown()) {
            strap.mo11639kv(ListingRequestConstants.JSON_ROOM_TYPE_KEY, this.spaceType.serverDescKey);
        }
        if (this.propertyTypeRow.isShown()) {
            strap.mo11637kv(ListingRequestConstants.JSON_PROPERTY_TYPE_KEY, this.propertyTypeServerKey);
        }
        if (this.bedroomCountRow.isShown()) {
            strap.mo11637kv(ListingRequestConstants.JSON_BEDROOMS_KEY, this.bedroomCount);
        }
        if (this.bedCountRow.isShown()) {
            strap.mo11637kv(ListingRequestConstants.JSON_BEDS_KEY, this.bedCount);
        }
        if (this.bedTypeRow.isShown()) {
            strap.mo11639kv(ListingRequestConstants.JSON_BED_TYPE_CATEGORY_KEY, this.legacyBedType.serverDescKey);
        }
        if (this.personCapacityRow.isShown()) {
            strap.mo11637kv(ListingRequestConstants.JSON_PERSON_CAPACITY_KEY, this.personCapacity);
        }
        if (this.bathroomCountRow.isShown()) {
            strap.mo11636kv(ListingRequestConstants.JSON_BATHROOMS_KEY, this.bathroomCount);
        }
        if (this.bathroomPrivacyRow.isShown() || this.bathroomTypeRadioRowModelManager.isShown()) {
            strap.mo11639kv(ListingRequestConstants.JSON_BATHROOM_TYPE_KEY, this.bathroomType.serverKey);
        }
        return strap;
    }

    private boolean shouldShowBedTypeRow() {
        if ((isDefaultManageListing(this.mode) || this.mode == Mode.NonBathrooms) && this.bedCount == 1 && !FeatureToggles.showHostSideBedDetails()) {
            return true;
        }
        return false;
    }

    private int getTitleRes() {
        switch (this.mode) {
            case NonBathrooms:
                return C7213R.string.lys_dls_rooms_and_guests_title;
            case BathroomsOnly:
                return C7213R.string.lys_dls_bathrooms_title;
            default:
                return C7213R.string.manage_listing_rooms_and_guests_title;
        }
    }

    private void updateRows() {
        this.listingTypeRow.inputRes(this.spaceType.titleId);
        this.propertyTypeRow.inputRes(this.propertyTypeTitleRes);
        this.bedroomCountRow.input(ListingTextUtils.createSelectedBedroomCount(this.context, this.bedroomCount));
        this.bedCountRow.input(ListingTextUtils.createCountWithMaxPlus(this.context, this.bedCount, 16));
        this.bedTypeRow.inputRes(getBedTypeTitleId(this.legacyBedType)).show(shouldShowBedTypeRow());
        this.personCapacityRow.input(ListingTextUtils.createCountWithMaxPlus(this.context, this.personCapacity, 16));
        this.bathroomCountRow.input(ListingTextUtils.createCountWithMaxPlus(this.context, this.bathroomCount, 8.0f));
        this.bathroomPrivacyRow.inputRes(getBathroomTypeTitleId(this.bathroomType));
        notifyModelsChanged();
    }

    /* access modifiers changed from: private */
    public void showListingTypeOptions() {
        OptionsMenuFactory.forItems(this.context, (T[]) SpaceType.values()).setTitleResTransformer(RoomsAndGuestsAdapter$$Lambda$11.lambdaFactory$()).setListener(RoomsAndGuestsAdapter$$Lambda$12.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$showListingTypeOptions$11(RoomsAndGuestsAdapter roomsAndGuestsAdapter, SpaceType value) {
        roomsAndGuestsAdapter.spaceType = value;
        roomsAndGuestsAdapter.updateRows();
    }

    /* access modifiers changed from: private */
    public void showPropertyTypeOptions() {
        OptionsMenuFactory.forItems(this.context, (T[]) PropertyType.values()).setTitleResTransformer(RoomsAndGuestsAdapter$$Lambda$13.lambdaFactory$()).setListener(RoomsAndGuestsAdapter$$Lambda$14.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$showPropertyTypeOptions$13(RoomsAndGuestsAdapter roomsAndGuestsAdapter, PropertyType value) {
        roomsAndGuestsAdapter.propertyTypeServerKey = value.serverDescKey;
        roomsAndGuestsAdapter.propertyTypeTitleRes = value.titleId;
        roomsAndGuestsAdapter.updateRows();
    }

    /* access modifiers changed from: private */
    public void showBedroomCountOptions() {
        OptionsMenuFactory.forIntRange(this.context, 0, 10).setTitleTransformer(RoomsAndGuestsAdapter$$Lambda$15.lambdaFactory$(this)).setListener(RoomsAndGuestsAdapter$$Lambda$16.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$showBedroomCountOptions$15(RoomsAndGuestsAdapter roomsAndGuestsAdapter, Integer value) {
        roomsAndGuestsAdapter.bedroomCount = value.intValue();
        roomsAndGuestsAdapter.listener.logBedroomsNumRooms(roomsAndGuestsAdapter.bedroomCount);
        roomsAndGuestsAdapter.updateRows();
    }

    /* access modifiers changed from: private */
    public void showBedCountOptions() {
        OptionsMenuFactory.forIntRange(this.context, 1, 16).setTitleTransformer(RoomsAndGuestsAdapter$$Lambda$17.lambdaFactory$(this)).setListener(RoomsAndGuestsAdapter$$Lambda$18.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$showBedCountOptions$17(RoomsAndGuestsAdapter roomsAndGuestsAdapter, Integer value) {
        roomsAndGuestsAdapter.bedCount = value.intValue();
        roomsAndGuestsAdapter.listener.logBedroomsNumBeds(roomsAndGuestsAdapter.bedCount);
        roomsAndGuestsAdapter.updateRows();
    }

    /* access modifiers changed from: private */
    public void showBedTypeOptions() {
        OptionsMenuFactory.forItems(this.context, (T[]) LegacyBedType.values()).setTitleResTransformer(RoomsAndGuestsAdapter$$Lambda$19.lambdaFactory$(this)).setListener(RoomsAndGuestsAdapter$$Lambda$20.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$showBedTypeOptions$18(RoomsAndGuestsAdapter roomsAndGuestsAdapter, LegacyBedType value) {
        roomsAndGuestsAdapter.legacyBedType = value;
        roomsAndGuestsAdapter.listener.logBedroomsSelectBedType(roomsAndGuestsAdapter.legacyBedType);
        roomsAndGuestsAdapter.updateRows();
    }

    /* access modifiers changed from: private */
    public void showPersonCapacityOptions() {
        OptionsMenuFactory.forIntRange(this.context, 1, 16).setTitleTransformer(RoomsAndGuestsAdapter$$Lambda$21.lambdaFactory$(this)).setListener(RoomsAndGuestsAdapter$$Lambda$22.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$showPersonCapacityOptions$20(RoomsAndGuestsAdapter roomsAndGuestsAdapter, Integer value) {
        roomsAndGuestsAdapter.personCapacity = value.intValue();
        roomsAndGuestsAdapter.listener.logBedroomsSelectNumGuests(roomsAndGuestsAdapter.personCapacity);
        roomsAndGuestsAdapter.updateRows();
    }

    /* access modifiers changed from: private */
    public void showBathroomCountOptions() {
        OptionsMenuFactory.forItems(this.context, (T[]) ListingRequestConstants.BATHROOM_COUNT_OPTIONS).setTitleTransformer(RoomsAndGuestsAdapter$$Lambda$23.lambdaFactory$(this)).setListener(RoomsAndGuestsAdapter$$Lambda$24.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$showBathroomCountOptions$22(RoomsAndGuestsAdapter roomsAndGuestsAdapter, Float value) {
        roomsAndGuestsAdapter.bathroomCount = value.floatValue();
        roomsAndGuestsAdapter.listener.logBathroomsSelectNum(roomsAndGuestsAdapter.bathroomCount);
        roomsAndGuestsAdapter.updateRows();
    }

    /* access modifiers changed from: private */
    public void showBathroomPrivacyOptions() {
        OptionsMenuFactory.forItems(this.context, FluentIterable.from((E[]) BathroomType.values()).filter(RoomsAndGuestsAdapter$$Lambda$25.lambdaFactory$(this)).toList()).setTitleResTransformer(RoomsAndGuestsAdapter$$Lambda$26.lambdaFactory$(this)).setListener(RoomsAndGuestsAdapter$$Lambda$27.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ boolean lambda$showBathroomPrivacyOptions$23(RoomsAndGuestsAdapter roomsAndGuestsAdapter, BathroomType type) {
        return roomsAndGuestsAdapter.getBathroomTypeTitleId(type) != 0;
    }

    static /* synthetic */ void lambda$showBathroomPrivacyOptions$24(RoomsAndGuestsAdapter roomsAndGuestsAdapter, BathroomType value) {
        roomsAndGuestsAdapter.bathroomType = value;
        roomsAndGuestsAdapter.updateRows();
    }

    /* access modifiers changed from: private */
    public int getBathroomTypeTitleId(BathroomType bathroomType2) {
        switch (bathroomType2) {
            case PrivateBathroom:
                return isManageListing(this.mode) ? C7213R.string.f9711xe52ae61f : C7213R.string.lys_dls_bathroom_type_private_bathroom_selection;
            case SharedBathroom:
                return isManageListing(this.mode) ? C7213R.string.f9712xdc55aced : C7213R.string.lys_dls_bathroom_type_shared_bathroom_selection;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: private */
    public int getBedTypeTitleId(LegacyBedType bedType) {
        switch (bedType) {
            case RealBed:
                return C7213R.string.bed_type_real_bed_selection;
            case PullOutSofa:
                return C7213R.string.bed_type_sofa_selection;
            case AirBed:
                return C7213R.string.bed_type_airbed_selection;
            case Futon:
                return C7213R.string.bed_type_futon_selection;
            case Couch:
                return C7213R.string.bed_type_couch_selection;
            default:
                return 0;
        }
    }
}
