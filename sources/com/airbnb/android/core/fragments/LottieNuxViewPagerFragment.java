package com.airbnb.android.core.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.enums.LottieNuxViewPagerArguments;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.appboy.support.StringUtils;
import java.util.AbstractMap.SimpleEntry;

public class LottieNuxViewPagerFragment extends AirFragment {
    public static final String EXTRA_NUX_ARGUMENTS = "extra_nux_arguments";
    private final String PATH_IMAGES = "images/";
    /* access modifiers changed from: private */
    public LottieViewPagerAdapter adapter;
    @BindView
    LottieAnimationView animationView;
    /* access modifiers changed from: private */
    public LottieNuxViewPagerArguments arguments;
    @BindView
    TabLayout dotsCounter;
    private final SimpleOnPageChangeListener listener = new SimpleOnPageChangeListener() {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            LottieNuxViewPagerFragment.this.setAnimationProgress(position, positionOffset);
        }

        public void onPageSelected(int position) {
            LottieNuxViewPagerFragment.this.showDotsCounter(position < LottieNuxViewPagerFragment.this.adapter.getCount() + -1);
        }
    };
    @BindView
    AirButton nextButton;
    SharedPrefsHelper sharedPrefsHelper;
    @BindView
    AirToolbar toolbar;
    @BindView
    ViewPager viewPager;

    public class LottieViewPagerAdapter extends FragmentStatePagerAdapter {
        public LottieViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            SimpleEntry<Integer, Integer> pageContent = (SimpleEntry) LottieNuxViewPagerFragment.this.arguments.pagesContent().get(position);
            return LottieNuxFragment.newInstance(((Integer) pageContent.getKey()).intValue(), ((Integer) pageContent.getValue()).intValue());
        }

        public int getCount() {
            return LottieNuxViewPagerFragment.this.arguments.pagesContent().size();
        }
    }

    public static Intent intentForNuxArguments(Context context, LottieNuxViewPagerArguments nuxArguments) {
        return ((Builder) AutoFragmentActivity.create(context, LottieNuxViewPagerFragment.class).putParcelable("extra_nux_arguments", nuxArguments)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar(this.toolbar);
        this.arguments = (LottieNuxViewPagerArguments) getArguments().getParcelable("extra_nux_arguments");
        this.adapter = new LottieViewPagerAdapter(getChildFragmentManager());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_lottie_nux_view_pager, container, false);
        bindViews(view);
        ((CoreGraph) CoreApplication.instance().component()).inject(this);
        this.animationView.setAnimation(this.arguments.animationFilename());
        this.animationView.setImageAssetsFolder("images/");
        if (hasNextButton()) {
            this.nextButton.setVisibility(0);
            this.nextButton.setText((CharSequence) this.arguments.buttonText().get());
            this.nextButton.setOnClickListener(LottieNuxViewPagerFragment$$Lambda$1.lambdaFactory$(this));
        } else {
            this.nextButton.setVisibility(8);
        }
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.addOnPageChangeListener(this.listener);
        this.dotsCounter.setupWithViewPager(this.viewPager);
        this.sharedPrefsHelper.setHasSeenNuxFlowForFeature(AirbnbConstants.PREFS_CHECK_IN_GUIDE_NUX, true);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(LottieNuxViewPagerFragment lottieNuxViewPagerFragment, View v) {
        if (!StringUtils.isNullOrEmpty((String) lottieNuxViewPagerFragment.arguments.buttonDeepLink().get())) {
            DeepLinkUtils.startActivityForDeepLink(lottieNuxViewPagerFragment.getContext(), (String) lottieNuxViewPagerFragment.arguments.buttonDeepLink().get());
        }
        lottieNuxViewPagerFragment.getActivity().finish();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.toolbar.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0716R.C0718id.skip) {
            return false;
        }
        getActivity().finish();
        return true;
    }

    public void showDotsCounter(boolean showDots) {
        int i;
        int i2 = 0;
        TabLayout tabLayout = this.dotsCounter;
        if (showDots) {
            i = 0;
        } else {
            i = 8;
        }
        tabLayout.setVisibility(i);
        AirButton airButton = this.nextButton;
        if (showDots || !hasNextButton()) {
            i2 = 8;
        }
        airButton.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public void setAnimationProgress(int position, float positionOffset) {
        this.animationView.setProgress(interpolate(((Float) this.arguments.animationTimes().get(position)).floatValue(), ((Float) this.arguments.animationTimes().get(position + 1)).floatValue(), positionOffset));
    }

    private float interpolate(float startValue, float endValue, float f) {
        return ((endValue - startValue) * f) + startValue;
    }

    private boolean hasNextButton() {
        return this.arguments.buttonText().isPresent() && this.arguments.buttonDeepLink().isPresent();
    }
}
