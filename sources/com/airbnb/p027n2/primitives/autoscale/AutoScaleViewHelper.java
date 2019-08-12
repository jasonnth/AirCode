package com.airbnb.p027n2.primitives.autoscale;

import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.autoscale.AutoScaleViewHelper */
public class AutoScaleViewHelper {
    private int mLines;
    private float mMinTextSize;
    private boolean mNeedsResize;
    private final float mPreferredTextSize;
    private int mSuggestedNumLines;
    private TextPaint mTextPaint = new TextPaint();
    private final TextView textView;

    public AutoScaleViewHelper(TextView textView2, AttributeSet attrs) {
        this.textView = textView2;
        this.mPreferredTextSize = textView2.getTextSize();
        this.mNeedsResize = false;
        TypedArray a = textView2.getContext().obtainStyledAttributes(attrs, R.styleable.n2_AutoScaleTextView);
        this.mSuggestedNumLines = a.getInteger(R.styleable.n2_AutoScaleTextView_n2_suggestedLines, 1);
        this.mMinTextSize = a.getDimension(R.styleable.n2_AutoScaleTextView_n2_minTextSize, -1.0f);
        if (this.mMinTextSize == -1.0f) {
            this.mMinTextSize = 8.0f * textView2.getResources().getDisplayMetrics().density;
        }
        textView2.setLines(this.mSuggestedNumLines);
        this.mNeedsResize = true;
        a.recycle();
    }

    private void resizeText(int widthLimit, int heightLimit) {
        float size;
        boolean done;
        String text = this.textView.getText().toString();
        if (widthLimit > 0 && heightLimit > 0 && text.length() != 0) {
            this.mTextPaint = new TextPaint(this.textView.getPaintFlags());
            this.mTextPaint.setTypeface(this.textView.getTypeface());
            this.mTextPaint.setTextSize(this.mPreferredTextSize);
            float size2 = this.mTextPaint.measureText(text);
            if (((float) widthLimit) > size2) {
                size = this.mPreferredTextSize;
                done = true;
            } else {
                size = (this.mPreferredTextSize * ((float) widthLimit)) / size2;
                done = false;
            }
            if (size < this.mMinTextSize) {
                size = this.mMinTextSize;
                done = true;
            }
            while (!done) {
                this.mTextPaint.setTextSize(size);
                if (this.mTextPaint.measureText(text) <= ((float) widthLimit)) {
                    break;
                }
                size -= 0.5f;
            }
            int height = getTextHeight(this.mTextPaint, text);
            if ((this.mLines + 1) * height < heightLimit) {
                this.mLines++;
                this.textView.setLines(this.mLines);
                resizeText((int) (((float) widthLimit) * (((float) this.mLines) / ((float) (this.mLines - 1)))), heightLimit);
                return;
            }
            if (this.mLines * height > heightLimit && this.mLines > this.mSuggestedNumLines) {
                while (true) {
                    this.mTextPaint.setTextSize(size);
                    if (((float) this.mLines) * ((float) getTextHeight(this.mTextPaint, text)) <= ((float) heightLimit)) {
                        break;
                    }
                    size -= 0.5f;
                }
            }
            this.textView.setTextSize(0, size);
            this.mNeedsResize = false;
        }
    }

    private int getTextHeight(TextPaint paint, String text) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return (int) (((float) bounds.height()) + paint.getFontSpacing());
    }

    public void setMinTextSize(float minTextSize) {
        this.mMinTextSize = minTextSize;
    }

    public void setSuggestedNumlines(int lines) {
        this.mSuggestedNumLines = lines;
    }

    /* access modifiers changed from: protected */
    public void onTextChanged() {
        this.textView.setLines(this.mSuggestedNumLines);
        this.mNeedsResize = true;
        this.textView.requestLayout();
        if (this.mPreferredTextSize > 0.0f) {
            this.textView.setTextSize(0, this.mPreferredTextSize);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(int left, int right) {
        if (this.mNeedsResize) {
            int widthLimit = ((right - left) - this.textView.getCompoundPaddingLeft()) - this.textView.getCompoundPaddingRight();
            int heightLimit = (this.textView.getHeight() - this.textView.getPaddingBottom()) - this.textView.getPaddingTop();
            this.mLines = this.mSuggestedNumLines;
            resizeText(this.mLines * widthLimit, heightLimit);
        }
    }
}
