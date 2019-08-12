package com.airbnb.p027n2.utils;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.utils.RowMeasurementUtils */
public class RowMeasurementUtils {
    public static boolean resizeRowText(ViewGroup textContainer, AirTextView titleTextView, AirTextView inputTextView, int minInputTextWidthPx) {
        return resizeRowText(textContainer, titleTextView, inputTextView, minInputTextWidthPx, 0);
    }

    public static boolean resizeRowText(ViewGroup textContainer, AirTextView titleTextView, AirTextView inputTextView, int minInputTextWidthPx, int additionalContentWidthPx) {
        boolean needsToShortenTitleText;
        boolean needsToShortenInputText;
        int innerWidth = (textContainer.getMeasuredWidth() - textContainer.getPaddingLeft()) - textContainer.getPaddingRight();
        int minInputTextWidthPx2 = Math.min(inputTextView.getMeasuredWidth(), minInputTextWidthPx);
        int maxTitleWidth = (innerWidth - minInputTextWidthPx2) - additionalContentWidthPx;
        int titleTextWidth = titleTextView.getMeasuredWidth();
        if (titleTextView.getMeasuredWidth() > maxTitleWidth) {
            needsToShortenTitleText = true;
        } else {
            needsToShortenTitleText = false;
        }
        if (needsToShortenTitleText) {
            LayoutParams lp = titleTextView.getLayoutParams();
            lp.width = maxTitleWidth;
            titleTextWidth = maxTitleWidth;
            titleTextView.setLayoutParams(lp);
        }
        if (inputTextView.getMeasuredWidth() + titleTextWidth + additionalContentWidthPx > innerWidth) {
            needsToShortenInputText = true;
        } else {
            needsToShortenInputText = false;
        }
        if (needsToShortenInputText) {
            LayoutParams lp2 = inputTextView.getLayoutParams();
            lp2.width = Math.max(minInputTextWidthPx2, (innerWidth - titleTextWidth) - additionalContentWidthPx);
            inputTextView.setLayoutParams(lp2);
        }
        if (needsToShortenInputText || needsToShortenTitleText) {
            return true;
        }
        return false;
    }
}
