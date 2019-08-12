package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class SaveButton_ViewBinding implements Unbinder {
    private SaveButton target;

    public SaveButton_ViewBinding(SaveButton target2) {
        this(target2, target2);
    }

    public SaveButton_ViewBinding(SaveButton target2, View source) {
        this.target = target2;
        target2.saveButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.save_button_internal_btn, "field 'saveButton'", Button.class);
        target2.progressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0880R.C0882id.save_button_internal_progress, "field 'progressBar'", ProgressBar.class);
        target2.successImage = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.save_button_success_image, "field 'successImage'", ImageView.class);
    }

    public void unbind() {
        SaveButton target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.saveButton = null;
        target2.progressBar = null;
        target2.successImage = null;
    }
}
