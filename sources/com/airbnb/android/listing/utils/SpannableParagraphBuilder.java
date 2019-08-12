package com.airbnb.android.listing.utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import com.airbnb.android.core.utils.SpannableUtils;

public class SpannableParagraphBuilder {
    private static final String NEWLINE = "\n";
    private final SpannableStringBuilder builder = new SpannableStringBuilder();
    private final Context context;

    public SpannableParagraphBuilder(Context context2) {
        this.context = context2;
    }

    public SpannableParagraphBuilder appendWithoutTitle(int textRes) {
        this.builder.append(this.context.getString(textRes));
        return this;
    }

    public SpannableParagraphBuilder append(int titleRes, int paragraphRes) {
        if (this.builder.length() > 0) {
            this.builder.append(NEWLINE).append(NEWLINE);
        }
        this.builder.append(SpannableUtils.makeBoldedString(titleRes, this.context)).append(NEWLINE).append(this.context.getString(paragraphRes));
        return this;
    }

    public CharSequence build() {
        return this.builder;
    }
}
