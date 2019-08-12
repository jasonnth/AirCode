package com.airbnb.android.photopicker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p002v7.app.AlertDialog.Builder;

public class PhotoPickerDialogFragment extends DialogFragment {
    private static final String ARG_TITLE = "title";

    public static PhotoPickerDialogFragment newInstance() {
        return new PhotoPickerDialogFragment();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Builder(getActivity()).setTitle(C7593R.string.photo_picker_pick_photo_title).setMessage(C7593R.string.photo_picker_pick_photo_message).setPositiveButton(C7593R.string.photo_picker_select_camera, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                ((PhotoPickerDialogInterface) PhotoPickerDialogFragment.this.getActivity()).onSelectCamera();
            }
        }).setNegativeButton(C7593R.string.photo_picker_select_gallery, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                ((PhotoPickerDialogInterface) PhotoPickerDialogFragment.this.getActivity()).onSelectGallery();
            }
        }).create();
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        ((PhotoPickerDialogInterface) getActivity()).onCancel();
    }
}
