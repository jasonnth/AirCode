package com.airbnb.android.lib.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.airbnb.android.lib.C0880R;

public class StickyStepProgressBar extends LinearLayout {
    private Integer mCurrentStep;
    private Integer mNumberSteps;
    private final LinearLayout mProgressBar;

    public StickyStepProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mProgressBar = (LinearLayout) LayoutInflater.from(context).inflate(C0880R.layout.sticky_step_progress_bar, this).findViewById(C0880R.C0882id.spb_progress_bar);
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

    private void setupViews() {
        if (this.mNumberSteps != null) {
            this.mProgressBar.removeAllViews();
            if (this.mCurrentStep.intValue() > 0) {
                createSection(this.mProgressBar, C0880R.C0881drawable.step_progress_bar_complete_middle);
            } else {
                createSection(this.mProgressBar, C0880R.C0881drawable.step_progress_bar_incomplete_middle);
            }
            int incrementToStep = this.mCurrentStep.equals(this.mNumberSteps) ? this.mCurrentStep.intValue() - 1 : this.mCurrentStep.intValue();
            for (int i = 1; i < incrementToStep; i++) {
                createSection(this.mProgressBar, C0880R.C0881drawable.step_progress_bar_complete_middle);
            }
            for (int i2 = this.mCurrentStep.intValue() + 1; i2 < this.mNumberSteps.intValue(); i2++) {
                createSection(this.mProgressBar, C0880R.C0881drawable.step_progress_bar_incomplete_middle);
            }
            if (this.mCurrentStep.equals(this.mNumberSteps)) {
                createSection(this.mProgressBar, C0880R.C0881drawable.step_progress_bar_complete_middle);
            } else {
                createSection(this.mProgressBar, C0880R.C0881drawable.step_progress_bar_incomplete_middle);
            }
        }
    }

    private void createSection(LinearLayout progressBar, int sectionDrawable) {
        ImageView section = new ImageView(getContext());
        Drawable drawable = getResources().getDrawable(sectionDrawable);
        LayoutParams params = new LayoutParams(0, -1, 1.0f);
        params.setMargins(1, 1, 1, 1);
        section.setLayoutParams(params);
        section.setBackground(drawable);
        progressBar.addView(section);
    }
}
