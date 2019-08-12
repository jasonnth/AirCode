package p005cn.jpush.android.p006ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import java.util.List;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.ui.ListViewActivity */
public class ListViewActivity extends Activity {
    private static final String TAG = "ListViewActivity";
    private Entity entity;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        Logger.m1424i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            this.entity = (Entity) getIntent().getSerializableExtra("body");
            showListView(this.entity);
            return;
        }
        Logger.m1432w(TAG, "NULL intent");
    }

    private void showListView(Entity aEntity) {
        Logger.m1424i(TAG, "Action: showListView");
        GridView ls = new GridView(getApplicationContext());
        ls.setNumColumns(2);
        final List<Entity> msgs = aEntity.messages;
        ls.setAdapter(new MsgListAdapter(this, Integer.MIN_VALUE, msgs));
        ls.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Entity entity = (Entity) msgs.get(position);
                entity.isFullScreen = false;
                ListViewActivity.this.startActivity(AndroidUtil.getIntentForStartPushActivity(ListViewActivity.this, entity, false));
            }
        });
        setContentView(ls);
    }
}
