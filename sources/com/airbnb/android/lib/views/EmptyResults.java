package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirButton;

public class EmptyResults extends LinearLayout {
    @BindView
    AirButton mActionButton;
    @BindView
    TextView mSubTitle;
    @BindView
    TextView mTitle;

    public EmptyResults(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.EmptyResults, 0, 0);
        String titleText = a.getString(C0880R.styleable.EmptyResults_titleText);
        String subTitleText = a.getString(C0880R.styleable.EmptyResults_subTitleText);
        a.recycle();
        setOrientation(0);
        setGravity(16);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0880R.layout.empty_results, this, true);
        ButterKnife.bind((View) this);
        this.mTitle.setText(titleText);
        this.mSubTitle.setText(subTitleText);
    }

    public EmptyResults(Context context) {
        this(context, null);
    }

    public void setTitle(String title) {
        this.mTitle.setText(title);
    }

    public void setTitle(int titleRes) {
        this.mTitle.setText(titleRes);
    }

    public void setSubTitle(String subTitle) {
        this.mSubTitle.setText(subTitle);
    }

    public void setSubtitle(int subtitleRes) {
        this.mSubTitle.setText(subtitleRes);
    }

    public void setupButton(int buttonTitleRes, OnClickListener onClickListener) {
        this.mActionButton.setVisibility(0);
        this.mActionButton.setText(buttonTitleRes);
        this.mActionButton.setOnClickListener(onClickListener);
    }

    public void setButtonVisibility(boolean visible) {
        ViewUtils.setVisibleIf((View) this.mActionButton, visible);
    }

    public void setSubtitleVisible(boolean visible) {
        ViewUtils.setVisibleIf((View) this.mSubTitle, visible);
    }
}
