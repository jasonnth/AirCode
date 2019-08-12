package com.airbnb.p027n2.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.NavigationPill */
public class NavigationPill extends LinearLayout {
    @BindView
    View divider;
    @BindView
    AirTextView leftButton;
    @BindView
    AirTextView leftButtonBadge;
    @BindView
    ViewGroup leftButtonContainer;
    @BindView
    AirImageView leftButtonIcon;
    @BindView
    AirTextView middleButton;
    @BindView
    AirTextView middleButtonBadge;
    @BindView
    ViewGroup middleButtonContainer;
    @BindView
    View middleButtonDivider;
    @BindView
    AirImageView middleButtonIcon;
    @BindView
    AirTextView rightButton;
    @BindView
    AirTextView rightButtonBadge;
    @BindView
    ViewGroup rightButtonContainer;
    @BindView
    AirImageView rightButtonIcon;

    public NavigationPill(Context context) {
        super(context);
        init(null);
    }

    public NavigationPill(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NavigationPill(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @SuppressLint({"NewApi"})
    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_navigation_pill, this);
        ButterKnife.bind((View) this);
        setUpAttributes(attrs);
        setOrientation(0);
        setGravity(16);
        setBackgroundResource(R.drawable.n2_navigation_pill_background);
        if (ViewLibUtils.isAtLeastLollipop()) {
            setElevation((float) getResources().getDimensionPixelSize(R.dimen.n2_navigation_pill_elevation));
        }
    }

    private void setUpAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.n2_NavigationPill, 0, 0);
        setLeftButtonText(ta.getString(R.styleable.n2_NavigationPill_n2_leftText));
        setRightButtonText(ta.getString(R.styleable.n2_NavigationPill_n2_rightText));
        setLeftButtonIcon(ViewLibUtils.getDrawableCompat(context, ta, R.styleable.n2_NavigationPill_n2_leftDrawable));
        setRightButtonIcon(ViewLibUtils.getDrawableCompat(context, ta, R.styleable.n2_NavigationPill_n2_rightDrawable));
        setMode(ta.getInt(R.styleable.n2_NavigationPill_n2_mode, 2));
        ta.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.leftButtonContainer.getLayoutParams().width = -2;
        this.rightButtonContainer.getLayoutParams().width = -2;
        measureChild(this.leftButtonContainer, widthMeasureSpec, heightMeasureSpec);
        measureChild(this.rightButtonContainer, widthMeasureSpec, heightMeasureSpec);
        int itemWidth = Math.max(this.leftButtonContainer.getMeasuredWidth(), this.rightButtonContainer.getMeasuredWidth());
        this.leftButtonContainer.getLayoutParams().width = itemWidth;
        this.rightButtonContainer.getLayoutParams().width = itemWidth;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setLeftButtonText(CharSequence text) {
        this.leftButton.setText(text);
    }

    public void setLeftButtonTextColor(int colorRes) {
        if (colorRes != 0) {
            this.leftButton.setTextColor(ContextCompat.getColor(getContext(), colorRes));
        }
    }

    public void setLeftButtonIcon(int drawableRes) {
        this.leftButtonIcon.setImageDrawableCompat(drawableRes);
    }

    public void setLeftButtonIcon(Drawable drawable) {
        this.leftButtonIcon.setImageDrawable(drawable);
    }

    public void setLeftButtonBadgeCount(int badgeCount) {
        updateBadge(this.leftButtonBadge, this.leftButtonIcon, badgeCount);
    }

    private void updateBadge(AirTextView badgeView, AirImageView iconView, int badgeCount) {
        boolean showCount = badgeCount > 0;
        if (showCount) {
            badgeView.setText(String.valueOf(badgeCount));
        }
        ViewLibUtils.setVisibleIf(badgeView, showCount);
        ViewLibUtils.setGoneIf(iconView, showCount);
    }

    public void setLeftButtonClickListener(OnClickListener clickListener) {
        this.leftButtonContainer.setOnClickListener(clickListener);
    }

    public void setRightButtonText(CharSequence text) {
        this.rightButton.setText(text);
    }

    public void setRightButtonIcon(int drawableRes) {
        this.rightButtonIcon.setImageDrawableCompat(drawableRes);
    }

    public void setRightButtonIcon(Drawable drawable) {
        this.rightButtonIcon.setImageDrawable(drawable);
    }

    public void setRightButtonBadgeCount(int badgeCount) {
        updateBadge(this.rightButtonBadge, this.rightButtonIcon, badgeCount);
    }

    public void setRightButtonClickListener(OnClickListener clickListener) {
        this.rightButtonContainer.setOnClickListener(clickListener);
    }

    public void setMiddleButtonText(CharSequence text) {
        this.middleButton.setText(text);
    }

    public void setMiddleButtonTextColor(int colorRes) {
        if (colorRes != 0) {
            this.middleButton.setTextColor(ContextCompat.getColor(getContext(), colorRes));
        }
    }

    public void setMiddleButtonIcon(int drawableRes) {
        this.middleButtonIcon.setImageDrawableCompat(drawableRes);
    }

    public void setMiddleButtonIcon(Drawable drawable) {
        this.middleButtonIcon.setImageDrawable(drawable);
    }

    public void setMiddleButtonBadgeCount(int badgeCount) {
        updateBadge(this.middleButtonBadge, this.middleButtonIcon, badgeCount);
    }

    public void setMiddleButtonClickListener(OnClickListener clickListener) {
        this.middleButtonContainer.setOnClickListener(clickListener);
    }

    public void setMode(int mode) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        ViewLibUtils.setVisibleIf(this.leftButtonContainer, mode == 2 || mode == 0 || mode == 3);
        ViewGroup viewGroup = this.rightButtonContainer;
        if (mode == 2 || mode == 1 || mode == 3) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(viewGroup, z);
        View view = this.divider;
        if (mode == 2 || mode == 3) {
            z2 = true;
        } else {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(view, z2);
        ViewGroup viewGroup2 = this.middleButtonContainer;
        if (mode == 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewLibUtils.setVisibleIf(viewGroup2, z3);
        View view2 = this.middleButtonDivider;
        if (mode != 3) {
            z4 = false;
        }
        ViewLibUtils.setVisibleIf(view2, z4);
    }

    public static void mock(NavigationPill view) {
        view.setLeftButtonText("Left");
        view.setRightButtonText("Right");
        view.setMiddleButtonText("Middle");
    }
}
