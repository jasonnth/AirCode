package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ReviewBulletRow */
public class ReviewBulletRow extends LinearLayout implements DividerView {
    @BindView
    AirTextView titleText;

    public ReviewBulletRow(Context context) {
        super(context);
        init(null);
    }

    public ReviewBulletRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ReviewBulletRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_review_bullet_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
        setOrientation(0);
        setLayoutParams(new LayoutParams(-1, -2));
        setPadding(getResources().getDimensionPixelOffset(R.dimen.n2_horizontal_padding_tiny), getResources().getDimensionPixelOffset(R.dimen.n2_vertical_padding_tiny), getResources().getDimensionPixelOffset(R.dimen.n2_horizontal_padding_tiny), getResources().getDimensionPixelOffset(R.dimen.n2_vertical_padding_tiny));
        setGravity(48);
        setClickable(false);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ReviewBulletRow);
        setTitle((CharSequence) ta.getString(R.styleable.n2_ReviewBulletRow_n2_text));
        ta.recycle();
    }

    public void setTitle(CharSequence text) {
        this.titleText.setText(text);
    }

    public void setTitle(int stringId) {
        setTitle((CharSequence) getResources().getString(stringId));
    }

    public void showDivider(boolean showDivider) {
    }

    public static void mock(ReviewBulletRow view) {
        view.setTitle((CharSequence) "Title");
    }
}
