package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSCharacterCountMarqueeFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSCharacterCountMarqueeFragment target;
    private View view2131755495;
    private View view2131755577;

    public LYSCharacterCountMarqueeFragment_ViewBinding(final LYSCharacterCountMarqueeFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.editTextPage = (AirEditTextPageView) Utils.findRequiredViewAsType(source, C7251R.C7253id.edit_text_page, "field 'editTextPage'", AirEditTextPageView.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.preview, "field 'previewButton' and method 'onClickPreview'");
        target2.previewButton = (AirButton) Utils.castView(view, C7251R.C7253id.preview, "field 'previewButton'", AirButton.class);
        this.view2131755577 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickPreview();
            }
        });
        View view2 = Utils.findRequiredView(source, C7251R.C7253id.next_btn, "method 'onClickNext'");
        this.view2131755495 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNext();
            }
        });
    }

    public void unbind() {
        LYSCharacterCountMarqueeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.editTextPage = null;
        target2.previewButton = null;
        this.view2131755577.setOnClickListener(null);
        this.view2131755577 = null;
        this.view2131755495.setOnClickListener(null);
        this.view2131755495 = null;
        super.unbind();
    }
}
