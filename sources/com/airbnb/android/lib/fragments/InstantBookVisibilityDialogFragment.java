package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.enums.InstantBookVisibility;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import java.util.List;

public class InstantBookVisibilityDialogFragment extends ZenDialog {
    public static final String LIST_SELECT_REQUEST_CODE = "list_sel_req_code";
    public static final String PERSISTED_VISIBILITY = "presisted_visibility";
    public static final String SELECTED_VISIBILITY = "sel_visibility";
    private InstantBookVisibilityArrayAdapter visibilityAdapter;

    public static final class InstantBookVisibilityArrayAdapter extends ArrayAdapter<InstantBookVisibility> {
        private final int layoutId;
        private final InstantBookVisibility selectedVisibility;

        public InstantBookVisibilityArrayAdapter(Context context, int resource, List<InstantBookVisibility> visibilities, InstantBookVisibility selectedVisibility2) {
            super(context, resource, visibilities);
            this.selectedVisibility = selectedVisibility2;
            this.layoutId = resource;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(this.layoutId, parent, false);
            }
            InstantBookVisibility visibility = (InstantBookVisibility) getItem(position);
            TextView visibilityLabel = (TextView) ButterKnife.findById(convertView, C0880R.C0882id.zen_text);
            visibilityLabel.setText(visibility.titleId);
            boolean isSelected = this.selectedVisibility.equals(visibility);
            visibilityLabel.setTextColor(getContext().getResources().getColor(isSelected ? C0880R.color.c_rausch : C0880R.color.c_gray_1));
            ViewUtils.setVisibleIf((View) (ImageView) ButterKnife.findById(convertView, C0880R.C0882id.zen_checkmark), isSelected);
            return convertView;
        }
    }

    public static InstantBookVisibilityDialogFragment newInstance(Fragment targetFragment, InstantBookVisibility preselectedVisibility, InstantBookVisibility persistedVisibility, int listSelectRequestCode) {
        Bundle bundle = new Bundle();
        bundle.putString(SELECTED_VISIBILITY, preselectedVisibility.serverDescKey);
        bundle.putString(PERSISTED_VISIBILITY, persistedVisibility.serverDescKey);
        bundle.putInt("list_sel_req_code", listSelectRequestCode);
        return (InstantBookVisibilityDialogFragment) new ZenBuilder(new InstantBookVisibilityDialogFragment()).withTitle(C0880R.string.ml_ib_who_can_book_instantly_title).withTargetFragment(targetFragment).withListView(bundle).create();
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        this.visibilityAdapter = new InstantBookVisibilityArrayAdapter(getActivity(), C0880R.layout.list_item_zen_checkable, InstantBookVisibility.userFacingValues(InstantBookVisibility.getTypeFromKey(getArguments().getString(PERSISTED_VISIBILITY))), InstantBookVisibility.getTypeFromKey(getArguments().getString(SELECTED_VISIBILITY)));
        return this.visibilityAdapter;
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return InstantBookVisibilityDialogFragment$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$getItemClickListener$0(InstantBookVisibilityDialogFragment instantBookVisibilityDialogFragment, AdapterView parent, View view, int position, long id) {
        instantBookVisibilityDialogFragment.sendActivityResult(instantBookVisibilityDialogFragment.getArguments().getInt("list_sel_req_code"), -1, new Intent().putExtra(SELECTED_VISIBILITY, ((InstantBookVisibility) instantBookVisibilityDialogFragment.visibilityAdapter.getItem(position)).serverDescKey));
        instantBookVisibilityDialogFragment.dismiss();
    }
}
