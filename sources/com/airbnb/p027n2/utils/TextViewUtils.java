package com.airbnb.p027n2.utils;

import android.graphics.Paint;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.utils.TextViewUtils */
public class TextViewUtils {
    public static void addReadMoreTextIfNeeded(TextView view, CharSequence readMoreText, int readMoreTextColor, boolean breakAtWords) {
        Layout layout = view.getLayout();
        if (layout != null && layout.getLineCount() > view.getMaxLines()) {
            Paint paint = view.getPaint();
            CharSequence text = view.getText();
            int lastLineStart = layout.getLineStart(Math.min(view.getMaxLines(), layout.getLineCount()) - 1);
            CharSequence lastLine = ViewLibUtils.trim(text.subSequence(lastLineStart, text.length() - 1));
            int width = (view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight();
            String readMoreTextWithEllipsis = view.getResources().getString(R.string.n2_ellipsize_and_read_more, new Object[]{readMoreText});
            float readMoreTextLength = paint.measureText(readMoreTextWithEllipsis);
            int lastLineLength = 1;
            float lastLineContentWidth = 0.0f;
            while (lastLineContentWidth + readMoreTextLength < ((float) width) && lastLineLength < lastLine.length() && lastLine.charAt(lastLineLength) != 10) {
                lastLineContentWidth = paint.measureText(lastLine, 0, lastLineLength);
                lastLineLength++;
            }
            int lastLineLength2 = Math.max(lastLineLength - 2, 0);
            if (breakAtWords) {
                while (lastLineLength2 > 0 && lastLine.charAt(lastLineLength2) != ' ') {
                    lastLineLength2--;
                }
            }
            SpannableStringBuilder ssb = new SpannableStringBuilder(text.subSequence(0, lastLineStart)).append(lastLine.subSequence(0, lastLineLength2)).append(readMoreTextWithEllipsis);
            ssb.setSpan(new ForegroundColorSpan(readMoreTextColor), ssb.length() - (readMoreText == null ? 0 : readMoreText.length()), ssb.length(), 0);
            view.setText(ssb);
        }
    }
}
