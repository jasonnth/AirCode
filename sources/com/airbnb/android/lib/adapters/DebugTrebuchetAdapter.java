package com.airbnb.android.lib.adapters;

import android.view.KeyEvent;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import com.airbnb.android.core.events.TrebuchetUpdatedEvent;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.InputMarqueeEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.InputMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.google.common.collect.FluentIterable;
import com.squareup.otto.Bus;
import java.util.ArrayList;
import java.util.List;

public final class DebugTrebuchetAdapter extends AirEpoxyAdapter implements Filterable {
    /* access modifiers changed from: private */
    public final ArrayList<String> allTrebuchets;
    private final Bus bus;
    private final DebugSettings debugSettings;
    private final Filter filter = newTrebuchetFilter();
    /* access modifiers changed from: private */
    public List<String> filteredTrebuchets;
    private final InputMarqueeEpoxyModel searchBox;

    public DebugTrebuchetAdapter(List<String> trebuchetKeys, DebugSettings debugSettings2, Bus bus2) {
        this.allTrebuchets = new ArrayList<>(trebuchetKeys);
        this.filteredTrebuchets = new ArrayList(trebuchetKeys);
        this.debugSettings = debugSettings2;
        this.bus = bus2;
        InputMarqueeEpoxyModel_ hint = new InputMarqueeEpoxyModel_().forSearch(true).hint(C0880R.string.search);
        Filter filter2 = this.filter;
        filter2.getClass();
        this.searchBox = hint.addTextWatcher(TextWatcherUtils.create(DebugTrebuchetAdapter$$Lambda$1.lambdaFactory$(filter2))).editorActionListener(DebugTrebuchetAdapter$$Lambda$2.lambdaFactory$());
        this.models.add(this.searchBox);
        rebuildModels();
    }

    static /* synthetic */ boolean lambda$new$0(TextView v, int actionId, KeyEvent event) {
        return true;
    }

    public Filter getFilter() {
        return this.filter;
    }

    private Filter newTrebuchetFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public FilterResults performFiltering(CharSequence filter) {
                FilterResults filterResults = new FilterResults();
                List<String> filteredList = FluentIterable.from((Iterable<E>) DebugTrebuchetAdapter.this.allTrebuchets).filter(DebugTrebuchetAdapter$1$$Lambda$1.lambdaFactory$(filter)).toList();
                filterResults.values = filteredList;
                filterResults.count = filteredList.size();
                return filterResults;
            }

            /* access modifiers changed from: protected */
            public void publishResults(CharSequence charSequence, FilterResults filterResults) {
                DebugTrebuchetAdapter.this.filteredTrebuchets = (List) filterResults.values;
                DebugTrebuchetAdapter.this.rebuildModels();
            }
        };
    }

    /* access modifiers changed from: private */
    public void rebuildModels() {
        removeAllAfterModel(this.searchBox);
        for (String trebuchet : this.filteredTrebuchets) {
            this.models.add(new SwitchRowEpoxyModel_().title(trebuchet).checked(Boolean.parseBoolean(this.debugSettings.getTrebuchetFlag(trebuchet))).style(SwitchStyle.Filled).checkedChangeListener(DebugTrebuchetAdapter$$Lambda$3.lambdaFactory$(this, trebuchet)));
        }
        notifyDataSetChanged();
    }

    static /* synthetic */ void lambda$rebuildModels$1(DebugTrebuchetAdapter debugTrebuchetAdapter, String trebuchet, SwitchRowInterface switchView, boolean isChecked) {
        debugTrebuchetAdapter.debugSettings.setTrebuchetFlag(trebuchet, Boolean.toString(isChecked));
        debugTrebuchetAdapter.bus.post(new TrebuchetUpdatedEvent());
    }
}
