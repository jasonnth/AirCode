package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.support.p002v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import com.airbnb.p027n2.interfaces.Typefaceable;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontHelper;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.primitives.AirAutoCompleteTextView */
public class AirAutoCompleteTextView extends AppCompatAutoCompleteTextView implements Typefaceable {
    public AirAutoCompleteTextView(Context context) {
        super(context);
        init(context, null);
    }

    public AirAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AirAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
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
}
