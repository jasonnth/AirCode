package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class CompleteProfilePhotoConfirmChildFragment_ViewBinding implements Unbinder {
    private CompleteProfilePhotoConfirmChildFragment target;

    public CompleteProfilePhotoConfirmChildFragment_ViewBinding(CompleteProfilePhotoConfirmChildFragment target2, View source) {
        this.target = target2;
        target2.photoPreview = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.photo_preview, "field 'photoPreview'", HaloImageView.class);
    }

    public void unbind() {
        CompleteProfilePhotoConfirmChildFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.photoPreview = null;
    }
}
