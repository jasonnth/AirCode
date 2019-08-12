package com.airbnb.android.lib.utils.animation;

import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.lib.views.GroupedRadioCheck;
import com.airbnb.android.lib.views.GroupedRadioCheck.OnCheckedChangeListener;
import java.util.Map;
import java.util.Map.Entry;

public class GroupedRadioCheckManager<T> {
    GroupedRadioCheck currentSelected;
    private final OnCheckedChangeListener onCheckedChangeListener = GroupedRadioCheckManager$$Lambda$1.lambdaFactory$(this);
    Map<GroupedRadioCheck, T> radioButtonsToValueMap = new ArrayMap();

    static /* synthetic */ void lambda$new$0(GroupedRadioCheckManager groupedRadioCheckManager, GroupedRadioCheck buttonView, boolean isChecked) {
        if (isChecked) {
            groupedRadioCheckManager.onItemSelected(buttonView);
        }
    }

    public void setCurrentValue(T value) {
        for (Entry<GroupedRadioCheck, T> entry : this.radioButtonsToValueMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                onItemSelected((GroupedRadioCheck) entry.getKey());
                return;
            }
        }
        throw new IllegalArgumentException("Value does not exist: " + value);
    }

    private void onItemSelected(GroupedRadioCheck button) {
        if (!button.equals(this.currentSelected)) {
            if (this.currentSelected != null) {
                this.currentSelected.setChecked(false);
            }
            button.setChecked(true);
            this.currentSelected = button;
        }
    }

    public void addRadioButton(GroupedRadioCheck button, T value) {
        button.setOnCheckedChangeListener(this.onCheckedChangeListener);
        this.radioButtonsToValueMap.put(button, value);
    }

    public T getCurrentValue() {
        return this.radioButtonsToValueMap.get(this.currentSelected);
    }
}
