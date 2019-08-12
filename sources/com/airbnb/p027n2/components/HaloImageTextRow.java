package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.HaloImageTextRow */
public class HaloImageTextRow extends BaseDividerComponent {
    @BindView
    AirTextView firstRowText;
    @BindView
    HaloImageView haloImage;
    @BindView
    AirTextView secondRowText;

    public HaloImageTextRow(Context context) {
        super(context);
    }

    public HaloImageTextRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HaloImageTextRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_halo_image_text_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    public void setFirstTextRow(CharSequence text) {
        this.firstRowText.setText(text);
    }

    public void setSecondTextRow(CharSequence text) {
        this.secondRowText.setText(text);
    }

    public void setHaloImageUrl(String url) {
        if (url != null) {
            this.haloImage.setImageUrl(url);
        }
    }

    public static void mock(HaloImageTextRow row) {
        row.setFirstTextRow("First text row");
        row.setSecondTextRow("Second text row");
        row.setHaloImageUrl("https://a0.muscache.com/im/users/14056223/profile_pic/1396996965/original.jpg?aki_policy=profile_x_medium");
    }
}
