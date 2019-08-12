package com.airbnb.android.wishlists;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.components.TweenRow;

public class WishListTweenFragment_ViewBinding implements Unbinder {
    private WishListTweenFragment target;
    private View view2131755602;
    private View view2131755603;
    private View view2131755605;

    public WishListTweenFragment_ViewBinding(final WishListTweenFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C1758R.C1760id.wish_list_tween_dates_row, "field 'datesRow' and method 'onDatesClicked'");
        target2.datesRow = (TweenRow) Utils.castView(view, C1758R.C1760id.wish_list_tween_dates_row, "field 'datesRow'", TweenRow.class);
        this.view2131755602 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDatesClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C1758R.C1760id.wish_list_tween_guests_row, "field 'guestsRow' and method 'onGuestsClicked'");
        target2.guestsRow = (TweenRow) Utils.castView(view2, C1758R.C1760id.wish_list_tween_guests_row, "field 'guestsRow'", TweenRow.class);
        this.view2131755603 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onGuestsClicked();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1758R.C1760id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.filtersReasonText = (SimpleTextRow) Utils.findRequiredViewAsType(source, C1758R.C1760id.filters_reason_text, "field 'filtersReasonText'", SimpleTextRow.class);
        View view3 = Utils.findRequiredView(source, C1758R.C1760id.wish_list_tween_save_button, "method 'onSaveClicked'");
        this.view2131755605 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSaveClicked();
            }
        });
    }

    public void unbind() {
        WishListTweenFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.datesRow = null;
        target2.guestsRow = null;
        target2.toolbar = null;
        target2.filtersReasonText = null;
        this.view2131755602.setOnClickListener(null);
        this.view2131755602 = null;
        this.view2131755603.setOnClickListener(null);
        this.view2131755603 = null;
        this.view2131755605.setOnClickListener(null);
        this.view2131755605 = null;
    }
}
