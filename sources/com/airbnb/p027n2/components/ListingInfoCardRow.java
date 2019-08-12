package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p002v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ListingInfoCardRow */
public class ListingInfoCardRow extends FrameLayout {
    @BindView
    CardView cardView;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView subTitleText;
    @BindView
    AirTextView titleText;

    public ListingInfoCardRow(Context context) {
        super(context);
        init(null);
    }

    public ListingInfoCardRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ListingInfoCardRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_listing_info_card_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ListingInfoCardRow, 0, 0);
        String titleText2 = a.getString(R.styleable.n2_ListingInfoCardRow_n2_titleText);
        String subtitleText = a.getString(R.styleable.n2_ListingInfoCardRow_n2_subtitleText);
        setTitleText(titleText2);
        setSubTitleText(subtitleText);
        a.recycle();
    }

    public void setOnClickListener(OnClickListener l) {
        this.cardView.setOnClickListener(l);
    }

    public void setTitleText(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setSubTitleText(CharSequence text) {
        this.subTitleText.setText(text);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public void setImageDrawable(int drawableRes) {
        this.imageView.setImageResource(drawableRes);
    }

    public void clearImage() {
        this.imageView.clear();
    }

    public static void mock(ListingInfoCardRow view) {
        view.setTitleText("Beautiful Studio in Echo Park");
        view.setSubTitleText("Los Angeles, CA");
    }

    public static void mockImage(ListingInfoCardRow view) {
        view.setTitleText("Beautiful Studio in Echo Park");
        view.setSubTitleText("Los Angeles, CA");
        view.setImageDrawable(R.drawable.n2_ic_camera);
    }
}
