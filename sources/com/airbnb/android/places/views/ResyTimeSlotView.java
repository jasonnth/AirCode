package com.airbnb.android.places.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class ResyTimeSlotView extends FrameLayout {
    @BindColor
    int actionableTextColor;
    @BindColor
    int invertedTextColor;
    @BindDrawable
    Drawable selectedBackground;
    @BindDimen
    int sideMargin;
    @BindView
    AirTextView subtitleView;
    @BindView
    AirTextView titleView;
    @BindDrawable
    Drawable unselectedBackground;

    public ResyTimeSlotView(Context context) {
        super(context);
        init();
    }

    public ResyTimeSlotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ResyTimeSlotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C7627R.layout.view_time_slot, this);
        ButterKnife.bind((View) this);
        setSelected(false);
    }

    public void setTitle(CharSequence title) {
        this.titleView.setText(title);
    }

    public void setSubtitle(CharSequence subtitle) {
        this.subtitleView.setText(subtitle);
        ViewLibUtils.setVisibleIf(this.subtitleView, !TextUtils.isEmpty(subtitle));
    }

    public void setRightMargin(boolean shouldBeZero) {
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        if (shouldBeZero && layoutParams.rightMargin != 0) {
            layoutParams.rightMargin = 0;
            setLayoutParams(layoutParams);
        }
        if (!shouldBeZero && layoutParams.rightMargin != this.sideMargin) {
            layoutParams.rightMargin = this.sideMargin;
            setLayoutParams(layoutParams);
        }
    }

    public void setSelected(boolean selected) {
        setBackground(selected ? this.selectedBackground : this.unselectedBackground);
        int textColor = selected ? this.invertedTextColor : this.actionableTextColor;
        this.titleView.setTextColor(textColor);
        this.subtitleView.setTextColor(textColor);
    }
}
