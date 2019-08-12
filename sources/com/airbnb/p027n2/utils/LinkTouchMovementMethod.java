package com.airbnb.p027n2.utils;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.widget.TextView;

/* renamed from: com.airbnb.n2.utils.LinkTouchMovementMethod */
public class LinkTouchMovementMethod extends LinkMovementMethod {
    private static LinkMovementMethod sInstance;
    private ClickableSpan mPressedSpan;

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
        if (event.getAction() == 0) {
            this.mPressedSpan = getPressedSpan(textView, spannable, event);
            if (this.mPressedSpan != null) {
                setPressed(this.mPressedSpan, true);
                Selection.setSelection(spannable, spannable.getSpanStart(this.mPressedSpan), spannable.getSpanEnd(this.mPressedSpan));
            }
        } else if (event.getAction() == 2) {
            ClickableSpan touchedSpan = getPressedSpan(textView, spannable, event);
            if (!(this.mPressedSpan == null || touchedSpan == this.mPressedSpan)) {
                setPressed(this.mPressedSpan, false);
                this.mPressedSpan = null;
                Selection.removeSelection(spannable);
            }
        } else {
            if (this.mPressedSpan != null) {
                setPressed(this.mPressedSpan, false);
                super.onTouchEvent(textView, spannable, event);
            }
            this.mPressedSpan = null;
            Selection.removeSelection(spannable);
        }
        return true;
    }

    private void setPressed(ClickableSpan span, boolean pressed) {
        if (span instanceof IndexedClickableSpan) {
            ((IndexedClickableSpan) span).setPressed(pressed);
        }
    }

    private ClickableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {
        int x = (((int) event.getX()) - textView.getTotalPaddingLeft()) + textView.getScrollX();
        int y = (((int) event.getY()) - textView.getTotalPaddingTop()) + textView.getScrollY();
        Layout layout = textView.getLayout();
        int off = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
        ClickableSpan[] link = (ClickableSpan[]) spannable.getSpans(off, off, ClickableSpan.class);
        if (link.length > 0) {
            return link[0];
        }
        return null;
    }

    public static MovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new LinkTouchMovementMethod();
        }
        return sInstance;
    }
}
