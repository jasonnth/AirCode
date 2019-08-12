package com.airbnb.android.misnap;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class MiSnapIdentityCaptureActivity_ViewBinding implements Unbinder {
    private MiSnapIdentityCaptureActivity target;
    private View view2131755301;
    private View view2131755302;
    private View view2131755305;

    public MiSnapIdentityCaptureActivity_ViewBinding(MiSnapIdentityCaptureActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public MiSnapIdentityCaptureActivity_ViewBinding(final MiSnapIdentityCaptureActivity target2, View source) {
        this.target = target2;
        target2.overlayView = Utils.findRequiredView(source, C7471R.C7473id.misnap_capture_overlay, "field 'overlayView'");
        target2.statusView = (TextView) Utils.findRequiredViewAsType(source, C7471R.C7473id.camera_overlay_status_text, "field 'statusView'", TextView.class);
        target2.errorTextView = (TextView) Utils.findRequiredViewAsType(source, C7471R.C7473id.camera_error_text, "field 'errorTextView'", TextView.class);
        View view = Utils.findRequiredView(source, C7471R.C7473id.camera_overlay_capture_mode_button, "field 'captureModeButton' and method 'onModeChange'");
        target2.captureModeButton = (TextView) Utils.castView(view, C7471R.C7473id.camera_overlay_capture_mode_button, "field 'captureModeButton'", TextView.class);
        this.view2131755302 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onModeChange();
            }
        });
        View view2 = Utils.findRequiredView(source, C7471R.C7473id.camera_overlay_capture_button, "field 'captureButton' and method 'onCapture'");
        target2.captureButton = view2;
        this.view2131755305 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCapture();
            }
        });
        target2.idFrameView = Utils.findRequiredView(source, C7471R.C7473id.camera_overlay_id_frame, "field 'idFrameView'");
        View view3 = Utils.findRequiredView(source, C7471R.C7473id.camera_overlay_close_button, "method 'onClose'");
        this.view2131755301 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClose();
            }
        });
    }

    public void unbind() {
        MiSnapIdentityCaptureActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.overlayView = null;
        target2.statusView = null;
        target2.errorTextView = null;
        target2.captureModeButton = null;
        target2.captureButton = null;
        target2.idFrameView = null;
        this.view2131755302.setOnClickListener(null);
        this.view2131755302 = null;
        this.view2131755305.setOnClickListener(null);
        this.view2131755305 = null;
        this.view2131755301.setOnClickListener(null);
        this.view2131755301 = null;
    }
}
