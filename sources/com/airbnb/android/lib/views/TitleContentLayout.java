package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class TitleContentLayout extends LinearLayout {
    @BindView
    AirTextView mContentText;
    @BindView
    FrameLayout mCustomLayout;
    @BindView
    HaloImageView mSubtitleImage;
    @BindView
    AirTextView mTitleText;

    public TitleContentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public TitleContentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleContentLayout(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        int i = 1;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ButterKnife.bind(this, layoutInflater.inflate(C0880R.layout.title_content_layout, this, true));
        if (MiscUtils.isTabletScreen(getContext())) {
            i = 0;
        }
        setOrientation(i);
        if (attrs != null) {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, C0880R.styleable.TitleContentLayout, 0, 0);
            if (styledAttrs != null) {
                String titleText = styledAttrs.getString(C0880R.styleable.TitleContentLayout_title);
                String contentText = styledAttrs.getString(C0880R.styleable.TitleContentLayout_content);
                int subtitleImageId = styledAttrs.getResourceId(C0880R.styleable.TitleContentLayout_subtitle_image, 0);
                int contentLayoutId = styledAttrs.getResourceId(C0880R.styleable.TitleContentLayout_content_layout, 0);
                styledAttrs.recycle();
                if (subtitleImageId > 0) {
                    setSubtitleImage(subtitleImageId);
                } else {
                    this.mSubtitleImage.setVisibility(8);
                }
                this.mTitleText.setText(titleText);
                if (contentLayoutId == 0) {
                    this.mContentText.setText(contentText);
                    return;
                }
                View customLayout = layoutInflater.inflate(contentLayoutId, this.mCustomLayout, false);
                this.mCustomLayout.removeAllViews();
                this.mCustomLayout.addView(customLayout);
            }
        }
    }

    public View getCustomView() {
        if (this.mCustomLayout.getChildCount() > 0) {
            return this.mCustomLayout.getChildAt(0);
        }
        return null;
    }

    public HaloImageView getSubtitleImage() {
        this.mSubtitleImage.setVisibility(0);
        return this.mSubtitleImage;
    }

    public void setSubtitleImage(int drawableId) {
        this.mSubtitleImage.setVisibility(0);
        this.mSubtitleImage.setImageResource(drawableId);
    }

    public void setTitle(String title) {
        this.mTitleText.setText(title);
    }

    public void setTitle(int titleTextRes) {
        this.mTitleText.setText(titleTextRes);
    }

    public void setText(String text) {
        this.mContentText.setText(text);
    }

    public CharSequence getTitle() {
        return this.mTitleText.getText();
    }
}
