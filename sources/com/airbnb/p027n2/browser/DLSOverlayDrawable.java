package com.airbnb.p027n2.browser;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import com.airbnb.p027n2.DLSComponentType;
import com.airbnb.p027n2.components.DLSComponent;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontManager;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.browser.DLSOverlayDrawable */
public class DLSOverlayDrawable extends Drawable {
    private static Paint dividerPaint;
    private static int nameHorizontalPadding;
    private static TextPaint nameTextPaint;
    private static int nameVerticalPadding;
    private static boolean resourcesInitialized;
    private final DLSComponent<?> component;

    public DLSOverlayDrawable(Context context, DLSComponent<?> component2) {
        this.component = component2;
        if (!resourcesInitialized) {
            synchronized (DLSOverlayDrawable.class) {
                if (!resourcesInitialized) {
                    initializeResources(context);
                }
            }
        }
    }

    private void initializeResources(Context context) {
        nameTextPaint = new TextPaint();
        nameTextPaint.setAntiAlias(true);
        nameTextPaint.setColor(-1);
        nameTextPaint.setTextSize((float) ViewLibUtils.dpToPx(context, 11.0f));
        nameTextPaint.setTypeface(FontManager.getTypeface(Font.CircularBold, context));
        nameTextPaint.setShadowLayer(2.0f, 0.0f, 1.0f, 1073741824);
        dividerPaint = new Paint();
        dividerPaint.setColor(1073741824);
        dividerPaint.setStrokeWidth((float) ViewLibUtils.dpToPx(context, 2.0f));
        nameHorizontalPadding = ViewLibUtils.dpToPx(context, 8.0f);
        nameVerticalPadding = ViewLibUtils.dpToPx(context, 4.0f) + ((int) nameTextPaint.getTextSize());
        resourcesInitialized = true;
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(getOverlayColor(this.component.type()));
        canvas.drawText(this.component.name(), (float) nameHorizontalPadding, (float) nameVerticalPadding, nameTextPaint);
        canvas.drawLine(0.0f, (float) canvas.getHeight(), (float) canvas.getWidth(), (float) canvas.getHeight(), dividerPaint);
    }

    public void setAlpha(int alpha) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public int getOpacity() {
        return 0;
    }

    private static int getOverlayColor(DLSComponentType componentType) {
        switch (componentType) {
            case Core:
                return -1996531105;
            case Deprecated:
                return -2008791996;
            default:
                return -1996504269;
        }
    }
}
