package com.squareup.sqldelight;

import android.database.Cursor;

public interface RowMapper<T> {
    T map(Cursor cursor);
}
