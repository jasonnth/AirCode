package com.airbnb.android.hostcalendar.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.InlineFormattedIntegerInputRow;
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.TriStateSwitchRow;
import com.airbnb.p027n2.primitives.AirButton;

public class CalendarWithPriceTipsUpdateFragment_ViewBinding implements Unbinder {
    private CalendarWithPriceTipsUpdateFragment target;
    private View view2131755539;
    private View view2131755540;
    private View view2131755541;

    public CalendarWithPriceTipsUpdateFragment_ViewBinding(final CalendarWithPriceTipsUpdateFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6418R.C6420id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.calendarUpdateMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C6418R.C6420id.calendar_update_marquee, "field 'calendarUpdateMarquee'", DocumentMarquee.class);
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C6418R.C6420id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.availabilityHeader = (SectionHeader) Utils.findRequiredViewAsType(source, C6418R.C6420id.availability_header, "field 'availabilityHeader'", SectionHeader.class);
        target2.availableToggle = (ToggleActionRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.available_toggle, "field 'availableToggle'", ToggleActionRow.class);
        target2.blockedToggle = (ToggleActionRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.blocked_toggle, "field 'blockedToggle'", ToggleActionRow.class);
        target2.blockedUntilToggle = (ToggleActionRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.blocked_until_toggle, "field 'blockedUntilToggle'", ToggleActionRow.class);
        target2.pricingHeader = (SectionHeader) Utils.findRequiredViewAsType(source, C6418R.C6420id.pricing_header, "field 'pricingHeader'", SectionHeader.class);
        target2.smartPriceSwitch = (SwitchRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.smart_price_switch, "field 'smartPriceSwitch'", SwitchRow.class);
        target2.smartPriceTriSwitch = (TriStateSwitchRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.smart_price_tri_switch, "field 'smartPriceTriSwitch'", TriStateSwitchRow.class);
        target2.priceField = (InlineFormattedIntegerInputRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.price_field, "field 'priceField'", InlineFormattedIntegerInputRow.class);
        View view = Utils.findRequiredView(source, C6418R.C6420id.save_button, "field 'saveButton' and method 'onClickSaveButton'");
        target2.saveButton = (AirButton) Utils.castView(view, C6418R.C6420id.save_button, "field 'saveButton'", AirButton.class);
        this.view2131755541 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSaveButton();
            }
        });
        target2.reasons = (SimpleTextRow) Utils.findRequiredViewAsType(source, C6418R.C6420id.reasons, "field 'reasons'", SimpleTextRow.class);
        View view2 = Utils.findRequiredView(source, C6418R.C6420id.view_price_tips, "field 'viewPriceTipsRow' and method 'onClickViewPriceTips'");
        target2.viewPriceTipsRow = (SimpleTextRow) Utils.castView(view2, C6418R.C6420id.view_price_tips, "field 'viewPriceTipsRow'", SimpleTextRow.class);
        this.view2131755539 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickViewPriceTips();
            }
        });
        target2.smartPromoRow = (RecyclerView) Utils.findRequiredViewAsType(source, C6418R.C6420id.smart_promo, "field 'smartPromoRow'", RecyclerView.class);
        View view3 = Utils.findRequiredView(source, C6418R.C6420id.disclaimer, "field 'disclaimer' and method 'onClickDisclaimerButton'");
        target2.disclaimer = (SimpleTextRow) Utils.castView(view3, C6418R.C6420id.disclaimer, "field 'disclaimer'", SimpleTextRow.class);
        this.view2131755540 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickDisclaimerButton();
            }
        });
    }

    public void unbind() {
        CalendarWithPriceTipsUpdateFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.calendarUpdateMarquee = null;
        target2.scrollView = null;
        target2.availabilityHeader = null;
        target2.availableToggle = null;
        target2.blockedToggle = null;
        target2.blockedUntilToggle = null;
        target2.pricingHeader = null;
        target2.smartPriceSwitch = null;
        target2.smartPriceTriSwitch = null;
        target2.priceField = null;
        target2.saveButton = null;
        target2.reasons = null;
        target2.viewPriceTipsRow = null;
        target2.smartPromoRow = null;
        target2.disclaimer = null;
        this.view2131755541.setOnClickListener(null);
        this.view2131755541 = null;
        this.view2131755539.setOnClickListener(null);
        this.view2131755539 = null;
        this.view2131755540.setOnClickListener(null);
        this.view2131755540 = null;
    }
}
