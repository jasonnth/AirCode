package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.content.ContextCompat;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.GiftCardPromo */
public class GiftCardPromo extends PercentRelativeLayout {
    @BindView
    View bottomSpace;
    @BindView
    AirImageView promoImage;

    public GiftCardPromo(Context context) {
        super(context);
        init(null);
    }

    public GiftCardPromo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GiftCardPromo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_gift_card_promo, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_GiftCardPromo, 0, 0);
        showBottomSpace(ta.getBoolean(R.styleable.n2_GiftCardPromo_n2_showBottomSpace, true));
        ta.recycle();
    }

    public void setImageUrl(String url) {
        if (url != null) {
            this.promoImage.setBackground(null);
            this.promoImage.setImageUrl(url);
            return;
        }
        this.promoImage.clear();
        this.promoImage.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.n2_loading_background));
    }

    private void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public static void mock(GiftCardPromo promo) {
    }
}
