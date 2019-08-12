package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.os.AsyncTask;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.utils.EmailQueryTask;
import com.airbnb.epoxy.EpoxyModel;
import java.util.ArrayList;
import java.util.List;

public class InviteGuestSelectAdapter extends AirEpoxyAdapter {
    private static final int QUERY_MIN_LENGTH = 1;
    private final Context context;
    private final EmailSelected listener;
    private EmailQueryTask queryTask;

    public interface EmailSelected {
        void onEmailSelected(String str);
    }

    public InviteGuestSelectAdapter(Context context2, EmailSelected listener2) {
        this.context = context2;
        this.listener = listener2;
    }

    public void setQuery(String query) {
        if (this.queryTask != null) {
            this.queryTask.cancel(false);
        }
        if (query.length() < 1) {
            setEmails(new ArrayList());
            return;
        }
        this.queryTask = new EmailQueryTask(this.context.getContentResolver(), query) {
            /* access modifiers changed from: protected */
            public void onPostExecute(List<String> result) {
                InviteGuestSelectAdapter.this.setEmails(result);
            }
        };
        this.queryTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void setEmails(List<String> emails) {
        this.models.clear();
        for (String email : emails) {
            this.models.add(buildRows(email));
        }
        notifyDataSetChanged();
    }

    private EpoxyModel buildRows(String email) {
        return new StandardRowEpoxyModel_().title((CharSequence) email).clickListener(InviteGuestSelectAdapter$$Lambda$1.lambdaFactory$(this, email));
    }
}
