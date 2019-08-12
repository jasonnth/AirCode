package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import java.util.ArrayList;
import java.util.List;

public class HourPickerDialog extends ZenDialog {
    public static final String ARG_INITIAL_HOUR = "default_hour";
    public static final String EXTRA_RESULT_HOUR = "result_hour";
    public static final int MAX_HOUR = 24;
    public static final int MIN_HOUR = 6;
    public static final int REQUEST_CODE_HOUR_PICKER = 3522;
    /* access modifiers changed from: private */
    public int currentlySelectedHour;
    /* access modifiers changed from: private */
    public final List<Integer> hours = new ArrayList(19);
    @BindView
    RecyclerView recyclerView;

    public class HourAdapter extends Adapter<HourViewHolder> {

        public class HourViewHolder extends ViewHolder {
            @BindView
            ImageView checkMark;
            @BindView
            TextView hourText;

            public HourViewHolder(ViewGroup parent) {
                super(LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.list_item_hour_picker, parent, false));
                ButterKnife.bind(this, this.itemView);
            }
        }

        public class HourViewHolder_ViewBinding implements Unbinder {
            private HourViewHolder target;

            public HourViewHolder_ViewBinding(HourViewHolder target2, View source) {
                this.target = target2;
                target2.hourText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.hour_text, "field 'hourText'", TextView.class);
                target2.checkMark = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.zen_checkmark, "field 'checkMark'", ImageView.class);
            }

            public void unbind() {
                HourViewHolder target2 = this.target;
                if (target2 == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                this.target = null;
                target2.hourText = null;
                target2.checkMark = null;
            }
        }

        public HourAdapter() {
        }

        public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HourViewHolder(parent);
        }

        public int getItemCount() {
            return HourPickerDialog.this.hours.size();
        }

        public void onBindViewHolder(HourViewHolder holder, int position) {
            boolean isSelected = true;
            int hour = ((Integer) HourPickerDialog.this.hours.get(position)).intValue();
            holder.hourText.setText(DateHelper.formatHourOfDay(holder.itemView.getContext(), hour, true));
            if (hour != HourPickerDialog.this.currentlySelectedHour) {
                isSelected = false;
            }
            holder.hourText.setTextColor(HourPickerDialog.this.getResources().getColor(isSelected ? C0880R.color.c_rausch : C0880R.color.c_gray_1));
            ViewUtils.setVisibleIf((View) holder.checkMark, isSelected);
            holder.itemView.setOnClickListener(HourPickerDialog$HourAdapter$$Lambda$1.lambdaFactory$(this, position));
        }
    }

    public static HourPickerDialog instance(int titleRes, int initialHourSelection, Fragment targetFragment) {
        Bundle args = new Bundle();
        args.putInt(ARG_INITIAL_HOUR, initialHourSelection);
        HourPickerDialog dialog = (HourPickerDialog) new ZenBuilder(new HourPickerDialog()).withTitle(titleRes).withCustomLayout().withArguments(args).create();
        dialog.setTargetFragment(targetFragment, REQUEST_CODE_HOUR_PICKER);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View frame = inflater.inflate(C0880R.layout.dialog_fragment_hour_picker, container, false);
        setCustomView(frame);
        ButterKnife.bind(this, frame);
        initHours();
        setUpList();
        return view;
    }

    private void initHours() {
        this.currentlySelectedHour = getArguments().getInt(ARG_INITIAL_HOUR, -1);
        for (int hour = 6; hour <= 24; hour++) {
            this.hours.add(Integer.valueOf(hour));
        }
    }

    private void setUpList() {
        this.recyclerView.setAdapter(new HourAdapter());
        this.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(layoutManager);
        layoutManager.scrollToPosition(this.currentlySelectedHour - 6);
    }

    /* access modifiers changed from: protected */
    public void onHourSelected(int hour) {
        Intent data = new Intent();
        data.putExtra(EXTRA_RESULT_HOUR, hour);
        sendActivityResult(REQUEST_CODE_HOUR_PICKER, -1, data);
        dismiss();
    }
}
