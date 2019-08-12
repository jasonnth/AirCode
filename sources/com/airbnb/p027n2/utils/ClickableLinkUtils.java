package com.airbnb.p027n2.utils;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.airbnb.p027n2.interfaces.LinkClickableTextView;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;

/* renamed from: com.airbnb.n2.utils.ClickableLinkUtils */
public class ClickableLinkUtils {
    public static void setupClickableTextView(TextView textView, String htmlText, LinkOnClickListener linkOnClickListener, int pressedColor, boolean underlineLinks) {
        CharSequence sequence = TextUtil.fromHtmlSafe(htmlText);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        ForegroundColorSpan[] colorSpans = (ForegroundColorSpan[]) strBuilder.getSpans(0, sequence.length(), ForegroundColorSpan.class);
        for (int i = 0; i < colorSpans.length; i++) {
            ForegroundColorSpan span = colorSpans[i];
            strBuilder.setSpan(new IndexedClickableSpan(textView.getContext(), i, Integer.valueOf(span.getForegroundColor()), underlineLinks, pressedColor), strBuilder.getSpanStart(span), strBuilder.getSpanEnd(span), strBuilder.getSpanFlags(span));
            strBuilder.removeSpan(span);
        }
        setLinkClickListener(textView, linkOnClickListener);
        textView.setMovementMethod(LinkTouchMovementMethod.getInstance());
        textView.setText(strBuilder);
    }

    public static void setupClickableTextView(TextView textView, String htmlText, int pressedColor, LinkOnClickListener linkOnClickListener) {
        setupClickableTextView(textView, htmlText, linkOnClickListener, pressedColor, false);
    }

    public static void setupClickableTextView(TextView textView, String fullText, String linkedText, int pressedColor, LinkOnClickListener linkOnClickListener) {
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(fullText);
        int linkIndex = 0;
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = fullText.indexOf(linkedText, lastIndex);
            if (lastIndex != -1) {
                strBuilder.setSpan(new IndexedClickableSpan(textView.getContext(), linkIndex, pressedColor), lastIndex, linkedText.length() + lastIndex, 33);
                lastIndex += linkedText.length();
                linkIndex++;
            }
        }
        setLinkClickListener(textView, linkOnClickListener);
        textView.setMovementMethod(LinkTouchMovementMethod.getInstance());
        textView.setText(strBuilder);
    }

    public static void setupClickableTextView(TextView textView, String fullText, String linkedText, int normalColor, int pressedColor, LinkOnClickListener linkOnClickListener, boolean underlineLinks) {
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(fullText);
        int linkIndex = 0;
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = fullText.indexOf(linkedText, lastIndex);
            if (lastIndex != -1) {
                strBuilder.setSpan(new IndexedClickableSpan(textView.getContext(), linkIndex, Integer.valueOf(normalColor), underlineLinks, pressedColor), lastIndex, linkedText.length() + lastIndex, 33);
                lastIndex += linkedText.length();
                linkIndex++;
            }
        }
        setLinkClickListener(textView, linkOnClickListener);
        textView.setFocusableInTouchMode(true);
        textView.setMovementMethod(LinkTouchMovementMethod.getInstance());
        textView.setText(strBuilder);
    }

    private static void setLinkClickListener(TextView textView, LinkOnClickListener linkOnClickListener) {
        if (textView instanceof LinkClickableTextView) {
            ((LinkClickableTextView) textView).setOnLinkClickListener(linkOnClickListener);
            return;
        }
        throw new IllegalStateException(textView.getClass() + " must implement LinkClickableTextView");
    }
}
