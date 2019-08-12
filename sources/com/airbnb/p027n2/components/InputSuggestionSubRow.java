package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.InputSuggestionSubRow */
public class InputSuggestionSubRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    View subRowDivider;
    @BindView
    AirTextView title;
    @BindView
    View verticalDivider;

    public InputSuggestionSubRow(Context context) {
        super(context);
        init(null);
    }

    public InputSuggestionSubRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public InputSuggestionSubRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_input_suggestion_sub_row, this);
        setOrientation(1);
        TypedArray ta = getContext().obtainStyledAttributes(new int[]{16843534});
        setBackground(ta.getDrawable(0));
        ta.recycle();
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_InputSuggestionVerticalRow, 0, 0);
        String titleText = a.getString(R.styleable.n2_InputSuggestionVerticalRow_n2_titleText);
        String boldText = a.getString(R.styleable.n2_InputSuggestionVerticalRow_n2_boldText);
        boolean dividerVisible = a.getBoolean(R.styleable.n2_InputSuggestionVerticalRow_n2_showDivider, true);
        configureText(titleText, boldText);
        showDivider(dividerVisible);
        a.recycle();
    }

    public void invertColors(boolean invertColors) {
        this.title.setHasInvertedColors(invertColors);
        this.divider.setBackgroundResource(invertColors ? R.color.n2_divider_color_inverted_10 : R.color.n2_divider_color);
        this.verticalDivider.setBackgroundResource(invertColors ? R.color.n2_divider_color_inverted_10 : R.color.n2_divider_color);
        this.subRowDivider.setBackgroundResource(invertColors ? R.color.n2_divider_color_inverted_10 : R.color.n2_divider_color);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void showSubRowDivider(boolean showSubRowDivider) {
        ViewLibUtils.setVisibleIf(this.subRowDivider, showSubRowDivider);
    }

    public void configureText(String titleText, String boldText) {
        if (TextUtils.isEmpty(titleText)) {
            return;
        }
        if (!TextUtils.isEmpty(boldText)) {
            this.title.setText(TextUtil.makeCircularBold(getContext(), titleText, boldText));
        } else {
            this.title.setText(titleText);
        }
    }

    public static void mock(InputSuggestionSubRow row) {
        row.configureText("Experiences in San Francisco", "Experiences");
    }
}
