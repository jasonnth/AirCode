package com.airbnb.android.insights;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontManager;

public class InsightIncreaseGraphView extends View {
    @BindColor
    int babu;
    @BindColor
    int babuFilled;
    @BindDimen
    int barGraphHeight;
    @BindDimen
    int captionPadding;
    private String graphCaption;
    private final Paint graphPaint = new Paint();
    private final Path graphPath = new Path();
    private final TextPaint graphTextPaint = new TextPaint();
    private int increaseValue;
    private int increaseValueCenterPadding;
    private String increaseValueText;
    private float increaseValueTextWidth;
    @BindDimen
    int padding;
    private final Rect textRect = new Rect();
    @BindDimen
    int textSize;

    public InsightIncreaseGraphView(Context context) {
        super(context);
        init();
    }

    public InsightIncreaseGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InsightIncreaseGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ButterKnife.bind((View) this);
        this.graphPaint.setStyle(Style.FILL);
        this.graphTextPaint.setColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.graphTextPaint.setTextSize((float) this.textSize);
        this.graphTextPaint.setTypeface(FontManager.getTypeface(Font.CircularBook, getContext()));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int startHeight = (getHeight() - this.barGraphHeight) / 2;
        int viewWidth = getWidth() - (this.padding * 2);
        int barWidth = ((int) (((float) viewWidth) - this.increaseValueTextWidth)) - this.padding;
        int increaseColorWidth = (int) ((((double) this.increaseValue) / (100.0d + ((double) this.increaseValue))) * ((double) barWidth));
        int originalColorWidth = barWidth - increaseColorWidth;
        this.graphPath.reset();
        this.graphPath.moveTo(0.0f, (float) startHeight);
        this.graphPath.lineTo(0.0f, (float) (this.barGraphHeight + startHeight));
        this.graphPath.lineTo((float) originalColorWidth, (float) (this.barGraphHeight + startHeight));
        this.graphPath.lineTo((float) originalColorWidth, (float) startHeight);
        this.graphPath.lineTo(0.0f, (float) startHeight);
        this.graphPaint.setColor(this.babuFilled);
        canvas.drawPath(this.graphPath, this.graphPaint);
        this.graphPath.reset();
        this.graphPath.moveTo((float) originalColorWidth, (float) startHeight);
        this.graphPath.lineTo((float) originalColorWidth, (float) (this.barGraphHeight + startHeight));
        this.graphPath.lineTo((float) (originalColorWidth + increaseColorWidth), (float) (this.barGraphHeight + startHeight));
        this.graphPath.lineTo((float) (originalColorWidth + increaseColorWidth), (float) startHeight);
        this.graphPath.lineTo((float) originalColorWidth, (float) startHeight);
        this.graphPaint.setColor(this.babu);
        canvas.drawPath(this.graphPath, this.graphPaint);
        canvas.drawText(this.graphCaption, 0.0f, (float) (startHeight - this.captionPadding), this.graphTextPaint);
        canvas.drawText(this.increaseValueText, (((float) viewWidth) - this.increaseValueTextWidth) + ((float) this.padding), (float) (this.increaseValueCenterPadding + startHeight), this.graphTextPaint);
    }
}
