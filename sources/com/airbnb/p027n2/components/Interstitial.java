package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.Interstitial */
public class Interstitial extends RelativeLayout implements DividerView {
    @BindView
    View bottomSpace;
    @BindView
    AirButton button;
    @BindView
    AirTextView captionView;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirTextView textView;
    @BindView
    View topSpace;

    public Interstitial(Context context) {
        super(context);
        init();
    }

    public Interstitial(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Interstitial(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_interstitial, this);
        ButterKnife.bind((View) this);
    }

    public void setText(CharSequence text) {
        this.textView.setText(text);
    }

    public void setCaption(CharSequence caption) {
        ViewLibUtils.setVisibleIf(this.captionView, !TextUtils.isEmpty(caption));
        this.captionView.setText(caption);
    }

    public void setButtonText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.button, !TextUtils.isEmpty(text));
        this.button.setText(text);
    }

    public void setButtonClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public void setPaddingEnabled(boolean withPadding) {
        ViewLibUtils.setVisibleIf(this.topSpace, withPadding);
        ViewLibUtils.setVisibleIf(this.bottomSpace, withPadding);
    }

    public void setJellyfishPallete(Integer pallete, boolean animate) {
        ViewLibUtils.setVisibleIf(this.jellyfishView, pallete != null);
        if (pallete != null) {
            this.jellyfishView.setPalette(pallete.intValue(), animate);
        }
    }

    public void showDivider(boolean showDivider) {
    }

    public static void mock(Interstitial interstitial) {
        interstitial.setText("Text");
        interstitial.setCaption("Optional caption used for matching suggestions and promos.");
        interstitial.setButtonText("Button");
    }

    public static void mockRaush(Interstitial interstitial) {
        interstitial.setText("Text");
        interstitial.setCaption("Optional caption used for matching suggestions and promos.");
        interstitial.setButtonText("Button");
        interstitial.setJellyfishPallete(Integer.valueOf(2), false);
    }

    public static void mockHackberry(Interstitial interstitial) {
        interstitial.setText("Text");
        interstitial.setCaption("Optional caption used for matching suggestions and promos.");
        interstitial.setButtonText("Button");
        interstitial.setJellyfishPallete(Integer.valueOf(3), false);
    }

    public static void mockNoPadding(Interstitial interstitial) {
        interstitial.setText("Text");
        interstitial.setCaption("Optional caption used for matching suggestions and promos.");
        interstitial.setButtonText("Button");
        interstitial.setPaddingEnabled(false);
    }
}
