package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.InputSuggestionActionRowChina */
public final class InputSuggestionActionRowChina extends BaseDividerComponent {
    @BindView
    AirTextView label;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public InputSuggestionActionRowChina(Context context) {
        super(context);
    }

    public InputSuggestionActionRowChina(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputSuggestionActionRowChina(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_input_suggestion_action_row_china;
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitle(int subtitleRes) {
        setSubtitle((CharSequence) getResources().getString(subtitleRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitle, !TextUtils.isEmpty(text));
        this.subtitle.setText(text);
    }

    public void setLabel(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.label, !TextUtils.isEmpty(text));
        this.label.setText(text);
    }

    public static void mock(InputSuggestionActionRowChina row) {
        row.setTitle((CharSequence) "Title");
        row.setSubtitle((CharSequence) "Optional subtitle");
        row.setLabel("Optional label");
    }
}
