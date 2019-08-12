package com.airbnb.android.places.views;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class PlaceActivityHeaderView extends LinearLayout implements DividerView {
    @BindView
    AirTextView actionKickerView;
    @BindView
    View sectionDivider;
    @BindView
    AirTextView subtitleView;
    @BindView
    AirTextView titleView;

    public PlaceActivityHeaderView(Context context) {
        super(context);
        init();
    }

    public PlaceActivityHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlaceActivityHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C7627R.layout.view_place_activity_header, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setTitle(CharSequence title) {
        this.titleView.setText(title);
    }

    public void setSubtitle(CharSequence boldSubtitle, CharSequence helpText) {
        boolean hasBoldSubtitle;
        boolean hasHelpText;
        boolean z = false;
        SpannableStringBuilder subtitle = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(boldSubtitle)) {
            hasBoldSubtitle = true;
        } else {
            hasBoldSubtitle = false;
        }
        if (!TextUtils.isEmpty(helpText)) {
            hasHelpText = true;
        } else {
            hasHelpText = false;
        }
        if (hasBoldSubtitle) {
            subtitle.append(SpannableUtils.makeBookString(boldSubtitle, getContext()));
            if (hasHelpText) {
                subtitle.append(" · ");
            }
        }
        if (hasHelpText) {
            subtitle.append(helpText);
        }
        this.subtitleView.setText(subtitle);
        AirTextView airTextView = this.subtitleView;
        if (hasBoldSubtitle || hasHelpText) {
            z = true;
        }
        ViewLibUtils.setVisibleIf(airTextView, z);
    }

    public void setActionKicker(String actionKicker, int color) {
        ViewLibUtils.setVisibleIf(this.actionKickerView, !TextUtils.isEmpty(actionKicker));
        this.actionKickerView.setText(actionKicker);
        this.actionKickerView.setTextColor(color);
    }

    public void showDivider(boolean show) {
        ViewLibUtils.setVisibleIf(this.sectionDivider, show);
    }
}
