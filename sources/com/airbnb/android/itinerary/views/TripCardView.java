package com.airbnb.android.itinerary.views;

import android.content.Context;
import android.support.p002v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.SecondaryActionButtonType;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.AirmojiEnum;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class TripCardView extends CardView {
    @BindView
    AirTextView actionButton;
    @BindView
    LinearLayout actionLayout;
    @BindView
    AirTextView actionTitle;
    @BindView
    View divider;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;
    @BindView
    LinearLayout titleLayout;

    public TripCardView(Context context) {
        super(context);
        init();
    }

    public TripCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TripCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C5755R.layout.trip_card_view, this);
        setCardElevation(getResources().getDimension(C5755R.dimen.trip_card_view_elevation));
        setRadius(getResources().getDimension(C5755R.dimen.trip_card_view_corner_radius));
        setUseCompatPadding(true);
        ButterKnife.bind((View) this);
    }

    public void setImageUrl(String url) {
        this.imageView.setImageUrl(url);
    }

    public void setTitle(CharSequence text) {
        boolean z;
        boolean z2 = true;
        LinearLayout linearLayout = this.titleLayout;
        if (!TextUtils.isEmpty(text)) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(linearLayout, z);
        AirTextView airTextView = this.title;
        if (TextUtils.isEmpty(text)) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(airTextView, z2);
        this.title.setText(text);
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.subtitle, !TextUtils.isEmpty(text));
        this.subtitle.setText(text);
    }

    public void setCardSubtitleCopyToast(int resId) {
        this.subtitle.setOnLongClickListener(resId == -1 ? null : TripCardView$$Lambda$1.lambdaFactory$(this, resId));
    }

    public void setSecondaryActionTitle(CharSequence text) {
        boolean z = true;
        ViewLibUtils.setVisibleIf(this.actionLayout, !TextUtils.isEmpty(text));
        View view = this.divider;
        if (TextUtils.isEmpty(text)) {
            z = false;
        }
        ViewLibUtils.setVisibleIf(view, z);
        this.actionTitle.setText(text);
        this.actionTitle.setOnLongClickListener(TextUtils.isEmpty(text) ? null : TripCardView$$Lambda$2.lambdaFactory$(this));
    }

    public void setSecondaryActionButtonText(String text, SecondaryActionButtonType buttonType) {
        ViewLibUtils.setVisibleIf(this.actionButton, !TextUtils.isEmpty(text));
        boolean isAirmoji = SecondaryActionButtonType.Airmoji.equals(buttonType);
        AirTextView airTextView = this.actionButton;
        if (isAirmoji) {
            text = AirmojiEnum.fromKey(text);
        }
        airTextView.setText(text);
        this.actionButton.setTextAppearance(getContext(), isAirmoji ? C5755R.C5760style.n2_LargeText_Actionable : C5755R.C5760style.n2_SmallText_Actionable);
    }

    public void setSecondaryActionButtonClickListener(OnClickListener listener) {
        this.actionButton.setOnClickListener(listener);
        this.actionLayout.setOnClickListener(listener);
        this.actionTitle.setOnClickListener(listener);
    }

    public void clear() {
        this.imageView.clear();
        setTitle(null);
        setSubtitle(null);
        setCardSubtitleCopyToast(-1);
        setSecondaryActionButtonText(null, null);
        setSecondaryActionTitle(null);
    }
}
