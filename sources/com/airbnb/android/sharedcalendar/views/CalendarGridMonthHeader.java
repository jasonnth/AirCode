package com.airbnb.android.sharedcalendar.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.android.sharedcalendar.C1576R;
import com.airbnb.android.utils.DrawingUtils;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontManager;

public class CalendarGridMonthHeader extends View {
    private String[] dayOfWeekInitials;
    private final Paint dayTextPaint;
    @BindDimen
    int dayTextSize;
    private final Paint gridLinesPaint;
    private String month;
    @BindDimen
    int monthLeftPadding;
    private final Paint monthTextPaint;
    @BindDimen
    int monthTextSize;
    private final Rect rect;
    @BindDimen
    int strokeWidth;
    @BindDimen
    int verticalPadding;

    public CalendarGridMonthHeader(Context context) {
        this(context, null);
    }

    public CalendarGridMonthHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarGridMonthHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.dayTextPaint = new Paint();
        this.monthTextPaint = new Paint();
        this.gridLinesPaint = new Paint();
        this.rect = new Rect();
        init();
    }

    private void init() {
        ButterKnife.bind((View) this);
        Resources r = getContext().getResources();
        Typeface circularLightTf = FontManager.getTypeface(Font.CircularLight, getContext());
        this.dayTextPaint.setAntiAlias(true);
        this.dayTextPaint.setTextSize((float) this.dayTextSize);
        this.dayTextPaint.setStyle(Style.FILL);
        this.dayTextPaint.setFakeBoldText(false);
        this.dayTextPaint.setColor(r.getColor(C1576R.color.n2_hof));
        this.dayTextPaint.setTypeface(circularLightTf);
        this.monthTextPaint.set(this.dayTextPaint);
        this.monthTextPaint.setTextSize((float) this.monthTextSize);
        this.gridLinesPaint.setAntiAlias(true);
        this.gridLinesPaint.setStrokeWidth((float) this.strokeWidth);
        this.gridLinesPaint.setStyle(Style.STROKE);
        this.gridLinesPaint.setColor(ContextCompat.getColor(getContext(), C1576R.color.n2_background_gray));
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int monthTextBaselineY = 0;
        if (this.month != null) {
            this.monthTextPaint.getTextBounds(this.month, 0, this.month.length(), this.rect);
            monthTextBaselineY = (-this.rect.top) + this.verticalPadding;
            canvas.drawText(this.month, (float) this.monthLeftPadding, (float) monthTextBaselineY, this.monthTextPaint);
        }
        if (this.dayOfWeekInitials.length > 0) {
            int cellWidth = getWidth() / this.dayOfWeekInitials.length;
            int cellHeight = getHeight() - monthTextBaselineY;
            for (int i = 0; i < this.dayOfWeekInitials.length; i++) {
                DrawingUtils.drawTextCentred(canvas, this.dayTextPaint, this.dayOfWeekInitials[i], (float) ((cellWidth * i) + (cellWidth / 2)), (float) (monthTextBaselineY + (cellHeight / 2)));
            }
        }
        canvas.drawLine(0.0f, (float) (getHeight() - (this.strokeWidth / 2)), (float) getWidth(), (float) (getHeight() - (this.strokeWidth / 2)), this.gridLinesPaint);
    }

    public void setMonth(String month2) {
        this.month = month2;
    }

    public void setDayOfWeekInitials(String[] dayOfWeekInitials2) {
        this.dayOfWeekInitials = dayOfWeekInitials2;
    }
}
