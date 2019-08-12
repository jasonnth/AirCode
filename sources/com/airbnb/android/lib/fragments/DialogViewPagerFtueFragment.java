package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.ColorizedDrawable;

public class DialogViewPagerFtueFragment extends Fragment {
    private static final String ARG_ICON_RES = "icon";
    private static final String ARG_SUBTITLE = "subtitle";
    private static final String ARG_TITLE = "title";
    public static final int INVALID_ID = -1;
    private static final String TAG = DialogViewPagerFtueFragment.class.getSimpleName();

    public static DialogViewPagerFtueFragment findFragment(FragmentManager fm, int iconRes, String title, String subtitle) {
        DialogViewPagerFtueFragment fragment = (DialogViewPagerFtueFragment) fm.findFragmentByTag(TAG);
        if (fragment != null) {
            return fragment;
        }
        DialogViewPagerFtueFragment fragment2 = new DialogViewPagerFtueFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ICON_RES, iconRes);
        args.putString("title", title);
        args.putString(ARG_SUBTITLE, subtitle);
        fragment2.setArguments(args);
        return fragment2;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_dialog_view_pager_ftue, null);
        Bundle args = getArguments();
        int iconResId = args.getInt(ARG_ICON_RES, -1);
        String title = args.getString("title", "");
        String subtitle = args.getString(ARG_SUBTITLE, "");
        setupIcon(view, iconResId);
        ((TextView) view.findViewById(C0880R.C0882id.txt_title)).setText(title);
        ((TextView) view.findViewById(C0880R.C0882id.txt_subtitle)).setText(subtitle);
        return view;
    }

    private void setupIcon(View view, int iconResId) {
        ImageView image = (ImageView) view.findViewById(C0880R.C0882id.img_ftue_icon);
        if (iconResId > 0) {
            image.setImageDrawable(ColorizedDrawable.forIdWithColor(getActivity(), iconResId, C0880R.color.c_rausch));
        } else {
            image.setVisibility(8);
        }
    }
}
