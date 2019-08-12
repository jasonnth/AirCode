package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;
import com.airbnb.android.core.FragmentLauncher;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.BundleBuilder;

public class ViewPagerFtueFragment extends Fragment implements FragmentLauncher {
    private static final String ARG_FIRST_PAGE = "first_page";
    private static final String ARG_STICKY_BUTTON_COLOR = "sticky_button_color";
    private static final String ARG_SUBTITLE = "subtitle";
    private static final String ARG_TITLE = "title";
    private static final int SLIDE_IN_DELAY = 200;
    private static final int SLIDE_IN_DURATION = 500;
    private static final String TAG = ViewPagerFtueFragment.class.getSimpleName();
    private boolean mFirstPage;

    public static ViewPagerFtueFragment findFragment(FragmentManager fm, int titleRes, int subtitleRes, boolean isFirstPage, int stickyButtonColorRes) {
        String title = null;
        if (titleRes > 0) {
            title = AirbnbApplication.appContext().getString(titleRes);
        }
        String subtitle = null;
        if (subtitleRes > 0) {
            subtitle = AirbnbApplication.appContext().getString(subtitleRes);
        }
        return findFragment(fm, title, subtitle, isFirstPage, stickyButtonColorRes);
    }

    public static ViewPagerFtueFragment findFragment(FragmentManager fm, String title, String subtitle, boolean isFirstPage, int stickyButtonColorRes) {
        ViewPagerFtueFragment fragment = (ViewPagerFtueFragment) fm.findFragmentByTag(TAG);
        if (fragment != null) {
            return fragment;
        }
        ViewPagerFtueFragment fragment2 = new ViewPagerFtueFragment();
        fragment2.setArguments(makeArgs(title, subtitle, isFirstPage, stickyButtonColorRes));
        return fragment2;
    }

    protected static Bundle makeArgs(String title, String subtitle, boolean isFirstPage, int stickyButtonColorRes) {
        Bundle args = new Bundle();
        args.putBoolean(ARG_FIRST_PAGE, isFirstPage);
        if (title != null) {
            args.putString("title", title);
        }
        if (subtitle != null) {
            args.putString(ARG_SUBTITLE, subtitle);
        }
        if (stickyButtonColorRes > 0) {
            args.putInt(ARG_STICKY_BUTTON_COLOR, stickyButtonColorRes);
        }
        return args;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ScrollView view = (ScrollView) inflater.inflate(C0880R.layout.fragment_view_pager_ftue, null);
        Bundle args = getArguments();
        if (savedInstanceState == null) {
            this.mFirstPage = args.getBoolean(ARG_FIRST_PAGE, false);
        }
        String title = args.getString("title");
        String subtitle = args.getString(ARG_SUBTITLE);
        setTextViewContent((TextView) view.findViewById(C0880R.C0882id.txt_title), title, false);
        setTextViewContent((TextView) view.findViewById(C0880R.C0882id.txt_subtitle), subtitle, false);
        slideInContentIfNeeded(this.mFirstPage, view);
        return view;
    }

    public int getStickyButtonColor() {
        return getArguments().getInt(ARG_STICKY_BUTTON_COLOR, 0);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ARG_FIRST_PAGE, this.mFirstPage);
    }

    private void slideInContentIfNeeded(boolean shouldShowAnim, ScrollView view) {
        if (shouldShowAnim) {
            this.mFirstPage = false;
            Animation fragAnim = AnimationUtils.loadAnimation(getActivity(), C0880R.anim.activity_transition_slide_in_new);
            fragAnim.setDuration(500);
            fragAnim.setStartOffset(200);
            view.setAnimation(fragAnim);
        }
    }

    private void setTextViewContent(TextView textView, String text, boolean goneIfNotSet) {
        if (text != null) {
            textView.setText(text);
        } else {
            textView.setVisibility(goneIfNotSet ? 8 : 4);
        }
    }

    public Bundle getDummyArguments() {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putBoolean(ARG_FIRST_PAGE, true)).putString("title", "Title")).putString(ARG_SUBTITLE, "Subtitle")).putInt(ARG_STICKY_BUTTON_COLOR, C0880R.color.c_rausch)).toBundle();
    }
}
