package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.p027n2.components.InlineInputRow.ErrorDismissalMode;

/* renamed from: com.airbnb.n2.components.InputField */
public final class InputField extends InlineInputRow {
    public InputField(Context context) {
        super(context);
    }

    public InputField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void mock(InputField row) {
        row.setTitle((CharSequence) "Label row");
        row.setHint("Placeholder text");
    }

    public static void mockInputtedText(InputField row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text");
    }

    public static void mockInputtedTextWraps(InputField row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text can allow text to wrap to two lines");
        row.setMaxLines(2);
    }

    public static void mockError(InputField row) {
        row.setTitle((CharSequence) "Label row");
        row.setInputText((CharSequence) "Inputted text");
        row.showError(true);
        row.setError((CharSequence) "This is an error message");
        row.setErrorDismissal(ErrorDismissalMode.MANUAL);
    }

    public static void mockSubtitle(InputField row) {
        row.setTitle((CharSequence) "Label row");
        row.setSubTitleText("Subtitle to talk about some legal copy about stuff that needs to be shown");
        row.setInputText((CharSequence) "Inputted text");
    }
}
