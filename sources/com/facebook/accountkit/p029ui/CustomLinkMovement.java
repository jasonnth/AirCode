package com.facebook.accountkit.p029ui;

import android.text.Layout;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.widget.TextView;

/* renamed from: com.facebook.accountkit.ui.CustomLinkMovement */
final class CustomLinkMovement extends LinkMovementMethod {
    private final OnURLClickedListener listener;

    /* renamed from: com.facebook.accountkit.ui.CustomLinkMovement$OnURLClickedListener */
    interface OnURLClickedListener {
        void onURLClicked(String str);
    }

    public CustomLinkMovement(OnURLClickedListener listener2) {
        this.listener = listener2;
    }

    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        if (event.getAction() == 1) {
            int x = (((int) event.getX()) - widget.getTotalPaddingLeft()) + widget.getScrollX();
            int y = (((int) event.getY()) - widget.getTotalPaddingTop()) + widget.getScrollY();
            Layout layout = widget.getLayout();
            int off = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
            URLSpan[] link = (URLSpan[]) buffer.getSpans(off, off, URLSpan.class);
            if (link.length != 0) {
                this.listener.onURLClicked(link[0].getURL());
            }
        }
        return super.onTouchEvent(widget, buffer, event);
    }
}
