package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.DisplayCard */
public class DisplayCard extends RelativeLayout implements DividerView {
    @BindView
    View bottomSpace;
    @BindView
    View clickOverlay;
    @BindView
    AirTextView commentCount;
    @BindView
    View divider;
    @BindView
    LinearLayout extraRow;
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView likeCount;
    @BindView
    AirTextView textView;

    public DisplayCard(Context context) {
        super(context);
        init(null);
    }

    public DisplayCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DisplayCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_display_card, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_DisplayCard);
        String text = a.getString(R.styleable.n2_DisplayCard_n2_titleText);
        Drawable image = ViewLibUtils.getDrawableCompat(getContext(), a, R.styleable.n2_DisplayCard_n2_image);
        boolean showBottomSpace = a.getBoolean(R.styleable.n2_DisplayCard_n2_showBottomSpace, true);
        boolean showDivider = a.getBoolean(R.styleable.n2_DisplayCard_n2_showDivider, true);
        setText(text);
        setImage(image);
        showBottomSpace(showBottomSpace);
        showDivider(showDivider);
        a.recycle();
    }

    public void setText(int textRes) {
        setText(getContext().getString(textRes));
    }

    public void setText(String text) {
        this.textView.setText(text);
    }

    public void setCommentCount(int comments) {
        this.commentCount.setText(Integer.toString(comments));
    }

    public void setLikeCount(int likes) {
        this.likeCount.setText(Integer.toString(likes));
    }

    public void setImage(int drawableRes) {
        setImage(AppCompatResources.getDrawable(getContext(), drawableRes));
    }

    public void setImage(Drawable image) {
        this.imageView.setImageDrawable(image);
    }

    public void setImageUrl(String imageUrl) {
        this.imageView.setImageUrl(imageUrl);
    }

    public void showBottomSpace(boolean showBottomSpace) {
        ViewLibUtils.setVisibleIf(this.bottomSpace, showBottomSpace);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public void setOnClickListener(OnClickListener l) {
        this.clickOverlay.setOnClickListener(l);
    }

    public static void mock(DisplayCard card) {
    }

    public static void mockWithText(DisplayCard card) {
        card.setText("DisplayCard can have two lines of text with wrapping (Optional)");
    }
}
