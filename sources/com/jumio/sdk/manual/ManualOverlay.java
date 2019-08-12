package com.jumio.sdk.manual;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.jumio.core.C4404R;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.overlay.Overlay;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;

public class ManualOverlay implements Overlay {
    private int height;
    private ImageView mShutterButton;
    private int width;

    public ManualOverlay(Context context) {
        this.mShutterButton = new ImageView(context);
    }

    public void calculate(DocumentScanMode scanMode, int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void addViews(ViewGroup rootView) {
        this.mShutterButton.setImageResource(C4404R.C4405drawable.netverify_button_shutter);
        this.mShutterButton.setAdjustViewBounds(true);
        LayoutParams params = new LayoutParams(-2, -2);
        params.addRule(14);
        params.addRule(12);
        this.mShutterButton.setLayoutParams(params);
        this.mShutterButton.setVisibility(0);
        this.mShutterButton.setContentDescription("Shutter");
        this.mShutterButton.setClickable(true);
        rootView.addView(this.mShutterButton);
    }

    public void setVisible(int visibility) {
        this.mShutterButton.setVisibility(visibility);
    }

    public Rect getOverlayBounds() {
        return new Rect(0, 0, this.width, this.height);
    }

    public void prepareDraw(ScanSide scanSide, boolean isFrontCamera, boolean isPortrait) {
    }

    public void doDraw(Canvas canvas) {
    }

    public void update(ExtractionUpdate extractionUpdate) {
        if (extractionUpdate.getState() == ExtractionUpdateState.receiveClickListener) {
            this.mShutterButton.setOnClickListener((OnClickListener) extractionUpdate.getData());
        }
    }
}
