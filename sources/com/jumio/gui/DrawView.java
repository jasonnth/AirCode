package com.jumio.gui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class DrawView extends RelativeLayout {
    private DrawViewInterface drawViewInterface;

    public interface DrawViewInterface {
        void addChildren(ViewGroup viewGroup);

        void draw(Canvas canvas);

        Rect getOverlayBounds();

        void measure(int i, int i2);
    }

    public DrawView(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    @TargetApi(21)
    public DrawView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWillNotDraw(false);
    }

    public void setDrawViewInterface(DrawViewInterface drawViewInterface2) {
        this.drawViewInterface = drawViewInterface2;
        if (drawViewInterface2 != null) {
            drawViewInterface2.addChildren(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        if (w != 0 && h != 0 && this.drawViewInterface != null) {
            this.drawViewInterface.measure(w, h);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.drawViewInterface != null) {
            this.drawViewInterface.draw(canvas);
        }
    }
}
