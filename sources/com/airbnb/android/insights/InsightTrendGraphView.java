package com.airbnb.android.insights;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.p000v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.core.models.ActionCardMonthlyMarketDemand;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontManager;
import java.util.ArrayList;
import java.util.List;

public class InsightTrendGraphView extends View {
    private static final float GRAPH_HEIGHT_FACTOR = 0.6f;
    private final int NUM_TICKS = 11;
    private final Point curPoint = new Point();
    private String firstMonthText;
    @BindDimen
    int graphAndTickPadding;
    private int graphHeight;
    @BindDimen
    int graphStrokeWidth;
    private float maxValue;
    private float minValue;
    private final TextPaint monthTextPaint = new TextPaint();
    private final Paint monthTickMarkPaint = new Paint();
    private final List<ActionCardMonthlyMarketDemand> monthlyMarketDemands = new ArrayList();
    @BindDimen
    int padding;
    private final Path pageViewsGraph = new Path();
    private final Paint pageViewsGraphPaint = new Paint();
    private final Point prevControlPoint = new Point();
    private String secondMonthText;
    private int startTextY;
    private int startTickY;
    private int textHeight;
    private final Rect textRect = new Rect();
    private String thirdMonthText;
    @BindDimen
    int tickHeight;
    @BindDimen
    int tickWidth;
    private int viewWidth;
    private float xNormalizationValue;
    private float yNormalizationValue;

    public InsightTrendGraphView(Context context) {
        super(context);
        init();
    }

    public InsightTrendGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InsightTrendGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ButterKnife.bind((View) this);
        this.pageViewsGraphPaint.setColor(ContextCompat.getColor(getContext(), C6552R.color.n2_babu));
        this.pageViewsGraphPaint.setStrokeWidth((float) this.graphStrokeWidth);
        this.pageViewsGraphPaint.setStyle(Style.STROKE);
        this.pageViewsGraphPaint.setAntiAlias(true);
        this.monthTickMarkPaint.setColor(ContextCompat.getColor(getContext(), C6552R.color.c_foggy_light));
        this.monthTickMarkPaint.setStrokeWidth((float) this.tickWidth);
        this.monthTickMarkPaint.setStyle(Style.STROKE);
        this.monthTextPaint.setColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.monthTextPaint.setTextSize((float) getResources().getDimensionPixelSize(C6552R.dimen.insight_month_text_size));
        this.monthTextPaint.setTypeface(FontManager.getTypeface(Font.CircularBook, getContext()));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int nextX;
        int nextY;
        this.pageViewsGraph.reset();
        this.viewWidth = getWidth();
        this.graphHeight = ((((getHeight() - (this.padding * 2)) - this.tickHeight) - this.textHeight) - (this.padding * 2)) - this.graphAndTickPadding;
        this.startTickY = this.graphHeight + this.graphAndTickPadding;
        this.startTextY = this.startTickY + this.tickHeight;
        this.xNormalizationValue = (float) (this.viewWidth / (this.monthlyMarketDemands.size() - 2));
        this.yNormalizationValue = ((float) this.graphHeight) / (this.maxValue - this.minValue);
        for (int i = 0; i < this.monthlyMarketDemands.size(); i++) {
            this.curPoint.set(getXValue(i), getYValue(i));
            if (i == 0) {
                this.pageViewsGraph.moveTo((float) this.curPoint.x, (float) this.curPoint.y);
                int nextX2 = getXValue(i + 1);
                this.prevControlPoint.set(this.curPoint.x + ((nextX2 - this.curPoint.x) / 3), this.curPoint.y + ((getYValue(i + 1) - this.curPoint.y) / 3));
            } else {
                int prevX = getXValue(i - 1);
                int prevY = getYValue(i - 1);
                if (i != this.monthlyMarketDemands.size() - 1) {
                    nextX = getXValue(i + 1);
                    nextY = getYValue(i + 1);
                } else {
                    nextX = this.curPoint.x;
                    nextY = this.curPoint.y;
                }
                int dX = (nextX - prevX) / 3;
                int dY = (nextY - prevY) / 3;
                this.pageViewsGraph.cubicTo((float) this.prevControlPoint.x, (float) this.prevControlPoint.y, (float) (this.curPoint.x - dX), (float) (this.curPoint.y - dY), (float) this.curPoint.x, (float) this.curPoint.y);
                this.prevControlPoint.set(this.curPoint.x + dX, this.curPoint.y + dY);
            }
        }
        canvas.drawPath(this.pageViewsGraph, this.pageViewsGraphPaint);
        drawMonthLabels(canvas);
        drawMonthTicks(canvas);
    }

    private int getXValue(int position) {
        return (int) (((float) position) * this.xNormalizationValue);
    }

    private int getYValue(int position) {
        return (int) (((float) this.graphHeight) - (((((ActionCardMonthlyMarketDemand) this.monthlyMarketDemands.get(position)).getPageViews() - this.minValue) * this.yNormalizationValue) * GRAPH_HEIGHT_FACTOR));
    }

    private void drawMonthLabels(Canvas canvas) {
        int secondTextX = (int) (((float) (this.viewWidth / 2)) - (this.monthTextPaint.measureText(this.secondMonthText) / 2.0f));
        int thirdTextX = (int) ((((float) this.viewWidth) - (this.monthTextPaint.measureText(this.thirdMonthText) / 2.0f)) - ((float) (this.viewWidth / 10)));
        canvas.drawText(this.firstMonthText, (float) ((int) (((float) (this.padding + (this.viewWidth / 10))) - (this.monthTextPaint.measureText(this.firstMonthText) / 2.0f))), (float) (this.startTextY + this.textHeight + this.padding), this.monthTextPaint);
        canvas.drawText(this.secondMonthText, (float) secondTextX, (float) (this.startTextY + this.textHeight + this.padding), this.monthTextPaint);
        canvas.drawText(this.thirdMonthText, (float) thirdTextX, (float) (this.startTextY + this.textHeight + this.padding), this.monthTextPaint);
    }

    private void drawMonthTicks(Canvas canvas) {
        int xIncrement = (this.viewWidth - (this.tickWidth * 2)) / 10;
        for (int i = 0; i < 11; i++) {
            this.pageViewsGraph.reset();
            int xPos = (i * xIncrement) + (this.tickWidth / 2);
            this.pageViewsGraph.moveTo((float) xPos, (float) this.startTickY);
            this.pageViewsGraph.lineTo((float) xPos, (float) (this.startTickY + this.tickHeight));
            canvas.drawPath(this.pageViewsGraph, this.monthTickMarkPaint);
        }
    }
}
