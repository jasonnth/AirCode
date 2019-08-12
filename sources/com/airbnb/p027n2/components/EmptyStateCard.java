package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.EmptyStateCard */
public class EmptyStateCard extends BaseComponent {
    @BindView
    AirImageView image;
    @BindView
    AirTextView textView;

    public EmptyStateCard(Context context) {
        super(context);
    }

    public EmptyStateCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyStateCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_empty_state_card;
    }

    public void setText(CharSequence text) {
        ViewLibUtils.bindOptionalTextView((TextView) this.textView, text, true);
    }

    public void setText(int textRes) {
        setText((CharSequence) getResources().getString(textRes));
    }

    public void setImage(int drawableRes) {
        this.image.setImageResource(drawableRes);
    }

    public void setImage(Image image2) {
        this.image.setImage(image2);
    }

    public static void mock(EmptyStateCard card) {
        card.setText((CharSequence) "You have not been hired by anyone yet. When you are added to a listing by an owner, the listing will appear here.");
        card.setImage((Image) new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg"));
    }
}
