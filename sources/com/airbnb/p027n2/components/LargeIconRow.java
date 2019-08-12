package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.LargeIconRow */
public class LargeIconRow extends RelativeLayout {
    @BindView
    AirImageView imageView;

    public LargeIconRow(Context context) {
        super(context);
        init(null);
    }

    public LargeIconRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LargeIconRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_large_icon_row, this);
        ButterKnife.bind((View) this);
    }

    public void setDrawable(int drawableRes) {
        this.imageView.setImageResource(drawableRes);
    }

    public static void mock(LargeIconRow row) {
        row.setDrawable(R.drawable.n2_ic_entire_place);
    }
}
