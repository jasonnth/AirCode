package com.airbnb.android.contentframework.imagepicker;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.provider.MediaStore.Images.Media;
import p005cn.jpush.android.data.DbHelper;

public class MediaItem implements Parcelable {
    public static final Creator<MediaItem> CREATOR = new Creator<MediaItem>() {
        public MediaItem createFromParcel(Parcel source) {
            return new MediaItem(source);
        }

        public MediaItem[] newArray(int size) {
            return new MediaItem[size];
        }
    };
    public final Uri uri;

    public static MediaItem valueOf(Cursor cursor) {
        return new MediaItem(cursor.getLong(cursor.getColumnIndex(DbHelper.TABLE_ID)));
    }

    public Uri getUri() {
        return this.uri;
    }

    private MediaItem(long id) {
        this.uri = ContentUris.withAppendedId(Media.EXTERNAL_CONTENT_URI, id);
    }

    private MediaItem(Parcel source) {
        this.uri = (Uri) source.readParcelable(Uri.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.uri, 0);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        return ((MediaItem) obj).uri.equals(this.uri);
    }

    public int hashCode() {
        return this.uri.hashCode() + 31;
    }
}
