package com.airbnb.p027n2.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.BaseComponent */
public abstract class BaseComponent extends FrameLayout {
    int dividerHeight;
    private Paint paint;
    private boolean showDivider;

    /* access modifiers changed from: protected */
    public abstract int layout();

    public BaseComponent(Context context) {
        super(context);
        init(null);
    }

    public BaseComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BaseComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        if (this.paint != null) {
            throw new IllegalStateException("View was already init'd");
        }
        setClipChildren(false);
        setClipToPadding(false);
        this.paint = new Paint(1);
        this.paint.setStyle(Style.FILL);
        inflate(getContext(), layout(), this);
        ButterKnife.bind((View) this);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.showDivider) {
            canvas.drawRect((float) getPaddingLeft(), (float) (getHeight() - this.dividerHeight), (float) (getWidth() - getPaddingRight()), (float) getHeight(), this.paint);
        }
    }

    /* access modifiers changed from: protected */
    public void showDivider(boolean showDivider2) {
        this.showDivider = showDivider2;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void setDividerColor(int dividerColor) {
        this.paint.setColor(dividerColor);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void setDividerColorRes(int dividerColor) {
        setDividerColor(ContextCompat.getColor(getContext(), dividerColor));
    }

    /* access modifiers changed from: protected */
    public void setDividerHeight(int dividerHeight2) {
        this.dividerHeight = dividerHeight2;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void setDividerHeightRes(int dividerHeight2) {
        setDividerHeight(getResources().getDimensionPixelSize(dividerHeight2));
    }

    public void setOnClickListener(OnClickListener listener) {
        super.setOnClickListener(listener);
        setClickable(listener != null);
    }

    public void setOnLongClickListener(OnLongClickListener listener) {
        super.setOnLongClickListener(listener);
        setLongClickable(listener != null);
    }

    public void setPaddingTop(int padding) {
        ViewLibUtils.setPaddingTop(this, padding);
    }

    public void setPaddingTopRes(int padding) {
        setPaddingTop(getResources().getDimensionPixelSize(padding));
    }

    public void setPaddingBottom(int padding) {
        ViewLibUtils.setPaddingBottom(this, padding);
    }

    public void setPaddingBottomRes(int padding) {
        setPaddingBottom(getResources().getDimensionPixelSize(padding));
    }

    public void setPaddingVertical(int padding) {
        ViewLibUtils.setPaddingVertical(this, padding);
    }

    public void setPaddingVerticalRes(int padding) {
        setPaddingVertical(getResources().getDimensionPixelSize(padding));
    }
}
