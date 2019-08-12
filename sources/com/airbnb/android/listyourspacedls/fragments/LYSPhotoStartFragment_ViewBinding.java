package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;

public class LYSPhotoStartFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSPhotoStartFragment target;
    private View view2131755590;
    private View view2131755591;

    public LYSPhotoStartFragment_ViewBinding(final LYSPhotoStartFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C7251R.C7253id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.add_button, "method 'onClickAddButton'");
        this.view2131755590 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickAddButton();
            }
        });
        View view2 = Utils.findRequiredView(source, C7251R.C7253id.skip_button, "method 'onClickSkipButton'");
        this.view2131755591 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSkipButton();
            }
        });
    }

    public void unbind() {
        LYSPhotoStartFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.scrollView = null;
        target2.toolbar = null;
        this.view2131755590.setOnClickListener(null);
        this.view2131755590 = null;
        this.view2131755591.setOnClickListener(null);
        this.view2131755591 = null;
        super.unbind();
    }
}
