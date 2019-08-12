package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.EventScheduleInterstitial */
public class EventScheduleInterstitial extends RelativeLayout {
    @BindView
    AirTextView addressText;
    @BindView
    AirButton button;
    @BindView
    AirTextView dateTimeText;
    @BindView
    AirTextView headerText;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirTextView titleText;

    public EventScheduleInterstitial(Context context) {
        super(context);
        init();
    }

    public EventScheduleInterstitial(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EventScheduleInterstitial(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_event_schedule_interstitial, this);
        ButterKnife.bind((View) this);
    }

    public void setHeaderText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.headerText, text);
    }

    public void setTitleText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.titleText, text);
    }

    public void setDateTimeText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.dateTimeText, text);
    }

    public void setAddressText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.addressText, text);
    }

    public void setButtonText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.button, text);
    }

    public void setButtonClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public void setJellyfishPallete(int pallete, boolean animate) {
        this.jellyfishView.setPalette(pallete, animate);
    }

    public static void mock(EventScheduleInterstitial view) {
        view.setHeaderText("Header");
        view.setTitleText("Title Message");
        view.setDateTimeText("Date and time \n April 2 at 7:15PM");
        view.setAddressText("Address \n 1415 McDuff St \n Los Angeles, CA 90026");
        view.setButtonText("Button");
    }
}
