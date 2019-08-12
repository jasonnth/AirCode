package com.airbnb.android.lib.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;

public class EllipsizingTextView extends AirTextView {
    private boolean dontUpdate;
    private String fullText;
    private int maxLines;
    private boolean needToReEllipsize;

    public EllipsizingTextView(Context context) {
        super(context);
    }

    public EllipsizingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getMaxLinesFromAttrs(attrs);
    }

    public EllipsizingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getMaxLinesFromAttrs(attrs);
    }

    private void getMaxLinesFromAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0880R.styleable.EllipsizingTextView);
        int maxLines2 = a.getInteger(C0880R.styleable.EllipsizingTextView_android_maxLines, 0);
        if (maxLines2 > 0) {
            setMaxLines(maxLines2);
        }
        a.recycle();
    }

    public void setMaxLines(int maxLines2) {
        super.setMaxLines(maxLines2);
        this.maxLines = maxLines2;
        this.needToReEllipsize = true;
    }

    public int getMaxLines() {
        return this.maxLines;
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
        if (this.dontUpdate) {
            this.dontUpdate = false;
            return;
        }
        this.fullText = text.toString();
        this.needToReEllipsize = true;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.needToReEllipsize) {
            super.setEllipsize(TruncateAt.END);
            ellipsizeText();
            this.needToReEllipsize = false;
        }
        super.onDraw(canvas);
    }

    @SuppressLint({"NewApi"})
    private void ellipsizeText() {
        post(EllipsizingTextView$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$ellipsizeText$0(EllipsizingTextView ellipsizingTextView) {
        if (ellipsizingTextView.maxLines > 0 && !TextUtils.isEmpty(ellipsizingTextView.getText())) {
            Layout layout = ellipsizingTextView.getLayout();
            int visibleLines = Math.min(ellipsizingTextView.getMaxLines(), layout.getLineCount());
            if (visibleLines == ellipsizingTextView.getMaxLines() && layout.getEllipsisCount(visibleLines - 1) > 0) {
                int ellipsisIndex = layout.getLineStart(visibleLines - 1) + layout.getEllipsisStart(visibleLines - 1);
                ellipsizingTextView.dontUpdate = true;
                ellipsizingTextView.setText(ellipsizingTextView.getText().subSequence(0, ellipsisIndex).toString().trim() + ellipsizingTextView.getContext().getString(C0880R.string.ellipsis));
            }
        }
    }

    public CharSequence getText() {
        return this.fullText;
    }
}
