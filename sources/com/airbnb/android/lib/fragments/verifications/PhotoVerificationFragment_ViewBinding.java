package com.airbnb.android.lib.fragments.verifications;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class PhotoVerificationFragment_ViewBinding implements Unbinder {
    private PhotoVerificationFragment target;
    private View view2131756893;
    private View view2131756894;
    private View view2131756897;
    private View view2131756898;
    private View view2131756901;
    private View view2131756902;
    private View view2131757735;

    public PhotoVerificationFragment_ViewBinding(final PhotoVerificationFragment target2, View source) {
        this.target = target2;
        target2.headerTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_header, "field 'headerTextView'", TextView.class);
        target2.subtextTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text_subtext, "field 'subtextTextView'", TextView.class);
        target2.addPhotoContainer = Utils.findRequiredView(source, C0880R.C0882id.container_add_photo, "field 'addPhotoContainer'");
        target2.photoAddedSuccessContainer = Utils.findRequiredView(source, C0880R.C0882id.container_photo_added_success, "field 'photoAddedSuccessContainer'");
        target2.photoErrorContainer = Utils.findRequiredView(source, C0880R.C0882id.container_photo_error, "field 'photoErrorContainer'");
        target2.userHostHalosContainer = Utils.findRequiredView(source, C0880R.C0882id.container_user_host_halos, "field 'userHostHalosContainer'");
        target2.singleEmptyHaloImageView = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.halo_single_empty, "field 'singleEmptyHaloImageView'", ImageView.class);
        target2.userHaloImageView = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.halo_user_photo, "field 'userHaloImageView'", HaloImageView.class);
        target2.userHaloErrorPageImageView = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.halo_user_photo_error, "field 'userHaloErrorPageImageView'", HaloImageView.class);
        target2.hostHaloImageView = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.halo_host, "field 'hostHaloImageView'", HaloImageView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_link_change_photo, "field 'changePhotoButtonLink' and method 'changePhotoButtonLink'");
        target2.changePhotoButtonLink = (TextView) Utils.castView(view, C0880R.C0882id.btn_link_change_photo, "field 'changePhotoButtonLink'", TextView.class);
        this.view2131756897 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.changePhotoButtonLink();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_verifications_skip, "field 'skipButton' and method 'skip'");
        target2.skipButton = view2;
        this.view2131757735 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.skip();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.btn_choose_from_library, "method 'clickChooseFromLibrary'");
        this.view2131756893 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickChooseFromLibrary();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.btn_take_photo, "method 'clickTakePhoto'");
        this.view2131756894 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickTakePhoto();
            }
        });
        View view5 = Utils.findRequiredView(source, C0880R.C0882id.btn_complete_photo_verification, "method 'clickNext'");
        this.view2131756898 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickNext();
            }
        });
        View view6 = Utils.findRequiredView(source, C0880R.C0882id.btn_change_photo_error, "method 'changePhoto'");
        this.view2131756901 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.changePhoto();
            }
        });
        View view7 = Utils.findRequiredView(source, C0880R.C0882id.btn_thats_me, "method 'confirmPhoto'");
        this.view2131756902 = view7;
        view7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.confirmPhoto();
            }
        });
    }

    public void unbind() {
        PhotoVerificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.headerTextView = null;
        target2.subtextTextView = null;
        target2.addPhotoContainer = null;
        target2.photoAddedSuccessContainer = null;
        target2.photoErrorContainer = null;
        target2.userHostHalosContainer = null;
        target2.singleEmptyHaloImageView = null;
        target2.userHaloImageView = null;
        target2.userHaloErrorPageImageView = null;
        target2.hostHaloImageView = null;
        target2.changePhotoButtonLink = null;
        target2.skipButton = null;
        this.view2131756897.setOnClickListener(null);
        this.view2131756897 = null;
        this.view2131757735.setOnClickListener(null);
        this.view2131757735 = null;
        this.view2131756893.setOnClickListener(null);
        this.view2131756893 = null;
        this.view2131756894.setOnClickListener(null);
        this.view2131756894 = null;
        this.view2131756898.setOnClickListener(null);
        this.view2131756898 = null;
        this.view2131756901.setOnClickListener(null);
        this.view2131756901 = null;
        this.view2131756902.setOnClickListener(null);
        this.view2131756902 = null;
    }
}
