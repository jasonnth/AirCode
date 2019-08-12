package com.airbnb.android.misnap;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class MiSnapTakeSelfieActivity_ViewBinding implements Unbinder {
    private MiSnapTakeSelfieActivity target;
    private View view2131755317;
    private View view2131755318;
    private View view2131755320;
    private View view2131755323;

    public MiSnapTakeSelfieActivity_ViewBinding(MiSnapTakeSelfieActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public MiSnapTakeSelfieActivity_ViewBinding(final MiSnapTakeSelfieActivity target2, View source) {
        this.target = target2;
        target2.overlayView = Utils.findRequiredView(source, C7471R.C7473id.selfie_capture_overlay, "field 'overlayView'");
        target2.errorTextView = (TextView) Utils.findRequiredViewAsType(source, C7471R.C7473id.selfie_error_text, "field 'errorTextView'", TextView.class);
        View view = Utils.findRequiredView(source, C7471R.C7473id.selfie_overlay_capture_mode_button, "field 'captureModeButton' and method 'onModeChange'");
        target2.captureModeButton = (TextView) Utils.castView(view, C7471R.C7473id.selfie_overlay_capture_mode_button, "field 'captureModeButton'", TextView.class);
        this.view2131755320 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onModeChange();
            }
        });
        View view2 = Utils.findRequiredView(source, C7471R.C7473id.selfie_overlay_capture_button, "field 'captureButton' and method 'onCapture'");
        target2.captureButton = (ImageButton) Utils.castView(view2, C7471R.C7473id.selfie_overlay_capture_button, "field 'captureButton'", ImageButton.class);
        this.view2131755323 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCapture();
            }
        });
        View view3 = Utils.findRequiredView(source, C7471R.C7473id.selfie_overlay_close_button, "field 'closeButton' and method 'onClose'");
        target2.closeButton = (ImageButton) Utils.castView(view3, C7471R.C7473id.selfie_overlay_close_button, "field 'closeButton'", ImageButton.class);
        this.view2131755317 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClose();
            }
        });
        target2.capturedFullOverlay = (ImageView) Utils.findRequiredViewAsType(source, C7471R.C7473id.selfie_overlay_captured_bg, "field 'capturedFullOverlay'", ImageView.class);
        View view4 = Utils.findRequiredView(source, C7471R.C7473id.selfie_overlay_help_button, "method 'onHelp'");
        this.view2131755318 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onHelp();
            }
        });
    }

    public void unbind() {
        MiSnapTakeSelfieActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.overlayView = null;
        target2.errorTextView = null;
        target2.captureModeButton = null;
        target2.captureButton = null;
        target2.closeButton = null;
        target2.capturedFullOverlay = null;
        this.view2131755320.setOnClickListener(null);
        this.view2131755320 = null;
        this.view2131755323.setOnClickListener(null);
        this.view2131755323 = null;
        this.view2131755317.setOnClickListener(null);
        this.view2131755317 = null;
        this.view2131755318.setOnClickListener(null);
        this.view2131755318 = null;
    }
}
