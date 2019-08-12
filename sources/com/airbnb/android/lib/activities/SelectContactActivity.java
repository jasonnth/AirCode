package com.airbnb.android.lib.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import com.airbnb.android.core.activities.AirActivity;
import java.util.HashMap;

public class SelectContactActivity extends AirActivity {
    private final String EXTRA_PAYLOAD = "payload";
    private final int REQUEST_CODE = 100;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SelectContactActivityPermissionsDispatcher.showContactsWithCheck(this);
    }

    public void showContacts() {
        startActivityForResult(new Intent("android.intent.action.PICK", Email.CONTENT_URI), 100);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == -1) {
            HashMap<String, String> contact = getContact(data);
            Intent response = new Intent();
            response.putExtra("payload", contact);
            setResult(-1, response);
        }
        finish();
    }

    private HashMap<String, String> getContact(Intent data) {
        Cursor cursor = getContentResolver().query(data.getData(), null, null, null, null);
        cursor.moveToFirst();
        HashMap<String, String> payload = new HashMap<>();
        payload.put("name", cursor.getString(cursor.getColumnIndex("display_name")));
        payload.put("photoUri", cursor.getString(cursor.getColumnIndex("photo_uri")));
        payload.put("phoneNumber", cursor.getString(cursor.getColumnIndex("data4")));
        payload.put("email", cursor.getString(cursor.getColumnIndex("data1")));
        cursor.close();
        return payload;
    }
}
