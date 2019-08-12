package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.CheckInGuideStepCard_ViewBinding */
public class CheckInGuideStepCard_ViewBinding implements Unbinder {
    private CheckInGuideStepCard target;

    public CheckInGuideStepCard_ViewBinding(CheckInGuideStepCard target2, View source) {
        this.target = target2;
        target2.photo = (AirImageView) Utils.findRequiredViewAsType(source, R.id.photo, "field 'photo'", AirImageView.class);
        target2.editImageIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.edit_image_icon, "field 'editImageIcon'", AirImageView.class);
        target2.imageLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, R.id.photo_loader, "field 'imageLoader'", RefreshLoader.class);
        target2.errorStateView = (RelativeLayout) Utils.findRequiredViewAsType(source, R.id.error_state, "field 'errorStateView'", RelativeLayout.class);
        target2.errorStateText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.error_state_text, "field 'errorStateText'", AirTextView.class);
        target2.emptyPhotoContent = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.empty_photo_content, "field 'emptyPhotoContent'", LinearLayout.class);
        target2.stepNumberView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.step_number_view, "field 'stepNumberView'", AirTextView.class);
        target2.stepInstructionsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.step_instructions_view, "field 'stepInstructionsView'", AirTextView.class);
        target2.addPhotoButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.add_photo_button, "field 'addPhotoButton'", AirButton.class);
        target2.noteRow = (AirTextView) Utils.findRequiredViewAsType(source, R.id.note_row, "field 'noteRow'", AirTextView.class);
    }

    public void unbind() {
        CheckInGuideStepCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.photo = null;
        target2.editImageIcon = null;
        target2.imageLoader = null;
        target2.errorStateView = null;
        target2.errorStateText = null;
        target2.emptyPhotoContent = null;
        target2.stepNumberView = null;
        target2.stepInstructionsView = null;
        target2.addPhotoButton = null;
        target2.noteRow = null;
    }
}
