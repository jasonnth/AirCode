package com.airbnb.android.core.messaging.p007db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.utils.BuildHelper;

/* renamed from: com.airbnb.android.core.messaging.db.LoggingDatabaseErrorHandler */
class LoggingDatabaseErrorHandler implements DatabaseErrorHandler {
    private final Context context;
    private final DatabaseErrorHandler defaultErrorHandler = new DefaultDatabaseErrorHandler();

    LoggingDatabaseErrorHandler(Context context2) {
        this.context = context2;
    }

    public void onCorruption(SQLiteDatabase sqLiteDatabase) {
        if (BuildHelper.isDevelopmentBuild()) {
            Toast.makeText(this.context, this.context.getString(C0716R.string.corruption_in_database_toast, new Object[]{sqLiteDatabase.getPath()}), 1).show();
        } else {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Database corruption occured: " + sqLiteDatabase.getPath()));
        }
        this.defaultErrorHandler.onCorruption(sqLiteDatabase);
    }
}
