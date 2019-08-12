package com.airbnb.android.lib.identity.psb;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class KonaSelectGuestProfileFragment_ViewBinding implements Unbinder {
    private KonaSelectGuestProfileFragment target;
    private View view2131756164;
    private View view2131756576;
    private View view2131756728;
    private View view2131756730;

    public KonaSelectGuestProfileFragment_ViewBinding(final KonaSelectGuestProfileFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.save_button, "field 'primaryButton' and method 'onSaveClick'");
        target2.primaryButton = (AirButton) Utils.castView(view, C0880R.C0882id.save_button, "field 'primaryButton'", AirButton.class);
        this.view2131756164 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSaveClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.add_button, "field 'addButton' and method 'onAddClick'");
        target2.addButton = (AirButton) Utils.castView(view2, C0880R.C0882id.add_button, "field 'addButton'", AirButton.class);
        this.view2131756576 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onAddClick();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.add_profile_button, "field 'addProfileButton' and method 'onCreateProfileClick'");
        target2.addProfileButton = (AirButton) Utils.castView(view3, C0880R.C0882id.add_profile_button, "field 'addProfileButton'", AirButton.class);
        this.view2131756728 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCreateProfileClick();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.add_profile_button_white, "field 'addProfileButtonWhite' and method 'onCreateProfileWhiteClick'");
        target2.addProfileButtonWhite = (AirButton) Utils.castView(view4, C0880R.C0882id.add_profile_button_white, "field 'addProfileButtonWhite'", AirButton.class);
        this.view2131756730 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCreateProfileWhiteClick();
            }
        });
        target2.selectionView = (GuestProfileSelectionView) Utils.findRequiredViewAsType(source, C0880R.C0882id.selection_view, "field 'selectionView'", GuestProfileSelectionView.class);
        target2.loaderFrame = Utils.findRequiredView(source, C0880R.C0882id.loader_frame, "field 'loaderFrame'");
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0880R.C0882id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        KonaSelectGuestProfileFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.primaryButton = null;
        target2.addButton = null;
        target2.addProfileButton = null;
        target2.addProfileButtonWhite = null;
        target2.selectionView = null;
        target2.loaderFrame = null;
        target2.jellyfishView = null;
        target2.toolbar = null;
        this.view2131756164.setOnClickListener(null);
        this.view2131756164 = null;
        this.view2131756576.setOnClickListener(null);
        this.view2131756576 = null;
        this.view2131756728.setOnClickListener(null);
        this.view2131756728 = null;
        this.view2131756730.setOnClickListener(null);
        this.view2131756730 = null;
    }
}
