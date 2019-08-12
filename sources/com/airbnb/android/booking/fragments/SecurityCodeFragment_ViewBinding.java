package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class SecurityCodeFragment_ViewBinding implements Unbinder {
    private SecurityCodeFragment target;
    private View view2131755474;

    public SecurityCodeFragment_ViewBinding(final SecurityCodeFragment target2, View source) {
        this.target = target2;
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0704R.C0706id.security_code_marquee, "field 'marquee'", SheetMarquee.class);
        target2.sheetInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0704R.C0706id.security_code_sheetInput, "field 'sheetInput'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.next_button, "field 'nextButton' and method 'onClickNextButton'");
        target2.nextButton = (AirButton) Utils.castView(view, C0704R.C0706id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131755474 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNextButton();
            }
        });
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0704R.C0706id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
    }

    public void unbind() {
        SecurityCodeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.sheetInput = null;
        target2.nextButton = null;
        target2.jellyfishView = null;
        this.view2131755474.setOnClickListener(null);
        this.view2131755474 = null;
    }
}
