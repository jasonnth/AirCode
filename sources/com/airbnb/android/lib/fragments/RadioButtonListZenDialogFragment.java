package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.lib.C0880R;
import java.util.ArrayList;
import java.util.List;

public class RadioButtonListZenDialogFragment extends ZenDialog {
    private static final String ARG_ITEMS = "items";
    private static final String ARG_SELECTED_ITEM = "selectedItem";
    public static final String EXTRA_SELECTED_ITEM = "selected_item";

    private static class RadioItemAdapter extends ArrayAdapter<String> {
        private final int mResource;
        private final int mSelectedItem;

        public RadioItemAdapter(Context context, int resource, List<String> items, int selectedItem) {
            super(context, resource, items);
            this.mResource = resource;
            this.mSelectedItem = selectedItem;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            boolean z;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(this.mResource, parent, false);
                convertView.setTag(new ViewHolder(convertView));
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mTextView.setText((CharSequence) getItem(position));
            RadioButton radioButton = viewHolder.mRadioButton;
            if (position == this.mSelectedItem) {
                z = true;
            } else {
                z = false;
            }
            radioButton.setChecked(z);
            return convertView;
        }
    }

    public static class ViewHolder {
        @BindView
        RadioButton mRadioButton;
        @BindView
        TextView mTextView;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target2, View source) {
            this.target = target2;
            target2.mTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text, "field 'mTextView'", TextView.class);
            target2.mRadioButton = (RadioButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.radio_button, "field 'mRadioButton'", RadioButton.class);
        }

        public void unbind() {
            ViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.mTextView = null;
            target2.mRadioButton = null;
        }
    }

    public static RadioButtonListZenDialogFragment newInstance(int title, ArrayList<String> items, int selectedItem) {
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_ITEMS, items);
        args.putInt(ARG_SELECTED_ITEM, selectedItem);
        return (RadioButtonListZenDialogFragment) new ZenBuilder(new RadioButtonListZenDialogFragment()).withTitle(title).withCancelButton().withListView(args).withoutDividers().create();
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        Bundle args = getArguments();
        return new RadioItemAdapter(getActivity(), C0880R.layout.list_item_radio_item, args.getStringArrayList(ARG_ITEMS), args.getInt(ARG_SELECTED_ITEM));
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return RadioButtonListZenDialogFragment$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$getItemClickListener$0(RadioButtonListZenDialogFragment radioButtonListZenDialogFragment, AdapterView parent, View view, int position, long id) {
        radioButtonListZenDialogFragment.dismiss();
        if (radioButtonListZenDialogFragment.getTargetFragment() != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SELECTED_ITEM, position);
            radioButtonListZenDialogFragment.getTargetFragment().onActivityResult(radioButtonListZenDialogFragment.getTargetRequestCode(), -1, intent);
        }
    }
}
