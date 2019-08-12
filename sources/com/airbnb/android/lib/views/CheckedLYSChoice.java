package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;

public class CheckedLYSChoice extends LinearLayout {
    private CheckBox mCheckBox;
    private String mDefaultDescriptionText;
    private String mDefaultTitleText;
    float mDetailsTextSize;
    private TextView mDetailsTextView;
    float mTitleTextSize;
    private TextView mTitleTextView;

    public CheckedLYSChoice(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(C0880R.layout.lys_choice_checked, this);
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0880R.styleable.CheckedTwoLineSettings);
        setupViews(a);
        a.recycle();
    }

    private void setupViews(TypedArray a) {
        this.mTitleTextView = (TextView) findViewById(C0880R.C0882id.txt_title);
        this.mDefaultTitleText = a.getString(C0880R.styleable.CheckedTwoLineSettings_title_text);
        this.mTitleTextView.setText(this.mDefaultTitleText);
        this.mDefaultDescriptionText = a.getString(C0880R.styleable.CheckedTwoLineSettings_details_text);
        this.mDetailsTextView = (TextView) findViewById(C0880R.C0882id.txt_desc);
        if (!TextUtils.isEmpty(this.mDefaultDescriptionText)) {
            this.mDetailsTextView.setText(this.mDefaultDescriptionText);
            this.mDetailsTextView.setVisibility(0);
        }
        this.mCheckBox = (CheckBox) findViewById(C0880R.C0882id.check_box);
        this.mCheckBox.setChecked(a.getBoolean(C0880R.styleable.CheckedTwoLineSettings_checked, false));
    }

    public boolean isChecked() {
        return this.mCheckBox.isChecked();
    }

    public void setChecked(boolean checked) {
        this.mCheckBox.setChecked(checked);
    }

    public void setTitle(String title) {
        this.mTitleTextView.setText(title);
        this.mTitleTextView.setVisibility(0);
    }

    public void setTitle(Spannable title) {
        this.mTitleTextView.setText(title);
        this.mTitleTextView.setVisibility(0);
    }

    public void hideTitle() {
        this.mTitleTextView.setVisibility(8);
    }

    public void setDescription(String description) {
        this.mDetailsTextView.setText(description);
        this.mDetailsTextView.setVisibility(0);
    }

    public void setDescriptionSize(int dimen) {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        this.mDetailsTextSize = this.mDetailsTextView.getTextSize() / metrics.scaledDensity;
        this.mDetailsTextView.setTextSize(((float) getResources().getDimensionPixelSize(dimen)) / metrics.scaledDensity);
    }

    public void setTitleSize(int dimen) {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        this.mTitleTextSize = this.mTitleTextView.getTextSize() / metrics.scaledDensity;
        this.mTitleTextView.setTextSize(((float) getResources().getDimensionPixelSize(dimen)) / metrics.scaledDensity);
    }

    public void hideDescription() {
        this.mDetailsTextView.setVisibility(8);
    }

    public void setTitle() {
        this.mTitleTextView.setText(this.mDefaultTitleText);
        this.mTitleTextView.setVisibility(0);
        this.mDetailsTextView.setVisibility(0);
        if (this.mTitleTextSize > 0.0f) {
            this.mTitleTextView.setTextSize(this.mTitleTextSize);
        }
    }

    public void setDescription() {
        this.mDetailsTextView.setText(this.mDefaultDescriptionText);
        this.mDetailsTextView.setVisibility(0);
        this.mTitleTextView.setVisibility(0);
        if (this.mDetailsTextSize > 0.0f) {
            this.mDetailsTextView.setTextSize(this.mDetailsTextSize);
        }
    }

    public void setTitleColor(int color) {
        this.mTitleTextView.setTextColor(color);
    }
}
