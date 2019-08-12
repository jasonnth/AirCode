package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.p027n2.components.InlineInputRow.ErrorDismissalMode;

/* renamed from: com.airbnb.n2.components.InlineMultilineInputRow */
public final class InlineMultilineInputRow extends InlineInputRow {
    public InlineMultilineInputRow(Context context) {
        super(context);
    }

    public InlineMultilineInputRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InlineMultilineInputRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void mock(InlineMultilineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setHint("Placeholder text");
    }

    public static void mockInputtedText(InlineMultilineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text");
    }

    public static void mockInputtedTextWraps(InlineMultilineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text can allow text to wrap to multiple lines");
    }

    public static void mockError(InlineMultilineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text");
        row.showError(true);
        row.setError((CharSequence) "This is an error message");
        row.setErrorDismissal(ErrorDismissalMode.MANUAL);
    }

    public static void mockSubtitle(InlineMultilineInputRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setSubTitleText("Subtitle to talk about some legal copy about stuff that needs to be shown");
        row.setInputText((CharSequence) "Inputted text");
    }
}
