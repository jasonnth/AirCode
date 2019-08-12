package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.n2.components.CollaboratorsRow_ViewBinding */
public class CollaboratorsRow_ViewBinding implements Unbinder {
    private CollaboratorsRow target;
    private View view2131493166;
    private View view2131493167;
    private View view2131493168;
    private View view2131493169;

    public CollaboratorsRow_ViewBinding(final CollaboratorsRow target2, View source) {
        this.target = target2;
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
        View view = Utils.findRequiredView(source, R.id.faces_container, "field 'facesContainer' and method 'onFriendsClicked'");
        target2.facesContainer = (FrameLayout) Utils.castView(view, R.id.faces_container, "field 'facesContainer'", FrameLayout.class);
        this.view2131493166 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onFriendsClicked(p0);
            }
        });
        View view2 = Utils.findRequiredView(source, R.id.overflow_count_text, "field 'overflowText' and method 'onFriendsClicked'");
        target2.overflowText = (TextView) Utils.castView(view2, R.id.overflow_count_text, "field 'overflowText'", TextView.class);
        this.view2131493167 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onFriendsClicked(p0);
            }
        });
        View view3 = Utils.findRequiredView(source, R.id.invite_button_text, "field 'inviteButtonText' and method 'onInviteClicked'");
        target2.inviteButtonText = (AirButton) Utils.castView(view3, R.id.invite_button_text, "field 'inviteButtonText'", AirButton.class);
        this.view2131493169 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onInviteClicked(p0);
            }
        });
        View view4 = Utils.findRequiredView(source, R.id.invite_button_icon, "field 'inviteButtonIcon' and method 'onInviteClicked'");
        target2.inviteButtonIcon = (ImageView) Utils.castView(view4, R.id.invite_button_icon, "field 'inviteButtonIcon'", ImageView.class);
        this.view2131493168 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onInviteClicked(p0);
            }
        });
        target2.imageDiameter = source.getContext().getResources().getDimensionPixelSize(R.dimen.n2_collaborator_row_circle_diameter);
    }

    public void unbind() {
        CollaboratorsRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.divider = null;
        target2.facesContainer = null;
        target2.overflowText = null;
        target2.inviteButtonText = null;
        target2.inviteButtonIcon = null;
        this.view2131493166.setOnClickListener(null);
        this.view2131493166 = null;
        this.view2131493167.setOnClickListener(null);
        this.view2131493167 = null;
        this.view2131493169.setOnClickListener(null);
        this.view2131493169 = null;
        this.view2131493168.setOnClickListener(null);
        this.view2131493168 = null;
    }
}
