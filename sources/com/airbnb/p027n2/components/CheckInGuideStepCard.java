package com.airbnb.p027n2.components;

import android.content.Context;
import android.support.p002v7.widget.CardView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.CheckInGuideStepCard */
public class CheckInGuideStepCard extends CardView {
    @BindView
    AirButton addPhotoButton;
    @BindView
    AirImageView editImageIcon;
    @BindView
    LinearLayout emptyPhotoContent;
    @BindView
    AirTextView errorStateText;
    @BindView
    RelativeLayout errorStateView;
    @BindView
    RefreshLoader imageLoader;
    @BindView
    AirTextView noteRow;
    @BindView
    AirImageView photo;
    @BindView
    AirTextView stepInstructionsView;
    @BindView
    AirTextView stepNumberView;

    /* renamed from: com.airbnb.n2.components.CheckInGuideStepCard$LoadingState */
    public enum LoadingState {
        Loading,
        Failed,
        None
    }

    public CheckInGuideStepCard(Context context) {
        super(context);
        init();
    }

    public CheckInGuideStepCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckInGuideStepCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_checkin_guide_step_card, this);
        ButterKnife.bind((View) this);
    }

    public void setTitle(CharSequence title) {
        this.stepNumberView.setText(title);
    }

    public void setSubtitle(CharSequence instructionText) {
        this.stepInstructionsView.setText(instructionText);
    }

    public void setButtonText(CharSequence buttonText) {
        this.addPhotoButton.setText(buttonText);
    }

    public void setButtonClickListener(OnClickListener listener) {
        this.addPhotoButton.setOnClickListener(listener);
    }

    public void setImageUrl(String imageUrl) {
        boolean hasImage;
        boolean z = true;
        this.photo.setImageUrl(imageUrl);
        if (!TextUtils.isEmpty(imageUrl)) {
            hasImage = true;
        } else {
            hasImage = false;
        }
        ViewLibUtils.setVisibleIf(this.photo, hasImage);
        ViewLibUtils.setVisibleIf(this.editImageIcon, hasImage);
        LinearLayout linearLayout = this.emptyPhotoContent;
        if (hasImage) {
            z = false;
        }
        ViewLibUtils.setVisibleIf(linearLayout, z);
    }

    public void setImageLoadingState(LoadingState state) {
        boolean hasImage;
        int i = 0;
        if (this.photo.getVisibility() == 0) {
            hasImage = true;
        } else {
            hasImage = false;
        }
        switch (state) {
            case Loading:
                this.photo.setAlpha(0.35f);
                this.editImageIcon.setVisibility(8);
                this.errorStateView.setVisibility(8);
                this.imageLoader.setVisibility(0);
                return;
            case Failed:
                this.photo.setAlpha(0.35f);
                this.editImageIcon.setVisibility(8);
                this.errorStateView.setVisibility(0);
                this.imageLoader.setVisibility(8);
                return;
            case None:
                this.photo.setAlpha(1.0f);
                AirImageView airImageView = this.editImageIcon;
                if (!hasImage) {
                    i = 8;
                }
                airImageView.setVisibility(i);
                this.errorStateView.setVisibility(8);
                this.imageLoader.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void setImageClickListener(OnClickListener listener) {
        this.photo.setOnClickListener(listener);
    }

    public void setErrorStateText(CharSequence text) {
        this.errorStateText.setText(text);
    }

    public void setErrorStateClickListener(OnClickListener listener) {
        this.errorStateView.setOnClickListener(listener);
    }

    public void setNoteText(CharSequence note) {
        this.noteRow.setText(note);
    }

    public void setNoteClickListener(OnClickListener listener) {
        this.noteRow.setOnClickListener(listener);
    }

    public static void mockFullStep(CheckInGuideStepCard card) {
        card.setImageUrl("https://a0.muscache.com/ac/pictures/7efdd33f-199e-4bd2-9ff5-8577d147f347.jpg?aki_policy=large");
        card.setNoteText("Look for this door");
    }

    public static void mockEmptyFirstStep(CheckInGuideStepCard card) {
        card.setImageUrl(null);
        card.setTitle("1");
        card.setSubtitle("What should your guest look for to know they are in the right place?");
        card.setButtonText("Add a photo");
        card.setNoteText("Add a note");
    }

    public static void mockNoPhoto(CheckInGuideStepCard card) {
        card.setImageUrl("https://a0.muscache.com/ac/pictures/08667359-7df3-46f3-b59f-b19c2eaba8ea.jpg?aki_policy=large");
        card.setTitle("2");
        card.setSubtitle("What should guests do next?");
        card.setButtonText("Add a photo");
        card.setNoteText("Look for this door");
    }

    public static void mockColoredText(CheckInGuideStepCard card) {
        card.setImageUrl(null);
        card.setTitle("2");
        card.setSubtitle("What should guests do next?");
        card.setButtonText("Add a photo");
        CharSequence rawText = "We can add colored text by using spannable utils. It doesn't need to be set directly by the view";
        SpannableString spannable = new SpannableString(rawText);
        spannable.setSpan(new ForegroundColorSpan(R.color.n2_babu), 0, rawText.length(), 17);
        card.setNoteText(spannable);
    }

    public static void mockPartiallyColoredText(CheckInGuideStepCard card) {
        card.setImageUrl(null);
        card.setTitle("2");
        card.setSubtitle("What should guests do next?");
        card.setButtonText("Add a photo");
        SpannableStringBuilder builder = new SpannableStringBuilder();
        CharSequence rawText = "Edit your note: ";
        SpannableString spannable = new SpannableString(rawText);
        spannable.setSpan(new ForegroundColorSpan(R.color.n2_babu), 0, rawText.length(), 17);
        builder.append(spannable);
        builder.append("This is the normal colored text you want to differentiate from the previous span");
        card.setNoteText(builder);
    }

    public static void mockLongNote(CheckInGuideStepCard card) {
        card.setImageUrl(null);
        card.setTitle("2");
        card.setSubtitle("What should guests do next?");
        card.setButtonText("Add a photo");
        card.setNoteText("This is an extremely long text note that the host would have entered to give directions to their guests. We want to make sure that text appropriately ellipsizes in that case. Are we there yet? Please ellipsize for me");
    }

    public static void mockLoadingPhotoWithInstructions(CheckInGuideStepCard card) {
        card.setImageUrl("https://a0.muscache.com/ac/pictures/08667359-7df3-46f3-b59f-b19c2eaba8ea.jpg?aki_policy=large");
        card.setImageLoadingState(LoadingState.Loading);
        CharSequence rawText = "Add a note for this step";
        SpannableString spannable = new SpannableString(rawText);
        spannable.setSpan(new ForegroundColorSpan(R.color.n2_babu), 0, rawText.length(), 17);
        card.setNoteText(spannable);
    }

    public static void mockLoadingPhotoWithNote(CheckInGuideStepCard card) {
        card.setImageUrl("https://a0.muscache.com/ac/pictures/08667359-7df3-46f3-b59f-b19c2eaba8ea.jpg?aki_policy=large");
        card.setImageLoadingState(LoadingState.Loading);
        card.setNoteText("Look for this door");
    }

    public static void mockErrorPhotoWithNote(CheckInGuideStepCard card) {
        card.setImageUrl("https://a0.muscache.com/ac/pictures/08667359-7df3-46f3-b59f-b19c2eaba8ea.jpg?aki_policy=large");
        card.setImageLoadingState(LoadingState.Failed);
        card.setErrorStateText("Tap to retry");
        card.setNoteText("Look for this door");
    }
}
