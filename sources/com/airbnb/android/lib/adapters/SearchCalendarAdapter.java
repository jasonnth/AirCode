package com.airbnb.android.lib.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.CalendarHelper;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SearchCalendarAdapter extends BaseCalendarAdapter {
    private final Calendar mSelectionTestLocal;

    public SearchCalendarAdapter() {
        this(Calendar.getInstance(), null, null);
    }

    public SearchCalendarAdapter(Calendar month, Calendar startTime, Calendar endTime) {
        super(month);
        ((AirbnbGraph) AirbnbApplication.instance().component()).inject(this);
        setStartTime(startTime);
        setEndTime(endTime);
        this.mSelectionTestLocal = Calendar.getInstance();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View convertView2 = super.getView(position, convertView, parent);
        this.mSelectionTestLocal.setTimeInMillis(((Long) this.mMillisCache.getEntry(position, Integer.valueOf(position))).longValue());
        if (this.mStartTime != null && ((this.mEndTime == null && CalendarHelper.isSameDay(this.mStartTime, this.mSelectionTestLocal)) || (this.mSelectionTestLocal.after(this.mStartTime) && this.mSelectionTestLocal.before(this.mEndTime)))) {
            convertView2.setBackgroundResource(C0880R.color.search_calendar_selected);
            ((TextView) convertView2.findViewById(C0880R.C0882id.txt_date)).setTextColor(convertView2.getResources().getColor(17170443));
        }
        return convertView2;
    }

    @SuppressLint({"SimpleDateFormat"})
    public String getDates(Context context) {
        if (this.mStartTime == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(C0880R.string.date_name_format));
        return sdf.format(this.mStartTime.getTime()) + (this.mEndTime != null ? " - " + sdf.format(this.mEndTime.getTime()) : "");
    }
}
