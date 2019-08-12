package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.onboarding_overlay.CustomBulletSpan;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.ExpandableTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.InquiryCard */
public class InquiryCard extends BaseComponent {
    @BindView
    AirTextView fourthRowText;
    @BindView
    ExpandableTextView messageText;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView thirdRowText;
    @BindView
    AirTextView timeAgoText;
    @BindView
    AirTextView titleText;
    @BindView
    AirImageView unreadIndicator;

    public InquiryCard(Context context) {
        super(context);
    }

    public InquiryCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InquiryCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_inquiry_card;
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitleText, text, true);
    }

    public void setSubtitle(int textRes) {
        setSubtitle((CharSequence) getResources().getString(textRes));
    }

    public void setThirdRowText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.thirdRowText, !TextUtils.isEmpty(text));
        setBulletedText(this.thirdRowText, text);
    }

    public void setThirdRowText(int textRes) {
        setThirdRowText((CharSequence) getResources().getString(textRes));
    }

    public void setFourthRowText(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.fourthRowText, !TextUtils.isEmpty(text));
        setBulletedText(this.fourthRowText, text);
    }

    public void setFourthRowText(int textRes) {
        setFourthRowText((CharSequence) getResources().getString(textRes));
    }

    public void setReadMoreText(CharSequence readMoreText) {
        if (!TextUtils.isEmpty(readMoreText)) {
            this.messageText.setReadMoreText(TextUtil.makeColored(getContext(), R.color.n2_babu, readMoreText.toString()));
        }
    }

    public void setReadMoreText(int textRes) {
        setReadMoreText((CharSequence) getResources().getString(textRes));
    }

    public void setMessageText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.messageText, text, true);
    }

    public void setMessageText(int textRes) {
        setMessageText((CharSequence) getResources().getString(textRes));
    }

    public void setTimeAgoText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.timeAgoText, text, true);
    }

    public void setTimeAgoText(int textRes) {
        setTimeAgoText((CharSequence) getResources().getString(textRes));
    }

    public void showUnreadIndicator(boolean isVisible) {
        ViewLibUtils.setVisibleIf(this.unreadIndicator, isVisible);
    }

    private void setBulletedText(AirTextView textView, CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            SpannableString spannable = new SpannableString(text);
            spannable.setSpan(new CustomBulletSpan(getResources().getDimensionPixelOffset(R.dimen.n2_horizontal_padding_tiny), getResources().getDimensionPixelOffset(R.dimen.n2_default_bullet_radius)), 0, text.length(), 0);
            textView.setText(spannable);
        }
    }

    public static void mock(InquiryCard card) {
        card.setTitle((CharSequence) "Marcus");
        card.setSubtitle((CharSequence) "Full Service");
        card.setThirdRowText((CharSequence) "0.9 mi away");
        card.setFourthRowText((CharSequence) "Ongoing");
        card.showUnreadIndicator(true);
        card.setTimeAgoText((CharSequence) "1h ago");
        card.setReadMoreText((CharSequence) "read more");
        card.setMessageText((CharSequence) "Hello I am going to be traveling for two weeks and it would great if you can take care of my listing.");
    }
}
