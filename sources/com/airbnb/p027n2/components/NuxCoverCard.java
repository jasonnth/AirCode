package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.NuxCoverCard */
public class NuxCoverCard extends BaseComponent {
    @BindView
    AirButton button;
    @BindView
    AirImageView image;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    public NuxCoverCard(Context context) {
        super(context);
    }

    public NuxCoverCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NuxCoverCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_nux_cover_card;
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setTitle(int textRes) {
        setTitle((CharSequence) getResources().getString(textRes));
    }

    public void setSubtitle(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.subtitle, text, true);
    }

    public void setSubtitle(int textRes) {
        setSubtitle((CharSequence) getResources().getString(textRes));
    }

    public void setImage(int drawableRes) {
        this.image.setImageResource(drawableRes);
    }

    public void setImage(Image image2) {
        this.image.setImage(image2);
    }

    public void setButton(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.button, text, true);
    }

    public void setButton(int textRes) {
        setButton((CharSequence) getResources().getString(textRes));
    }

    public void setButtonClickListener(OnClickListener listener) {
        this.button.setOnClickListener(listener);
    }

    public static void mock(NuxCoverCard card) {
        card.setTitle((CharSequence) "Title");
        card.setSubtitle((CharSequence) "subTitle");
        card.setButton((CharSequence) "buttonText");
        card.setImage((Image) new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg"));
    }
}
