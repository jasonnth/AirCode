package com.airbnb.android.listyourspacedls.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSPhotoManagerFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSPhotoManagerFragment target;
    private View view2131755495;
    private View view2131755577;
    private View view2131755587;
    private View view2131755588;

    public LYSPhotoManagerFragment_ViewBinding(final LYSPhotoManagerFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.skip_btn, "field 'skipButton' and method 'onClickSkip'");
        target2.skipButton = (AirButton) Utils.castView(view, C7251R.C7253id.skip_btn, "field 'skipButton'", AirButton.class);
        this.view2131755588 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSkip();
            }
        });
        View view2 = Utils.findRequiredView(source, C7251R.C7253id.button_save_rearrange, "field 'saveRearrangingButton' and method 'onClickDone'");
        target2.saveRearrangingButton = (AirButton) Utils.castView(view2, C7251R.C7253id.button_save_rearrange, "field 'saveRearrangingButton'", AirButton.class);
        this.view2131755587 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickDone();
            }
        });
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7251R.C7253id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view3 = Utils.findRequiredView(source, C7251R.C7253id.next_btn, "method 'onClickNext'");
        this.view2131755495 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNext();
            }
        });
        View view4 = Utils.findRequiredView(source, C7251R.C7253id.preview, "method 'onClickPreview'");
        this.view2131755577 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickPreview();
            }
        });
    }

    public void unbind() {
        LYSPhotoManagerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.skipButton = null;
        target2.saveRearrangingButton = null;
        target2.recyclerView = null;
        this.view2131755588.setOnClickListener(null);
        this.view2131755588 = null;
        this.view2131755587.setOnClickListener(null);
        this.view2131755587 = null;
        this.view2131755495.setOnClickListener(null);
        this.view2131755495 = null;
        this.view2131755577.setOnClickListener(null);
        this.view2131755577 = null;
        super.unbind();
    }
}
