package com.airbnb.android.contentframework.imagepicker;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import com.airbnb.android.contentframework.imagepicker.MediaGridItemView.OnMediaItemClickListener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import java.util.List;
import p005cn.jpush.android.data.DbHelper;

public class MediaGridAdapter extends AirEpoxyAdapter {
    private final OnMediaItemClickListener onMediaItemClickListener;
    private final List<Uri> selectedItems;

    public MediaGridAdapter(OnMediaItemClickListener onMediaItemClickListener2, List<Uri> selectedItems2) {
        super(false);
        this.onMediaItemClickListener = onMediaItemClickListener2;
        this.selectedItems = selectedItems2;
    }

    public void setData(Cursor cursor) {
        if (isDataValid(cursor)) {
            cursor.moveToFirst();
            this.models.clear();
            while (cursor.moveToNext()) {
                Uri uri = getUriForCursor(cursor);
                this.models.add(new MediaGridItemEpoxyModel_().m4070id((CharSequence) String.valueOf(uri)).uri(uri).selectedItems(this.selectedItems).onMediaItemClickListener(this.onMediaItemClickListener));
            }
            notifyDataSetChanged();
        }
    }

    private static boolean isDataValid(Cursor cursor) {
        return cursor != null && !cursor.isClosed();
    }

    private static Uri getUriForCursor(Cursor cursor) {
        return ContentUris.withAppendedId(Media.EXTERNAL_CONTENT_URI, cursor.getLong(cursor.getColumnIndex(DbHelper.TABLE_ID)));
    }
}
