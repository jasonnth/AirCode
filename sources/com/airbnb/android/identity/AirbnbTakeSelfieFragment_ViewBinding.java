package com.airbnb.android.identity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.LoadingView;

public class AirbnbTakeSelfieFragment_ViewBinding implements Unbinder {
    private AirbnbTakeSelfieFragment target;
    private View view2131755344;
    private View view2131755345;
    private View view2131755350;

    public AirbnbTakeSelfieFragment_ViewBinding(final AirbnbTakeSelfieFragment target2, View source) {
        this.target = target2;
        target2.overlayView = Utils.findRequiredView(source, C6533R.C6535id.selfie_capture_overlay, "field 'overlayView'");
        target2.errorTextView = (TextView) Utils.findRequiredViewAsType(source, C6533R.C6535id.selfie_error_text, "field 'errorTextView'", TextView.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.selfie_overlay_capture_button, "field 'captureButton' and method 'onCapture'");
        target2.captureButton = (ImageButton) Utils.castView(view, C6533R.C6535id.selfie_overlay_capture_button, "field 'captureButton'", ImageButton.class);
        this.view2131755350 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCapture();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.selfie_overlay_close_button, "field 'closeButton' and method 'onClose'");
        target2.closeButton = (ImageButton) Utils.castView(view2, C6533R.C6535id.selfie_overlay_close_button, "field 'closeButton'", ImageButton.class);
        this.view2131755344 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClose();
            }
        });
        target2.capturedFullOverlay = (ImageView) Utils.findRequiredViewAsType(source, C6533R.C6535id.selfie_overlay_captured_bg, "field 'capturedFullOverlay'", ImageView.class);
        target2.selfieCaptureContainer = (FrameLayout) Utils.findRequiredViewAsType(source, C6533R.C6535id.selfie_capture_container, "field 'selfieCaptureContainer'", FrameLayout.class);
        target2.countdownTextView = (TextView) Utils.findRequiredViewAsType(source, C6533R.C6535id.selfie_countdown, "field 'countdownTextView'", TextView.class);
        target2.selfieTip = (TextView) Utils.findRequiredViewAsType(source, C6533R.C6535id.selfie_tip, "field 'selfieTip'", TextView.class);
        target2.loader = (LoadingView) Utils.findRequiredViewAsType(source, C6533R.C6535id.loader, "field 'loader'", LoadingView.class);
        target2.flash = (ImageView) Utils.findRequiredViewAsType(source, C6533R.C6535id.flash, "field 'flash'", ImageView.class);
        View view3 = Utils.findRequiredView(source, C6533R.C6535id.selfie_overlay_help_button, "method 'onHelp'");
        this.view2131755345 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onHelp();
            }
        });
    }

    public void unbind() {
        AirbnbTakeSelfieFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.overlayView = null;
        target2.errorTextView = null;
        target2.captureButton = null;
        target2.closeButton = null;
        target2.capturedFullOverlay = null;
        target2.selfieCaptureContainer = null;
        target2.countdownTextView = null;
        target2.selfieTip = null;
        target2.loader = null;
        target2.flash = null;
        this.view2131755350.setOnClickListener(null);
        this.view2131755350 = null;
        this.view2131755344.setOnClickListener(null);
        this.view2131755344 = null;
        this.view2131755345.setOnClickListener(null);
        this.view2131755345 = null;
    }
}
