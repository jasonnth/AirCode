package com.airbnb.android.core.views;

import android.content.Context;
import android.support.p000v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;

public class SuperHeroDismissView extends FrameLayout {
    @BindView
    View dismissChevron;
    private GestureDetectorCompat gestureDetector;
    /* access modifiers changed from: private */
    public SuperHeroDismissInterface superHeroDismissInterface;

    public interface SuperHeroDismissInterface {
        void onFlingOrTap();
    }

    public SuperHeroDismissView(Context context) {
        super(context);
        init(context);
    }

    public SuperHeroDismissView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SuperHeroDismissView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, C0716R.layout.super_hero_dismiss_view, this);
        ButterKnife.bind((View) this);
        this.gestureDetector = new GestureDetectorCompat(context, new SimpleOnGestureListener() {
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (SuperHeroDismissView.this.superHeroDismissInterface == null) {
                    return super.onFling(e1, e2, velocityX, velocityY);
                }
                SuperHeroDismissView.this.superHeroDismissInterface.onFlingOrTap();
                return true;
            }

            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.superHeroDismissInterface != null) {
            return this.gestureDetector.onTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    public void enable(SuperHeroDismissInterface superHeroDismissInterface2) {
        this.superHeroDismissInterface = superHeroDismissInterface2;
        this.dismissChevron.setOnClickListener(SuperHeroDismissView$$Lambda$1.lambdaFactory$(superHeroDismissInterface2));
        setVisibility(0);
    }

    public void disable() {
        this.superHeroDismissInterface = null;
        this.dismissChevron.setOnClickListener(null);
        setVisibility(8);
    }
}
