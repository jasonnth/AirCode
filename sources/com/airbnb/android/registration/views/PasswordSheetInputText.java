package com.airbnb.android.registration.views;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.registration.C1562R;
import com.airbnb.p027n2.components.SheetInputText;

public class PasswordSheetInputText extends SheetInputText {
    public PasswordSheetInputText(Context context) {
        super(context);
    }

    public PasswordSheetInputText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordSheetInputText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int getPositiveActionLabel() {
        return C1562R.string.registration_password_show;
    }

    /* access modifiers changed from: protected */
    public int getNegativeActionLabel() {
        return C1562R.string.registration_password_hide;
    }
}
