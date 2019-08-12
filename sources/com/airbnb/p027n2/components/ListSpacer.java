package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.widget.Space;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.ListSpacer */
public class ListSpacer extends LinearLayout {
    @BindView
    Space space;

    public ListSpacer(Context context) {
        super(context);
        init(null);
    }

    public ListSpacer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_list_spacer, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    public void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.n2_ListSpacer, 0, 0);
        setSpaceHeight(a.getInt(R.styleable.n2_ListSpacer_n2_spaceHeight, 12));
        a.recycle();
    }

    public void setSpaceHeight(int spaceHeight) {
        this.space.setLayoutParams(new LayoutParams(0, spaceHeight));
    }

    public void setSpaceHeightRes(int spaceHeight) {
        setSpaceHeight(getResources().getDimensionPixelSize(spaceHeight));
    }
}
