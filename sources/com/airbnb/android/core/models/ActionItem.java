package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenActionItem;
import java.util.ArrayList;

public class ActionItem extends GenActionItem {
    public static final Creator<ActionItem> CREATOR = new Creator<ActionItem>() {
        public ActionItem[] newArray(int size) {
            return new ActionItem[size];
        }

        public ActionItem createFromParcel(Parcel source) {
            ActionItem object = new ActionItem();
            object.readFromParcel(source);
            return object;
        }
    };
    private Context mContext;

    protected ActionItem(Context context) {
        super(new ArrayList(), null, 0, 0, 0);
        this.mContext = context;
    }

    protected ActionItem(int iconRes, int iconColorRes, String textString, int actionItemId) {
        super(null, textString, iconRes, actionItemId, iconColorRes);
    }

    public ActionItem() {
        super(null, null, 0, 0, 0);
    }

    public static ActionItem make(Context context) {
        return new ActionItem(context);
    }

    public ActionItem add(int iconRes, int colorRes, int textRes) {
        this.mActionItemList.add(new ActionItem(iconRes, colorRes, this.mContext.getString(textRes), textRes));
        return this;
    }

    public ArrayList<ActionItem> toList() {
        return this.mActionItemList;
    }
}
