package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import icepick.State;

public class SaveButton extends FrameLayout {
    private static final int PROGRESS = 1;
    private static final int SAVE = 0;
    private static final int SUCCESS = 2;
    @BindView
    ProgressBar progressBar;
    @BindView
    Button saveButton;
    @State
    protected boolean saveButtonEnabled;
    private SaveButtonListener saveButtonListener;
    private final Runnable successIconAnimationRunnable;
    @BindView
    ImageView successImage;
    @State
    protected int viewState;

    public interface SaveButtonListener {
        void onSuccessAnimationComplete();
    }

    public SaveButton(Context context) {
        this(context, null);
    }

    public SaveButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SaveButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.saveButtonEnabled = true;
        this.successIconAnimationRunnable = SaveButton$$Lambda$1.lambdaFactory$(this);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(C0880R.layout.view_save_button, this);
        ButterKnife.bind((View) this);
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, C0880R.styleable.SaveButton, defStyleAttr, 0);
            int successRes = array.getResourceId(C0880R.styleable.SaveButton_successIcon, -1);
            if (successRes != -1) {
                this.successImage.setImageResource(successRes);
            }
            array.recycle();
        }
        setViewState(0);
    }

    public void setOnClickListener(OnClickListener l) {
        this.saveButton.setOnClickListener(l);
    }

    public void setEnabled(boolean enabled) {
        boolean z;
        boolean z2 = true;
        this.saveButtonEnabled = enabled;
        if (!this.saveButtonEnabled || this.viewState != 0) {
            z = false;
        } else {
            z = true;
        }
        super.setEnabled(z);
        Button button = this.saveButton;
        if (!this.saveButtonEnabled || this.viewState != 0) {
            z2 = false;
        }
        button.setEnabled(z2);
    }

    public void setListener(SaveButtonListener listener) {
        this.saveButtonListener = listener;
    }

    private void setViewState(int state) {
        boolean z;
        boolean z2 = true;
        removeCallbacks(this.successIconAnimationRunnable);
        this.viewState = state;
        ViewUtils.setInvisibleIf(this.saveButton, state != 0);
        ProgressBar progressBar2 = this.progressBar;
        if (state != 1) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setInvisibleIf(progressBar2, z);
        ImageView imageView = this.successImage;
        if (state == 2) {
            z2 = false;
        }
        ViewUtils.setInvisibleIf(imageView, z2);
        setEnabled(this.saveButtonEnabled);
        if (state == 2) {
            postDelayed(this.successIconAnimationRunnable, 750);
        }
    }

    static /* synthetic */ void lambda$new$0(SaveButton saveButton2) {
        Check.state(saveButton2.viewState == 2);
        if (saveButton2.saveButtonListener != null) {
            saveButton2.saveButtonListener.onSuccessAnimationComplete();
        }
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
        setViewState(this.viewState);
    }
}
