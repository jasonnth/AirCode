package com.airbnb.android.core.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import butterknife.BindView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.p027n2.components.HeroMarquee;

public class HeroMarqueeFragment extends AirDialogFragment {
    private static final String BODY_TEXT = "text_body";
    private static final String FIRST_BUTTON_TEXT = "first_button_text";
    private static final String HEADER_TITLE = "header_title";
    private static final String ICON_RES = "icon_res";
    private static final String REQUEST_CODE = "request_code";
    private static final String SECOND_BUTTON_TEXT = "second_button_text";
    @BindView
    HeroMarquee heroMarquee;

    public static class HeroMarqueeFragmentBuilder {
        private final Context context = CoreApplication.appContext();
        private final Bundle mArgs = new Bundle();

        public HeroMarqueeFragment build() {
            HeroMarqueeFragment fragment = new HeroMarqueeFragment();
            fragment.setArguments(this.mArgs);
            return fragment;
        }

        public HeroMarqueeFragmentBuilder withTitle(int titleRes) {
            return withTitle(this.context.getString(titleRes));
        }

        public HeroMarqueeFragmentBuilder withTitle(String title) {
            this.mArgs.putString(HeroMarqueeFragment.HEADER_TITLE, title);
            return this;
        }

        public HeroMarqueeFragmentBuilder withBodyText(int bodyTextRes) {
            return withBodyText(this.context.getString(bodyTextRes));
        }

        public HeroMarqueeFragmentBuilder withBodyText(String bodyText) {
            this.mArgs.putString(HeroMarqueeFragment.BODY_TEXT, bodyText);
            return this;
        }

        public HeroMarqueeFragmentBuilder withFirstButtonText(String firstButtonText) {
            this.mArgs.putString(HeroMarqueeFragment.FIRST_BUTTON_TEXT, firstButtonText);
            return this;
        }

        public HeroMarqueeFragmentBuilder withFirstButton(int firstButtonRes) {
            withFirstButtonText(this.context.getString(firstButtonRes));
            return this;
        }

        public HeroMarqueeFragmentBuilder withSecondButtonText(String secondButtonText) {
            this.mArgs.putString(HeroMarqueeFragment.SECOND_BUTTON_TEXT, secondButtonText);
            return this;
        }

        public HeroMarqueeFragmentBuilder withSecondButton(int secondButtonRes) {
            withSecondButtonText(this.context.getString(secondButtonRes));
            return this;
        }

        public HeroMarqueeFragmentBuilder withIcon(int iconRes) {
            this.mArgs.putInt(HeroMarqueeFragment.ICON_RES, iconRes);
            return this;
        }

        public HeroMarqueeFragmentBuilder withRequestCode(int requestCode) {
            this.mArgs.putInt(HeroMarqueeFragment.REQUEST_CODE, requestCode);
            return this;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_hero_marquee, container, false);
        bindViews(view);
        Bundle args = getArguments();
        if (args.containsKey(HEADER_TITLE)) {
            this.heroMarquee.setTitle((CharSequence) args.getString(HEADER_TITLE));
        }
        if (args.containsKey(BODY_TEXT)) {
            this.heroMarquee.setCaption((CharSequence) args.getString(BODY_TEXT));
        }
        if (args.containsKey(FIRST_BUTTON_TEXT)) {
            this.heroMarquee.setFirstButtonText((CharSequence) args.getString(FIRST_BUTTON_TEXT));
        }
        if (args.containsKey(SECOND_BUTTON_TEXT)) {
            this.heroMarquee.setSecondButtonText((CharSequence) args.getString(SECOND_BUTTON_TEXT));
        }
        if (args.containsKey(ICON_RES)) {
            this.heroMarquee.setIcon(args.getInt(ICON_RES));
        }
        this.heroMarquee.setFirstButtonClickListener(HeroMarqueeFragment$$Lambda$1.lambdaFactory$(this, args));
        this.heroMarquee.setSecondButtonClickListener(HeroMarqueeFragment$$Lambda$2.lambdaFactory$(this, args));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(HeroMarqueeFragment heroMarqueeFragment, Bundle args, View v) {
        ((AirActivity) heroMarqueeFragment.getActivity()).dispatchActivityResult(args.getInt(REQUEST_CODE), -1, null);
        heroMarqueeFragment.dismiss();
    }

    static /* synthetic */ void lambda$onCreateView$1(HeroMarqueeFragment heroMarqueeFragment, Bundle args, View v) {
        ((AirActivity) heroMarqueeFragment.getActivity()).dispatchActivityResult(args.getInt(REQUEST_CODE), 0, null);
        heroMarqueeFragment.dismiss();
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    public static HeroMarqueeFragmentBuilder builder() {
        return new HeroMarqueeFragmentBuilder();
    }
}
