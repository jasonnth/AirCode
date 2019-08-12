package com.airbnb.p027n2.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.PriceFilterButtons */
public class PriceFilterButtons extends FrameLayout {
    @BindView
    AirTextView button1;
    @BindView
    View button1Divider;
    @BindView
    AirTextView button2;
    @BindView
    View button2Divider;
    @BindView
    AirTextView button3;

    public PriceFilterButtons(Context context) {
        super(context);
        init(null);
    }

    public PriceFilterButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PriceFilterButtons(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @SuppressLint({"NewApi"})
    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_price_filter_buttons, this);
        ButterKnife.bind((View) this);
        setPadding(0, getResources().getDimensionPixelOffset(R.dimen.n2_vertical_padding_medium), 0, 0);
    }

    public void setButton1Text(CharSequence text) {
        this.button1.setText(text);
    }

    public void setButton1ClickListener(OnClickListener clickListener) {
        this.button1.setOnClickListener(clickListener);
    }

    public void setButton2Text(CharSequence text) {
        this.button2.setText(text);
    }

    public void setButton2ClickListener(OnClickListener clickListener) {
        this.button2.setOnClickListener(clickListener);
    }

    public void setButton3Text(CharSequence text) {
        this.button3.setText(text);
    }

    public void setButton3ClickListener(OnClickListener clickListener) {
        this.button3.setOnClickListener(clickListener);
    }

    public void setSelection(int selection) {
        this.button1.setSelected(false);
        this.button2.setSelected(false);
        this.button3.setSelected(false);
        switch (selection) {
            case 1:
                this.button1.setSelected(true);
                return;
            case 2:
                this.button2.setSelected(true);
                return;
            case 3:
                this.button3.setSelected(true);
                return;
            default:
                return;
        }
    }

    public static void mock(PriceFilterButtons view) {
        view.setButton1Text("$");
        view.setButton2Text("$$");
        view.setButton3Text("$$$");
    }
}
