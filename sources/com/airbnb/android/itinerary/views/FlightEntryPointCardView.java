package com.airbnb.android.itinerary.views;

import android.content.Context;
import android.support.p002v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class FlightEntryPointCardView extends CardView {
    @BindView
    AirTextView acceptText;
    @BindView
    AirTextView dismissText;
    @BindView
    AirTextView title;

    public FlightEntryPointCardView(Context context) {
        super(context);
        init();
    }

    public FlightEntryPointCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlightEntryPointCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C5755R.layout.flight_entry_point_card_view, this);
        setCardElevation(getResources().getDimension(C5755R.dimen.trip_card_view_elevation));
        setRadius(getResources().getDimension(C5755R.dimen.trip_card_view_corner_radius));
        setUseCompatPadding(true);
        ButterKnife.bind((View) this);
    }

    public void setTitle(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.title, !TextUtils.isEmpty(text));
        this.title.setText(text);
    }

    public void setAcceptText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.acceptText, !TextUtils.isEmpty(text));
        this.acceptText.setText(text);
    }

    public void setDismissText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.dismissText, !TextUtils.isEmpty(text));
        this.dismissText.setText(text);
    }

    public void setAcceptClickListener(OnClickListener listener) {
        this.acceptText.setOnClickListener(listener);
    }

    public void setDismissClickListener(OnClickListener listener) {
        this.dismissText.setOnClickListener(listener);
    }

    public void clear() {
        setTitle(null);
        setAcceptText(null);
        setDismissText(null);
        setAcceptClickListener(null);
        setDismissClickListener(null);
    }
}
