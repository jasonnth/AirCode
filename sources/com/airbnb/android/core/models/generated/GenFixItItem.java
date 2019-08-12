package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenFixItItem implements Parcelable {
    @JsonProperty("category")
    protected String mCategory;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("host_comment")
    protected String mHostComment;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("issue_pictures")
    protected List<Photo> mIssuePictures;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("proof_required")
    protected int mProofRequired;
    @JsonProperty("proofs")
    protected List<Photo> mProofs;
    @JsonProperty("fixit_report_id")
    protected long mReportId;
    @JsonProperty("severity_level")
    protected int mSeverityLevel;
    @JsonProperty("status")
    protected int mStatus;

    protected GenFixItItem(List<Photo> issuePictures, List<Photo> proofs, String category, String description, String name, String hostComment, int proofRequired, int severityLevel, int status, long id, long reportId) {
        this();
        this.mIssuePictures = issuePictures;
        this.mProofs = proofs;
        this.mCategory = category;
        this.mDescription = description;
        this.mName = name;
        this.mHostComment = hostComment;
        this.mProofRequired = proofRequired;
        this.mSeverityLevel = severityLevel;
        this.mStatus = status;
        this.mId = id;
        this.mReportId = reportId;
    }

    protected GenFixItItem() {
    }

    public List<Photo> getIssuePictures() {
        return this.mIssuePictures;
    }

    @JsonProperty("issue_pictures")
    public void setIssuePictures(List<Photo> value) {
        this.mIssuePictures = value;
    }

    public List<Photo> getProofs() {
        return this.mProofs;
    }

    @JsonProperty("proofs")
    public void setProofs(List<Photo> value) {
        this.mProofs = value;
    }

    public String getCategory() {
        return this.mCategory;
    }

    @JsonProperty("category")
    public void setCategory(String value) {
        this.mCategory = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getHostComment() {
        return this.mHostComment;
    }

    @JsonProperty("host_comment")
    public void setHostComment(String value) {
        this.mHostComment = value;
    }

    public int getProofRequired() {
        return this.mProofRequired;
    }

    @JsonProperty("proof_required")
    public void setProofRequired(int value) {
        this.mProofRequired = value;
    }

    public int getSeverityLevel() {
        return this.mSeverityLevel;
    }

    @JsonProperty("severity_level")
    public void setSeverityLevel(int value) {
        this.mSeverityLevel = value;
    }

    public int getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(int value) {
        this.mStatus = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getReportId() {
        return this.mReportId;
    }

    @JsonProperty("fixit_report_id")
    public void setReportId(long value) {
        this.mReportId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mIssuePictures);
        parcel.writeTypedList(this.mProofs);
        parcel.writeString(this.mCategory);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mName);
        parcel.writeString(this.mHostComment);
        parcel.writeInt(this.mProofRequired);
        parcel.writeInt(this.mSeverityLevel);
        parcel.writeInt(this.mStatus);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mReportId);
    }

    public void readFromParcel(Parcel source) {
        this.mIssuePictures = source.createTypedArrayList(Photo.CREATOR);
        this.mProofs = source.createTypedArrayList(Photo.CREATOR);
        this.mCategory = source.readString();
        this.mDescription = source.readString();
        this.mName = source.readString();
        this.mHostComment = source.readString();
        this.mProofRequired = source.readInt();
        this.mSeverityLevel = source.readInt();
        this.mStatus = source.readInt();
        this.mId = source.readLong();
        this.mReportId = source.readLong();
    }
}
