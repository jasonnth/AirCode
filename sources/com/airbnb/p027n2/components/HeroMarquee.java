package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p002v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.HeroMarquee */
public class HeroMarquee extends RelativeLayout implements DividerView {
    @BindView
    AirButton buttonFirst;
    @BindView
    AirButton buttonSecond;
    @BindView
    AirTextView captionText;
    @BindView
    ViewGroup contentView;
    @BindView
    View gradient;
    @BindView
    AirImageView icon;
    @BindView
    protected AirImageView imageView;
    @BindView
    AirTextView titleText;

    public HeroMarquee(Context context) {
        super(context);
        init(null);
    }

    public HeroMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HeroMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_hero_marquee, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        this.contentView.setClipToPadding(false);
    }

    public void setupAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_HeroMarquee, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_HeroMarquee_n2_titleText);
        String captionText2 = a.getString(R.styleable.n2_HeroMarquee_n2_captionText);
        Drawable iconImage = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_HeroMarquee_n2_icon);
        String firstButtonText = a.getString(R.styleable.n2_HeroMarquee_n2_firstButtonText);
        String secondButtonText = a.getString(R.styleable.n2_HeroMarquee_n2_secondButtonText);
        int themeColor = a.getColor(R.styleable.n2_HeroMarquee_n2_themeColor, 0);
        setTitle((CharSequence) titleText2);
        setCaption((CharSequence) captionText2);
        setIcon(iconImage);
        setFirstButtonText((CharSequence) firstButtonText);
        setSecondButtonText((CharSequence) secondButtonText);
        setThemeColor(themeColor);
        if (a.hasValue(R.styleable.n2_HeroMarquee_n2_firstButtonTextColor)) {
            setFirstButtonTextColor(a.getColor(R.styleable.n2_HeroMarquee_n2_firstButtonTextColor, 0));
        }
        if (a.hasValue(R.styleable.n2_HeroMarquee_n2_backgroundColor)) {
            setBackgroundColor(a.getColor(R.styleable.n2_HeroMarquee_n2_backgroundColor, 0));
        }
        if (a.hasValue(R.styleable.n2_HeroMarquee_n2_titleColor)) {
            setTitleColor(a.getColor(R.styleable.n2_HeroMarquee_n2_titleColor, 0));
        }
        if (a.hasValue(R.styleable.n2_HeroMarquee_n2_captionColor)) {
            setCaptionColor(a.getColor(R.styleable.n2_HeroMarquee_n2_captionColor, 0));
        }
        if (a.hasValue(R.styleable.n2_HeroMarquee_n2_firstButtonBackground)) {
            setFirstButtonBackgroundDrawable(ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_HeroMarquee_n2_firstButtonBackground));
        }
        a.recycle();
    }

    public void setThemeColor(int themeColor) {
        if (themeColor != 0) {
            this.buttonFirst.setTextColor(themeColor);
            setBackgroundColor(themeColor);
        }
    }

    public void setTitle(CharSequence string) {
        ViewLibUtils.setGoneIf(this.titleText, TextUtils.isEmpty(string));
        this.titleText.setText(string);
    }

    public void setTitle(int stringRes) {
        setTitle((CharSequence) getResources().getString(stringRes));
    }

    public void setTitleColor(int color) {
        this.titleText.setTextColor(color);
    }

    public void setCaption(CharSequence string) {
        ViewLibUtils.setGoneIf(this.captionText, TextUtils.isEmpty(string));
        this.captionText.setText(string);
    }

    public void setCaption(int stringRes) {
        setCaption((CharSequence) getResources().getString(stringRes));
    }

    public void setCaptionColor(int color) {
        this.captionText.setTextColor(color);
    }

    public void setFirstButtonText(CharSequence firstButtonText) {
        this.buttonFirst.setText(firstButtonText);
        setFirstButtonVisiblity(!TextUtils.isEmpty(firstButtonText));
    }

    public void setFirstButtonBackgroundDrawable(Drawable drawable) {
        this.buttonFirst.setBackground(drawable);
    }

    public void setFirstButtonBackground(int background) {
        this.buttonFirst.setBackgroundResource(background);
    }

    public void setSecondButtonBackground(int background) {
        this.buttonSecond.setBackgroundResource(background);
    }

    public void setSecondButtonText(CharSequence secondButtonText) {
        this.buttonSecond.setText(secondButtonText);
        setSecondButtonVisiblity(!TextUtils.isEmpty(secondButtonText));
    }

    public void setFirstButtonText(int firstButtonTextRes) {
        this.buttonFirst.setText(getResources().getString(firstButtonTextRes));
        setFirstButtonVisiblity(firstButtonTextRes != 0);
    }

    public void setSecondButtonText(int secondButtonTextRes) {
        this.buttonSecond.setText(getResources().getString(secondButtonTextRes));
        setSecondButtonVisiblity(secondButtonTextRes != 0);
    }

    public void setFirstButtonClickListener(OnClickListener firstButtonListener) {
        this.buttonFirst.setOnClickListener(firstButtonListener);
    }

    public void setSecondButtonClickListener(OnClickListener secondButtonListener) {
        this.buttonSecond.setOnClickListener(secondButtonListener);
    }

    public void setFirstButtonVisiblity(boolean firstButtonVisible) {
        ViewLibUtils.setVisibleIf(this.buttonFirst, firstButtonVisible);
    }

    public void setFirstButtonState(State state) {
        this.buttonFirst.setState(state);
    }

    public void setSecondButtonState(State state) {
        this.buttonSecond.setState(state);
    }

    public void setSecondButtonVisiblity(boolean secondButtonVisible) {
        ViewLibUtils.setVisibleIf(this.buttonSecond, secondButtonVisible);
    }

    public void setFirstButtonTextColor(int colorInt) {
        this.buttonFirst.setTextColor(colorInt);
    }

    public void setSecondButtonTextColor(int colorInt) {
        this.buttonSecond.setTextColor(colorInt);
    }

    public void setFirstButtonEnabled(boolean enabled) {
        this.buttonFirst.setEnabled(enabled);
    }

    public void setSecondButtonEnabled(boolean enabled) {
        this.buttonSecond.setEnabled(enabled);
    }

    public void setIconUrl(String url) {
        this.icon.setImageUrl(url);
        ViewLibUtils.setGoneIf(this.icon, TextUtils.isEmpty(url));
    }

    public void setIcon(Drawable iconImage) {
        this.icon.setImageDrawable(iconImage);
        ViewLibUtils.setGoneIf(this.icon, iconImage == null);
    }

    public void setIcon(int icon2) {
        setIcon(AppCompatResources.getDrawable(getContext(), icon2));
    }

    public void setImageUrl(String url) {
        ViewLibUtils.setGoneIf(this.imageView, TextUtils.isEmpty(url));
        setBackgroundResource(R.color.n2_hof);
        this.imageView.setImageUrl(url);
    }

    public void setImageResource(int resId) {
        ViewLibUtils.setVisibleIf(this.imageView, resId != 0);
        this.imageView.clear();
        this.imageView.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable) {
        ViewLibUtils.setVisibleIf(this.imageView, drawable != null);
        this.imageView.clear();
        this.imageView.setImageDrawable(drawable);
    }

    public void setGradientEnabled(boolean enabled) {
        ViewLibUtils.setVisibleIf(this.gradient, enabled);
    }

    public void setScrimEnabled(boolean enabled) {
        this.imageView.setScrimForText(enabled);
    }

    public void showDivider(boolean showDivider) {
    }

    public static void mock(HeroMarquee marquee) {
        marquee.setTitle((CharSequence) "Title");
        marquee.setCaption((CharSequence) "Optional caption");
    }
}
