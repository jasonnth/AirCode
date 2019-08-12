package com.jumio.p311nv.gui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;

/* renamed from: com.jumio.nv.gui.TextOverlayView */
public class TextOverlayView {

    /* renamed from: a */
    private Paint f3381a;

    /* renamed from: b */
    private String[] f3382b;

    /* renamed from: c */
    private float f3383c;

    /* renamed from: d */
    private float f3384d;

    public TextOverlayView() {
        this.f3381a = null;
        this.f3382b = null;
        this.f3383c = 0.0f;
        this.f3384d = 0.0f;
        this.f3381a = new Paint();
        this.f3381a.setAntiAlias(true);
    }

    public void setColor(int i) {
        this.f3381a.setColor(i);
    }

    public void setAlpha(int i) {
        this.f3381a.setAlpha(i);
    }

    public void setDropShadow(int i) {
        this.f3381a.setShadowLayer(1.0f, 1.0f, 1.0f, i);
    }

    public void setStyle(Style style) {
        this.f3381a.setStyle(style);
    }

    public void setTextSize(float f) {
        this.f3381a.setTextSize(f);
    }

    public float getTextSize() {
        return this.f3381a.getTextSize();
    }

    public void setTypeface(Typeface typeface) {
        this.f3381a.setTypeface(typeface);
    }

    public void setText(String str) {
        this.f3382b = str.split("\n");
    }

    public float measureText() {
        float f = 0.0f;
        if (!(this.f3382b == null || this.f3382b.length == 0)) {
            for (String measureText : this.f3382b) {
                float measureText2 = this.f3381a.measureText(measureText);
                if (measureText2 > f) {
                    f = measureText2;
                }
            }
        }
        return f;
    }

    public float measureTextHeight() {
        return this.f3381a.descent() - this.f3381a.ascent();
    }

    public void setPosition(float f, float f2) {
        this.f3383c = f;
        this.f3384d = f2;
    }

    public void draw(Canvas canvas) {
        if (this.f3382b != null) {
            int textSize = (int) getTextSize();
            int length = ((this.f3382b.length - 1) * textSize) / 2;
            for (int i = 0; i < this.f3382b.length; i++) {
                canvas.drawText(this.f3382b[i], this.f3383c, (this.f3384d - ((float) length)) + ((float) (i * textSize)), this.f3381a);
            }
        }
    }
}
