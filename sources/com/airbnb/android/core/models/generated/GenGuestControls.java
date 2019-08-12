package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.StructuredHouseRule;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenGuestControls implements Parcelable {
    @JsonProperty("allows_children")
    protected boolean mAllowsChildren;
    @JsonProperty("allows_children_as_host")
    protected Boolean mAllowsChildrenAsHost;
    @JsonProperty("allows_events")
    protected boolean mAllowsEvents;
    @JsonProperty("allows_events_as_host")
    protected Boolean mAllowsEventsAsHost;
    @JsonProperty("allows_infants")
    protected boolean mAllowsInfants;
    @JsonProperty("allows_infants_as_host")
    protected Boolean mAllowsInfantsAsHost;
    @JsonProperty("allows_pets")
    protected boolean mAllowsPets;
    @JsonProperty("allows_pets_as_host")
    protected Boolean mAllowsPetsAsHost;
    @JsonProperty("allows_smoking")
    protected boolean mAllowsSmoking;
    @JsonProperty("allows_smoking_as_host")
    protected Boolean mAllowsSmokingAsHost;
    @JsonProperty("host_check_in_time_message")
    protected String mHostCheckInTimeMessage;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("structured_house_rules")
    protected List<String> mStructuredHouseRules;
    @JsonProperty("structured_house_rules_with_tips")
    protected List<StructuredHouseRule> mStructuredHouseRulesWithTips;

    protected GenGuestControls(Boolean allowsChildrenAsHost, Boolean allowsInfantsAsHost, Boolean allowsPetsAsHost, Boolean allowsSmokingAsHost, Boolean allowsEventsAsHost, List<String> structuredHouseRules, List<StructuredHouseRule> structuredHouseRulesWithTips, String hostCheckInTimeMessage, boolean allowsChildren, boolean allowsInfants, boolean allowsPets, boolean allowsSmoking, boolean allowsEvents, long id) {
        this();
        this.mAllowsChildrenAsHost = allowsChildrenAsHost;
        this.mAllowsInfantsAsHost = allowsInfantsAsHost;
        this.mAllowsPetsAsHost = allowsPetsAsHost;
        this.mAllowsSmokingAsHost = allowsSmokingAsHost;
        this.mAllowsEventsAsHost = allowsEventsAsHost;
        this.mStructuredHouseRules = structuredHouseRules;
        this.mStructuredHouseRulesWithTips = structuredHouseRulesWithTips;
        this.mHostCheckInTimeMessage = hostCheckInTimeMessage;
        this.mAllowsChildren = allowsChildren;
        this.mAllowsInfants = allowsInfants;
        this.mAllowsPets = allowsPets;
        this.mAllowsSmoking = allowsSmoking;
        this.mAllowsEvents = allowsEvents;
        this.mId = id;
    }

    protected GenGuestControls() {
    }

    public Boolean isAllowsChildrenAsHost() {
        return this.mAllowsChildrenAsHost;
    }

    @JsonProperty("allows_children_as_host")
    public void setAllowsChildrenAsHost(Boolean value) {
        this.mAllowsChildrenAsHost = value;
    }

    public Boolean isAllowsInfantsAsHost() {
        return this.mAllowsInfantsAsHost;
    }

    @JsonProperty("allows_infants_as_host")
    public void setAllowsInfantsAsHost(Boolean value) {
        this.mAllowsInfantsAsHost = value;
    }

    public Boolean isAllowsPetsAsHost() {
        return this.mAllowsPetsAsHost;
    }

    @JsonProperty("allows_pets_as_host")
    public void setAllowsPetsAsHost(Boolean value) {
        this.mAllowsPetsAsHost = value;
    }

    public Boolean isAllowsSmokingAsHost() {
        return this.mAllowsSmokingAsHost;
    }

    @JsonProperty("allows_smoking_as_host")
    public void setAllowsSmokingAsHost(Boolean value) {
        this.mAllowsSmokingAsHost = value;
    }

    public Boolean isAllowsEventsAsHost() {
        return this.mAllowsEventsAsHost;
    }

    @JsonProperty("allows_events_as_host")
    public void setAllowsEventsAsHost(Boolean value) {
        this.mAllowsEventsAsHost = value;
    }

    public List<String> getStructuredHouseRules() {
        return this.mStructuredHouseRules;
    }

    @JsonProperty("structured_house_rules")
    public void setStructuredHouseRules(List<String> value) {
        this.mStructuredHouseRules = value;
    }

    public List<StructuredHouseRule> getStructuredHouseRulesWithTips() {
        return this.mStructuredHouseRulesWithTips;
    }

    @JsonProperty("structured_house_rules_with_tips")
    public void setStructuredHouseRulesWithTips(List<StructuredHouseRule> value) {
        this.mStructuredHouseRulesWithTips = value;
    }

    public String getHostCheckInTimeMessage() {
        return this.mHostCheckInTimeMessage;
    }

    @JsonProperty("host_check_in_time_message")
    public void setHostCheckInTimeMessage(String value) {
        this.mHostCheckInTimeMessage = value;
    }

    public boolean isAllowsChildren() {
        return this.mAllowsChildren;
    }

    @JsonProperty("allows_children")
    public void setAllowsChildren(boolean value) {
        this.mAllowsChildren = value;
    }

    public boolean isAllowsInfants() {
        return this.mAllowsInfants;
    }

    @JsonProperty("allows_infants")
    public void setAllowsInfants(boolean value) {
        this.mAllowsInfants = value;
    }

    public boolean isAllowsPets() {
        return this.mAllowsPets;
    }

    @JsonProperty("allows_pets")
    public void setAllowsPets(boolean value) {
        this.mAllowsPets = value;
    }

    public boolean isAllowsSmoking() {
        return this.mAllowsSmoking;
    }

    @JsonProperty("allows_smoking")
    public void setAllowsSmoking(boolean value) {
        this.mAllowsSmoking = value;
    }

    public boolean isAllowsEvents() {
        return this.mAllowsEvents;
    }

    @JsonProperty("allows_events")
    public void setAllowsEvents(boolean value) {
        this.mAllowsEvents = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mAllowsChildrenAsHost);
        parcel.writeValue(this.mAllowsInfantsAsHost);
        parcel.writeValue(this.mAllowsPetsAsHost);
        parcel.writeValue(this.mAllowsSmokingAsHost);
        parcel.writeValue(this.mAllowsEventsAsHost);
        parcel.writeStringList(this.mStructuredHouseRules);
        parcel.writeTypedList(this.mStructuredHouseRulesWithTips);
        parcel.writeString(this.mHostCheckInTimeMessage);
        parcel.writeBooleanArray(new boolean[]{this.mAllowsChildren, this.mAllowsInfants, this.mAllowsPets, this.mAllowsSmoking, this.mAllowsEvents});
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mAllowsChildrenAsHost = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAllowsInfantsAsHost = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAllowsPetsAsHost = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAllowsSmokingAsHost = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mAllowsEventsAsHost = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mStructuredHouseRules = source.createStringArrayList();
        this.mStructuredHouseRulesWithTips = source.createTypedArrayList(StructuredHouseRule.CREATOR);
        this.mHostCheckInTimeMessage = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mAllowsChildren = bools[0];
        this.mAllowsInfants = bools[1];
        this.mAllowsPets = bools[2];
        this.mAllowsSmoking = bools[3];
        this.mAllowsEvents = bools[4];
        this.mId = source.readLong();
    }
}
