package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.LabelRow */
public class LabelRow extends BaseDividerComponent {
    @BindView
    AirTextView label;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public LabelRow(Context context) {
        super(context);
    }

    public LabelRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_label_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_LabelRow, 0, 0);
        String titleText = a.getString(R.styleable.n2_LabelRow_n2_titleText);
        String subtitleText = a.getString(R.styleable.n2_LabelRow_n2_subtitleText);
        String labelText = a.getString(R.styleable.n2_LabelRow_n2_labelText);
        setTitle((CharSequence) titleText);
        setSubtitle((CharSequence) subtitleText);
        setLabel((CharSequence) labelText);
        a.recycle();
    }

    public void setTitle(CharSequence string) {
        ViewLibUtils.setText((TextView) this.title, string);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getResources().getString(stringRes));
    }

    public void setSubtitle(CharSequence string) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitle, string, true);
    }

    public void setSubtitle(int stringRes) {
        setSubtitle((CharSequence) getResources().getString(stringRes));
    }

    public void setLabel(CharSequence string) {
        ViewLibUtils.bindOptionalTextView((TextView) this.label, string, true);
    }

    public void setLabel(int stringRes) {
        setLabel((CharSequence) getResources().getString(stringRes));
    }

    public static void mock(LabelRow row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "Optional subtitle");
        row.setLabel((CharSequence) "Optional label");
    }
}
