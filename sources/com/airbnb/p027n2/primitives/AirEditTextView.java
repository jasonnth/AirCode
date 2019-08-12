package com.airbnb.p027n2.primitives;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.p002v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.Typefaceable;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontHelper;
import com.airbnb.paris.Paris;
import java.lang.reflect.Field;

/* renamed from: com.airbnb.n2.primitives.AirEditTextView */
public class AirEditTextView extends AppCompatEditText implements Typefaceable {
    private static final int[] STATE_INVERTED = {R.attr.n2_state_inverted};
    private Field cursorDrawableField;
    private boolean invertColors;

    public AirEditTextView(Context context) {
        super(context);
        init(context, null);
    }

    public AirEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AirEditTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public AirEditTextView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            Paris.style(this).apply(attrs);
        }
    }

    public void setFont(Font font) {
        FontHelper.setFont(this, font);
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int extraSpace) {
        int[] state = super.onCreateDrawableState(extraSpace + 1);
        if (this.invertColors) {
            mergeDrawableStates(state, STATE_INVERTED);
        }
        return state;
    }

    public boolean isEmpty() {
        return getText() == null || TextUtils.isEmpty(getText().toString());
    }

    public void invertColors(boolean invertColors2) {
        this.invertColors = invertColors2;
        refreshDrawableState();
    }

    public void setCursorDrawableRes(int cursorDrawableRes) {
        if (this.cursorDrawableField == null) {
            try {
                this.cursorDrawableField = TextView.class.getDeclaredField("mCursorDrawableRes");
                this.cursorDrawableField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                return;
            }
        }
        try {
            this.cursorDrawableField.set(this, Integer.valueOf(cursorDrawableRes));
        } catch (IllegalAccessException e2) {
        }
    }
}
