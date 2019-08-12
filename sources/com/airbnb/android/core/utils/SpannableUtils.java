package com.airbnb.android.core.utils;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.p027n2.primitives.fonts.CustomFontSpan;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.utils.IndexedClickableSpan;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpannableUtils {
    public static final String COLORED_SUBSTRING_TOKEN = "#%SUBSTRING%#";

    private static class ClickableUrlText extends ClickableSpan {
        private final Context context;
        private final IndexedClickableSpan indexedClickableSpan;
        private final String url;

        private ClickableUrlText(Context context2, String url2, IndexedClickableSpan indexedClickableSpan2) {
            this.context = context2;
            this.url = url2;
            this.indexedClickableSpan = indexedClickableSpan2;
        }

        public void onClick(View view) {
            this.context.startActivity(WebViewIntentBuilder.newBuilder(this.context, this.url).flags(268435456).authenticate().toIntent());
        }

        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            this.indexedClickableSpan.updateDrawState(ds);
        }
    }

    private static class SpanRecord {
        public final int end;
        public final ForegroundColorSpan span;
        public final int start;

        public SpanRecord(int start2, int end2, ForegroundColorSpan span2) {
            this.start = start2;
            this.end = end2;
            this.span = span2;
        }
    }

    public static class UrlText {
        /* access modifiers changed from: private */
        public final String text;
        /* access modifiers changed from: private */
        public final String url;

        public UrlText(String text2, String url2) {
            this.text = text2;
            this.url = url2;
        }
    }

    public static SpannableString makeColoredString(CharSequence text, int color) {
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(color), 0, text.length(), 17);
        return spannable;
    }

    public static SpannableString makeBoldedString(int textRes, Context context) {
        return makeBoldedString((CharSequence) context.getString(textRes), context);
    }

    public static SpannableString makeBoldedString(CharSequence text, Context context) {
        return makeFontString(text, context, Font.CircularBold);
    }

    public static SpannableString makeBookString(CharSequence text, Context context) {
        return makeFontString(text, context, Font.CircularBook);
    }

    public static SpannableString makeFontString(CharSequence text, Context context, Font font) {
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new CustomFontSpan(context, font), 0, text.length(), 17);
        return spannable;
    }

    public static SpannableString appendBoldedSubString(String tokenizedText, String subtext, Context context) {
        SpannableStringBuilder builder = new SpannableStringBuilder(tokenizedText);
        builder.append(subtext);
        builder.setSpan(new CustomFontSpan(context, Font.CircularBold), tokenizedText.length(), builder.length(), 33);
        return new SpannableString(builder);
    }

    public static SpannableString makeBoldedSubString(String fullText, Context context, String... subtexts) {
        SpannableStringBuilder builder = new SpannableStringBuilder(fullText);
        int lastIndex = 0;
        for (String subtext : subtexts) {
            int subTextIndex = fullText.indexOf(subtext, lastIndex);
            if (subTextIndex == -1) {
                break;
            }
            builder.setSpan(new CustomFontSpan(context, Font.CircularBold), subTextIndex, subtext.length() + subTextIndex, 17);
            lastIndex = subTextIndex + subtext.length();
        }
        return new SpannableString(builder);
    }

    public static SpannableString makeColoredSubstring(String tokenizedText, String subtext, int color) {
        int start = tokenizedText.indexOf(COLORED_SUBSTRING_TOKEN);
        if (start == -1) {
            return new SpannableString(tokenizedText);
        }
        SpannableString s = new SpannableString(tokenizedText.replace(COLORED_SUBSTRING_TOKEN, subtext));
        s.setSpan(new ForegroundColorSpan(color), start, start + subtext.length(), 17);
        return s;
    }

    public static SpannableString makeColoredSubstring(int stringId, Context context, int colorRes, String... subtexts) {
        String[] getStringParams = new String[subtexts.length];
        for (int i = 0; i < getStringParams.length; i++) {
            getStringParams[i] = COLORED_SUBSTRING_TOKEN;
        }
        String tokenizedText = context.getString(stringId, (Object[]) getStringParams);
        int color = ContextCompat.getColor(context, colorRes);
        List<SpanRecord> spans = new ArrayList<>();
        int tokenCount = 0;
        while (true) {
            int start = tokenizedText.indexOf(COLORED_SUBSTRING_TOKEN);
            if (start != -1) {
                tokenCount++;
                if (tokenCount > subtexts.length) {
                    break;
                }
                String subtext = subtexts[tokenCount - 1];
                tokenizedText = tokenizedText.replaceFirst(Pattern.quote(COLORED_SUBSTRING_TOKEN), Matcher.quoteReplacement(subtext));
                spans.add(new SpanRecord(start, start + subtext.length(), new ForegroundColorSpan(color)));
            } else {
                break;
            }
        }
        if (!BuildHelper.isDevelopmentBuild() || subtexts.length == tokenCount) {
            SpannableString spannableString = new SpannableString(tokenizedText);
            for (SpanRecord spanRecord : spans) {
                spannableString.setSpan(spanRecord.span, spanRecord.start, spanRecord.end, 17);
            }
            return spannableString;
        }
        throw new RuntimeException("occurrences of #%SUBSTRING%#(" + tokenCount + ") not the same as the size of subtexts array (" + subtexts.length + ")");
    }

    public static void appendImageSpan(Context context, SpannableStringBuilder builder, int drawableRes) {
        int length = builder.length();
        builder.append("�").setSpan(new ImageSpan(context, drawableRes, 1), length, length + 1, 17);
    }

    public static SpannableString createClickableUrls(Context context, String body, List<UrlText> links) {
        return createColoredClickableUrls(context, body, links, C0716R.color.c_rausch);
    }

    public static SpannableString createColoredClickableUrls(Context context, String body, List<UrlText> links, int color) {
        SpannableString spannableString = new SpannableString(body);
        for (UrlText link : links) {
            int indexOfText = body.indexOf(link.text);
            if (indexOfText != -1) {
                spannableString.setSpan(new ClickableUrlText(context, link.url, new IndexedClickableSpan(context, indexOfText, Integer.valueOf(ContextCompat.getColor(context, color)), true, C0716R.color.n2_transparent)), indexOfText, link.text.length() + indexOfText, 33);
            }
        }
        return spannableString;
    }
}
