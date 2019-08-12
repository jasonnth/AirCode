package com.airbnb.android.checkin;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.checkin.CheckInStepController.Listener;
import com.airbnb.android.checkin.data.CheckInComponent.Builder;
import com.airbnb.android.checkin.data.CheckInDataBindings;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInGuide;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;

public class CheckinStepFragment extends CheckinBaseFragment {
    private static final String ARG_STEP = "checkin_step";
    private static final String ARG_STEP_INDEX = "step_index";
    private static final String ARG_SUPPORTS_TRANSLATE = "supports_translation";
    private CheckInStepController adapterController;
    GuestCheckInJitneyLogger jitneyLogger;
    private final Listener listener = new Listener() {
        public void onImageSelected() {
            CheckinStepFragment.this.controller.showFullScreenImageViewer(CheckinStepFragment.this.step);
            CheckinStepFragment.this.jitneyLogger.logCheckinGuideGuestClickPhotoInEvent(CheckinStepFragment.this.controller.getGuide().getListingId(), (long) CheckinStepFragment.this.stepIndex);
        }

        public void onTranslationSelected(boolean translate) {
            CheckinStepFragment.this.controller.setShowTranslatedNote(translate);
            CheckInGuide guide = CheckinStepFragment.this.controller.getGuide();
            long listingId = guide.getListingId();
            String deviceLanguage = guide.getLocalizedLanguage();
            String guideLanguage = guide.getLanguage();
            if (translate) {
                CheckinStepFragment.this.jitneyLogger.logCheckinGuideGuestTranslateEvent(listingId, (long) CheckinStepFragment.this.stepIndex, deviceLanguage, guideLanguage);
            } else {
                CheckinStepFragment.this.jitneyLogger.logCheckinGuideGuestTranslateOriginalEvent(listingId, (long) CheckinStepFragment.this.stepIndex, deviceLanguage, guideLanguage);
            }
        }
    };
    @BindView
    RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public CheckInStep step;
    /* access modifiers changed from: private */
    public int stepIndex;

    public static CheckinStepFragment create(CheckInStep step2, boolean supportsTranslation, int stepIndex2) {
        return (CheckinStepFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CheckinStepFragment()).putParcelable(ARG_STEP, step2)).putBoolean(ARG_SUPPORTS_TRANSLATE, supportsTranslation)).putInt(ARG_STEP_INDEX, stepIndex2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((CheckInDataBindings) CoreApplication.instance().componentProvider()).checkInComponentProvider().get()).build().inject(this);
        boolean supportsTranslate = getArguments().getBoolean(ARG_SUPPORTS_TRANSLATE);
        this.step = (CheckInStep) getArguments().getParcelable(ARG_STEP);
        this.stepIndex = getArguments().getInt(ARG_STEP_INDEX);
        this.adapterController = new CheckInStepController(getContext(), this.step, this.listener, supportsTranslate, this.controller.showLocalizedGuide);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5618R.layout.fragment_check_in_guide_step, container, false);
        bindViews(view);
        this.recyclerView.setAdapter(this.adapterController.getAdapter());
        return view;
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            this.adapterController.setShowTranslatedNote(this.controller.showLocalizedGuide);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CheckinGuideGuestViewStepIndex;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("listing_id", this.controller.getGuide().getListingId()).mo11637kv(ARG_STEP_INDEX, this.stepIndex);
    }
}
