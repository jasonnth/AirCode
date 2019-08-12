package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.onboarding_overlay.CustomBulletSpan;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BulletTextRow */
public class BulletTextRow extends FrameLayout {
    private int bulletRadiusRes;
    private int gapWidthRes;
    @BindView
    AirTextView textView;

    public BulletTextRow(Context context) {
        super(context);
        init(null);
    }

    public BulletTextRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BulletTextRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_bullet_text_row, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_BulletTextRow, 0, 0);
        String text = a.getString(R.styleable.n2_BulletTextRow_n2_text);
        this.bulletRadiusRes = a.getResourceId(R.styleable.n2_BulletTextRow_n2_bulletRadiusRes, R.dimen.n2_default_bullet_radius);
        this.gapWidthRes = a.getResourceId(R.styleable.n2_BulletTextRow_n2_gapWidthRes, R.dimen.n2_horizontal_padding_tiny);
        setText((CharSequence) text);
        a.recycle();
    }

    public void setText(CharSequence body) {
        addBullet(body);
    }

    public void setText(int strRes) {
        addBullet(getResources().getString(strRes));
    }

    private void addBullet(CharSequence text) {
        if (text == null) {
            this.textView.setText(null);
            return;
        }
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new CustomBulletSpan(getResources().getDimensionPixelOffset(this.gapWidthRes), getResources().getDimensionPixelOffset(this.bulletRadiusRes)), 0, text.length(), 0);
        this.textView.setText(spannable);
    }

    public static void mock(BulletTextRow row) {
        row.setText((CharSequence) "Text");
    }
}
