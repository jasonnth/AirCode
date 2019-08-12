package com.airbnb.android.lib.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class ImageFragment extends Fragment {
    private static final String ARGS_DEFAULT_IMAGE_RES = "bg_color";
    private static final String ARGS_FRAGMENT_TO_USE = "custom_fragment_res";
    private static final String ARGS_IMAGE_CAPTION = "image_caption";
    private static final String ARGS_IMAGE_FILL = "fill";
    private static final String ARGS_IMAGE_URL = "image_url";

    public static ImageFragment newInstance(String imageUrl, String imageCaption, int fragmentLayoutRes, int defaultImageRes) {
        return (ImageFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ImageFragment()).putString(ARGS_IMAGE_URL, imageUrl)).putString(ARGS_IMAGE_CAPTION, imageCaption)).putInt(ARGS_FRAGMENT_TO_USE, fragmentLayoutRes)).putInt(ARGS_DEFAULT_IMAGE_RES, defaultImageRes)).build();
    }

    public static ImageFragment newInstance(String imageUrl, String imageCaption, boolean fill) {
        return (ImageFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ImageFragment()).putString(ARGS_IMAGE_URL, imageUrl)).putString(ARGS_IMAGE_CAPTION, imageCaption)).putBoolean(ARGS_IMAGE_FILL, fill)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getArguments().getInt(ARGS_FRAGMENT_TO_USE, C0880R.layout.fragment_image), container, false);
        Bundle args = getArguments();
        String imageUrl = args.getString(ARGS_IMAGE_URL);
        boolean fill = args.getBoolean(ARGS_IMAGE_FILL, false);
        AirImageView image = (AirImageView) view.findViewById(C0880R.C0882id.img_listing);
        image.setPlaceholderResId(args.getInt(ARGS_DEFAULT_IMAGE_RES, C0880R.color.c_gray_6));
        if (AndroidVersion.isJellyBean()) {
            Point screenSize = ViewUtils.getScreenSize(getActivity());
            image.getLayoutParams().width = screenSize.x;
            image.getLayoutParams().height = screenSize.y;
        }
        image.setScaleType(fill ? ScaleType.CENTER_CROP : ScaleType.FIT_CENTER);
        image.setImageUrl(imageUrl);
        TextView photoCaptionView = (TextView) view.findViewById(C0880R.C0882id.txt_photo_caption);
        String caption = args.getString(ARGS_IMAGE_CAPTION);
        if (photoCaptionView != null) {
            if (!TextUtils.isEmpty(caption)) {
                photoCaptionView.setText(caption);
            } else {
                photoCaptionView.setVisibility(8);
            }
        }
        view.setOnLongClickListener(ImageFragment$$Lambda$1.lambdaFactory$(this, imageUrl));
        return view;
    }
}
