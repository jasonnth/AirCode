package com.airbnb.p027n2.components.decide.select;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;

/* renamed from: com.airbnb.n2.components.decide.select.SelectMarquee */
public class SelectMarquee extends LinearLayout {
    @BindView
    LinearLayout homeTourButton;
    @BindView
    AirTextView homeTourButtonTextView;
    @BindView
    AirImageView imageView;
    private final OnScrollListener onScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == 0) {
                SelectMarquee.this.homeTourButtonTextView.setVisibility(0);
                recyclerView.removeOnScrollListener(this);
            }
        }
    };
    @BindView
    TextView titleView;

    public SelectMarquee(Context context) {
        super(context);
        init(null);
    }

    public SelectMarquee(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SelectMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setOrientation(1);
        inflate(getContext(), R.layout.n2_select_marquee, this);
        ButterKnife.bind((View) this);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof RecyclerView) {
            ((RecyclerView) parent).addOnScrollListener(this.onScrollListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewParent parent = getParent();
        if (parent instanceof RecyclerView) {
            ((RecyclerView) parent).removeOnScrollListener(this.onScrollListener);
        }
    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }

    public void setTitle(CharSequence text) {
        this.titleView.setText(text);
    }

    public void setHomeTourClickListener(OnClickListener listener) {
        this.homeTourButton.setOnClickListener(listener);
    }

    public static void mock(SelectMarquee row) {
        row.setTitle("Listing title");
        row.setImage(new SimpleImage("https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large"));
    }
}
