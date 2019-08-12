package com.airbnb.android.managelisting.settings;

import android.content.Context;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.AmenityGroup;
import com.airbnb.android.listing.constants.AmenityGroupings;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.google.common.collect.FluentIterable;
import com.google.common.primitives.Ints;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManageListingAmenityListController extends AirEpoxyController {
    private Set<Integer> amenitiesIds;
    private final Context context;
    private final ControllerInterface controllerInterface;
    private final AmenityGroup essentialAmenityGroup;
    private final AmenityGroup familyAmenityGroup;
    private final AmenityGroup homeSafetyAmenityGroup;
    private final AmenityGroup locationAmenityGroup;
    DocumentMarqueeEpoxyModel_ marquee;
    SectionHeaderEpoxyModel_ otherCategoriesHeader;
    private final AmenityGroup selectRequiredAmenityGroup;
    SectionHeaderEpoxyModel_ selectRequiredHeader;
    private final boolean separateSelectSection;
    private final AmenityGroup spacesAmenityGroup;

    public interface ControllerInterface {
        void onRowClicked(int i, AmenityGroup amenityGroup);
    }

    public ManageListingAmenityListController(ControllerInterface controllerInterface2, Context context2, boolean separateSelectSection2, List<Integer> selectRequiredAmenities) {
        this.controllerInterface = controllerInterface2;
        this.context = context2;
        this.separateSelectSection = separateSelectSection2;
        Set<Integer> idsToFilter = null;
        if (separateSelectSection2) {
            idsToFilter = FluentIterable.from((Iterable<E>) selectRequiredAmenities).toSet();
        }
        this.essentialAmenityGroup = new AmenityGroup(filteredAmenities(AmenityGroupings.AMENITIES_CORE_ML, idsToFilter), "essentials");
        this.spacesAmenityGroup = new AmenityGroup(filteredAmenities(AmenityGroupings.AMENITIES_SPACES, idsToFilter), "spaces");
        this.familyAmenityGroup = new AmenityGroup(filteredAmenities(AmenityGroupings.AMENITIES_FAMILY, idsToFilter), "family");
        this.homeSafetyAmenityGroup = new AmenityGroup(filteredAmenities(AmenityGroupings.AMENITIES_HOME_SAFETY, idsToFilter), "homeSafety");
        this.locationAmenityGroup = new AmenityGroup(filteredAmenities(AmenityGroupings.AMENITIES_LOCATION, idsToFilter), "locationAmenities");
        this.selectRequiredAmenityGroup = new AmenityGroup(selectRequiredAmenities, "selectAmenities");
    }

    private Amenity[] filteredAmenities(Amenity[] amenities, Set<Integer> idsToFilter) {
        if (idsToFilter == null || idsToFilter.isEmpty()) {
            return amenities;
        }
        return (Amenity[]) FluentIterable.from((E[]) amenities).filter(ManageListingAmenityListController$$Lambda$1.lambdaFactory$(idsToFilter)).toArray(Amenity.class);
    }

    static /* synthetic */ boolean lambda$filteredAmenities$0(Set idsToFilter, Amenity amenity) {
        return amenity != null && !idsToFilter.contains(Integer.valueOf(amenity.mo58911id()));
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.marquee.titleRes(C7368R.string.manage_listing_details_item_amenities).addTo(this);
        if (this.separateSelectSection) {
            this.selectRequiredHeader.titleRes(C7368R.string.manage_listing_select_required_amenities_section_header).addTo(this);
            addAmenityGroupRow(Integer.valueOf(C7368R.string.collections_listing_select_brand_name), this.selectRequiredAmenityGroup, Integer.valueOf(C7368R.string.collections_listing_select_brand_name));
            this.otherCategoriesHeader.titleRes(C7368R.string.manage_listing_other_amenities_groups_section_header).addTo(this);
        }
        addAmenityGroupRow(Integer.valueOf(C7368R.string.manage_listing_essential_amenities_section_header), this.essentialAmenityGroup, Integer.valueOf(C7368R.string.manage_listing_essential_amenities_section_header));
        addAmenityGroupRow(Integer.valueOf(C7368R.string.manage_listing_amenities_spaces_section_header), this.spacesAmenityGroup, Integer.valueOf(C7368R.string.manage_listing_amenities_spaces_section_header));
        if (FeatureToggles.shouldShowFamilyAmenitiesML()) {
            addAmenityGroupRow(Integer.valueOf(C7368R.string.manage_listing_amenities_family_section_header), this.familyAmenityGroup, Integer.valueOf(C7368R.string.manage_listing_amenities_family_modal_header));
        }
        addAmenityGroupRow(Integer.valueOf(C7368R.string.manage_listing_amenities_home_safety_section_header), this.homeSafetyAmenityGroup, Integer.valueOf(C7368R.string.manage_listing_amenities_home_safety_section_header));
        if (FeatureToggles.shouldShowLocationAmenitiesML()) {
            addAmenityGroupRow(Integer.valueOf(C7368R.string.manage_listing_location_amenities_section_header), this.locationAmenityGroup, Integer.valueOf(C7368R.string.manage_listing_location_amenities_modal_header));
        }
    }

    private void addAmenityGroupRow(Integer titleRes, AmenityGroup amenityGroup, Integer modalTitleRes) {
        new StandardRowEpoxyModel_().m5604id((CharSequence) amenityGroup.getId()).titleRes(titleRes.intValue()).subtitle((CharSequence) getAmenitySubtitle(amenityGroup)).actionText(C7368R.string.manage_listing_amenities_index_edit).clickListener(ManageListingAmenityListController$$Lambda$2.lambdaFactory$(this, modalTitleRes, amenityGroup)).addTo(this);
    }

    public void setAmenitiesIds(int[] amenitiesIds2) {
        this.amenitiesIds = new HashSet(Ints.asList(amenitiesIds2));
        requestModelBuild();
    }

    private String getAmenitySubtitle(AmenityGroup amenityGroup) {
        int count = FluentIterable.from((E[]) amenityGroup.getAmenities()).filter(ManageListingAmenityListController$$Lambda$3.lambdaFactory$(this)).size();
        if (count == 0) {
            return this.context.getResources().getString(C7368R.string.x_amenities_selected_none);
        }
        return this.context.getResources().getQuantityString(C7368R.plurals.x_amenities_selected, count, new Object[]{Integer.valueOf(count)});
    }
}
