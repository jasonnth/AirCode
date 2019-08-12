package com.airbnb.android.lib.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;

public class DotsProgressBar extends LinearLayout {
    private static final int FADE_IN_DELAY_MILLIS = 500;
    private Drawable completeDot;
    private Drawable currentDot;
    @BindDimen
    int dotMargin;
    private DotState[] dotStates;
    private Drawable incompleteDot;
    private int numDots;
    private final Paint paint = new Paint();
    @BindDimen
    int strokeWidth;

    public enum DotState {
        Incomplete(C0880R.color.c_gray_3, Style.STROKE),
        Current(C0880R.color.c_gray_3, Style.FILL_AND_STROKE),
        Complete(C0880R.color.completed_dot_green, Style.FILL_AND_STROKE);
        
        public final int color;
        public final Style style;

        private DotState(int color2, Style style2) {
            this.color = color2;
            this.style = style2;
        }
    }

    public DotsProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        ButterKnife.bind((View) this);
    }

    public void setUpDots(int numDots2, int numCompleted, int currentIndex) {
        this.numDots = numDots2;
        this.dotStates = new DotState[numDots2];
        int i = 0;
        while (i < numDots2) {
            if (i == currentIndex) {
                this.dotStates[i] = DotState.Current;
            } else {
                this.dotStates[i] = i < numCompleted ? DotState.Complete : DotState.Incomplete;
            }
            i++;
        }
        setUpView();
    }

    public void skipCurrentDot(int currentIndex) {
        this.dotStates[currentIndex] = DotState.Incomplete;
        incrementCurrentDotIndex(currentIndex);
        setUpView();
    }

    public void backUpCurrentDot(int currentIndex) {
        this.dotStates[currentIndex] = DotState.Incomplete;
        decrementCurrentDotIndex(currentIndex);
        setUpView();
    }

    public void completeCurrentDot(int currentIndex) {
        this.dotStates[currentIndex] = DotState.Complete;
        incrementCurrentDotIndex(currentIndex);
        setUpView();
    }

    public void fadeInView() {
        postDelayed(DotsProgressBar$$Lambda$1.lambdaFactory$(this), 500);
    }

    private void incrementCurrentDotIndex(int currentIndex) {
        if (currentIndex < this.dotStates.length - 1) {
            this.dotStates[currentIndex + 1] = DotState.Current;
        }
    }

    private void decrementCurrentDotIndex(int currentIndex) {
        if (currentIndex > 0) {
            this.dotStates[currentIndex - 1] = DotState.Current;
        }
    }

    private void setUpView() {
        post(DotsProgressBar$$Lambda$2.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void addDots() {
        if (this.numDots != 0) {
            removeAllViews();
            if (getHeight() > 0) {
                for (int i = this.numDots - 1; i >= 0; i--) {
                    View dot = getDot(this.dotStates[i]);
                    addView(dot, 0);
                    setDotMargins(dot);
                }
            }
        }
    }

    private void setDotMargins(View dot) {
        LayoutParams params = (LayoutParams) dot.getLayoutParams();
        params.setMargins(this.dotMargin, 0, this.dotMargin, 0);
        dot.setLayoutParams(params);
    }

    private View getDot(DotState dotState) {
        Drawable dotDrawable;
        ImageView dotImageView = new ImageView(getContext());
        switch (dotState) {
            case Incomplete:
                dotDrawable = getIncompleteDot();
                break;
            case Current:
                dotDrawable = getCurrentDot();
                break;
            case Complete:
                dotDrawable = getCompleteDot();
                break;
            default:
                dotDrawable = null;
                break;
        }
        dotImageView.setBackground(dotDrawable);
        return dotImageView;
    }

    private Drawable getDotDrawable(DotState dotState) {
        float dotSize = (float) (getHeight() - (this.strokeWidth * 2));
        int intDotSize = Math.round(dotSize);
        Bitmap bitmap = Bitmap.createBitmap(intDotSize, intDotSize, Config.ARGB_8888);
        float halfDot = dotSize / 2.0f;
        new Canvas(bitmap).drawCircle(halfDot, halfDot, halfDot - ((float) (this.strokeWidth / 2)), initializePaint(dotState));
        return new BitmapDrawable(getResources(), bitmap);
    }

    private Paint initializePaint(DotState state) {
        this.paint.setColor(getResources().getColor(state.color));
        this.paint.setStyle(state.style);
        this.paint.setStrokeWidth((float) this.strokeWidth);
        this.paint.setAntiAlias(true);
        return this.paint;
    }

    private Drawable getIncompleteDot() {
        if (this.incompleteDot == null) {
            this.incompleteDot = getDotDrawable(DotState.Incomplete);
        }
        return this.incompleteDot;
    }

    private Drawable getCurrentDot() {
        if (this.currentDot == null) {
            this.currentDot = getDotDrawable(DotState.Current);
        }
        return this.currentDot;
    }

    private Drawable getCompleteDot() {
        if (this.completeDot == null) {
            this.completeDot = getDotDrawable(DotState.Complete);
        }
        return this.completeDot;
    }
}
