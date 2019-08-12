package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ListingInfoRow_ViewBinding */
public class ListingInfoRow_ViewBinding implements Unbinder {
    private ListingInfoRow target;

    public ListingInfoRow_ViewBinding(ListingInfoRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.imageDrawable = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageDrawable'", AirImageView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.optional_subtitle, "field 'subtitleText'", AirTextView.class);
        target2.progressBar = (ProgressBar) Utils.findRequiredViewAsType(source, R.id.progress_bar, "field 'progressBar'", ProgressBar.class);
        target2.label = (AirTextView) Utils.findRequiredViewAsType(source, R.id.label, "field 'label'", AirTextView.class);
        target2.primaryButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.primary_button, "field 'primaryButton'", AirButton.class);
    }

    public void unbind() {
        ListingInfoRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.imageDrawable = null;
        target2.subtitleText = null;
        target2.progressBar = null;
        target2.label = null;
        target2.primaryButton = null;
    }
}
