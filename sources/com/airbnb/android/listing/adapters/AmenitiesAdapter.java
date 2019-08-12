package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.listing.AmenityGroup;
import com.airbnb.android.listing.C7213R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import icepick.State;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AmenitiesAdapter extends AirEpoxyAdapter implements InputAdapter {
    private final AmenityGroup[] amenityGroups;
    @State
    HashMap<Integer, Boolean> amenityMap;

    public AmenitiesAdapter(int titleRes, Listing listing, Bundle savedInstanceState, AmenityGroup... amenityGroups2) {
        super(true);
        enableDiffing();
        this.amenityGroups = amenityGroups2;
        if (savedInstanceState == null) {
            this.amenityMap = createAmenityMap(listing);
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(titleRes));
        for (AmenityGroup amenityGroup : amenityGroups2) {
            if (amenityGroup.getTitleRes() != 0) {
                addModel(createSectionModel(amenityGroup.getTitleRes()));
            }
            addModels((Collection<? extends EpoxyModel<?>>) createAmenitiesRows(amenityGroup.getAmenities(), listing));
        }
    }

    public int[] getSelectedAmenities(Listing listing) {
        return Ints.toArray(FluentIterable.from((Iterable<E>) this.amenityMap.keySet()).filter(AmenitiesAdapter$$Lambda$2.lambdaFactory$(this)).append((Iterable<? extends E>) FluentIterable.from((Iterable<E>) listing.getAmenityIds()).filter(AmenitiesAdapter$$Lambda$1.lambdaFactory$(this))).toList());
    }

    static /* synthetic */ boolean lambda$getSelectedAmenities$0(AmenitiesAdapter amenitiesAdapter, Integer amenityId) {
        return !amenitiesAdapter.amenityMap.containsKey(amenityId);
    }

    public boolean hasChanged(Listing listing) {
        HashMap<Integer, Boolean> existingAmenityMap = createAmenityMap(listing);
        return !FluentIterable.from((Iterable<E>) existingAmenityMap.keySet()).allMatch(AmenitiesAdapter$$Lambda$3.lambdaFactory$(this, existingAmenityMap));
    }

    public void setInputEnabled(boolean enabled) {
        Iterator it = FluentIterable.from((Iterable<E>) this.models).filter(AmenitiesAdapter$$Lambda$4.lambdaFactory$()).transform(AmenitiesAdapter$$Lambda$5.lambdaFactory$()).iterator();
        while (it.hasNext()) {
            SwitchRowEpoxyModel_ switchModel = (SwitchRowEpoxyModel_) it.next();
            switchModel.enabled(enabled);
            notifyModelChanged(switchModel);
        }
    }

    static /* synthetic */ boolean lambda$setInputEnabled$3(EpoxyModel model) {
        return model instanceof SwitchRowEpoxyModel_;
    }

    static /* synthetic */ SwitchRowEpoxyModel_ lambda$setInputEnabled$4(EpoxyModel model) {
        return (SwitchRowEpoxyModel_) model;
    }

    private SectionHeaderEpoxyModel_ createSectionModel(int titleRes) {
        return new SectionHeaderEpoxyModel_().titleRes(titleRes);
    }

    private List<SwitchRowEpoxyModel_> createAmenitiesRows(Amenity[] amenities, Listing listing) {
        return FluentIterable.from((E[]) amenities).filter(AmenitiesAdapter$$Lambda$6.lambdaFactory$(this, listing)).transform(AmenitiesAdapter$$Lambda$7.lambdaFactory$(this)).toList();
    }

    /* access modifiers changed from: private */
    public boolean shouldShowAmenity(Amenity amenity, Listing listing) {
        boolean z = false;
        SpaceType listingType = listing.getSpaceType();
        switch (amenity) {
            case BedroomDoorLock:
                if (listingType != SpaceType.PrivateRoom) {
                    return false;
                }
                return true;
            case PrivateEntrance:
                if (listingType == SpaceType.PrivateRoom || listingType == SpaceType.EntireHome) {
                    z = true;
                }
                return z;
            case PrivateLivingRoom:
                if (listingType != SpaceType.PrivateRoom) {
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    /* access modifiers changed from: private */
    public SwitchRowEpoxyModel_ createAmenityRow(Amenity amenity) {
        return new SwitchRowEpoxyModel_().m5674id((long) amenity.f8471id).titleRes(amenity.stringRes).descriptionRes(getAmenityDescription(amenity)).style(SwitchStyle.Filled).checked(((Boolean) this.amenityMap.get(Integer.valueOf(amenity.mo58911id()))).booleanValue()).checkedChangeListener(AmenitiesAdapter$$Lambda$8.lambdaFactory$(this, amenity));
    }

    static /* synthetic */ void lambda$createAmenityRow$6(AmenitiesAdapter amenitiesAdapter, Amenity amenity, SwitchRowInterface view, boolean isChecked) {
        Boolean bool = (Boolean) amenitiesAdapter.amenityMap.put(Integer.valueOf(amenity.mo58911id()), Boolean.valueOf(isChecked));
    }

    private static int getAmenityDescription(Amenity amenity) {
        switch (amenity) {
            case PrivateEntrance:
                return C7213R.string.amenity_host_description_private_entrance;
            case WirelessInternet:
                return C7213R.string.amenity_host_description_wireless_internet;
            case Pool:
                return C7213R.string.amenity_host_description_pool;
            case Kitchen:
                return C7213R.string.amenity_host_description_kitchen;
            case Gym:
                return C7213R.string.amenity_host_description_gym;
            case Heating:
                return C7213R.string.amenity_host_description_heating;
            case Washer:
                return C7213R.string.amenity_host_description_washer;
            case Dryer:
                return C7213R.string.amenity_host_description_dryer;
            case Essentials:
                return C7213R.string.amenity_host_description_essentials;
            case SmokeDetector:
                return C7213R.string.amenity_host_description_smoke_detector;
            case CarbonMonoxideDetector:
                return C7213R.string.amenity_host_description_carbon_monoxide_detector;
            case TwentyFourHourCheckIn:
                return C7213R.string.amenity_host_description_twenty_four_hour_checkin;
            case HandicapAccessible:
                return C7213R.string.amenity_host_description_handicap_accessible;
            case LaptopFriendly:
                return C7213R.string.amenity_host_description_desk_workspace;
            case SafetyCard:
                return C7213R.string.amenity_host_description_safety_card;
            default:
                return 0;
        }
    }

    private HashMap<Integer, Boolean> createAmenityMap(Listing listing) {
        Set<Integer> listingAmenities = new HashSet<>(listing.getAmenityIds());
        FluentIterable<Amenity> targetAmenities = FluentIterable.from((E[]) this.amenityGroups).transformAndConcat(AmenitiesAdapter$$Lambda$9.lambdaFactory$()).filter(AmenitiesAdapter$$Lambda$10.lambdaFactory$(this, listing));
        HashMap<Integer, Boolean> targetAmenityMap = Maps.newHashMap();
        Iterator it = targetAmenities.iterator();
        while (it.hasNext()) {
            Amenity amenity = (Amenity) it.next();
            targetAmenityMap.put(Integer.valueOf(amenity.mo58911id()), Boolean.valueOf(listingAmenities.contains(Integer.valueOf(amenity.mo58911id()))));
        }
        return targetAmenityMap;
    }
}
