package com.airbnb.android.superhero;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.airbnb.android.superhero.SuperHeroMessage.Status;
import com.airbnb.android.superhero.SuperHeroMessageModel.Update_message_status;
import com.airbnb.android.utils.IOUtils;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import java.util.ArrayList;
import java.util.List;
import p032rx.schedulers.Schedulers;

public class SuperHeroTableOpenHelper extends SQLiteOpenHelper {
    private static final String FILE_NAME = "super_hero.db";
    private static final int VERSION = 3;
    private final BriteDatabase database = SqlBrite.create().wrapDatabaseHelper(this, Schedulers.m4048io());

    SuperHeroTableOpenHelper(Context context) {
        super(context, FILE_NAME, null, 3);
    }

    public void onCreate(SQLiteDatabase db) {
        synchronized (this.database) {
            db.execSQL(SuperHeroMessageModel.DROP_TABLE);
            db.execSQL(SuperHeroMessageModel.CREATE_TABLE);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void clearAll() {
        synchronized (this.database) {
            this.database.delete(SuperHeroMessageModel.TABLE_NAME, null, new String[0]);
        }
    }

    public void insert(SuperHeroMessage model) {
        model.newInsertStatement(getWritableDatabase()).program.executeInsert();
    }

    /* access modifiers changed from: 0000 */
    public void update(SuperHeroMessage model) {
        model.newUpdateStatement(getWritableDatabase()).program.executeUpdateDelete();
    }

    public SuperHeroMessage getSuperHeroMessageById(long id) {
        return fetchSuperHeroMessage(SuperHeroMessageModel.SELECT_MESSAGE_BY_ID, id);
    }

    public List<SuperHeroMessage> allMessages() {
        return fetchList(SuperHeroMessageModel.SELECT_ALL);
    }

    /* access modifiers changed from: 0000 */
    public List<SuperHeroMessage> messagesToPresent() {
        return fetchList(SuperHeroMessageModel.SELECT_MESSAGES_TO_PRESENT);
    }

    public SuperHeroMessage messageToPreview() {
        List<SuperHeroMessage> heroMessages = fetchList(SuperHeroMessageModel.SELECT_PREVIEW_MESSAGES_FOR_INBOX);
        if (heroMessages.isEmpty()) {
            return null;
        }
        SuperHeroMessage messageToPreview = (SuperHeroMessage) heroMessages.get(0);
        if (heroMessages.size() <= 1 || messageToPreview.statusEnum() != Status.PRESENTED) {
            return messageToPreview;
        }
        return (SuperHeroMessage) heroMessages.get(1);
    }

    /* access modifiers changed from: 0000 */
    public List<SuperHeroMessage> messagesForOnLaunch() {
        return fetchList(SuperHeroMessageModel.SELECT_MESSAGES_FOR_LAUNCH);
    }

    private List<SuperHeroMessage> fetchList(String queryString) {
        Cursor cursor = null;
        try {
            cursor = this.database.query(queryString, new String[0]);
            List<SuperHeroMessage> items = new ArrayList<>(cursor.getCount());
            while (cursor.moveToNext()) {
                items.add(SuperHeroMessage.MAPPER.map(cursor));
            }
            return items;
        } finally {
            IOUtils.closeQuietly(cursor);
        }
    }

    private SuperHeroMessage fetchSuperHeroMessage(String queryString, long id) {
        Cursor cursor = null;
        SuperHeroMessage message = null;
        try {
            cursor = this.database.query(queryString, Long.toString(id));
            if (cursor.moveToNext()) {
                message = (SuperHeroMessage) SuperHeroMessage.MAPPER.map(cursor);
            }
            return message;
        } finally {
            IOUtils.closeQuietly(cursor);
        }
    }

    /* access modifiers changed from: 0000 */
    public void delete(SuperHeroMessage message) {
        message.newDeleteStatement(getWritableDatabase()).program.executeUpdateDelete();
    }

    /* access modifiers changed from: 0000 */
    public void updateStatus(long id, Status newStatus) {
        Update_message_status statement = new Update_message_status(getWritableDatabase());
        statement.bind((long) newStatus.value, id);
        statement.program.executeUpdateDelete();
    }
}
