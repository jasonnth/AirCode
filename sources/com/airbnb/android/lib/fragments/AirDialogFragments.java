package com.airbnb.android.lib.fragments;

import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;

@Deprecated
public class AirDialogFragments {
    public static final String DIALOG_FRAGMENT_TAG = "air_dialog";

    public static void showDialog(FragmentManager fragmentManager, DialogFragment dialogFragment, Fragment optionalTargetFragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment.setTargetFragment(optionalTargetFragment, 0);
        dialogFragment.show(ft, DIALOG_FRAGMENT_TAG);
    }
}
