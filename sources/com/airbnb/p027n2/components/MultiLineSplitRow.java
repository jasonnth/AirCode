package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.MultiLineSplitRow */
public class MultiLineSplitRow extends BaseDividerComponent {
    @BindView
    AirTextView infoText;
    @BindView
    AirTextView titleText;

    public MultiLineSplitRow(Context context) {
        super(context);
    }

    public MultiLineSplitRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiLineSplitRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_multi_line_split_row;
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setInfoText(CharSequence text) {
        this.infoText.setText(text);
    }

    public void setInfoText(int textRes) {
        setInfoText((CharSequence) getResources().getString(textRes));
    }

    public static void mock(MultiLineSplitRow row) {
        row.setTitle((CharSequence) "very long title text very long title text very long title text very long title text very long title text");
        row.setInfoText((CharSequence) "very long info text very long info text very long info text very long info text very long info text very long info text");
        Paris.style(row).applyRegular();
    }

    public static void mockNoTopPadding(MultiLineSplitRow row) {
        row.setTitle((CharSequence) "very long title text very long title text very long title text very long title text very long title text");
        row.setInfoText((CharSequence) "very long info text very long info text very long info text very long info text very long info text very long info text");
        Paris.style(row).applyNoTopPadding();
    }
}
