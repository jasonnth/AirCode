package com.airbnb.android.core.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.DocumentMarquee;

public class LottieNuxFragment extends AirFragment {
    private static final String ARG_DESCRIPTION_RES = "description_res";
    private static final String ARG_TITLE_RES = "title_res";
    @BindView
    DocumentMarquee documentMarquee;

    public static LottieNuxFragment newInstance(int titleRes, int descriptionRes) {
        return (LottieNuxFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new LottieNuxFragment()).putInt(ARG_TITLE_RES, titleRes)).putInt(ARG_DESCRIPTION_RES, descriptionRes)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_lottie_nux, container, false);
        bindViews(view);
        this.documentMarquee.setTitle(getArguments().getInt(ARG_TITLE_RES));
        this.documentMarquee.setCaption(getArguments().getInt(ARG_DESCRIPTION_RES));
        this.documentMarquee.setTitleMaxLines(2);
        return view;
    }
}
