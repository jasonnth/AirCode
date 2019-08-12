package com.airbnb.android.lib.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.views.AppOverlayView;
import com.airbnb.android.lib.C0880R;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DebugLogView extends AppOverlayView {
    public static final Set<String> DEBUG_LOGGING_WHITE_LIST = ImmutableSet.m1299of(BookingAnalytics.EVENT_NAME);
    /* access modifiers changed from: private */
    public final List<DebugLogData> allDebugLogs = new ArrayList();
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final LogDebugViewAdapter logDebugAdapter = new LogDebugViewAdapter();
    private ListView logListView;

    class DebugLogData {
        final String data;
        final String eventName;

        DebugLogData(String event, String data2) {
            this.eventName = event;
            this.data = data2;
        }
    }

    class DebugLogViewHolder {
        @BindView
        View container;
        @BindView
        TextView data;
        @BindView
        TextView eventName;

        DebugLogViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class DebugLogViewHolder_ViewBinding implements Unbinder {
        private DebugLogViewHolder target;

        public DebugLogViewHolder_ViewBinding(DebugLogViewHolder target2, View source) {
            this.target = target2;
            target2.eventName = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.log_event_name, "field 'eventName'", TextView.class);
            target2.data = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.log_event_data, "field 'data'", TextView.class);
            target2.container = Utils.findRequiredView(source, C0880R.C0882id.container, "field 'container'");
        }

        public void unbind() {
            DebugLogViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.eventName = null;
            target2.data = null;
            target2.container = null;
        }
    }

    class LogDebugViewAdapter extends BaseAdapter {
        LogDebugViewAdapter() {
        }

        public int getCount() {
            return DebugLogView.this.allDebugLogs.size();
        }

        public Object getItem(int i) {
            return DebugLogView.this.allDebugLogs.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View convertView, ViewGroup viewGroup) {
            if (convertView == null) {
                convertView = LayoutInflater.from(DebugLogView.this.context).inflate(C0880R.layout.log_debug_list_item_view, viewGroup, false);
                convertView.setTag(new DebugLogViewHolder(convertView));
            }
            DebugLogViewHolder holder = (DebugLogViewHolder) convertView.getTag();
            holder.container.setOnClickListener(DebugLogView$LogDebugViewAdapter$$Lambda$1.lambdaFactory$(this, i));
            holder.eventName.setText(((DebugLogData) DebugLogView.this.allDebugLogs.get(i)).eventName);
            holder.data.setText(((DebugLogData) DebugLogView.this.allDebugLogs.get(i)).data);
            return convertView;
        }

        /* access modifiers changed from: private */
        public void handleClick(int i) {
            DebugLogView.this.allDebugLogs.remove(i);
            DebugLogView.this.logDebugAdapter.notifyDataSetChanged();
            if (getCount() == 0) {
                DebugLogView.this.destroyLogList();
            }
        }
    }

    public DebugLogView(Context context2) {
        super(context2.getApplicationContext());
        this.context = context2;
    }

    private void initListView() {
        if (this.logListView == null) {
            this.logListView = new ListView(this.context);
            this.logListView.setAdapter(this.logDebugAdapter);
            addView(this.logListView);
        }
    }

    public void displayLogDebug(String event, Object eventData) {
        if (BuildHelper.isDebugFeaturesEnabled() && shouldDisplayEvent(event)) {
            if (this.allDebugLogs.isEmpty()) {
                initListView();
            }
            this.allDebugLogs.add(new DebugLogData(event, eventData.toString()));
            this.logDebugAdapter.notifyDataSetChanged();
        }
    }

    private boolean shouldDisplayEvent(String event) {
        return DEBUG_LOGGING_WHITE_LIST.contains(event) || DebugSettings.EVENT_LOG_VIEW.isEnabled();
    }

    /* access modifiers changed from: private */
    public void destroyLogList() {
        if (this.logListView != null) {
            removeView(this.logListView);
            this.logListView = null;
        }
    }
}
