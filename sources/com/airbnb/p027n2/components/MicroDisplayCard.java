package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.percent.PercentFrameLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.MicroDisplayCard */
public class MicroDisplayCard extends PercentFrameLayout {
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView titleTextView;

    public MicroDisplayCard(Context context) {
        super(context);
        init(null);
    }

    public MicroDisplayCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MicroDisplayCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_micro_display_card, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_MicroDisplayCard);
        String title = a.getString(R.styleable.n2_MicroDisplayCard_n2_titleText);
        Drawable image = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_MicroDisplayCard_n2_image);
        setTitleText(title);
        setImage(image);
        a.recycle();
    }

    public void setTitleText(CharSequence title) {
        this.titleTextView.setText(title);
        this.titleTextView.setBackgroundResource(TextUtils.isEmpty(title) ? 0 : R.drawable.n2_scrim_gradient);
    }

    public void setImage(Drawable image) {
        this.imageView.setImageDrawable(image);
    }

    public void setImageUrl(String url) {
        this.imageView.setImageUrl(url);
    }

    public void clearImage() {
        this.imageView.clear();
    }

    public static void mock(MicroDisplayCard card) {
        card.setTitleText("Title");
    }
}
