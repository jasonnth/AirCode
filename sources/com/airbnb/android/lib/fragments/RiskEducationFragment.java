package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.HeroMarquee;

public class RiskEducationFragment extends AirFragment {
    private static final String ARG_CONTENT = "content";
    private static final String ARG_TITLE = "title";
    @BindView
    HeroMarquee heroMarquee;

    public static Intent newIntent(Context context, String title, String content) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", content);
        return AutoAirActivity.intentForFragment(context, RiskEducationFragment.class, args).putExtra(AutoAirActivity.EXTRA_ACTION_BAR, false);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View v = inflater.inflate(C0880R.layout.fragment_risk_education, container, false);
        bindViews(v);
        setupViews();
        return v;
    }

    private void setupViews() {
        this.heroMarquee.setTitle((CharSequence) getArguments().getString("title"));
        this.heroMarquee.setCaption((CharSequence) getArguments().getString("content"));
        this.heroMarquee.setSecondButtonClickListener(RiskEducationFragment$$Lambda$1.lambdaFactory$(this));
    }
}
