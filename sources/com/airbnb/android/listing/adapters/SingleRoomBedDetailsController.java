package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.airbnb.android.core.enums.BedDetailType;
import com.airbnb.android.core.models.BedType;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.utils.listing.BedDetailsDisplay;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StepperRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import icepick.State;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleRoomBedDetailsController extends AirEpoxyController {
    private static final List<BedDetailType> defaultTypes = ImmutableList.m1291of(BedDetailType.King, BedDetailType.Queen, BedDetailType.Double, BedDetailType.Single, BedDetailType.SofaBed, BedDetailType.Couch, BedDetailType.Crib);
    private static final List<BedDetailType> expandedTypes = ImmutableList.m1290of(BedDetailType.AirMattress, BedDetailType.BunkBed, BedDetailType.FloorMattress, BedDetailType.ToddlerBed, BedDetailType.WaterBed, BedDetailType.Hammock);
    private final Context context;
    DocumentMarqueeEpoxyModel_ header;
    LinkActionRowEpoxyModel_ moreOptionsRow;
    @State
    HashMap<BedDetailType, Integer> newCounts = new HashMap<>();
    private final Map<BedDetailType, Integer> originalCounts;
    private final int roomNumber;
    @State
    boolean showExpanded;

    public SingleRoomBedDetailsController(Context context2, int roomNumber2, ListingRoom room, Bundle savedInstanceState) {
        this.context = context2;
        this.roomNumber = roomNumber2;
        this.newCounts = new HashMap<>();
        for (BedDetailType bedDetailType : BedDetailType.values()) {
            this.newCounts.put(bedDetailType, Integer.valueOf(0));
        }
        if (room != null) {
            for (BedType bedType : room.getBedTypes()) {
                BedDetailType type = bedType.getType();
                this.newCounts.put(type, Integer.valueOf(bedType.getQuantity()));
                this.showExpanded = (bedType.getQuantity() > 0 && expandedTypes.contains(type)) | this.showExpanded;
            }
        }
        this.originalCounts = ImmutableMap.copyOf((Map<? extends K, ? extends V>) this.newCounts);
        onRestoreInstanceState(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.header.titleText((CharSequence) BedDetailsDisplay.getRoomName(this.context, this.roomNumber));
        for (BedDetailType type : defaultTypes) {
            addStepperRow(type);
        }
        this.moreOptionsRow.textRes(C7213R.string.manage_listing_bed_details_more_options_action).clickListener(SingleRoomBedDetailsController$$Lambda$1.lambdaFactory$(this)).addIf(!this.showExpanded, (EpoxyController) this);
        if (this.showExpanded) {
            for (BedDetailType type2 : expandedTypes) {
                addStepperRow(type2);
            }
        }
    }

    static /* synthetic */ void lambda$buildModels$0(SingleRoomBedDetailsController singleRoomBedDetailsController, View v) {
        singleRoomBedDetailsController.showExpanded = true;
        singleRoomBedDetailsController.requestModelBuild();
    }

    private void addStepperRow(BedDetailType type) {
        new StepperRowEpoxyModel_().m5626id((long) type.ordinal()).textRes(type.nameRes).maxValue(5).value(((Integer) this.newCounts.get(type)).intValue()).valueChangedListener(SingleRoomBedDetailsController$$Lambda$2.lambdaFactory$(this, type)).addTo(this);
    }

    static /* synthetic */ void lambda$addStepperRow$1(SingleRoomBedDetailsController singleRoomBedDetailsController, BedDetailType type, int oldValue, int newValue) {
        singleRoomBedDetailsController.newCounts.put(type, Integer.valueOf(newValue));
        singleRoomBedDetailsController.requestModelBuild();
    }

    public boolean hasChanged() {
        return !this.newCounts.equals(this.originalCounts);
    }

    public List<BedType> getChanges() {
        return FluentIterable.from((E[]) BedDetailType.values()).filter(SingleRoomBedDetailsController$$Lambda$3.lambdaFactory$(this)).transform(SingleRoomBedDetailsController$$Lambda$4.lambdaFactory$(this)).toList();
    }

    static /* synthetic */ boolean lambda$getChanges$2(SingleRoomBedDetailsController singleRoomBedDetailsController, BedDetailType type) {
        return !type.equals(BedDetailType.Unknown) && !((Integer) singleRoomBedDetailsController.newCounts.get(type)).equals(singleRoomBedDetailsController.originalCounts.get(type));
    }

    public List<BedType> getNonemptyBeds() {
        return FluentIterable.from((E[]) BedDetailType.values()).filter(SingleRoomBedDetailsController$$Lambda$5.lambdaFactory$(this)).transform(SingleRoomBedDetailsController$$Lambda$6.lambdaFactory$(this)).toList();
    }

    static /* synthetic */ boolean lambda$getNonemptyBeds$3(SingleRoomBedDetailsController singleRoomBedDetailsController, BedDetailType typeEnum) {
        return ((Integer) singleRoomBedDetailsController.newCounts.get(typeEnum)).intValue() != 0;
    }

    /* access modifiers changed from: private */
    public BedType bedTypeFromEnum(BedDetailType typeEnum) {
        BedType result = new BedType();
        result.setType(typeEnum.serverDescKey);
        result.setQuantity(((Integer) this.newCounts.get(typeEnum)).intValue());
        return result;
    }
}
