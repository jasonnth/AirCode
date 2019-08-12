package com.airbnb.p027n2.components.onboarding_overlay;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;

/* renamed from: com.airbnb.n2.components.onboarding_overlay.CustomBulletSpan */
public class CustomBulletSpan implements LeadingMarginSpan {
    private static Path sBulletPath = null;
    private final int bulletRadius;
    private final int color;
    private final int gapWidth;
    private final boolean wantColor;

    public CustomBulletSpan() {
        this.bulletRadius = 3;
        this.gapWidth = 2;
        this.wantColor = false;
        this.color = 0;
    }

    public CustomBulletSpan(int gapWidth2, int bulletRadius2) {
        this.gapWidth = gapWidth2;
        this.bulletRadius = bulletRadius2;
        this.wantColor = false;
        this.color = 0;
    }

    public int getLeadingMargin(boolean first) {
        return (this.bulletRadius * 2) + this.gapWidth;
    }

    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout l) {
        if (((Spanned) text).getSpanStart(this) == start) {
            Style style = p.getStyle();
            int oldcolor = 0;
            if (this.wantColor) {
                oldcolor = p.getColor();
                p.setColor(this.color);
            }
            p.setStyle(Style.FILL);
            if (c.isHardwareAccelerated()) {
                if (sBulletPath == null) {
                    sBulletPath = new Path();
                    sBulletPath.addCircle(0.0f, 0.0f, 1.2f * ((float) this.bulletRadius), Direction.CW);
                }
                c.save();
                c.translate(((float) x) + (((float) dir) * ((((float) this.bulletRadius) * 1.2f) + 1.0f)), ((float) (top + bottom)) / 2.0f);
                c.drawPath(sBulletPath, p);
                c.restore();
            } else {
                c.drawCircle((float) (((this.bulletRadius + 1) * dir) + x), ((float) (top + bottom)) / 2.0f, (float) this.bulletRadius, p);
            }
            if (this.wantColor) {
                p.setColor(oldcolor);
            }
            p.setStyle(style);
        }
    }
}
