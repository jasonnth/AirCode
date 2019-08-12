package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Space;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.InputSuggestionActionRow */
public final class InputSuggestionActionRow extends BaseDividerComponent {
    @BindView
    AirImageView iconView;
    @BindView
    AirTextView label;
    @BindView
    Space space;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public InputSuggestionActionRow(Context context) {
        super(context);
    }

    public InputSuggestionActionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputSuggestionActionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_input_suggestion_action_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitle, text);
        invalidateSpace();
    }

    public void setSubtitle(int subtitleRes) {
        setSubtitle((CharSequence) getResources().getString(subtitleRes));
    }

    public void setLabel(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.label, text);
        invalidateSpace();
    }

    private void invalidateSpace() {
        boolean isLabelOrIconVisible;
        boolean z = true;
        if (this.label.getVisibility() == 0 || this.iconView.getVisibility() == 0) {
            isLabelOrIconVisible = true;
        } else {
            isLabelOrIconVisible = false;
        }
        Space space2 = this.space;
        if (!TextUtils.isEmpty(this.subtitle.getText()) || !isLabelOrIconVisible) {
            z = false;
        }
        ViewLibUtils.setVisibleIf(space2, z);
    }

    @Deprecated
    public void setIcon(int iconRes) {
        this.iconView.setImageResource(iconRes);
        ViewLibUtils.setVisibleIf(this.iconView, iconRes != 0);
        invalidateSpace();
    }

    public static void mock(InputSuggestionActionRow row) {
        row.setTitle((CharSequence) "Input suggestion action row");
    }

    public static void mockSubtitle(InputSuggestionActionRow row) {
        row.setTitle((CharSequence) "Input suggestion action row");
        row.setSubtitle((CharSequence) "Optional subtitle");
    }

    public static void mockSubtitleLabel(InputSuggestionActionRow row) {
        row.setTitle((CharSequence) "Input suggestion action row");
        row.setSubtitle((CharSequence) "Optional subtitle");
        row.setLabel("Optional label");
    }

    public static void mockLongTitleAndSubtitle(InputSuggestionActionRow row) {
        row.setTitle((CharSequence) "Input suggestion action row with a title that wraps");
        row.setSubtitle((CharSequence) "Optional subtitle that is very long and will wrap");
    }

    public static void mockInverse(InputSuggestionActionRow row) {
        row.setTitle((CharSequence) "Input suggestion action row");
        Paris.style(row).applyInverse();
    }

    public static void mockSubtitleInverse(InputSuggestionActionRow row) {
        row.setTitle((CharSequence) "Input suggestion action row");
        row.setSubtitle((CharSequence) "Optional subtitle");
        Paris.style(row).applyInverse();
    }

    public static void mockSubtitleLabelInverse(InputSuggestionActionRow row) {
        row.setTitle((CharSequence) "Input suggestion action row");
        row.setSubtitle((CharSequence) "Optional subtitle");
        row.setLabel("Optional label");
        Paris.style(row).applyInverse();
    }
}
