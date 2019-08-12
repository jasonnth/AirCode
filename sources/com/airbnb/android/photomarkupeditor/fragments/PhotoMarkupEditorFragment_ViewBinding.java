package com.airbnb.android.photomarkupeditor.fragments;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.photomarkupeditor.C0904R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.DrawOnImageView;

public class PhotoMarkupEditorFragment_ViewBinding implements Unbinder {
    private PhotoMarkupEditorFragment target;
    private View view2131755524;
    private View view2131755525;
    private View view2131755526;
    private View view2131755527;
    private View view2131755528;
    private View view2131755529;
    private View view2131755530;

    public PhotoMarkupEditorFragment_ViewBinding(final PhotoMarkupEditorFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0904R.C0906id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.drawOnImageView = (DrawOnImageView) Utils.findRequiredViewAsType(source, C0904R.C0906id.draw_on_image_view, "field 'drawOnImageView'", DrawOnImageView.class);
        View view = Utils.findRequiredView(source, C0904R.C0906id.icon_draw, "field 'iconDraw' and method 'onDrawIconClick'");
        target2.iconDraw = (ImageView) Utils.castView(view, C0904R.C0906id.icon_draw, "field 'iconDraw'", ImageView.class);
        this.view2131755528 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDrawIconClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C0904R.C0906id.undo_button, "field 'undoButton' and method 'onUndoClick'");
        target2.undoButton = (AirButton) Utils.castView(view2, C0904R.C0906id.undo_button, "field 'undoButton'", AirButton.class);
        this.view2131755530 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onUndoClick();
            }
        });
        target2.colorPicker = (FrameLayout) Utils.findRequiredViewAsType(source, C0904R.C0906id.color_picker, "field 'colorPicker'", FrameLayout.class);
        target2.fullScreenLoader = (LoaderFrame) Utils.findRequiredViewAsType(source, C0904R.C0906id.full_screen_loader, "field 'fullScreenLoader'", LoaderFrame.class);
        View view3 = Utils.findRequiredView(source, C0904R.C0906id.color_hof, "field 'colorHof' and method 'onSelectColorHof'");
        target2.colorHof = view3;
        this.view2131755524 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSelectColorHof(p0);
            }
        });
        View view4 = Utils.findRequiredView(source, C0904R.C0906id.color_beach, "field 'colorBeach' and method 'onSelectColorBeach'");
        target2.colorBeach = view4;
        this.view2131755525 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSelectColorBeach(p0);
            }
        });
        View view5 = Utils.findRequiredView(source, C0904R.C0906id.color_babu, "field 'colorBabu' and method 'onSelectColorBabu'");
        target2.colorBabu = view5;
        this.view2131755526 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSelectColorBabu(p0);
            }
        });
        View view6 = Utils.findRequiredView(source, C0904R.C0906id.color_rausch, "field 'colorRausch' and method 'onSelectColorRausch'");
        target2.colorRausch = view6;
        this.view2131755527 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSelectColorRausch(p0);
            }
        });
        View view7 = Utils.findRequiredView(source, C0904R.C0906id.icon_crop, "method 'onCropIconClick'");
        this.view2131755529 = view7;
        view7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCropIconClick();
            }
        });
    }

    public void unbind() {
        PhotoMarkupEditorFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.drawOnImageView = null;
        target2.iconDraw = null;
        target2.undoButton = null;
        target2.colorPicker = null;
        target2.fullScreenLoader = null;
        target2.colorHof = null;
        target2.colorBeach = null;
        target2.colorBabu = null;
        target2.colorRausch = null;
        this.view2131755528.setOnClickListener(null);
        this.view2131755528 = null;
        this.view2131755530.setOnClickListener(null);
        this.view2131755530 = null;
        this.view2131755524.setOnClickListener(null);
        this.view2131755524 = null;
        this.view2131755525.setOnClickListener(null);
        this.view2131755525 = null;
        this.view2131755526.setOnClickListener(null);
        this.view2131755526 = null;
        this.view2131755527.setOnClickListener(null);
        this.view2131755527 = null;
        this.view2131755529.setOnClickListener(null);
        this.view2131755529 = null;
    }
}
