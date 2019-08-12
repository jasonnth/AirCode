package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.HelpThreadNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenHelpThreadIssue implements Parcelable {
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("nodes")
    protected List<HelpThreadNode> mNodes;
    @JsonProperty("tree_major_version")
    protected long mTreeMajorVersion;
    @JsonProperty("updated_at")
    protected AirDateTime mUpdatedAt;

    protected GenHelpThreadIssue(AirDateTime createdAt, AirDateTime updatedAt, List<HelpThreadNode> nodes, long id, long treeMajorVersion) {
        this();
        this.mCreatedAt = createdAt;
        this.mUpdatedAt = updatedAt;
        this.mNodes = nodes;
        this.mId = id;
        this.mTreeMajorVersion = treeMajorVersion;
    }

    protected GenHelpThreadIssue() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public AirDateTime getUpdatedAt() {
        return this.mUpdatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(AirDateTime value) {
        this.mUpdatedAt = value;
    }

    public List<HelpThreadNode> getNodes() {
        return this.mNodes;
    }

    @JsonProperty("nodes")
    public void setNodes(List<HelpThreadNode> value) {
        this.mNodes = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getTreeMajorVersion() {
        return this.mTreeMajorVersion;
    }

    @JsonProperty("tree_major_version")
    public void setTreeMajorVersion(long value) {
        this.mTreeMajorVersion = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeParcelable(this.mUpdatedAt, 0);
        parcel.writeTypedList(this.mNodes);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mTreeMajorVersion);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mNodes = source.createTypedArrayList(HelpThreadNode.CREATOR);
        this.mId = source.readLong();
        this.mTreeMajorVersion = source.readLong();
    }
}
