package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.SummaryInterstitial */
public class SummaryInterstitial extends BaseComponent {
    @BindView
    AirButton firstButton;
    @BindView
    AirButton secondButton;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView thirdRowText;
    @BindView
    AirTextView titleText;

    public SummaryInterstitial(Context context) {
        super(context);
    }

    public SummaryInterstitial(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SummaryInterstitial(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_summary_interstitial;
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
        this.thirdRowText.setText(text);
    }

    public void setThirdRowText(int textRes) {
        setThirdRowText((CharSequence) getResources().getString(textRes));
    }

    public void setFirstButtonText(CharSequence text) {
        this.firstButton.setText(text);
    }

    public void setFirstButtonText(int textRes) {
        setFirstButtonText((CharSequence) getResources().getString(textRes));
    }

    public void setSecondButtonText(CharSequence text) {
        this.secondButton.setText(text);
    }

    public void setLeftButtonClickListener(OnClickListener listener) {
        this.firstButton.setOnClickListener(listener);
    }

    public void setRightButtonClickListener(OnClickListener listener) {
        this.secondButton.setOnClickListener(listener);
    }

    public void setSecondButtonText(int textRes) {
        setSecondButtonText((CharSequence) getResources().getString(textRes));
    }

    public static void mock(SummaryInterstitial row) {
        row.setTitle((CharSequence) "You quoted");
        row.setSubtitle((CharSequence) "20% per reservation (or a minimum of $50), plus cleaning fees");
        row.setThirdRowText((CharSequence) "sent july 10, 2017, 5:13pm");
        row.setFirstButtonText((CharSequence) "Update");
        row.setSecondButtonText((CharSequence) "Cancel");
        row.setLeftButtonClickListener(SummaryInterstitial$$Lambda$1.lambdaFactory$());
        row.setRightButtonClickListener(SummaryInterstitial$$Lambda$2.lambdaFactory$());
    }

    public static void mockNoSubtitle(SummaryInterstitial row) {
        row.setTitle((CharSequence) "title");
        row.setThirdRowText((CharSequence) "thirdRowText");
        row.setFirstButtonText((CharSequence) "Left");
        row.setSecondButtonText((CharSequence) "Right");
        row.setLeftButtonClickListener(SummaryInterstitial$$Lambda$3.lambdaFactory$());
        row.setRightButtonClickListener(SummaryInterstitial$$Lambda$4.lambdaFactory$());
    }
}
