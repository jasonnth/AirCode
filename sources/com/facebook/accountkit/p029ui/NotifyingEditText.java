package com.facebook.accountkit.p029ui;

import android.content.Context;
import android.support.p002v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

/* renamed from: com.facebook.accountkit.ui.NotifyingEditText */
public final class NotifyingEditText extends AppCompatEditText {
    /* access modifiers changed from: private */
    public OnKeyListener onSoftKeyListener;
    private PasteListener pasteListener;

    /* renamed from: com.facebook.accountkit.ui.NotifyingEditText$NotifyingInputConnection */
    private class NotifyingInputConnection extends InputConnectionWrapper {
        public NotifyingInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            boolean handled;
            if (NotifyingEditText.this.onSoftKeyListener != null) {
                boolean handled2 = NotifyingEditText.this.onSoftKeyListener.onKey(NotifyingEditText.this, 67, new KeyEvent(0, 67));
                if (NotifyingEditText.this.onSoftKeyListener.onKey(NotifyingEditText.this, 67, new KeyEvent(1, 67)) || handled2) {
                    handled = true;
                } else {
                    handled = false;
                }
                if (handled) {
                    return true;
                }
            }
            return super.deleteSurroundingText(beforeLength, afterLength);
        }

        public boolean sendKeyEvent(KeyEvent event) {
            return (NotifyingEditText.this.onSoftKeyListener != null && NotifyingEditText.this.onSoftKeyListener.onKey(NotifyingEditText.this, event.getKeyCode(), event)) || super.sendKeyEvent(event);
        }
    }

    /* renamed from: com.facebook.accountkit.ui.NotifyingEditText$PasteListener */
    public interface PasteListener {
        void onTextPaste();
    }

    public NotifyingEditText(Context context) {
        super(context);
    }

    public NotifyingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotifyingEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnSoftKeyListener(OnKeyListener onSoftKeyListener2) {
        this.onSoftKeyListener = onSoftKeyListener2;
    }

    public boolean onTextContextMenuItem(int id) {
        boolean result = super.onTextContextMenuItem(id);
        switch (id) {
            case 16908322:
                if (this.pasteListener != null) {
                    this.pasteListener.onTextPaste();
                    break;
                }
                break;
        }
        return result;
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new NotifyingInputConnection(super.onCreateInputConnection(outAttrs), true);
    }

    public void setPasteListener(PasteListener pasteHandler) {
        this.pasteListener = pasteHandler;
    }
}
