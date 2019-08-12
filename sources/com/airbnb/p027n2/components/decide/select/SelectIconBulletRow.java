package com.airbnb.p027n2.components.decide.select;

import android.content.Context;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.BaseDividerComponent;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectIconBulletRow */
public class SelectIconBulletRow extends BaseDividerComponent {
    @BindView
    AirImageView iconView;
    @BindView
    AirTextView textView;

    public SelectIconBulletRow(Context context) {
        super(context);
    }

    public SelectIconBulletRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectIconBulletRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(CharSequence text) {
        this.textView.setText(text);
    }

    public void setDrawableResource(int imageRes) {
        this.iconView.setImageResource(imageRes);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_icon_bullet_row;
    }

    public static void mock(SelectIconBulletRow row) {
        row.setDrawableResource(R.drawable.n2_ic_am_dog);
        row.setText("Distance from Los Angeles Intl. Airport (LAX) - 39 min by car");
    }
}
