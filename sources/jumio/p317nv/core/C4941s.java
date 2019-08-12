package jumio.p317nv.core;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.p311nv.C4430R;
import com.jumio.p311nv.NVOverlay;
import com.jumio.p311nv.NVOverlay.NVOverlayConfig;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import com.jumio.sdk.extraction.ExtractionUpdateState;

/* renamed from: jumio.nv.core.s */
/* compiled from: FaceOverlay */
public class C4941s extends NVOverlay {

    /* renamed from: a */
    private ImageView f4829a;

    /* renamed from: b */
    private NVOverlayConfig f4830b = new NVOverlayConfig();

    public C4941s(Context context) {
        super(context);
        this.f4829a = new ImageView(context);
    }

    public NVOverlayConfig getNetverifyOverlayConfiguration(Context context) {
        this.f4830b.drawBrackets = false;
        this.f4830b.dimBackground = false;
        return this.f4830b;
    }

    public void calculate(DocumentScanMode documentScanMode, int i, int i2) {
    }

    public void addViews(ViewGroup viewGroup) {
        this.f4829a.setImageResource(C4430R.C4431drawable.netverify_button_shutter);
        this.f4829a.setAdjustViewBounds(true);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(14);
        layoutParams.addRule(12);
        this.f4829a.setLayoutParams(layoutParams);
        this.f4829a.setVisibility(0);
        this.f4829a.setContentDescription("Shutter");
        this.f4829a.setClickable(true);
        viewGroup.addView(this.f4829a);
    }

    public void setVisible(int i) {
        this.f4829a.setVisibility(i);
    }

    public void prepareDraw(ScanSide scanSide, boolean z, boolean z2) {
    }

    public void doDraw(Canvas canvas) {
    }

    public void update(ExtractionUpdate extractionUpdate) {
        if (extractionUpdate.getState() == ExtractionUpdateState.receiveClickListener) {
            this.f4829a.setOnClickListener((OnClickListener) extractionUpdate.getData());
        }
    }
}
