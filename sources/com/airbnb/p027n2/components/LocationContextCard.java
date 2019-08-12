package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.LocationContextCard */
public class LocationContextCard extends LinearLayout implements DividerView {
    @BindView
    AirTextView bodyText;
    @BindView
    AirButton button;
    @BindView
    View divider;
    @BindView
    AirImageView locationImage;
    @BindView
    AirTextView recommendedLabel;
    @BindView
    AirTextView titleText;

    public LocationContextCard(Context context) {
        super(context);
        init(null);
    }

    public LocationContextCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LocationContextCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_location_context_card, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setTitleText(String text) {
        this.titleText.setText(text);
    }

    public void setRecommendedLabelText(String text) {
        this.recommendedLabel.setText(text);
    }

    public void setBodyText(String text) {
        this.bodyText.setText(text);
    }

    public void setButtonText(String text) {
        this.button.setText(text);
    }

    public void setLocationImageUrl(String url) {
        this.locationImage.setImageUrl(url);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(LocationContextCard view) {
        view.setTitleText("Title");
        view.setBodyText("Body");
        view.setButtonText("Button");
    }
}
