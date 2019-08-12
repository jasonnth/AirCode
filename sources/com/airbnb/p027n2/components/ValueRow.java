package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.ValueRow */
public class ValueRow extends BaseDividerComponent {
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;
    @BindView
    AirTextView valueText;

    public ValueRow(Context context) {
        super(context);
    }

    public ValueRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ValueRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_value_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text);
    }

    public void setSubtitle(int stringId) {
        setSubtitle((CharSequence) getResources().getString(stringId));
    }

    public void setValue(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.valueText, text);
    }

    public void setValue(int stringId) {
        setValue((CharSequence) getResources().getString(stringId));
    }

    public static void mock(ValueRow row) {
        row.setTitle((CharSequence) "Label row");
        row.setSubtitle((CharSequence) "Some optional subtitle");
        row.setValue((CharSequence) "Some input value");
    }
}
