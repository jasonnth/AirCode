package com.airbnb.android.booking.fragments.alipay;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AlipayNationalIdFragment_ViewBinding implements Unbinder {
    private AlipayNationalIdFragment target;
    private View view2131755474;

    public AlipayNationalIdFragment_ViewBinding(final AlipayNationalIdFragment target2, View source) {
        this.target = target2;
        target2.inputText = (SheetInputText) Utils.findRequiredViewAsType(source, C0704R.C0706id.alipay_national_id_sheetInput, "field 'inputText'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.next_button, "field 'nextButton' and method 'onClickNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C0704R.C0706id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131755474 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNext();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0704R.C0706id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
    }

    public void unbind() {
        AlipayNationalIdFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.inputText = null;
        target2.nextButton = null;
        target2.toolbar = null;
        target2.jellyfishView = null;
        this.view2131755474.setOnClickListener(null);
        this.view2131755474 = null;
    }
}
