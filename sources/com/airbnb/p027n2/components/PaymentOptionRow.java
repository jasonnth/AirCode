package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.PaymentOptionRow */
public class PaymentOptionRow extends RelativeLayout implements DividerView {
    @BindView
    AirImageView checkboxView;
    @BindView
    View divider;
    @BindView
    AirImageView imageView;
    private boolean isChecked;
    @BindView
    AirTextView subtitleText;
    @BindView
    AirTextView titleText;

    public PaymentOptionRow(Context context) {
        super(context);
        init(null);
    }

    public PaymentOptionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PaymentOptionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_payment_option_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_PaymentOptionRow, 0, 0);
        setTitle(a.getString(R.styleable.n2_PaymentOptionRow_n2_titleText));
        setSubtitleText(a.getString(R.styleable.n2_PaymentOptionRow_n2_subtitleText));
        setImage(ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_PaymentOptionRow_n2_image));
        showDivider(a.getBoolean(R.styleable.n2_PaymentOptionRow_n2_showDivider, true));
        a.recycle();
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setSubtitleText(CharSequence text) {
        if (text != null) {
            this.subtitleText.setText(text);
            this.subtitleText.setVisibility(0);
            return;
        }
        this.subtitleText.setVisibility(8);
    }

    public void setImage(int drawableRes) {
        this.imageView.setImageResource(drawableRes);
    }

    public void setImage(Drawable drawable) {
        if (drawable != null) {
            this.imageView.setImageDrawable(drawable);
        }
    }

    public void setChecked(boolean isChecked2) {
        this.isChecked = isChecked2;
        this.checkboxView.setImageDrawableCompat(isChecked2 ? R.drawable.n2_ic_radio_button_selected : R.drawable.n2_ic_radio_button_unselected);
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public static void mock(PaymentOptionRow view) {
        view.setTitle("Mastercard");
        view.setSubtitleText("USD Expires · 4/20");
        view.setImage(R.drawable.n2_creditcard_icon);
        view.setChecked(true);
        view.setOnClickListener(PaymentOptionRow$$Lambda$1.lambdaFactory$(view));
    }

    static /* synthetic */ void lambda$mock$0(PaymentOptionRow view, View v) {
        view.setChecked(!view.isChecked());
    }
}
