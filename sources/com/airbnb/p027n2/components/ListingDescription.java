package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.TextViewUtils;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.ListingDescription */
public class ListingDescription extends LinearLayout implements DividerView {
    @BindView
    View divider;
    private CharSequence readMoreText;
    @BindView
    AirTextView spaceDescriptionHeading;
    @BindView
    AirTextView spaceDescriptionText;
    @BindView
    AirTextView summaryHeading;
    @BindView
    AirTextView summaryHighlight;
    @BindView
    AirTextView summaryText;
    @BindView
    AirTextView translateText;

    public ListingDescription(Context context) {
        super(context);
        init(null);
    }

    public ListingDescription(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ListingDescription(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_listing_description, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setTranslateText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.translateText, !TextUtils.isEmpty(text));
        this.translateText.setText(text);
    }

    public void setTranslateClickListener(OnClickListener listener) {
        this.translateText.setOnClickListener(listener);
    }

    public void setSummaryText(CharSequence heading, CharSequence text) {
        boolean z;
        boolean z2 = true;
        AirTextView airTextView = this.summaryHeading;
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(heading)) {
            z = false;
        } else {
            z = true;
        }
        ViewLibUtils.setVisibleIf(airTextView, z);
        AirTextView airTextView2 = this.summaryText;
        if (TextUtils.isEmpty(text)) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(airTextView2, z2);
        this.summaryHeading.setText(heading);
        this.summaryText.setText(text);
    }

    public void setSummaryHighLight(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.summaryHighlight, text);
    }

    public void setSpaceDescriptionText(CharSequence heading, CharSequence text, CharSequence readMoreText2) {
        boolean z = true;
        ViewLibUtils.setVisibleIf(this.spaceDescriptionHeading, !TextUtils.isEmpty(text) && !TextUtils.isEmpty(heading));
        AirTextView airTextView = this.spaceDescriptionText;
        if (TextUtils.isEmpty(text)) {
            z = false;
        }
        ViewLibUtils.setVisibleIf(airTextView, z);
        this.spaceDescriptionHeading.setText(heading);
        this.spaceDescriptionText.setText(text);
        this.readMoreText = readMoreText2;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!TextUtils.isEmpty(this.spaceDescriptionText.getText()) && !TextUtils.isEmpty(this.readMoreText)) {
            TextViewUtils.addReadMoreTextIfNeeded(this.spaceDescriptionText, this.readMoreText, getResources().getColor(R.color.n2_babu), true);
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        super.setOnClickListener(listener);
        this.spaceDescriptionText.setOnClickListener(listener);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mock(ListingDescription view) {
        view.setSummaryText("Summary heading", "Summary text");
        view.setSpaceDescriptionText("Space description heading", "Space description text", "read more");
    }
}
