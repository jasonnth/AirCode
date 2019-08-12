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
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import java.util.List;

public abstract class OptionSelectionDialog<T> extends ZenDialog {
    private static final String ARG_REQUEST_CODE_OK = "air_request_code_ok";
    private SimpleItemAdapter identityTypeAdapter;
    private final OnItemClickListener onItemClickListener = OptionSelectionDialog$$Lambda$1.lambdaFactory$(this);

    class SimpleItemAdapter extends ArrayAdapter<T> {
        private final List<T> items;

        SimpleItemAdapter(Context context, List<T> items2) {
            super(context, 17367043);
            this.items = items2;
        }

        public int getCount() {
            return this.items.size();
        }

        public T getItem(int position) {
            return this.items.get(position);
        }

        public View getView(int position, View v, ViewGroup parent) {
            if (v == null) {
                v = LayoutInflater.from(getContext()).inflate(17367043, parent, false);
            }
            ((TextView) ButterKnife.findById(v, 16908308)).setText(OptionSelectionDialog.this.getDisplayString(getItem(position)));
            return v;
        }
    }

    /* access modifiers changed from: protected */
    public abstract String getDisplayString(T t);

    /* access modifiers changed from: protected */
    public abstract List<T> getItems();

    /* access modifiers changed from: protected */
    public abstract Bundle getResultBundle(T t);

    protected static OptionSelectionDialog newInstance(OptionSelectionDialog fragment, int requestCodeOk) {
        return newInstance(fragment, requestCodeOk, new Bundle());
    }

    protected static OptionSelectionDialog newInstance(OptionSelectionDialog fragment, int requestCodeOk, Bundle args) {
        args.putInt(ARG_REQUEST_CODE_OK, requestCodeOk);
        return (OptionSelectionDialog) new ZenBuilder(fragment).withListView().withoutDividers().withCancelButton().withArguments(args).create();
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        if (this.identityTypeAdapter == null) {
            this.identityTypeAdapter = new SimpleItemAdapter<>(getActivity(), getItems());
        }
        return this.identityTypeAdapter;
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return this.onItemClickListener;
    }

    static /* synthetic */ void lambda$new$0(OptionSelectionDialog optionSelectionDialog, AdapterView parent, View view, int position, long id) {
        int requestCode = optionSelectionDialog.getArguments().getInt(ARG_REQUEST_CODE_OK);
        Intent resultIntent = new Intent().putExtras(optionSelectionDialog.getResultBundle(optionSelectionDialog.identityTypeAdapter.getItem(position)));
        if (resultIntent.getExtras().size() == 0) {
            throw new IllegalStateException("result bundle must not be empty");
        }
        optionSelectionDialog.sendActivityResult(requestCode, -1, resultIntent);
        optionSelectionDialog.dismiss();
    }
}
