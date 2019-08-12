package com.airbnb.android.explore.views;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;

public class MTTripsTabletSearchView_ViewBinding implements Unbinder {
    private MTTripsTabletSearchView target;

    public MTTripsTabletSearchView_ViewBinding(MTTripsTabletSearchView target2) {
        this(target2, target2);
    }

    public MTTripsTabletSearchView_ViewBinding(MTTripsTabletSearchView target2, View source) {
        this.target = target2;
        target2.searchFieldsContainer = Utils.findRequiredView(source, C0857R.C0859id.search_fields_container, "field 'searchFieldsContainer'");
        target2.locationRow = (MTSearchInputField) Utils.findRequiredViewAsType(source, C0857R.C0859id.location_row, "field 'locationRow'", MTSearchInputField.class);
        target2.datesRow = (MTSearchInputField) Utils.findRequiredViewAsType(source, C0857R.C0859id.dates_row, "field 'datesRow'", MTSearchInputField.class);
        target2.guestsRow = (MTSearchInputField) Utils.findRequiredViewAsType(source, C0857R.C0859id.guests_row, "field 'guestsRow'", MTSearchInputField.class);
        target2.separator1 = Utils.findRequiredView(source, C0857R.C0859id.separator_1, "field 'separator1'");
        target2.separator2 = Utils.findRequiredView(source, C0857R.C0859id.separator_2, "field 'separator2'");
        Context context = source.getContext();
        target2.whiteColor = ContextCompat.getColor(context, C0857R.color.white);
        target2.mainTextColor = ContextCompat.getColor(context, C0857R.color.n2_text_color_main);
        target2.foggyColor = ContextCompat.getColor(context, C0857R.color.n2_foggy);
        target2.white60Color = ContextCompat.getColor(context, C0857R.color.n2_white_60);
    }

    public void unbind() {
        MTTripsTabletSearchView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.searchFieldsContainer = null;
        target2.locationRow = null;
        target2.datesRow = null;
        target2.guestsRow = null;
        target2.separator1 = null;
        target2.separator2 = null;
    }
}
