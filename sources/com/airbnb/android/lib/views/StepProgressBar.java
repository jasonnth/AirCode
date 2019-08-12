package com.airbnb.android.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.autoscale.AutoScaleTextView;

public class StepProgressBar extends LinearLayout {
    private final AutoScaleTextView mCaptionTextView;
    private Integer mCurrentStep;
    private Integer mNumberSteps;
    private final LinearLayout mProgressBar;

    public StepProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(C0880R.layout.step_progress_bar, this);
        this.mCaptionTextView = (AutoScaleTextView) view.findViewById(C0880R.C0882id.spb_caption);
        this.mProgressBar = (LinearLayout) view.findViewById(C0880R.C0882id.spb_progress_bar);
    }

    public void initializeView(int numberSteps, int currentStep) {
        this.mNumberSteps = Integer.valueOf(numberSteps);
        this.mCurrentStep = Integer.valueOf(currentStep);
        setupViews();
    }

    public void incrementStep() {
        if (this.mCurrentStep.intValue() < this.mNumberSteps.intValue()) {
            Integer num = this.mCurrentStep;
            this.mCurrentStep = Integer.valueOf(this.mCurrentStep.intValue() + 1);
            setupViews();
        }
    }

    public void incrementToStep(int step) {
        this.mCurrentStep = Integer.valueOf(step);
        setupViews();
    }

    public void setCaption(String text) {
        this.mCaptionTextView.setText(text);
    }

    public void setCaption(int textResourceId) {
        this.mCaptionTextView.setText(getResources().getString(textResourceId));
    }

    private void setupViews() {
        if (this.mNumberSteps != null) {
            this.mProgressBar.removeAllViews();
            if (this.mCurrentStep.intValue() > 0) {
                this.mProgressBar.addView(createSection(C0880R.C0881drawable.step_progress_bar_complete_left));
            } else {
                this.mProgressBar.addView(createSection(C0880R.C0881drawable.step_progress_bar_incomplete_left));
            }
            int incrementToStep = this.mCurrentStep.equals(this.mNumberSteps) ? this.mCurrentStep.intValue() - 1 : this.mCurrentStep.intValue();
            for (int i = 1; i < incrementToStep; i++) {
                this.mProgressBar.addView(createSection(C0880R.C0881drawable.step_progress_bar_complete_middle));
            }
            for (int i2 = this.mCurrentStep.intValue() + 1; i2 < this.mNumberSteps.intValue(); i2++) {
                this.mProgressBar.addView(createSection(C0880R.C0881drawable.step_progress_bar_incomplete_middle));
            }
            if (this.mCurrentStep.equals(this.mNumberSteps)) {
                this.mProgressBar.addView(createSection(C0880R.C0881drawable.step_progress_bar_complete_right));
            } else {
                this.mProgressBar.addView(createSection(C0880R.C0881drawable.step_progress_bar_incomplete_right));
            }
        }
    }

    private ImageView createSection(int sectionDrawable) {
        ImageView section = new ImageView(getContext());
        Drawable drawable = getResources().getDrawable(sectionDrawable);
        LayoutParams params = new LayoutParams(0, 10, 1.0f);
        params.setMargins(1, 1, 1, 1);
        section.setLayoutParams(params);
        section.setBackground(drawable);
        return section;
    }
}
