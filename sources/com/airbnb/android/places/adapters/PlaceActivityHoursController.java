package com.airbnb.android.places.adapters;

import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.models.HoursForDisplay;
import com.airbnb.android.core.models.PlaceActivityHours;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.Collection;
import java.util.List;

public class PlaceActivityHoursController extends TypedAirEpoxyController<Place> {
    SectionHeaderEpoxyModel_ sectionHeaderModel;
    ToolbarSpacerEpoxyModel_ toolbarSpacerModel;

    /* access modifiers changed from: protected */
    public void buildModels(Place place) {
        PlaceActivityHours openHours = place.getOpenHours();
        Check.notNull(openHours);
        this.toolbarSpacerModel.addTo(this);
        this.sectionHeaderModel.titleRes(C7627R.string.places_hours).addTo(this);
        List<HoursForDisplay> hoursForDisplay = openHours.getHoursForDisplay();
        if (!ListUtils.isEmpty((Collection<?>) hoursForDisplay)) {
            for (int i = 0; i < hoursForDisplay.size(); i++) {
                getRow((HoursForDisplay) hoursForDisplay.get(i)).m4351id((long) i).addTo(this);
            }
        }
    }

    private BasicRowEpoxyModel_ getRow(HoursForDisplay hours) {
        return new BasicRowEpoxyModel_().titleText(hours.getDays()).subtitleText(hours.getTimes()).layout(C7627R.layout.view_holder_basic_row_small_title);
    }
}
