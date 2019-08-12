package com.airbnb.android.lib.fragments;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.interfaces.EditProfileInterface.Gender;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.EditProfileGenderAdapter;

public class GenderSelectionFragment extends AirDialogFragment {
    private static final String ARG_OLD_GENDER = "old_gender";
    public static final String EXTRA_NEW_GENDER = "new_gender";
    public static final int REQUEST_CODE_GENDER = 701;

    public static GenderSelectionFragment newInstance(Gender gender) {
        GenderSelectionFragment f = new GenderSelectionFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_OLD_GENDER, gender);
        f.setArguments(args);
        return f;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditProfileGenderAdapter adapter = new EditProfileGenderAdapter(getActivity(), 0, 0, (Gender) getArguments().getParcelable(ARG_OLD_GENDER));
        Builder builder = new Builder(getActivity());
        builder.setTitle(C0880R.string.edit_profile_gender);
        builder.setAdapter(adapter, GenderSelectionFragment$$Lambda$1.lambdaFactory$(this));
        return builder.create();
    }

    static /* synthetic */ void lambda$onCreateDialog$0(GenderSelectionFragment genderSelectionFragment, DialogInterface dialog, int item) {
        Gender g = Gender.values()[item];
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NEW_GENDER, g);
        genderSelectionFragment.getTargetFragment().onActivityResult(REQUEST_CODE_GENDER, -1, intent);
    }
}
