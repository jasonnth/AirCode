package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.n2.components.ParticipantRow_ViewBinding */
public class ParticipantRow_ViewBinding implements Unbinder {
    private ParticipantRow target;
    private View view2131493174;
    private View view2131493515;

    public ParticipantRow_ViewBinding(final ParticipantRow target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, R.id.user_image, "field 'userImage' and method 'onImageClicked'");
        target2.userImage = (HaloImageView) Utils.castView(view, R.id.user_image, "field 'userImage'", HaloImageView.class);
        this.view2131493174 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onImageClicked();
            }
        });
        target2.nameText = (TextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'nameText'", TextView.class);
        View view2 = Utils.findRequiredView(source, R.id.remove_button, "field 'removeButton' and method 'onRemoveClicked'");
        target2.removeButton = (ImageView) Utils.castView(view2, R.id.remove_button, "field 'removeButton'", ImageView.class);
        this.view2131493515 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onRemoveClicked();
            }
        });
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        ParticipantRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.userImage = null;
        target2.nameText = null;
        target2.removeButton = null;
        target2.divider = null;
        this.view2131493174.setOnClickListener(null);
        this.view2131493174 = null;
        this.view2131493515.setOnClickListener(null);
        this.view2131493515 = null;
    }
}
