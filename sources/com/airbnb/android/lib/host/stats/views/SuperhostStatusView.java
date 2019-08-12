package com.airbnb.android.lib.host.stats.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.models.SuperhostData;
import com.airbnb.android.core.utils.AndroidUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class SuperhostStatusView extends LinearLayout {
    @BindView
    TextView aboutAssessmentText;
    @BindView
    TextView dateText;
    @BindView
    TextView dateTitle;
    private OnClickListener learnMoreClickListener;
    @BindView
    ImageView superhostBadgeImageView;
    @BindView
    SectionHeader title;

    public SuperhostStatusView(Context context) {
        super(context);
        init();
    }

    public SuperhostStatusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SuperhostStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(1);
        LayoutInflater.from(getContext()).inflate(C0880R.layout.superhost_status_view, this);
        ButterKnife.bind((View) this);
        setBackgroundResource(AndroidUtils.getResource(getContext()));
    }

    @OnClick
    public void onLearnMoreClick(View view) {
        this.learnMoreClickListener.onClick(view);
    }

    public void setLearnMoreClickListener(OnClickListener onClickListener) {
        this.learnMoreClickListener = onClickListener;
    }

    public void setSuperhostData(SuperhostData superhostData) {
        setupTitle(superhostData);
        setupAboutAssessmentText(superhostData);
        setupDateFields(superhostData);
        setupSuperhostBadge(superhostData);
        if (superhostData.isInEligibilityAssessmentWindow()) {
            this.title.setButtonText((CharSequence) null);
        }
    }

    private void setupSuperhostBadge(SuperhostData superhostData) {
        boolean inEligibilityAssessmentWindow = superhostData.isInEligibilityAssessmentWindow();
        ViewLibUtils.setGoneIf(this.superhostBadgeImageView, inEligibilityAssessmentWindow);
        if (!inEligibilityAssessmentWindow) {
            this.superhostBadgeImageView.setImageResource(superhostData.isSuperhost() ? C0880R.C0881drawable.ic_super_host_badge_colored : C0880R.C0881drawable.ic_super_host_badge_greyscale);
        }
    }

    private void setupDateFields(SuperhostData superhostData) {
        String dateTextStr;
        this.dateTitle.setText(superhostData.isInEligibilityAssessmentWindow() ? C0880R.string.superhost_assessment_period_title : C0880R.string.host_standards_next_superhost_assessment_title);
        if (superhostData.isInEligibilityAssessmentWindow()) {
            dateTextStr = getContext().getString(C0880R.string.separator_with_values, new Object[]{superhostData.getCurrentAssessmentWindowStartDate().formatDate(getContext().getString(C0880R.string.date_name_format)), superhostData.getCurrentAssessmentWindowEndDate().formatDate(getContext().getString(C0880R.string.mdy_format_shorter))});
        } else {
            dateTextStr = superhostData.getNextEligibilityDate().formatDate(getContext().getString(C0880R.string.mdy_format_full));
        }
        this.dateText.setText(dateTextStr);
    }

    private void setupAboutAssessmentText(SuperhostData superhostData) {
        int aboutAssessmentTextResId;
        if (superhostData.isInEligibilityAssessmentWindow()) {
            aboutAssessmentTextResId = C0880R.string.superhost_status_view_description_in_assessment_period;
        } else {
            boolean isMeetingRequirements = superhostData.isMeetingRequirements();
            aboutAssessmentTextResId = superhostData.isSuperhost() ? isMeetingRequirements ? C0880R.string.about_superhost_assessment_is_superhost_and_meeting_requirements : C0880R.string.f1168xe5daa80a : isMeetingRequirements ? C0880R.string.f1166x512dbd0a : C0880R.string.f1167x9b64d3f6;
        }
        this.aboutAssessmentText.setText(aboutAssessmentTextResId);
    }

    private void setupTitle(SuperhostData superhostData) {
        int titleResId = superhostData.isInEligibilityAssessmentWindow() ? C0880R.string.superhost_view_title_in_assessment_window : superhostData.isSuperhost() ? superhostData.isMeetingRequirements() ? C0880R.string.title_is_superhost_and_meeting_requirements : C0880R.string.title_is_superhost_and_not_meeting_requirements : superhostData.isMeetingRequirements() ? C0880R.string.title_not_superhost_and_meeting_requirements : C0880R.string.title_not_superhost_and_not_meeting_requirements;
        this.title.setTitle(titleResId);
    }
}
