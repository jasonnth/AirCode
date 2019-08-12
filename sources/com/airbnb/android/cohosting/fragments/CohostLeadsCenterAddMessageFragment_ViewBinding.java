package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirEditTextView;

public class CohostLeadsCenterAddMessageFragment_ViewBinding implements Unbinder {
    private CohostLeadsCenterAddMessageFragment target;
    private View view2131755483;

    public CohostLeadsCenterAddMessageFragment_ViewBinding(final CohostLeadsCenterAddMessageFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C5658R.C5660id.titleMarquee, "field 'marquee'", DocumentMarquee.class);
        target2.editTextView = (AirEditTextView) Utils.findRequiredViewAsType(source, C5658R.C5660id.edit_text, "field 'editTextView'", AirEditTextView.class);
        View view = Utils.findRequiredView(source, C5658R.C5660id.send_button, "field 'button' and method 'onSendMessageClicked'");
        target2.button = (AirButton) Utils.castView(view, C5658R.C5660id.send_button, "field 'button'", AirButton.class);
        this.view2131755483 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSendMessageClicked();
            }
        });
    }

    public void unbind() {
        CohostLeadsCenterAddMessageFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.editTextView = null;
        target2.button = null;
        this.view2131755483.setOnClickListener(null);
        this.view2131755483 = null;
    }
}
