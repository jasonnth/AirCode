package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.BigNumberRow */
public class BigNumberRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirTextView primarySubtitleText;
    @BindView
    AirTextView secondarySubtitleText;
    @BindView
    AirTextView titleText;

    public BigNumberRow(Context context) {
        super(context);
        init(null);
    }

    public BigNumberRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BigNumberRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_large_title_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_LargeTitleRow, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_LargeTitleRow_n2_titleText);
        String primarySubtitle = a.getString(R.styleable.n2_LargeTitleRow_n2_primarySubtitleText);
        String secondarySubtitle = a.getString(R.styleable.n2_LargeTitleRow_n2_secondarySubtitleText);
        boolean dividerVisible = a.getBoolean(R.styleable.n2_LargeTitleRow_n2_showDivider, true);
        setTitle((CharSequence) titleText2);
        showDivider(dividerVisible);
        setPrimarySubtitle((CharSequence) primarySubtitle);
        setSecondarySubtitle((CharSequence) secondarySubtitle);
        a.recycle();
    }

    public void setTitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.titleText, text);
    }

    public void setTitle(int titleResId) {
        ViewLibUtils.bindOptionalTextView((TextView) this.titleText, titleResId);
    }

    public void setPrimarySubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.primarySubtitleText, text);
    }

    public void setPrimarySubtitle(int primarySubtitleResId) {
        ViewLibUtils.bindOptionalTextView((TextView) this.primarySubtitleText, primarySubtitleResId);
    }

    public void setSecondarySubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.secondarySubtitleText, text);
    }

    public void setSecondarySubtitle(int secondarySubtitleResId) {
        ViewLibUtils.bindOptionalTextView((TextView) this.secondarySubtitleText, secondarySubtitleResId);
    }

    public void setTitleDrawableRight(int drawableResId, int paddingDimen) {
        this.titleText.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableResId, 0);
        this.titleText.setCompoundDrawablePadding(getResources().getDimensionPixelSize(paddingDimen));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(BigNumberRow row) {
        row.setTitle((CharSequence) "$151");
        row.setPrimarySubtitle((CharSequence) "Nothing paid out yet");
    }
}
