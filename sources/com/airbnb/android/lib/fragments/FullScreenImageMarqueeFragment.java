package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.FullScreenImageMarquee;

public class FullScreenImageMarqueeFragment extends AirFragment {
    private static final String ARG_DESCRIPTION_RES = "description";
    private static final String ARG_IMAGE_RES = "image";
    private static final String ARG_IMAGE_TEXT_RES = "image_text";
    private static final String ARG_TITLE_RES = "title";
    @BindView
    FullScreenImageMarquee fullScreenImageMarquee;

    public static FullScreenImageMarqueeFragment newInstance(int titleRes, int descriptionRes, int imageRes, int imageTitleRes) {
        return (FullScreenImageMarqueeFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new FullScreenImageMarqueeFragment()).putInt("title", titleRes)).putInt("description", descriptionRes)).putInt("image", imageRes)).putInt(ARG_IMAGE_TEXT_RES, imageTitleRes)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.full_screen_image_marquee_fragment, container, false);
        bindViews(view);
        int titleRes = getArguments().getInt("title");
        int descriptionRes = getArguments().getInt("description");
        int imageRes = getArguments().getInt("image");
        int imageTextRes = getArguments().getInt(ARG_IMAGE_TEXT_RES);
        this.fullScreenImageMarquee.setTitle(titleRes);
        this.fullScreenImageMarquee.setDescription(descriptionRes);
        this.fullScreenImageMarquee.setImage(imageRes);
        this.fullScreenImageMarquee.setImageText(imageTextRes);
        return view;
    }
}
