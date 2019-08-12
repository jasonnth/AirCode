package com.airbnb.p027n2.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.percent.PercentRelativeLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.DestinationCard */
public class DestinationCard extends PercentRelativeLayout {
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView titleTextView;

    public DestinationCard(Context context) {
        super(context);
        init();
    }

    public DestinationCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DestinationCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_destinations_card, this);
        ButterKnife.bind((View) this);
    }

    public void setTitleText(CharSequence title) {
        ViewLibUtils.setVisibleIf(this.titleTextView, !TextUtils.isEmpty(title));
        this.titleTextView.setText(title);
    }

    public void setImage(Drawable image) {
        this.imageView.setImageDrawable(image);
    }

    public void setImageUrl(String url) {
        this.imageView.setImageUrl(url);
    }

    public void clearImage() {
        this.imageView.clear();
    }

    public static void mock(DestinationCard card) {
        card.setTitleText("Title");
    }
}
