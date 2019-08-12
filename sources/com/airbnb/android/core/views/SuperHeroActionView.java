package com.airbnb.android.core.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;

public class SuperHeroActionView extends FrameLayout {
    @BindView
    Button bubble;

    public SuperHeroActionView(Context context) {
        super(context);
        init();
    }

    public SuperHeroActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), C0716R.layout.super_hero_action_view, this);
        ButterKnife.bind((View) this);
    }

    public void setText(CharSequence text) {
        this.bubble.setText(text);
    }

    public void setOnClickListener(OnClickListener l) {
        this.bubble.setOnClickListener(l);
    }

    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        this.bubble.setClickable(clickable);
    }
}
