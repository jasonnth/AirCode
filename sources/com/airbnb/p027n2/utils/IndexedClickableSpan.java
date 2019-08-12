package com.airbnb.p027n2.utils;

import android.content.Context;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.airbnb.p027n2.interfaces.LinkClickableTextView;

/* renamed from: com.airbnb.n2.utils.IndexedClickableSpan */
public class IndexedClickableSpan extends ClickableSpan {
    boolean isPressed;
    private final int mLinkIndex;
    private final Integer normalColor;
    private final int pressedColor;
    private final boolean underlineLinks;

    public IndexedClickableSpan(Context context, int index, Integer normalColor2, boolean underlineLinks2, int pressedColor2) {
        this.mLinkIndex = index;
        this.pressedColor = context.getResources().getColor(pressedColor2);
        this.normalColor = normalColor2;
        this.underlineLinks = underlineLinks2;
    }

    public IndexedClickableSpan(Context context, int index, int pressedColor2) {
        this(context, index, null, false, pressedColor2);
    }

    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        if (this.normalColor != null) {
            ds.setColor(this.isPressed ? this.pressedColor : this.normalColor.intValue());
        }
        ds.setUnderlineText(this.underlineLinks);
        ds.bgColor = this.isPressed ? this.pressedColor : 0;
    }

    public void onClick(View view) {
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text instanceof Spannable) {
                Selection.removeSelection((Spannable) text);
            }
        }
        if (view instanceof LinkClickableTextView) {
            ((LinkClickableTextView) view).onLinkClick(this.mLinkIndex);
        }
    }

    public void setPressed(boolean pressed) {
        this.isPressed = pressed;
    }
}
