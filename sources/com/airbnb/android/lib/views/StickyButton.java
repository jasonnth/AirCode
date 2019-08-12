package com.airbnb.android.lib.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.p027n2.primitives.AirTextView;

public class StickyButton extends FrameLayout {
    protected LinearLayout mRootView;
    private TextView mSubtitle;
    private AirTextView mTitle;

    public StickyButton(Context context) {
        super(context);
        setupViews(null);
    }

    public StickyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupViews(attrs);
    }

    public StickyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupViews(attrs);
    }

    private void setupViews(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(C0880R.layout.sticky_button, this);
        this.mRootView = (LinearLayout) findViewById(C0880R.C0882id.sticky_btn_root);
        this.mTitle = (AirTextView) this.mRootView.findViewById(C0880R.C0882id.title);
        this.mSubtitle = (TextView) this.mRootView.findViewById(C0880R.C0882id.subtitle);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, C0880R.styleable.StickyButton, 0, 0);
            String titleText = a.getString(C0880R.styleable.StickyButton_title);
            a.recycle();
            this.mTitle.setText(titleText);
        }
        setBackgroundResource(C0880R.C0881drawable.canonical_rausch_button_selector_no_corners);
    }

    public void setTitle(String title) {
        this.mTitle.setText(title);
    }

    public AirTextView getTitle() {
        return this.mTitle;
    }

    public void setTitle(int titleRes) {
        this.mTitle.setText(titleRes);
    }

    public void setSubtitle(String title) {
        this.mSubtitle.setText(title);
        enableSubtitle();
    }

    public void setSubtitle(int titleRes) {
        this.mSubtitle.setText(titleRes);
        enableSubtitle();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.mRootView.setEnabled(enabled);
        this.mTitle.setEnabled(enabled);
        this.mSubtitle.setEnabled(enabled);
    }

    public void setRoundedCorners(boolean roundCorners) {
        setBackgroundResource(roundCorners ? C0880R.C0881drawable.canonical_blue_button_selector : C0880R.C0881drawable.canonical_rausch_button_selector_no_corners);
    }

    public void setCustomBackgroundColorResource(int colorResId) {
        if (colorResId != C0880R.color.c_rausch) {
            if (getChildCount() == 1) {
                setForeground(AppCompatResources.getDrawable(getContext(), C0880R.C0881drawable.c_bg_transparent));
            }
            int color = ContextCompat.getColor(getContext(), colorResId);
            if (AndroidVersion.isAtLeastLollipop()) {
                setRippleColor(color);
            } else {
                setBackgroundColor(color);
            }
        }
    }

    @TargetApi(21)
    private void setRippleColor(int color) {
        if (getBackground() instanceof RippleDrawable) {
            Drawable rippleItemColor = ((RippleDrawable) getBackground()).findDrawableByLayerId(C0880R.C0882id.ripple_item_color);
            if (rippleItemColor instanceof ColorDrawable) {
                ((ColorDrawable) rippleItemColor).setColor(color);
            }
        }
    }

    private void enableSubtitle() {
        LayoutParams params = (LayoutParams) this.mTitle.getLayoutParams();
        params.gravity |= 80;
        this.mSubtitle.setVisibility(0);
        int heightWithSubtitle = (int) getResources().getDimension(C0880R.dimen.ro_bottom_btn_height);
        this.mRootView.getLayoutParams().height = heightWithSubtitle;
        getLayoutParams().height = heightWithSubtitle;
    }

    public int getButtonHeight() {
        return this.mRootView.getLayoutParams().height;
    }
}
