package com.airbnb.android.listing.views;

import android.content.Context;
import android.support.p002v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class TipView extends FrameLayout {
    private AppCompatActivity activity;
    private final OnGlobalLayoutListener globalLayoutListener;
    private View resizeableContainer;
    @BindView
    AirButton tipButton;
    @BindView
    LinearLayout tipContainer;
    @BindView
    AirTextView tipTextView;

    public TipView(Context context) {
        super(context);
        this.globalLayoutListener = TipView$$Lambda$1.lambdaFactory$(this);
        init();
    }

    public TipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.globalLayoutListener = TipView$$Lambda$2.lambdaFactory$(this);
        init();
    }

    public TipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.globalLayoutListener = TipView$$Lambda$3.lambdaFactory$(this);
        init();
    }

    private void init() {
        inflate(getContext(), C7213R.layout.tip_view, this);
        ButterKnife.bind((View) this);
    }

    public void initKeyboardHiding(AppCompatActivity activity2, View resizeableContainer2) {
        this.activity = activity2;
        this.resizeableContainer = resizeableContainer2;
        updateViewVisibility();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        super.onDetachedFromWindow();
        this.activity = null;
        this.resizeableContainer = null;
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.tipButton.setEnabled(enabled);
    }

    public void setTipTextRes(int textRes) {
        this.tipTextView.setText(textRes);
    }

    public void setTipText(String text) {
        this.tipTextView.setText(text);
    }

    public void setTipClickListener(OnClickListener clickListener) {
        setOnClickListener(clickListener);
        this.tipButton.setOnClickListener(clickListener);
        ViewLibUtils.setVisibleIf(this.tipButton, clickListener != null);
    }

    /* access modifiers changed from: private */
    public void updateViewVisibility() {
        int i = 0;
        if (this.activity == null || this.resizeableContainer == null) {
            this.tipContainer.setVisibility(0);
            return;
        }
        boolean keyboardIsShowing = KeyboardUtils.isKeyboardUp(this.activity, this.resizeableContainer);
        LinearLayout linearLayout = this.tipContainer;
        if (keyboardIsShowing) {
            i = 8;
        }
        linearLayout.setVisibility(i);
    }
}
