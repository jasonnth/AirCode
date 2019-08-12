package com.airbnb.android.lib.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.DrawingUtils;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontManager;

public class HistogramPriceValuesView extends View {
    private String leftVal;
    private final Paint paint = new Paint();
    private String rightVal;

    public HistogramPriceValuesView(Context context) {
        super(context);
        init();
    }

    public HistogramPriceValuesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HistogramPriceValuesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.paint.setStyle(Style.FILL);
        this.paint.setColor(getResources().getColor(C0880R.color.c_rausch));
        this.paint.setTextSize((float) getResources().getDimensionPixelSize(C0880R.dimen.histogram_price_text_size));
        this.paint.setTypeface(FontManager.getTypeface(Font.CircularBook, getContext()));
    }

    public void setValues(String left, String right) {
        this.leftVal = left;
        this.rightVal = right;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.leftVal != null && this.rightVal != null) {
            float halfHeight = (float) (getHeight() / 2);
            float rightX = ((float) getWidth()) - this.paint.measureText(this.rightVal);
            DrawingUtils.drawTextVerticallyCentred(canvas, this.paint, this.leftVal, 0.0f, halfHeight);
            DrawingUtils.drawTextVerticallyCentred(canvas, this.paint, this.rightVal, rightX, halfHeight);
        }
    }
}
