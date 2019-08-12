package com.airbnb.android.lib.fragments;

import android.view.View;
import com.airbnb.android.lib.views.LinearListView;
import com.airbnb.android.lib.views.LinearListView.OnItemClickListener;

final /* synthetic */ class EditProfileFragment$$Lambda$10 implements OnItemClickListener {
    private final EditProfileFragment arg$1;

    private EditProfileFragment$$Lambda$10(EditProfileFragment editProfileFragment) {
        this.arg$1 = editProfileFragment;
    }

    public static OnItemClickListener lambdaFactory$(EditProfileFragment editProfileFragment) {
        return new EditProfileFragment$$Lambda$10(editProfileFragment);
    }

    public void onItemClick(LinearListView linearListView, View view, int i, long j) {
        EditProfileFragment.lambda$onCreateView$9(this.arg$1, linearListView, view, i, j);
    }
}
