package com.airbnb.android.contentframework.utils;

import android.text.TextUtils;

public final class StoryUtils {
    private static final char NEW_LINE_CHAR = '\n';
    private static final String NEW_LINE_STRING = "\n";

    private StoryUtils() {
    }

    public static String removeExtraNewLinesFromBodyTextForPosting(String bodyText) {
        StringBuilder bodyTextBuilder = new StringBuilder();
        int i = 0;
        while (i < bodyText.length()) {
            char c = bodyText.charAt(i);
            bodyTextBuilder.append(c);
            if (c == 10) {
                while (true) {
                    i++;
                    if (i >= bodyText.length() || bodyText.charAt(i) != 10) {
                        break;
                    }
                }
            } else {
                i++;
            }
        }
        if (bodyTextBuilder.charAt(bodyTextBuilder.length() - 1) == 10) {
            bodyTextBuilder.deleteCharAt(bodyTextBuilder.length() - 1);
        }
        return bodyTextBuilder.toString();
    }

    public static CharSequence addExtraNewLineToBodyTextForRendering(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        String[] splitString = text.toString().split(NEW_LINE_STRING);
        StringBuilder sb = new StringBuilder(text.length() + splitString.length);
        for (int i = 0; i < splitString.length; i++) {
            sb.append(splitString[i]);
            if (i != splitString.length - 1) {
                sb.append(NEW_LINE_CHAR).append(NEW_LINE_CHAR);
            }
        }
        return sb.toString();
    }
}
