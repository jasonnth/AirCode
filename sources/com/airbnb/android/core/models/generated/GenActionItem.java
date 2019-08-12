package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ActionItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public abstract class GenActionItem implements Parcelable {
    @JsonProperty("action_item_id")
    protected int mActionItemId;
    @JsonProperty("action_item_list")
    protected ArrayList<ActionItem> mActionItemList;
    @JsonProperty("icon_color_res")
    protected int mIconColorRes;
    @JsonProperty("icon_res")
    protected int mIconRes;
    @JsonProperty("text_string")
    protected String mTextString;

    protected GenActionItem(ArrayList<ActionItem> actionItemList, String textString, int iconRes, int actionItemId, int iconColorRes) {
        this();
        this.mActionItemList = actionItemList;
        this.mTextString = textString;
        this.mIconRes = iconRes;
        this.mActionItemId = actionItemId;
        this.mIconColorRes = iconColorRes;
    }

    protected GenActionItem() {
    }

    public ArrayList<ActionItem> getActionItemList() {
        return this.mActionItemList;
    }

    @JsonProperty("action_item_list")
    public void setActionItemList(ArrayList<ActionItem> value) {
        this.mActionItemList = value;
    }

    public String getTextString() {
        return this.mTextString;
    }

    @JsonProperty("text_string")
    public void setTextString(String value) {
        this.mTextString = value;
    }

    public int getIconRes() {
        return this.mIconRes;
    }

    @JsonProperty("icon_res")
    public void setIconRes(int value) {
        this.mIconRes = value;
    }

    public int getActionItemId() {
        return this.mActionItemId;
    }

    @JsonProperty("action_item_id")
    public void setActionItemId(int value) {
        this.mActionItemId = value;
    }

    public int getIconColorRes() {
        return this.mIconColorRes;
    }

    @JsonProperty("icon_color_res")
    public void setIconColorRes(int value) {
        this.mIconColorRes = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mActionItemList);
        parcel.writeString(this.mTextString);
        parcel.writeInt(this.mIconRes);
        parcel.writeInt(this.mActionItemId);
        parcel.writeInt(this.mIconColorRes);
    }

    public void readFromParcel(Parcel source) {
        this.mActionItemList = source.createTypedArrayList(ActionItem.CREATOR);
        this.mTextString = source.readString();
        this.mIconRes = source.readInt();
        this.mActionItemId = source.readInt();
        this.mIconColorRes = source.readInt();
    }
}
