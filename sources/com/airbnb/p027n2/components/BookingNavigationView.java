package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BookingNavigationView */
public class BookingNavigationView extends RelativeLayout {
    @BindView
    AirButton button;
    @BindView
    RelativeLayout container;
    @BindView
    LoadingView loader;
    @BindView
    AirTextView priceDetails;
    @BindView
    AirTextView seePriceDetails;

    public BookingNavigationView(Context context) {
        super(context);
        init();
    }

    public BookingNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BookingNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_booking_navigation, this);
        ButterKnife.bind((View) this);
    }

    public void setButtonText(int stringRes) {
        setButtonText((CharSequence) getContext().getString(stringRes));
    }

    public void setButtonText(CharSequence text) {
        this.button.setText(text);
    }

    public void setPricingDetailsText(CharSequence text) {
        this.priceDetails.setText(text);
    }

    public void setSeePricingDetailsText(int stringRes) {
        setSeePricingDetailsText((CharSequence) getContext().getString(stringRes));
    }

    public void setSeePricingDetailsText(CharSequence text) {
        this.seePriceDetails.setText(text);
    }

    public void setButtonOnClickListener(OnClickListener l) {
        this.button.setOnClickListener(l);
    }

    public void setPriceDetailsOnClickListener(OnClickListener l) {
        this.priceDetails.setOnClickListener(l);
        this.seePriceDetails.setOnClickListener(l);
    }

    public void showLoader() {
        if (!isLoading()) {
            this.container.setVisibility(4);
            this.loader.setVisibility(0);
        }
    }

    public void hideLoader() {
        this.container.setVisibility(0);
        this.loader.setVisibility(4);
    }

    public boolean isLoading() {
        return this.button.getState() == State.Loading;
    }

    public void setEnabled(boolean enable) {
        this.button.setEnabled(enable);
    }

    public static void mock(BookingNavigationView view) {
        view.setButtonText((CharSequence) "Button");
        view.setPricingDetailsText("Pricing details");
        view.setSeePricingDetailsText((CharSequence) "See pricing details");
    }

    public static void mockLoading(BookingNavigationView view) {
        view.setButtonText((CharSequence) "Button");
        view.setPricingDetailsText("Pricing details");
        view.setSeePricingDetailsText((CharSequence) "See pricing details");
        view.showLoader();
    }
}
