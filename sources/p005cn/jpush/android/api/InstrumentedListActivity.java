package p005cn.jpush.android.api;

import android.app.ListActivity;

/* renamed from: cn.jpush.android.api.InstrumentedListActivity */
public class InstrumentedListActivity extends ListActivity {
    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
