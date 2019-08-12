package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.interfaces.EditProfileInterface.ProfileSection;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.UserProfileUtils;
import com.airbnb.android.lib.views.GroupedCell;
import java.util.EnumSet;

public class EditProfileDetailsAdapter extends BaseAdapter {
    AirbnbAccountManager mAccountManager;
    private final EnumSet<ProfileSection> mSections;

    public EditProfileDetailsAdapter(Context context, boolean privateSection, ProfileSection... notEditableSections) {
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        this.mSections = privateSection ? ProfileSection.PRIVATE_DETAILS : ProfileSection.OPTIONAL_DETAILS;
        for (ProfileSection section : notEditableSections) {
            this.mSections.remove(section);
        }
    }

    public int getCount() {
        return this.mSections.size();
    }

    public Object getItem(int position) {
        return ((ProfileSection[]) this.mSections.toArray(new ProfileSection[this.mSections.size()]))[position];
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        boolean z = false;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.edit_profile_grouped_cell, parent, false);
        }
        GroupedCell groupedCell = (GroupedCell) convertView;
        if (position != 0) {
            z = true;
        }
        groupedCell.showTopBorder(z);
        ProfileSection section = (ProfileSection) getItem(position);
        groupedCell.setTitle(section.getTitleId());
        setGroupedCellWithContent(groupedCell, UserProfileUtils.getValueForDisplay(this.mAccountManager.getCurrentUser(), section));
        return groupedCell;
    }

    private void setGroupedCellWithContent(GroupedCell groupedCell, String content) {
        if (!TextUtils.isEmpty(content)) {
            groupedCell.showRightCaret(false);
            groupedCell.setContent((CharSequence) content);
            return;
        }
        groupedCell.showRightCaret(true);
    }
}
