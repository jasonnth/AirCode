package com.airbnb.p027n2.components;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.StandardRow_ViewBinding */
public class StandardRow_ViewBinding implements Unbinder {
    private StandardRow target;

    public StandardRow_ViewBinding(StandardRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.text = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", AirTextView.class);
        target2.rowDrawable = (AirImageView) Utils.findRequiredViewAsType(source, R.id.row_drawable, "field 'rowDrawable'", AirImageView.class);
        target2.rowBadge = (AirImageView) Utils.findRequiredViewAsType(source, R.id.row_badge, "field 'rowBadge'", AirImageView.class);
        target2.textContainer = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.text_container, "field 'textContainer'", ViewGroup.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.optional_subtitle, "field 'subtitleText'", AirTextView.class);
        target2.subtitleSpace = (Space) Utils.findRequiredViewAsType(source, R.id.optional_subtitle_space, "field 'subtitleSpace'", Space.class);
        target2.extraSubtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.optional_extra_subtitle1, "field 'extraSubtitleText'", AirTextView.class);
        target2.extraSubtitleSpace = (Space) Utils.findRequiredViewAsType(source, R.id.optional_extra_subtitle_space1, "field 'extraSubtitleSpace'", Space.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
        target2.minInputTextWidth = source.getContext().getResources().getDimensionPixelSize(R.dimen.n2_standard_row_min_input_text_width);
    }

    public void unbind() {
        StandardRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.text = null;
        target2.rowDrawable = null;
        target2.rowBadge = null;
        target2.textContainer = null;
        target2.subtitleText = null;
        target2.subtitleSpace = null;
        target2.extraSubtitleText = null;
        target2.extraSubtitleSpace = null;
        target2.divider = null;
    }
}
