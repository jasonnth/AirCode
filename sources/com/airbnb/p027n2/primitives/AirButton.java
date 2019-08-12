package com.airbnb.p027n2.primitives;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.support.p002v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View.BaseSavedState;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.Typefaceable;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontHelper;
import com.airbnb.p027n2.utils.CenterDrawableWrapper;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;
import com.google.common.base.Preconditions;

/* renamed from: com.airbnb.n2.primitives.AirButton */
public class AirButton extends AppCompatButton implements Typefaceable {
    private int bottomPadding;
    private State currentState;
    private int currentTextColor;
    private boolean enableClickWhenLoading;
    private boolean isClickable;
    private int leftPadding;
    private int loadingDrawableHeight;
    private int loadingDrawableWidth;
    private Drawable normalStateBackgroundDrawable;
    private Drawable[] normalStateCompoundDrawables;
    private int rightPadding;
    private ColorStateList textColors;
    private int topPadding;

    /* renamed from: com.airbnb.n2.primitives.AirButton$State */
    public enum State {
        Normal,
        Loading,
        Success
    }

    /* renamed from: com.airbnb.n2.primitives.AirButton$SavedState */
    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        State buttonState;

        SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeSerializable(this.buttonState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.buttonState = (State) in.readSerializable();
        }
    }

    public AirButton(Context context) {
        super(context);
        init(context, null);
    }

    public AirButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AirButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public AirButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            this.currentState = State.Normal;
            saveState();
            Paris.style(this).apply(attrs);
            this.loadingDrawableWidth = getResources().getDimensionPixelSize(R.dimen.n2_loading_drawable_width);
            this.loadingDrawableHeight = getResources().getDimensionPixelSize(R.dimen.n2_loading_drawable_height);
            TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.n2_AirButton);
            compatSetDrawables(styledAttributes);
            styledAttributes.recycle();
        }
    }

    public void setFont(Font font) {
        FontHelper.setFont(this, font);
    }

    private void saveState() {
        this.normalStateBackgroundDrawable = getBackground();
        this.normalStateCompoundDrawables = getCompoundDrawables();
        this.textColors = getTextColors();
        this.currentTextColor = getCurrentTextColor();
        this.isClickable = isClickable();
        savePadding();
    }

    private void savePadding() {
        this.leftPadding = getPaddingLeft();
        this.rightPadding = getPaddingRight();
        this.topPadding = getPaddingTop();
        this.bottomPadding = getPaddingBottom();
    }

    private void restoreState() {
        setBackground(this.normalStateBackgroundDrawable);
        setTextColor(this.textColors);
        setClickable(this.isClickable);
        setCompoundDrawables(this.normalStateCompoundDrawables[0], this.normalStateCompoundDrawables[1], this.normalStateCompoundDrawables[2], this.normalStateCompoundDrawables[3]);
        setPadding(this.leftPadding, this.topPadding, this.rightPadding, this.bottomPadding);
    }

    public void enableClickWhenLoading(boolean enable) {
        this.enableClickWhenLoading = enable;
        if (this.currentState == State.Loading) {
            setClickable(enable);
        }
    }

    public void underlineText(boolean underline) {
        ViewLibUtils.underline(this, underline);
    }

    public void setState(State state) {
        int currentTextColor2 = getCurrentTextColor();
        if (currentTextColor2 == 0 && this.currentTextColor != 0) {
            currentTextColor2 = this.currentTextColor;
        }
        setState(state, currentTextColor2);
    }

    public void setState(State state, int colorInt) {
        if (this.currentState != state) {
            Preconditions.checkState(!isIllegalStateTransition(state), String.format("Illegal state transition. From %s to %s.", new Object[]{this.currentState.name(), state.name()}));
            switch (state) {
                case Normal:
                    restoreState();
                    break;
                case Loading:
                    saveState();
                    setState(state, getLoadingDrawable(colorInt));
                    break;
                case Success:
                    setState(state, getSuccessDrawable(colorInt));
                    break;
            }
            this.currentState = state;
        }
    }

    private boolean isIllegalStateTransition(State newState) {
        return this.currentState == State.Success && newState == State.Loading;
    }

    public State getState() {
        return this.currentState;
    }

    private void setState(State state, Drawable drawable) {
        boolean z;
        setBackground(drawable);
        setTextColor(0);
        if (state != State.Loading || !this.enableClickWhenLoading) {
            z = false;
        } else {
            z = true;
        }
        setClickable(z);
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public Drawable getSuccessDrawable(int colorInt) {
        Drawable drawable = DrawableCompat.wrap(AppCompatResources.getDrawable(getContext(), R.drawable.n2_ic_check_babu));
        DrawableCompat.setTint(drawable.mutate(), colorInt);
        return new LayerDrawable(new Drawable[]{this.normalStateBackgroundDrawable, new CenterDrawableWrapper(drawable)});
    }

    private void compatSetDrawables(TypedArray typedArray) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(ViewLibUtils.getDrawableCompat(context, typedArray, R.styleable.n2_AirButton_n2_drawableLeftCompat), ViewLibUtils.getDrawableCompat(context, typedArray, R.styleable.n2_AirButton_n2_drawableTopCompat), ViewLibUtils.getDrawableCompat(context, typedArray, R.styleable.n2_AirButton_n2_drawableRightCompat), ViewLibUtils.getDrawableCompat(context, typedArray, R.styleable.n2_AirButton_n2_drawableBottomCompat));
    }

    private Drawable getLoadingDrawable(int colorInt) {
        LoadingDrawable loadingDrawable = new LoadingDrawable(this.loadingDrawableWidth, this.loadingDrawableHeight);
        loadingDrawable.setColor(colorInt);
        return new LayerDrawable(new Drawable[]{this.normalStateBackgroundDrawable, loadingDrawable});
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.buttonState = this.currentState;
        return ss;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        if (ss.buttonState == State.Loading) {
            setState(ss.buttonState);
        }
    }
}
