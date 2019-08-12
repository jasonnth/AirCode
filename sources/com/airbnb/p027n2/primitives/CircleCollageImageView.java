package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.List;

/* renamed from: com.airbnb.n2.primitives.CircleCollageImageView */
public class CircleCollageImageView extends LinearLayout {
    private Paint eraser;
    @BindViews
    List<AirImageView> imageViews;
    private Bitmap mask;
    @BindView
    LinearLayout rightContainer;

    public CircleCollageImageView(Context context) {
        super(context);
        init();
    }

    public CircleCollageImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleCollageImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(0);
        setLayerType(1, null);
        inflate(getContext(), R.layout.n2_circle_collage_image_view, this);
        ButterKnife.bind((View) this);
        this.eraser = new Paint(1);
        this.eraser.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        if (this.mask != null && !this.mask.isRecycled()) {
            this.mask.recycle();
        }
        this.mask = createMask(width, height);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawBitmap(this.mask, 0.0f, 0.0f, this.eraser);
    }

    public void setImages(List<String> urls) {
        int i;
        LinearLayout linearLayout = this.rightContainer;
        if (urls.size() > 1) {
            i = 0;
        } else {
            i = 8;
        }
        linearLayout.setVisibility(i);
        for (int i2 = 0; i2 < this.imageViews.size(); i2++) {
            AirImageView view = (AirImageView) this.imageViews.get(i2);
            if (urls.size() > i2) {
                view.setImageUrl((String) urls.get(i2));
                view.setVisibility(0);
            } else {
                view.clear();
                view.setVisibility(8);
            }
        }
    }

    public void setImageRes(int imageRes) {
        this.rightContainer.setVisibility(8);
        for (int i = 0; i < this.imageViews.size(); i++) {
            AirImageView view = (AirImageView) this.imageViews.get(i);
            if (i == 0) {
                view.setImageDrawableCompat(imageRes);
                view.setVisibility(0);
            } else {
                view.clear();
                view.setVisibility(8);
            }
        }
    }

    private static Bitmap createMask(int width, int height) {
        Bitmap mask2 = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        new Canvas(mask2).drawOval(new RectF(0.0f, 0.0f, (float) width, (float) height), new Paint(1));
        return mask2;
    }
}
