package com.airbnb.android.explore.views;

import android.content.Context;
import android.content.res.Resources;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.primitives.AirTextView;

public class MTTripsSearchView_ViewBinding implements Unbinder {
    private MTTripsSearchView target;

    public MTTripsSearchView_ViewBinding(MTTripsSearchView target2) {
        this(target2, target2);
    }

    public MTTripsSearchView_ViewBinding(MTTripsSearchView target2, View source) {
        this.target = target2;
        target2.actionButton = (ImageButton) Utils.findRequiredViewAsType(source, C0857R.C0859id.action_button, "field 'actionButton'", ImageButton.class);
        target2.collapseButton = (ImageButton) Utils.findRequiredViewAsType(source, C0857R.C0859id.collapse_button, "field 'collapseButton'", ImageButton.class);
        target2.searchContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0857R.C0859id.search_container, "field 'searchContainer'", LinearLayout.class);
        target2.locationView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.location, "field 'locationView'", AirTextView.class);
        target2.datesView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.dates, "field 'datesView'", AirTextView.class);
        target2.guestsView = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.guests, "field 'guestsView'", AirTextView.class);
        target2.bullet1 = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.bullet_1, "field 'bullet1'", AirTextView.class);
        target2.bullet2 = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.bullet_2, "field 'bullet2'", AirTextView.class);
        target2.locationRow = (MTSearchInputField) Utils.findRequiredViewAsType(source, C0857R.C0859id.location_row, "field 'locationRow'", MTSearchInputField.class);
        target2.datesRow = (MTSearchInputField) Utils.findRequiredViewAsType(source, C0857R.C0859id.dates_row, "field 'datesRow'", MTSearchInputField.class);
        target2.guestsRow = (MTSearchInputField) Utils.findRequiredViewAsType(source, C0857R.C0859id.guests_row, "field 'guestsRow'", MTSearchInputField.class);
        target2.bottomDivider = Utils.findRequiredView(source, C0857R.C0859id.bottom_divider, "field 'bottomDivider'");
        target2.clearAllButton = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.clear_all, "field 'clearAllButton'", AirTextView.class);
        target2.searchBoxExtras = (View[]) Utils.arrayOf(Utils.findRequiredView(source, C0857R.C0859id.bullet_1, "field 'searchBoxExtras'"), Utils.findRequiredView(source, C0857R.C0859id.dates, "field 'searchBoxExtras'"), Utils.findRequiredView(source, C0857R.C0859id.bullet_2, "field 'searchBoxExtras'"), Utils.findRequiredView(source, C0857R.C0859id.guests, "field 'searchBoxExtras'"));
        Context context = source.getContext();
        Resources res = context.getResources();
        target2.whiteColor = ContextCompat.getColor(context, C0857R.color.white);
        target2.mainTextColor = ContextCompat.getColor(context, C0857R.color.n2_text_color_main);
        target2.searchFieldsMarginTop = res.getDimensionPixelSize(C0857R.dimen.search_fields_margin_top);
        target2.iconSpace = res.getDimensionPixelSize(C0857R.dimen.explore_marquee_icon_space);
    }

    public void unbind() {
        MTTripsSearchView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.actionButton = null;
        target2.collapseButton = null;
        target2.searchContainer = null;
        target2.locationView = null;
        target2.datesView = null;
        target2.guestsView = null;
        target2.bullet1 = null;
        target2.bullet2 = null;
        target2.locationRow = null;
        target2.datesRow = null;
        target2.guestsRow = null;
        target2.bottomDivider = null;
        target2.clearAllButton = null;
        target2.searchBoxExtras = null;
    }
}
