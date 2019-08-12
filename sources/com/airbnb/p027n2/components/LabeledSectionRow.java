package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.LabeledSectionRow */
public class LabeledSectionRow extends LinearLayout {
    @BindView
    AirTextView actionText;
    @BindView
    AirTextView bodyText;
    @BindView
    AirTextView labelText;
    @BindView
    AirTextView titleText;

    public LabeledSectionRow(Context context) {
        super(context);
        init(null);
    }

    public LabeledSectionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LabeledSectionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_labeled_section_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(0);
        setGravity(48);
        setClickable(false);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_LabeledSectionRow);
        setLabelText((CharSequence) ta.getString(R.styleable.n2_LabeledSectionRow_n2_labelText));
        setTitleText((CharSequence) ta.getString(R.styleable.n2_LabeledSectionRow_n2_titleText));
        setBodyText((CharSequence) ta.getString(R.styleable.n2_LabeledSectionRow_n2_bodyText));
        setActionText((CharSequence) ta.getString(R.styleable.n2_LabeledSectionRow_n2_actionText));
        ta.recycle();
    }

    public void setLabelText(CharSequence text) {
        this.labelText.setText(text);
    }

    public void setLabelText(int stringId) {
        setLabelText((CharSequence) getResources().getString(stringId));
    }

    public void setTitleText(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitleText(int stringId) {
        setTitleText((CharSequence) getResources().getString(stringId));
    }

    public void setBodyText(CharSequence text) {
        this.bodyText.setText(text);
    }

    public void setBodyText(int stringId) {
        setBodyText((CharSequence) getResources().getString(stringId));
    }

    public void setActionText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.actionText, text);
    }

    public void setActionText(int stringId) {
        setActionText((CharSequence) getResources().getString(stringId));
    }

    public void setLabelBackground(int drawableId) {
        this.labelText.setBackgroundResource(drawableId);
    }

    public void setOnClickListener(OnClickListener l) {
        this.actionText.setOnClickListener(l);
    }

    public static void mock(LabeledSectionRow view) {
        view.setLabelText((CharSequence) "Label");
        view.setTitleText((CharSequence) "Title");
        view.setBodyText((CharSequence) "Body");
        view.setActionText((CharSequence) "Action");
    }
}
