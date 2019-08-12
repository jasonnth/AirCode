package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.itinerary.TripEventModel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.android.p3.P3AmenitiesFragment */
public class P3AmenitiesFragment extends P3BaseFragment {
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    /* renamed from: com.airbnb.android.p3.P3AmenitiesFragment$AmenitiesController */
    private class AmenitiesController extends AirEpoxyController {
        private AmenitiesController() {
        }

        /* access modifiers changed from: protected */
        public void buildModels() {
            new DocumentMarqueeEpoxyModel_().m4536id((CharSequence) TripEventModel.HEADER).titleRes(C7532R.string.amenities).addTo(this);
            List<Amenity> familyAmenities = new ArrayList<>();
            for (Amenity amenity : P3AmenitiesFragment.this.controller.getState().listing().getAmenities()) {
                if (amenity.hasDrawableRes()) {
                    if (amenity.isFamilyAmenity()) {
                        familyAmenities.add(amenity);
                    } else {
                        new StandardRowEpoxyModel_().m5602id((long) amenity.f8471id).title(amenity.stringRes).subtitleRes(amenity == Amenity.HandicapAccessible ? C7532R.string.amenity_handicap_subtitle : 0).rowDrawableRes(amenity.getDrawableRes()).addTo(this);
                    }
                }
            }
            if (Trebuchet.launch(TrebuchetKeys.P3_FAMILY_FRIENDLY_AMENITIES) && familyAmenities.size() > 0) {
                new MicroSectionHeaderEpoxyModel_().m5172id((CharSequence) "family_micro_header").titleRes(C7532R.string.family_amenity_category).addTo(this);
                for (Amenity amenity2 : familyAmenities) {
                    new StandardRowEpoxyModel_().m5602id((long) amenity2.f8471id).title(amenity2.stringRes).rowDrawableRes(amenity2.getDrawableRes()).addTo(this);
                }
            }
        }
    }

    public static P3AmenitiesFragment newInstance() {
        return new P3AmenitiesFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_p3_amenities, container, false);
        bindViews(view);
        AmenitiesController amenitiesController = new AmenitiesController();
        amenitiesController.requestModelBuild();
        this.recyclerView.setAdapter(amenitiesController.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ListingAmenities;
    }
}
