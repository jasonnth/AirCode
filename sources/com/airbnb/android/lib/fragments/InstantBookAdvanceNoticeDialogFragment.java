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
import com.airbnb.android.core.enums.InstantBookAdvanceNotice;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class InstantBookAdvanceNoticeDialogFragment extends ZenDialog {
    public static final String LIST_SELECT_REQUEST_CODE = "list_sel_req_code";
    public static final String SELECTED_NOTICE = "sel_notice";
    private InstantBookAdvanceNoticeArrayAdapter noticeAdapter;

    public static final class InstantBookAdvanceNoticeArrayAdapter extends ArrayAdapter<InstantBookAdvanceNotice> {
        private final int layoutId;
        private final InstantBookAdvanceNotice selectedNotice;

        public InstantBookAdvanceNoticeArrayAdapter(Context context, int resource, InstantBookAdvanceNotice[] notices, InstantBookAdvanceNotice selectedNotice2) {
            super(context, resource, notices);
            this.selectedNotice = selectedNotice2;
            this.layoutId = resource;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(this.layoutId, parent, false);
            }
            InstantBookAdvanceNotice notice = (InstantBookAdvanceNotice) getItem(position);
            TextView noticeLabel = (TextView) ButterKnife.findById(convertView, C0880R.C0882id.zen_text);
            noticeLabel.setText(notice.titleId);
            boolean isSelected = this.selectedNotice.equals(notice);
            noticeLabel.setTextColor(getContext().getResources().getColor(isSelected ? C0880R.color.c_rausch : C0880R.color.c_gray_1));
            ViewUtils.setVisibleIf((View) (ImageView) ButterKnife.findById(convertView, C0880R.C0882id.zen_checkmark), isSelected);
            return convertView;
        }
    }

    public static InstantBookAdvanceNoticeDialogFragment newInstance(Fragment targetFragment, InstantBookAdvanceNotice preselectedNotice, int listSelectRequestCode) {
        Bundle bundle = new Bundle();
        bundle.putInt(SELECTED_NOTICE, preselectedNotice.serverDescKey);
        bundle.putInt("list_sel_req_code", listSelectRequestCode);
        return (InstantBookAdvanceNoticeDialogFragment) new ZenBuilder(new InstantBookAdvanceNoticeDialogFragment()).withTitle(C0880R.string.ml_ib_advance_notice).withListView(bundle).withTargetFragment(targetFragment).create();
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        this.noticeAdapter = new InstantBookAdvanceNoticeArrayAdapter(getActivity(), C0880R.layout.list_item_zen_checkable, InstantBookAdvanceNotice.values(), InstantBookAdvanceNotice.getTypeFromKey(getArguments().getInt(SELECTED_NOTICE)));
        return this.noticeAdapter;
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return InstantBookAdvanceNoticeDialogFragment$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$getItemClickListener$0(InstantBookAdvanceNoticeDialogFragment instantBookAdvanceNoticeDialogFragment, AdapterView parent, View view, int position, long id) {
        instantBookAdvanceNoticeDialogFragment.sendActivityResult(instantBookAdvanceNoticeDialogFragment.getArguments().getInt("list_sel_req_code"), -1, new Intent().putExtra(SELECTED_NOTICE, ((InstantBookAdvanceNotice) instantBookAdvanceNoticeDialogFragment.noticeAdapter.getItem(position)).serverDescKey));
        instantBookAdvanceNoticeDialogFragment.dismiss();
    }
}
