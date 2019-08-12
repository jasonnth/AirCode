package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.PosterRow */
public class PosterRow extends LinearLayout {
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView subtitle;
    @BindView
    AirTextView title;

    /* renamed from: com.airbnb.n2.components.PosterRow$PosterOrientation */
    public enum PosterOrientation {
        LANDSCAPE,
        PORTRAIT
    }

    public PosterRow(Context context) {
        super(context);
        init();
    }

    public PosterRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PosterRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_poster_row, this);
        ButterKnife.bind((View) this);
    }

    public void setTitle(CharSequence text) {
        this.title.setText(text);
    }

    public void setSubtitle(CharSequence text) {
        this.subtitle.setText(text);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public void setPosterOrientation(PosterOrientation orientation) {
        if (orientation != null && orientation == PosterOrientation.PORTRAIT) {
            float scale = getContext().getResources().getDisplayMetrics().density;
            int pxHWidth = (int) ((50.0f * scale) + 0.5f);
            this.imageView.getLayoutParams().height = (int) ((75.0f * scale) + 0.5f);
            this.imageView.getLayoutParams().width = pxHWidth;
            this.imageView.requestLayout();
        }
    }

    public static void mock(PosterRow row) {
        row.setTitle("Title");
        row.setSubtitle("Subtitle");
    }
}
