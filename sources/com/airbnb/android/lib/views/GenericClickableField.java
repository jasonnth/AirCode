package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;

public class GenericClickableField extends LinearLayout {
    private static final int DEFAULT_TEXT_SIZE_SP = 20;
    @BindView
    TextView textView;

    public GenericClickableField(Context context) {
        super(context);
        init(null);
    }

    public GenericClickableField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GenericClickableField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (!isInEditMode()) {
            ButterKnife.bind(this, LayoutInflater.from(getContext()).inflate(C0880R.layout.generic_clickable_field, this));
            setOrientation(1);
            float textSize = TypedValue.applyDimension(2, 20.0f, getResources().getDisplayMetrics());
            TypedArray a = getContext().obtainStyledAttributes(attrs, C0880R.styleable.GenericClickableField);
            this.textView.setText(a.getString(C0880R.styleable.GenericClickableField_title));
            this.textView.setTextColor(a.getColor(C0880R.styleable.GenericClickableField_textColor, getResources().getColor(C0880R.color.c_rausch)));
            this.textView.setTextSize(0, a.getDimension(C0880R.styleable.GenericClickableField_textSize, textSize));
            this.textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, a.getResourceId(C0880R.styleable.GenericClickableField_rightIcon, 0), 0);
            a.recycle();
        }
    }

    public void setText(String text) {
        this.textView.setText(text);
    }

    public String getText() {
        return this.textView.getText().toString();
    }

    public void setTextColor(ColorStateList textColor) {
        this.textView.setTextColor(textColor);
    }
}
