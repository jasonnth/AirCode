package com.airbnb.p027n2.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.PaymentMethodRow */
public class PaymentMethodRow extends LinearLayout implements DividerView {
    @BindView
    View divider;
    @BindView
    AirImageView image;
    @BindView
    AirImageView rowDrawable;
    @BindView
    AirTextView title;

    public PaymentMethodRow(Context context) {
        super(context);
        init(context, null);
    }

    public PaymentMethodRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PaymentMethodRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public PaymentMethodRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.n2_payment_method_row, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        setupAttributes(context, attrs);
    }

    private void setupAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_PaymentMethodRow);
        setTitle((CharSequence) a.getString(R.styleable.n2_PaymentMethodRow_n2_titleText));
        showDivider(a.getBoolean(R.styleable.n2_PaymentMethodRow_n2_showDivider, true));
        a.recycle();
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void setImageUrl(String imageUrl) {
        if (imageUrl != null) {
            this.image.setVisibility(0);
            this.image.setImageUrl(imageUrl);
            return;
        }
        this.image.setVisibility(8);
    }

    public void setImageDrawable(int drawableRes) {
        if (drawableRes != 0) {
            this.image.setVisibility(0);
            this.image.setImageResource(drawableRes);
            return;
        }
        this.image.setVisibility(8);
    }

    public void setRowDrawable(int drawableRes) {
        this.rowDrawable.setImageResource(drawableRes);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }
}
