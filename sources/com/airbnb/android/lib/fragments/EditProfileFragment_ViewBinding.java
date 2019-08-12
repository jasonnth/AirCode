package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.LinearListView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class EditProfileFragment_ViewBinding implements Unbinder {
    private EditProfileFragment target;

    public EditProfileFragment_ViewBinding(EditProfileFragment target2, View source) {
        this.target = target2;
        target2.mScrollView = (ScrollView) Utils.findRequiredViewAsType(source, C0880R.C0882id.edit_profile_scroll_view, "field 'mScrollView'", ScrollView.class);
        target2.mProfileImage = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.img_user_profile, "field 'mProfileImage'", AirImageView.class);
        target2.mUserNameTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.user_name, "field 'mUserNameTextView'", AirTextView.class);
        target2.mTxtAboutMe = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.about_me_content, "field 'mTxtAboutMe'", TextView.class);
        target2.mBtnEditName = Utils.findRequiredView(source, C0880R.C0882id.btn_edit_name, "field 'mBtnEditName'");
        target2.mBtnEditAboutMe = Utils.findRequiredView(source, C0880R.C0882id.btn_edit_about_me, "field 'mBtnEditAboutMe'");
        target2.mPrivateSections = (LinearListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.list_private_details, "field 'mPrivateSections'", LinearListView.class);
        target2.mOptionalSections = (LinearListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.list_optional_details, "field 'mOptionalSections'", LinearListView.class);
        target2.businessTravelSection = Utils.findRequiredView(source, C0880R.C0882id.section_business_travel, "field 'businessTravelSection'");
        target2.businessTravelContainer = Utils.findRequiredView(source, C0880R.C0882id.bt_container, "field 'businessTravelContainer'");
        target2.workEmail = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.work_email, "field 'workEmail'", TextView.class);
        target2.workEmailStatus = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.work_email_status, "field 'workEmailStatus'", TextView.class);
        target2.workEmailStatusDetails = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.work_email_status_details, "field 'workEmailStatusDetails'", TextView.class);
    }

    public void unbind() {
        EditProfileFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mScrollView = null;
        target2.mProfileImage = null;
        target2.mUserNameTextView = null;
        target2.mTxtAboutMe = null;
        target2.mBtnEditName = null;
        target2.mBtnEditAboutMe = null;
        target2.mPrivateSections = null;
        target2.mOptionalSections = null;
        target2.businessTravelSection = null;
        target2.businessTravelContainer = null;
        target2.workEmail = null;
        target2.workEmailStatus = null;
        target2.workEmailStatusDetails = null;
    }
}
