package com.airbnb.android.explore.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.View;
import android.view.View.MeasureSpec;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.primitives.Doubles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

@SuppressLint({"ViewConstructor"})
public class ExploreInlineRangeSeekBar extends ExploreBaseRangeSeekBar<Integer> {
    private static final float INTERPOLATION_POLL_FREQUENCY = 6.0f;
    @BindDimen
    float canvasHeight;
    private float graphBottom;
    @BindColor
    int graphColor;
    @BindDimen
    float graphHeight;
    private float graphHeightNormalizationFactor;
    private final Path histogramPath = new Path();
    @BindColor
    int horizontalRangeBarColor;
    @BindDimen
    float horizontalRangeHeight;
    private PolynomialSplineFunction interpolatedFunction;
    private final RectF rect = new RectF();
    @BindColor
    int selectedGraphColor;
    private final Path selectedHistogramPath = new Path();
    @BindDimen
    float selectedHorizontalRangeHeight;
    private double[] xValues;

    public ExploreInlineRangeSeekBar(Context context, int thumbDrawable, int thumbDrawablePressed) throws IllegalArgumentException {
        super(Integer.valueOf(0), Integer.valueOf(0), thumbDrawable, thumbDrawablePressed, context);
        ButterKnife.bind((View) this);
    }

    public void setHistogram(List<Integer> priceHistogram) {
        if (ListUtils.isEmpty((Collection<?>) priceHistogram)) {
            priceHistogram = new ArrayList<>(2);
            priceHistogram.add(Integer.valueOf(0));
            priceHistogram.add(Integer.valueOf(0));
        }
        this.graphBottom = this.canvasHeight - ((float) (this.pressedThumbImage.getHeight() / 2));
        int size = priceHistogram.size();
        double[] histData = new double[(size + 2)];
        for (int i = 1; i < size; i++) {
            histData[i] = (double) ((Integer) priceHistogram.get(i)).intValue();
        }
        histData[0] = 0.0d;
        histData[size] = 0.0d;
        this.xValues = new double[histData.length];
        this.xValues = Doubles.toArray(ContiguousSet.create(Range.closedOpen(Integer.valueOf(0), Integer.valueOf(histData.length)), DiscreteDomain.integers()).asList());
        this.interpolatedFunction = new SplineInterpolator().interpolate(this.xValues, histData);
        this.graphHeightNormalizationFactor = getGraphHeightNormalizationFactor(histData);
        invalidate();
        ViewLibUtils.setPaddingBottom(this, 48);
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        boolean minPressed = Thumb.MIN.equals(this.pressedThumb);
        boolean maxPressed = Thumb.MAX.equals(this.pressedThumb);
        Point minThumbLocation = calculateThumbPosition(canvas, normalizedToScreen(this.normalizedMinValue), minPressed);
        Point maxThumbLocation = calculateThumbPosition(canvas, normalizedToScreen(this.normalizedMaxValue), maxPressed);
        Bitmap minThumb = minPressed ? this.pressedThumbImage : this.thumbImage;
        Bitmap maxThumb = maxPressed ? this.pressedThumbImage : this.thumbImage;
        this.paint.setStyle(Style.FILL);
        this.paint.setColor(-1);
        this.rect.set(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
        canvas.drawRect(this.rect, this.paint);
        drawHistogram(canvas, minThumbLocation.x + (minThumb.getWidth() / 2), maxThumbLocation.x + (maxThumb.getWidth() / 2));
        drawHorizontalRangeBar(canvas);
        this.paint.setAlpha(255);
        this.paint.setColor(-1);
        canvas.drawBitmap(minThumb, (float) minThumbLocation.x, (float) minThumbLocation.y, this.paint);
        canvas.drawBitmap(maxThumb, (float) maxThumbLocation.x, (float) maxThumbLocation.y, this.paint);
    }

    private void drawHistogram(Canvas canvas, int minThumbX, int maxThumbX) {
        if (this.interpolatedFunction != null) {
            float graphWidth = ((float) getWidth()) - (2.0f * this.thumbHalfWidth);
            double numDataPoints = (double) this.xValues.length;
            this.histogramPath.moveTo(0.0f, this.graphBottom);
            this.histogramPath.lineTo(this.thumbHalfWidth, this.graphBottom);
            int size = ((int) ((numDataPoints - 1.0d) * 6.0d)) + 1;
            for (int i = 0; i < size * 2; i += 2) {
                double queryXValue = (double) (((float) i) / 12.0f);
                float xScreenPosition = (float) (((((double) graphWidth) * queryXValue) / (numDataPoints - 1.0d)) + ((double) this.thumbHalfWidth));
                float yScreenPosition = (float) (((double) this.graphBottom) - (this.interpolatedFunction.value(queryXValue) * ((double) this.graphHeightNormalizationFactor)));
                if (yScreenPosition > this.graphBottom) {
                    yScreenPosition = this.graphBottom;
                }
                this.histogramPath.lineTo(xScreenPosition, yScreenPosition);
                if (xScreenPosition >= ((float) minThumbX) && xScreenPosition <= ((float) maxThumbX)) {
                    if (this.selectedHistogramPath.isEmpty()) {
                        this.selectedHistogramPath.moveTo(xScreenPosition, yScreenPosition);
                    } else {
                        this.selectedHistogramPath.lineTo(xScreenPosition, yScreenPosition);
                    }
                }
            }
            this.selectedHistogramPath.lineTo((float) maxThumbX, this.graphBottom);
            this.selectedHistogramPath.lineTo((float) minThumbX, this.graphBottom);
            this.paint.setStyle(Style.FILL);
            this.paint.setColor(this.graphColor);
            canvas.drawPath(this.histogramPath, this.paint);
            this.histogramPath.reset();
            this.paint.setColor(this.selectedGraphColor);
            canvas.drawPath(this.selectedHistogramPath, this.paint);
            this.selectedHistogramPath.reset();
        }
    }

    private float getGraphHeightNormalizationFactor(double[] yVals) {
        return this.graphHeight / ((float) ((Double) Collections.max(Doubles.asList(yVals))).doubleValue());
    }

    /* access modifiers changed from: protected */
    public synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) this.canvasHeight;
        if (MeasureSpec.getMode(heightMeasureSpec) != 0) {
            height = Math.min(height, MeasureSpec.getSize(heightMeasureSpec));
        }
        setMeasuredDimension(width, height);
    }

    private Point calculateThumbPosition(Canvas canvas, float screenCoord, boolean pressed) {
        Bitmap thumb = (!pressed || this.pressedThumbImage == null) ? this.thumbImage : this.pressedThumbImage;
        return new Point((int) ViewLibUtils.clamp(screenCoord - ((float) (thumb.getWidth() / 2)), 0.0f, (float) (canvas.getWidth() - thumb.getWidth())), (canvas.getHeight() - (this.pressedThumbImage.getHeight() / 2)) - (thumb.getHeight() / 2));
    }

    private void drawHorizontalRangeBar(Canvas canvas) {
        if (Color.alpha(this.horizontalRangeBarColor) != 0) {
            float minThumbX = normalizedToScreen(this.normalizedMinValue);
            float maxThumbX = normalizedToScreen(this.normalizedMaxValue);
            int thumbCenterY = canvas.getHeight() - (this.pressedThumbImage.getHeight() / 2);
            this.paint.setStyle(Style.FILL);
            this.paint.setColor(this.graphColor);
            this.rect.set(0.0f, ((float) thumbCenterY) - (this.horizontalRangeHeight / 2.0f), (float) canvas.getWidth(), ((float) thumbCenterY) + (this.horizontalRangeHeight / 2.0f));
            this.paint.setStrokeCap(Cap.ROUND);
            this.paint.setStrokeWidth(this.horizontalRangeHeight);
            canvas.drawLine(this.horizontalRangeHeight / 2.0f, this.graphBottom, ((float) canvas.getWidth()) - (this.horizontalRangeHeight / 2.0f), this.graphBottom, this.paint);
            this.paint.setStyle(Style.FILL);
            this.paint.setColor(this.horizontalRangeBarColor);
            this.rect.set(minThumbX, ((float) thumbCenterY) - (this.selectedHorizontalRangeHeight / 2.0f), maxThumbX, ((float) thumbCenterY) + (this.selectedHorizontalRangeHeight / 2.0f));
            canvas.drawRect(this.rect, this.paint);
        }
    }
}
