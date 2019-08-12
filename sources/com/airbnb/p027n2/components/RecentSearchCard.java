package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.MaxWidthFrameLayout;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.RecentSearchCard */
public class RecentSearchCard extends MaxWidthFrameLayout {
    @BindView
    AirTextView locationView;
    @BindView
    AirTextView subtitleView;

    public RecentSearchCard(Context context) {
        super(context);
        init(null);
    }

    public RecentSearchCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RecentSearchCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_recent_search_card, this);
        ButterKnife.bind((View) this);
    }

    public void setLocationText(CharSequence locationText) {
        this.locationView.setText(locationText);
    }

    public void setSubtitleText(CharSequence subtitleText) {
        this.subtitleView.setText(subtitleText);
    }

    public static void mock(RecentSearchCard view) {
        view.setLocationText("Location");
        view.setSubtitleText("Anytime");
        view.setMaxWidth(ViewLibUtils.dpToPx(view.getContext(), 200.0f));
    }
}
