package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.widget.BaseAdapter;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class HHBaseAdapter extends BaseAdapter {
    AirbnbAccountManager mAccountManager;
    protected CurrencyFormatter mCurrencyHelper;

    public HHBaseAdapter(Context context) {
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
    }

    public static String getDateFormattedString(Context context, AirDate startDate, AirDate endDate) {
        int dateFormatRes = startDate.getYear() == endDate.getYear() ? C0880R.string.date_name_format : C0880R.string.mdy_format_shorter;
        SimpleDateFormat dateFormat = new SimpleDateFormat(context.getString(dateFormatRes));
        String startString = startDate.formatDate((DateFormat) dateFormat);
        if (dateFormatRes != C0880R.string.mdy_format_shorter) {
            dateFormat = new SimpleDateFormat(context.getString(C0880R.string.mdy_format_shorter));
        }
        return context.getString(C0880R.string.hh_two_dates, new Object[]{startString, endDate.formatDate((DateFormat) dateFormat)});
    }
}
