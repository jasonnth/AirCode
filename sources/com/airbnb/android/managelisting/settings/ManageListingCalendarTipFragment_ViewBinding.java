package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class ManageListingCalendarTipFragment_ViewBinding implements Unbinder {
    private ManageListingCalendarTipFragment target;
    private View view2131755199;

    public ManageListingCalendarTipFragment_ViewBinding(final ManageListingCalendarTipFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7368R.C7370id.document_marquee, "field 'marquee'", DocumentMarquee.class);
        target2.textRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C7368R.C7370id.text_row, "field 'textRow'", SimpleTextRow.class);
        View view = Utils.findRequiredView(source, C7368R.C7370id.button, "field 'button' and method 'onCustomize'");
        target2.button = (AirButton) Utils.castView(view, C7368R.C7370id.button, "field 'button'", AirButton.class);
        this.view2131755199 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCustomize();
            }
        });
    }

    public void unbind() {
        ManageListingCalendarTipFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.textRow = null;
        target2.button = null;
        this.view2131755199.setOnClickListener(null);
        this.view2131755199 = null;
    }
}
