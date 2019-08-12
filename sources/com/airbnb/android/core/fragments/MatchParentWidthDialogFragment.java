package com.airbnb.android.core.fragments;

import android.app.Dialog;
import android.graphics.Point;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.utils.ViewUtils;

@Deprecated
public class MatchParentWidthDialogFragment extends AirDialogFragment {
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Point p = ViewUtils.getScreenSize(getContext());
            if (MiscUtils.isTabletScreen(getContext())) {
                dialog.getWindow().setLayout((int) (((double) p.x) * 0.8d), (int) (((double) p.y) * 0.8d));
            } else {
                dialog.getWindow().setLayout(-1, (int) (((double) p.y) * 0.95d));
            }
        }
    }
}
